package water.bindings.pojos;

public class ColSpecifierV3 extends Schema {
    /** Name of the column */
    public String column_name;

    /** List of fields which specify columns that must contain this column */
    public String[] is_member_of_frames;
}
