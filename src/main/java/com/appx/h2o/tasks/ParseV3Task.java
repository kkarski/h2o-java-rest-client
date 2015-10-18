package com.appx.h2o.tasks;

import water.bindings.pojos.ParseV3;
import water.bindings.proxies.jaxrs.Jobs;

public class ParseV3Task extends JobV3Task<ParseV3> {

  public ParseV3Task(Jobs jobs, ParseV3 reply) {
    super(reply.job.key.name, jobs, reply);
  }

}
