package water.bindings.pojos;

public class FrameBase extends Schema {
    /** Frame ID */
    public String frame_id;

    /** Total data size in bytes */
    public long byte_size;

    /** Is this Frame raw unparsed data? */
    public boolean is_text;
}
