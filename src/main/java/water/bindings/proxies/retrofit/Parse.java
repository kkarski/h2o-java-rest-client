package water.bindings.proxies.retrofit;

import water.bindings.pojos.*;
import retrofit.*;
import retrofit.http.*;

public interface Parse {
    @Headers("Content-Type: application/x-www-form-urlencoded; charset=UTF-8")
    @POST("/3/Parse")
    ParseV3 parse(ParseV3 parms);
}
