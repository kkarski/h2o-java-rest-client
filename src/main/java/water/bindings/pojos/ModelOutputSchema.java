package water.bindings.pojos;

import java.util.Map;

public class ModelOutputSchema extends Schema {
    /** Column names */
    public String[] names;

    /** Domains for categorical (enum) columns */
    public String[][] domains;

    /** Cross-validation models (model ids) */
    public String[] cross_validation_models;

    /** Cross-validation predictions (frame ids) */
    public String[] cross_validation_predictions;

    /** Category of the model (e.g., Binomial) */
    public ModelCategory model_category;

    /** Model summary */
    public TwoDimTableBase model_summary;

    /** Scoring history */
    public TwoDimTableBase scoring_history;

    /** Training data model metrics */
    public ModelMetricsBase training_metrics;

    /** Validation data model metrics */
    public ModelMetricsBase validation_metrics;

    /** Cross-validation model metrics */
    public ModelMetricsBase cross_validation_metrics;

    /** Job status */
    public String status;

    /** Start time in milliseconds */
    public long start_time;

    /** End time in milliseconds */
    public long end_time;

    /** Runtime in milliseconds */
    public long run_time;

    /** Help information for output fields */
    public Map<String,String> help;
}
