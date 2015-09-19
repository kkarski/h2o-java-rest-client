package water.bindings.proxies.jaxrs;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import water.bindings.pojos.ImportFilesV3;

@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
@Path("/3/ImportFiles")
public interface ImportFiles {
	
	@GET
	ImportFilesV3 importFiles(@QueryParam("path") String path);

}
