package water.bindings.pojos;

public class TwoDimTableBase extends Schema {
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
