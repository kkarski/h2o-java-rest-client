package water.bindings.proxies.retrofit;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;
import water.bindings.pojos.ImportFilesV3;

public interface ImportFiles {
    
	@GET("/3/ImportFiles")
    ImportFilesV3 importFiles(@Query("path") String path);
	
	@GET("/3/ImportFiles")
    ImportFilesV3 importFiles();

    @Headers("Content-Type: application/x-www-form-urlencoded; charset=UTF-8")
    @POST("/3/ImportFiles")
    ImportFilesV3 importFiles(@Body ImportFilesV3 parms);
}
