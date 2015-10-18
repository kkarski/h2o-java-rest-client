package water.bindings.proxies.jaxrs;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import water.bindings.pojos.JobsV3;

@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
@Path("/3/Jobs")
public interface Jobs {

  @GET
  @Path("/{jobId}")
  public JobsV3 jobs(@PathParam("jobId") String jobId);

}
