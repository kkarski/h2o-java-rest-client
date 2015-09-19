package water.bindings.pojos;

public class JobV3 extends Schema {
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
    public FrameKeyV3 dest;

    /** exception */
    public String exception;

    /** Info, warning and error messages; NOTE: can be appended to while the Job is running */
    public ValidationMessageV3[] messages;

    /** Count of error messages */
    public int error_count;
}
