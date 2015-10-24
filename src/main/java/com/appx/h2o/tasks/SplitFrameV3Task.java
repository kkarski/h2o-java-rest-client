package com.appx.h2o.tasks;

import org.apache.http.impl.client.CloseableHttpClient;

import water.bindings.pojos.JobV3;
import water.bindings.pojos.SplitFrameV3;

public class SplitFrameV3Task extends JobV3Task<SplitFrameV3> {

  public SplitFrameV3Task(CloseableHttpClient jobs, String url, SplitFrameV3 reply) {
    super(reply.key.name, jobs, url, reply);
  }

  @Override
  protected SplitFrameV3 updateReplyStatus(SplitFrameV3 reply, JobV3 job) {

    reply.status = job.status;
    reply.error_count = job.error_count;
    reply.exception = job.exception;
    reply.messages = job.messages;
    reply.msec = job.msec;
    reply.progress = job.progress;
    reply.progress_msg = job.progress_msg;
    reply.start_time = job.start_time;
    return reply;

  }

}
