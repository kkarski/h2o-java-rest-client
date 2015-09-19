package water.bindings.pojos;

public class ModelParametersSchema extends Schema {
    /** Destination id for this model; auto-generated if not specified */
    public String model_id;

    /** Training frame */
    public String training_frame;

    /** Validation frame */
    public String validation_frame;

    /** Number of folds for N-fold cross-validation */
    public int nfolds;

    /** Keep cross-validation model predictions */
    public boolean keep_cross_validation_predictions;

    /** Response column */
    public ColSpecifierV3 response_column;

    /** Column with observation weights */
    public ColSpecifierV3 weights_column;

    /** Offset column */
    public ColSpecifierV3 offset_column;

    /** Column with cross-validation fold index assignment per observation */
    public ColSpecifierV3 fold_column;

    /** Cross-validation fold assignment scheme, if fold_column is not specified */
    public FoldAssignmentScheme fold_assignment;

    /** Ignored columns */
    public String[] ignored_columns;

    /** Ignore constant columns */
    public boolean ignore_const_cols;

    /** Whether to score during each iteration of model training */
    public boolean score_each_iteration;

    /** Model checkpoint to resume training with */
    public String checkpoint;
}
