package water.bindings.pojos;

public class FramesBase extends RequestSchema {
    /** Name of Frame of interest */
    public String frame_id;

    /** Name of column of interest */
    public String column;

    /** Row offset to return */
    public long row_offset;

    /** Number of rows to return */
    public int row_count;

    /** Column offset to return */
    public int column_offset;

    /** Number of columns to return */
    public int column_count;

    /** Find and return compatible models? */
    public boolean find_compatible_models;

    /** File output path */
    public String path;

    /** Overwrite existing file */
    public boolean force;

    /** Job for export file */
    public JobV3 job;

    /** Frames */
    public FrameBase[] frames;

    /** Compatible models */
    public ModelSchema[] compatible_models;

    /** Domains */
    public String[][] domain;

    /** Comma-separated list of JSON field paths to exclude from the result, used like: "/3/Frames?_exclude_fields=frames/frame_id/URL,__meta" */
    public String _exclude_fields;
}
