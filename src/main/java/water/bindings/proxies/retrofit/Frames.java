package water.bindings.proxies.retrofit;

import water.bindings.pojos.*;
import retrofit.*;
import retrofit.http.*;

public interface Frames {
    @GET("/3/Frames/{frameid}/export/{path}/overwrite/{force}")
    FramesV3 export(FrameKeyV3 frame_id, String path, boolean force);

    @Headers("Content-Type: application/x-www-form-urlencoded; charset=UTF-8")
    @POST("/3/Frames/{frameid}/export")
    FramesV3 export(FramesV3 parms);

    @GET("/3/Frames/{frameid}/columns/{column}/summary")
    FramesV3 columnSummary(FrameKeyV3 frame_id, String column);

    @GET("/3/Frames/{frameid}/columns/{column}/domain")
    FramesV3 columnDomain(FrameKeyV3 frame_id, String column);

    @GET("/3/Frames/{frameid}/columns/{column}")
    FramesV3 column(FrameKeyV3 frame_id, String column);

    @GET("/3/Frames/{frameid}/columns")
    FramesV3 columns(FrameKeyV3 frame_id);

    @GET("/3/Frames/{frameid}/summary")
    FramesV3 summary(FrameKeyV3 frame_id);

    @GET("/3/Frames/{frameid}")
    FramesV3 fetch(FrameKeyV3 frame_id);

    @GET("/3/Frames")
    FramesV3 list();

    @DELETE("/3/Frames/{frameid}")
    FramesV3 delete(FrameKeyV3 frame_id);

    @DELETE("/3/Frames")
    FramesV3 deleteAll();
}
