package water.bindings.pojos;

public class ModelMetricsBase extends Schema {
    /** The model used for this scoring run. */
    public String model;

    /** The checksum for the model used for this scoring run. */
    public long model_checksum;

    /** The frame used for this scoring run. */
    public String frame;

    /** The checksum for the frame used for this scoring run. */
    public long frame_checksum;

    /** Optional description for this scoring run (to note out-of-bag, sampled data, etc.) */
    public String description;

    /** The category (e.g., Clustering) for the model used for this scoring run. */
    public ModelCategory model_category;

    /** The time in mS since the epoch for the start of this scoring run. */
    public long scoring_time;

    /** Predictions Frame. */
    public FrameV3 predictions;

    /** The Mean Squared Error of the prediction for this scoring run. */
    public double MSE;
}
