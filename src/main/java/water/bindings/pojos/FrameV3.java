package water.bindings.pojos;

public class FrameV3 extends FrameBase {
    /** Row offset to display */
    public long row_offset;

    /** Number of rows to display */
    public int row_count;

    /** Column offset to return */
    public int column_offset;

    /** Number of columns to return */
    public int column_count;

    /** Total number of columns in the Frame */
    public int total_column_count;

    /** checksum */
    public long checksum;

    /** Number of rows in the Frame */
    public long rows;

    /** Default percentiles, from 0 to 1 */
    public double[] default_percentiles;

    /** Columns in the Frame */
    public ColV3[] columns;

    /** Compatible models, if requested */
    public String[] compatible_models;

    /** The set of IDs of vectors in the Frame */
    public String[] vec_ids;

    /** Chunk summary */
    public TwoDimTableV3 chunk_summary;

    /** Distribution summary */
    public TwoDimTableV3 distribution_summary;

    /** Frame ID */
    public String frame_id;

    /** Total data size in bytes */
    public long byte_size;

    /** Is this Frame raw unparsed data? */
    public boolean is_text;
}
