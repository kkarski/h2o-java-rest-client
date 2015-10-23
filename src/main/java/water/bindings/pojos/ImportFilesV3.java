package water.bindings.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImportFilesV3 extends RequestSchema {
  /** path */
  public String path;

  /** files */
  public String[] files;

  /** names */
  public String[] destination_frames;

  /** fails */
  public String[] fails;

  /** dels */
  public String[] dels;
}
