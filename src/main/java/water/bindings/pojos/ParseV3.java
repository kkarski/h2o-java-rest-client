package water.bindings.pojos;

public class ParseV3 extends RequestSchema {
    /** Final frame name */
    public FrameKeyV3 destination_frame;

    /** Source frames */
    public FrameKeyV3[] source_frames;

    /** Parser type */
    public ParserType parse_type;

    /** Field separator */
    public byte separator;

    /** Single Quotes */
    public boolean single_quotes;

    /** Check header: 0 means guess, +1 means 1st line is header not data, -1 means 1st line is data not header */
    public int check_header;

    /** Number of columns */
    public int number_columns;

    /** Column names */
    public String[] column_names;

    /** Value types for columns */
    public String[] column_types;

    /** Domains for categorical columns */
    public String[][] domains;

    /** NA strings for columns */
    public String[][] na_strings;

    /** Size of individual parse tasks */
    public int chunk_size;

    /** Delete input key after parse */
    public boolean delete_on_done;

    /** Block until the parse completes (as opposed to returning early and requiring polling */
    public boolean blocking;

    /** Remove frame after blocking parse, and return array of Vecs */
    public boolean remove_frame;

    /** Parse job */
    public JobV3 job;

    /** Rows */
    public long rows;

    /** Vec IDs */
    public String[] vec_ids;

    /** Comma-separated list of JSON field paths to exclude from the result, used like: "/3/Frames?_exclude_fields=frames/frame_id/URL,__meta" */
    public String _exclude_fields;
}
