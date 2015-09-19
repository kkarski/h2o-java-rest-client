package water.bindings.pojos;

public class TwoDimTableV3 extends TwoDimTableBase {
    /** Table Name */
    public String name;

    /** Table Description */
    public String description;

    /** Column Specification */
    public ColumnSpecsBase[] columns;

    /** Number of Rows */
    public int rowcount;

    /** Table Data (col-major) */
    public Object data;
}
