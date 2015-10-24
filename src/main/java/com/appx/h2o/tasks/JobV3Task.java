package com.appx.h2o.tasks;

import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.MediaType;

import water.bindings.pojos.JobV3;
import water.bindings.pojos.JobsV3;

public abstract class JobV3Task<V> extends TimerTask implements Future<V> {

  private final AtomicReference<JobV3> jobv3 = new AtomicReference<JobV3>();
  private final AtomicBoolean cancelled = new AtomicBoolean(false);
  private final CloseableHttpClient client;
  private final String jobId;
  private final V reply;
  private final String url;
  private final static ObjectMapper mapper = new ObjectMapper();

  public JobV3Task(String jobId, CloseableHttpClient jobs, String url, V reply) {
    this.client = jobs;
    this.jobId = jobId;
    this.reply = reply;
    this.url = url;
  }

  protected V updateReplyStatus(V reply, JobV3 job) {
    return reply;
  }

  @Override
  public void run() {

    HttpResponse response = null;
    JobsV3 job = null;
    try {

      HttpGet get = new HttpGet(new URIBuilder(url).setPath("/3/Jobs/" + jobId).build());
      get.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.JSON_UTF_8.toString());
      get.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");

      response = client.execute(get);

      if (response.getStatusLine().getStatusCode() == 200) {
        job = mapper.readValue(response.getEntity().getContent(), JobsV3.class);
      }

    } catch (Exception e) {

    } finally {
      try {
        EntityUtils.consume(response.getEntity());
      } catch (Exception ex) {

      }
    }

    jobv3.set(job.jobs[0]);
  }

  @Override
  public boolean cancel(boolean mayInterruptIfRunning) {
    super.cancel();
    this.cancelled.set(true);
    return cancelled.get();
  }

  @Override
  public V get() throws InterruptedException, ExecutionException {

    while (!isDone()) {

      if (cancelled.get()) {
        throw new InterruptedException();
      }

      Thread.sleep(1000);
    }

    cancel();
    V updated = updateReplyStatus(reply, jobv3.get());
    return updated;
  }

  @Override
  public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
    return get();
  }

  @Override
  public boolean isCancelled() {
    return cancelled.get();
  }

  @Override
  public boolean isDone() {
    return (jobv3.get() != null && (jobv3.get().status.equalsIgnoreCase("done") || jobv3.get().status.equalsIgnoreCase("failed")));
  }
}
