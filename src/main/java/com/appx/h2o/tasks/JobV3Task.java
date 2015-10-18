package com.appx.h2o.tasks;

import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import water.bindings.pojos.JobV3;
import water.bindings.pojos.JobsV3;
import water.bindings.proxies.jaxrs.Jobs;

public abstract class JobV3Task<V> extends TimerTask implements Future<V> {

  private final AtomicReference<JobV3> jobv3 = new AtomicReference<JobV3>();
  private final AtomicBoolean cancelled = new AtomicBoolean(false);
  private final Jobs jobs;
  private final String jobId;
  private final V reply;

  public JobV3Task(String jobId, Jobs jobs, V reply) {
    this.jobs = jobs;
    this.jobId = jobId;
    this.reply = reply;
  }

  @Override
  public void run() {
    final JobsV3 job = jobs.jobs(jobId);
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
    return reply;
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
    return (jobv3.get() != null && jobv3.get().status.equalsIgnoreCase("done"));
  }
}
