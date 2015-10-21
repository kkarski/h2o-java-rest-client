package water.bindings.proxies.jaxrs;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import water.bindings.pojos.ParseSetupV3;

@Produces(MediaType.APPLICATION_FORM_URLENCODED + "; " + MediaType.CHARSET_PARAMETER + "=UTF-8")
@Consumes(MediaType.APPLICATION_JSON + "; " + MediaType.CHARSET_PARAMETER + "=UTF-8")
@Path("/3/ParseSetup")
public interface ParseSetup {

  @POST
  ParseSetupV3 guessSetup(@QueryParam("source_frames") String frames);

  @POST
  ParseSetupV3 guessSetup(@QueryParam("source_frames") String[] frames);

  @POST
  ParseSetupV3 guessSetup(MultivaluedMap<String, String> form);

}
