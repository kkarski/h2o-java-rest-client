package com.appx.h2o;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Future;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.appx.h2o.tasks.ParseV3Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.MediaType;

import water.bindings.pojos.FrameKeyV3;
import water.bindings.pojos.ImportFilesV3;
import water.bindings.pojos.ParseSetupV3;
import water.bindings.pojos.ParseV3;

public class H2ORestClient {

  private final String url;
  private final Timer timer = new Timer();

  public final static String SOCKET_TIMEOUT = "filesystem.http.socketTimeout";
  public final static String CONNECTION_TIMEOUT = "filesystem.http.connectionTimeout";
  public final static String CONNECTION_MANAGER_TIMEOUT = "filesystem.http.connectionManagerTimeout";

  private PoolingHttpClientConnectionManager cm;
  private CloseableHttpClient client;
  private ObjectMapper mapper = new ObjectMapper();

  public H2ORestClient(final String url) {
    this.url = url;

    cm = new PoolingHttpClientConnectionManager();
    cm.setMaxTotal(200); // Increase max total connection to 200
    cm.setDefaultMaxPerRoute(20); // Increase default max connection per route
                                  // to 20
    RequestConfig reqConfig =
        RequestConfig.custom().setSocketTimeout(30 * 1000).setConnectTimeout(3 * 1000).setConnectionRequestTimeout(3 * 1000).build();

    client = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(reqConfig).build();

  }

  public ParseSetupV3 guessSetup(String[] source_frames) throws Exception {

    HttpPost post =
        new HttpPost(new URIBuilder(url).setPath("/3/ParseSetup").setParameter("source_frames", toArray(source_frames)).build());
    post.setHeader(HttpHeaders.CONTENT_TYPE, com.google.common.net.MediaType.FORM_DATA + "; charset=UTF-8");
    post.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");

    List<NameValuePair> vals = new ArrayList<>();
    vals.add(new BasicNameValuePair("source_frames", toArray(source_frames)));
    post.setEntity(new UrlEncodedFormEntity(vals));

    HttpResponse response = client.execute(post);

    ParseSetupV3 pv3 = null;
    try {
      if (response.getStatusLine().getStatusCode() == 200) {
        pv3 = mapper.readValue(response.getEntity().getContent(), ParseSetupV3.class);
      }
    } finally {
      EntityUtils.consume(response.getEntity());
    }

    return pv3;

  }

  public ParseSetupV3 guessSetup(ImportFilesV3 importV3) throws Exception {
    ParseSetupV3 setup = guessSetup(importV3.destination_frames);
    return setup;
  }

  public ImportFilesV3 importFiles(String path) throws Exception {

    HttpGet get = new HttpGet(new URIBuilder(url).setPath("/3/ImportFiles").setParameter("path", path).build());
    get.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.FORM_DATA + "; charset=UTF-8");
    get.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");

    HttpResponse response = client.execute(get);

    ImportFilesV3 pv3 = null;
    try {
      if (response.getStatusLine().getStatusCode() == 200) {
        pv3 = mapper.readValue(response.getEntity().getContent(), ImportFilesV3.class);
      }
    } finally {
      EntityUtils.consume(response.getEntity());
    }

    return pv3;
  }

  public Future<ParseV3> parse(ParseSetupV3 p) throws Exception {

    List<NameValuePair> vals = new ArrayList<>();
    vals.add(new BasicNameValuePair("source_frames", toArray(p.source_frames)));
    vals.add(new BasicNameValuePair("chunk_size", String.valueOf(p.chunk_size)));
    vals.add(new BasicNameValuePair("destination_frame", escapePath(p.destination_frame)));
    vals.add(new BasicNameValuePair("number_columns", String.valueOf(p.number_columns)));
    vals.add(new BasicNameValuePair("column_names", toArray(p.column_names)));
    vals.add(new BasicNameValuePair("column_types", toArray(p.column_types)));
    vals.add(new BasicNameValuePair("separator", String.valueOf(p.separator)));
    vals.add(new BasicNameValuePair("check_header", String.valueOf(p.check_header)));
    vals.add(new BasicNameValuePair("delete_on_done", Boolean.TRUE.toString()));
    vals.add(new BasicNameValuePair("parse_type", p.parse_type.name()));
    vals.add(new BasicNameValuePair("single_quotes", String.valueOf(p.single_quotes)));

    HttpPost post = new HttpPost(url + "/3/Parse");
    post.setEntity(new UrlEncodedFormEntity(vals));
    post.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.FORM_DATA + "; charset=UTF-8");
    post.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");

    HttpResponse response = client.execute(post);
    Thread.sleep(3000);
    ParseV3Task task = null;
    try {
      if (response.getStatusLine().getStatusCode() == 200) {

        ParseV3 pv3 = mapper.readValue(response.getEntity().getContent(), ParseV3.class);
        task = new ParseV3Task(client, url, pv3);
        timer.schedule(task, 500, 1000);

      }
    } finally {
      EntityUtils.consume(response.getEntity());
    }

    return task;
  }

  public Future<ParseV3> parse(ParseV3 p) throws Exception {

    List<NameValuePair> vals = new ArrayList<>();
    vals.add(new BasicNameValuePair("source_frames", toArray(p.source_frames)));
    vals.add(new BasicNameValuePair("chunk_size", String.valueOf(p.chunk_size)));
    vals.add(new BasicNameValuePair("destination_frame", escapePath(p.destination_frame.name)));
    vals.add(new BasicNameValuePair("number_columns", String.valueOf(p.number_columns)));
    vals.add(new BasicNameValuePair("column_names", toArray(p.column_names)));
    vals.add(new BasicNameValuePair("column_types", toArray(p.column_types)));
    vals.add(new BasicNameValuePair("separator", String.valueOf(p.separator)));
    vals.add(new BasicNameValuePair("check_header", String.valueOf(p.check_header)));
    vals.add(new BasicNameValuePair("delete_on_done", String.valueOf(p.delete_on_done)));
    vals.add(new BasicNameValuePair("parse_type", p.parse_type.name()));
    vals.add(new BasicNameValuePair("single_quotes", String.valueOf(p.single_quotes)));

    HttpPost post = new HttpPost(url);
    post.setEntity(new UrlEncodedFormEntity(vals));
    post.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.FORM_DATA + "; charset=UTF-8");
    post.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");

    HttpResponse response = client.execute(post);
    Thread.sleep(3000);
    ParseV3Task task = null;
    try {
      if (response.getStatusLine().getStatusCode() == 200) {

        ParseV3 pv3 = mapper.readValue(response.getEntity().getContent(), ParseV3.class);
        task = new ParseV3Task(client, url, pv3);
        timer.schedule(task, 500, 1000);
      }
    } finally {
      EntityUtils.consume(response.getEntity());
    }

    return task;

  }

  private String toArray(FrameKeyV3[] keys) {

    List<String> frames = new ArrayList<>();

    for (FrameKeyV3 k : keys) {
      frames.add(k.name);
    }

    return toArray(frames.toArray(new String[] {}));
  }

  private String escapePath(String path) {
    return "\"" + path + "\"";
  }

  private String toArray(String... strings) {
    StringBuffer buffer = new StringBuffer("[");

    int leng = strings.length;
    int i = 1;
    for (String s : strings) {
      buffer.append(escapePath(s));
      if (i < leng) {
        buffer.append(",");
      }
      i++;

    }
    buffer.append("]");
    return buffer.toString();
  }
}
