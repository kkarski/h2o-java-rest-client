package water.bindings.pojos;

public class ColV3 extends Schema {
    /** label */
    public String label;

    /** missing */
    public long missing_count;

    /** zeros */
    public long zero_count;

    /** positive infinities */
    public long positive_infinity_count;

    /** negative infinities */
    public long negative_infinity_count;

    /** mins */
    public double[] mins;

    /** maxs */
    public double[] maxs;

    /** mean */
    public double mean;

    /** sigma */
    public double sigma;

    /** datatype: {enum, string, int, real, time, uuid} */
    public String type;

    /** domain; not-null for enum columns only */
    public String[] domain;

    /** cardinality of this column's domain; not-null for enum columns only */
    public int domain_cardinality;

    /** data */
    public double[] data;

    /** string data */
    public String[] string_data;

    /** decimal precision, -1 for all digits */
    public byte precision;

    /** Histogram bins; null if not computed */
    public long[] histogram_bins;

    /** Start of histogram bin zero */
    public double histogram_base;

    /** Stride per bin */
    public double histogram_stride;

    /** Percentile values, matching the default percentiles */
    public double[] percentiles;
}
