package water.bindings.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SplitFrameV3 extends Schema {
  /** Dataset */
  public FrameKeyV3 dataset;

  /** Split ratios - resulting number of split is ratios.length+1 */
  public double[] ratios;

  /** Destination keys for each output frame split. */
  public FrameKeyV3[] destination_frames;

  /** Job Key */
  public JobKeyV3 key;

  /** Job description */
  public String description;

  /** job status */
  public String status;

  /** progress, from 0 to 1 */
  public float progress;

  /** current progress status description */
  public String progress_msg;

  /** Start time */
  public long start_time;

  /** Runtime in milliseconds */
  public long msec;

  /** destination key */
  public JobKeyV3 dest;

  /** exception */
  public String exception;

  /** Info, warning and error messages; NOTE: can be appended to while the Job is running */
  public ValidationMessageV3[] messages;

  /** Count of error messages */
  public int error_count;
}
