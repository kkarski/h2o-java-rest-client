package water.bindings.pojos;

public class ModelSchema extends ModelSchemaBase {
    /** The build parameters for the model (e.g. K for KMeans). */
    public ModelParametersSchema parameters;

    /** The build output for the model (e.g. the cluster centers for KMeans). */
    public ModelOutputSchema output;

    /** Compatible frames, if requested */
    public String[] compatible_frames;

    /** Checksum for all the things that go into building the Model. */
    public long checksum;

    /** Model key */
    public String model_id;

    /** The algo name for this Model. */
    public String algo;

    /** The pretty algo name for this Model (e.g., Generalized Linear Model, rather than GLM). */
    public String algo_full_name;

    /** The response column name for this Model (if applicable). Is null otherwise. */
    public String response_column_name;

    /** The Model's training frame key */
    public String data_frame;

    /** Timestamp for when this model was completed */
    public long timestamp;
}
