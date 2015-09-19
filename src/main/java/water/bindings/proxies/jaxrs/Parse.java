package water.bindings.proxies.jaxrs;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import water.bindings.pojos.ParseV3;

@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
@Path("/3/Parse")
public interface Parse {

	@POST
	ParseV3 parse(@FormParam("source_frames") String[] frames,
			@FormParam("destination_frame") String destination_frame,
			@FormParam("chunk_size") int chunk_size);
	
	@POST
	ParseV3 parse(ParseV3 parse);
	
	@POST
	ParseV3 parse(MultivaluedMap<String, String> form);
}
