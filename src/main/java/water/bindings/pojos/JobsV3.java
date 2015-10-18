package water.bindings.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobsV3 extends Schema {

  public JobKeyV3 job_id;

  public JobV3[] jobs;

}
