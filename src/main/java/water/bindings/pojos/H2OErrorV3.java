package water.bindings.pojos;

import java.util.Map;

public class H2OErrorV3 extends Schema {
    /** Milliseconds since the epoch for the time that this H2OError instance was created.  Generally this is a short time since the underlying error ocurred. */
    public long timestamp;

    /** Error url */
    public String error_url;

    /** Message intended for the end user (a data scientist). */
    public String msg;

    /** Potentially more detailed message intended for a developer (e.g. a front end engineer or someone designing a language binding). */
    public String dev_msg;

    /** HTTP status code for this error. */
    public int http_status;

    /** Any values that are relevant to reporting or handling this error.  Examples are a key name if the error is on a key, or a field name and object name if it's on a specific field. */
    public Map<String,Object> values;

    /** Exception type, if any. */
    public String exception_type;

    /** Raw exception message, if any. */
    public String exception_msg;

    /** Stacktrace, if any. */
    public String[] stacktrace;
}
