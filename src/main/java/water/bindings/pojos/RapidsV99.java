package water.bindings.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RapidsV99 extends Schema {
  /** An Abstract Syntax Tree. */
  public String ast;

  /** An array of function definitions. */
  public String fun;

  /** A pointer to a Frame */
  public String ast_key;

  /** Parsing error, if any */
  public String error;

  /** Result key */
  public FrameKeyV3 key;

  /** Rows in Frame result */
  public long num_rows;

  /** Columns in Frame result */
  public int num_cols;

  /** Scalar result */
  public double scalar;

  /** Function result */
  public String funstr;

  /** Column Names */
  public String[] col_names;

  /** String result */
  public String string;

  /** result */
  public String result;

  /** Was evaluated */
  public boolean evaluated;

  /** Head of a Frame result */
  public String[][] head;

  /** Result Type. */
  public int result_type;

  /** Vec keys for key result */
  public VecKeyV3[] vec_ids;

  /**
   * Comma-separated list of JSON field paths to exclude from the result, used like:
   * "/3/Frames?_exclude_fields=frames/frame_id/URL,__meta"
   */
  public String _exclude_fields;
}
