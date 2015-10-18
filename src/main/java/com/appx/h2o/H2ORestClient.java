package com.appx.h2o;

import java.util.List;
import java.util.Timer;
import java.util.concurrent.Future;

import javax.ws.rs.core.MultivaluedHashMap;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import com.appx.h2o.tasks.ParseV3Task;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.common.collect.Lists;

import water.bindings.pojos.ImportFilesV3;
import water.bindings.pojos.ParseSetupV3;
import water.bindings.pojos.ParseV3;
import water.bindings.proxies.jaxrs.ImportFiles;
import water.bindings.proxies.jaxrs.Jobs;
import water.bindings.proxies.jaxrs.Parse;
import water.bindings.proxies.jaxrs.ParseSetup;

public class H2ORestClient {

  private final String url;
  private final Jobs jobs;
  private final ParseSetup parseSetup;
  private final ImportFiles importFile;
  private final Timer timer = new Timer();
  private final Parse parse;

  public H2ORestClient(final String url) {
    this.url = url;
    List<Class<?>> providers = Lists.newArrayList(JacksonJsonProvider.class);
    jobs = JAXRSClientFactory.create(url, Jobs.class, providers);
    importFile = JAXRSClientFactory.create(url, ImportFiles.class, providers);
    parseSetup = JAXRSClientFactory.create(url, ParseSetup.class, providers);
    parse = JAXRSClientFactory.create(url, Parse.class, providers);
  }

  public ParseSetupV3 guessSetup(String[] source_frames) {
    return parseSetup.guessSetup(source_frames);
  }

  public ImportFilesV3 importFiles(String path) {
    return importFile.importFiles(path);
  }

  public Future<ParseV3> parse(ParseV3 p) throws Exception {

    MultivaluedHashMap<String, String> vals = new MultivaluedHashMap<String, String>();
    vals.add("source_frames", toArray(p.source_frames[0].name));
    vals.add("chunk_size", String.valueOf(p.chunk_size));
    vals.add("destination_frame", p.destination_frame.name);
    vals.add("number_columns", String.valueOf(p.number_columns));
    vals.addAll("column_names", toArray(p.column_names));
    vals.addAll("column_types", toArray(p.column_types));
    vals.add("separator", String.valueOf(p.separator));
    vals.add("check_header", String.valueOf(p.check_header));
    vals.add("delete_on_done", String.valueOf(p.delete_on_done));
    vals.add("parse_type", p.parse_type.name());
    vals.add("single_quotes", String.valueOf(p.single_quotes));

    ParseV3 parsev3 = parse.parse(vals);
    ParseV3Task task = new ParseV3Task(jobs, parsev3);
    timer.schedule(task, 1000, 1000);
    return task;

  }

  private String toArray(String... strings) {
    StringBuffer buffer = new StringBuffer("[");

    int leng = strings.length;
    int i = 1;
    for (String s : strings) {
      buffer.append("\"").append(s).append("\"");
      if (i < leng) {
        buffer.append(",");
      }
      i++;

    }
    buffer.append("]");
    return buffer.toString();
  }
}
