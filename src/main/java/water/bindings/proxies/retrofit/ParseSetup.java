package water.bindings.proxies.retrofit;

import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;
import water.bindings.pojos.ParseSetupV3;

public interface ParseSetup {

	@Headers("Content-Type: application/x-www-form-urlencoded; charset=UTF-8")
	@POST("/3/ParseSetup")
	ParseSetupV3 guessSetup(@Body ParseSetupV3 parse);
}
