package water.bindings.pojos;

public class ModelSchemaBase extends Schema {
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
