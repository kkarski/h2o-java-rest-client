package com.appx.h2o.tasks;

import org.apache.http.impl.client.CloseableHttpClient;

import water.bindings.pojos.ParseV3;

public class ParseV3Task extends JobV3Task<ParseV3> {

  public ParseV3Task(CloseableHttpClient client, String url, ParseV3 reply) {
    super(reply.job.key.name, client, url, reply);
  }

}
