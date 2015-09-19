package water.bindings.pojos;

public class RequestSchema extends Schema {
    /** Comma-separated list of JSON field paths to exclude from the result, used like: "/3/Frames?_exclude_fields=frames/frame_id/URL,__meta" */
    public String _exclude_fields;
}
