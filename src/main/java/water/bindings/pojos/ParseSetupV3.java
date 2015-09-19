package water.bindings.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParseSetupV3 extends RequestSchema {
	/** Source frames */
	public FrameKeyV3[] source_frames;

	/** Parser type */
	public ParserType parse_type;

	/** Field separator */
	public byte separator;

	/** Single quotes */
	public boolean single_quotes;

	/**
	 * Check header: 0 means guess, +1 means 1st line is header not data, -1
	 * means 1st line is data not header
	 */
	public int check_header;

	/** Column names */
	public String[] column_names;

	/** Value types for columns */
	public String[] column_types;

	/** NA strings for columns */
	public String[][] na_strings;

	/** Regex for names of columns to return */
	public String column_name_filter;

	/** Column offset to return */
	public int column_offset;

	/** Number of columns to return */
	public int column_count;

	/** Suggested name */
	public String destination_frame;

	/** Number of header lines found */
	public long header_lines;

	/** Number of columns */
	public int number_columns;

	/** Sample data */
	public String[][] data;

	/** Size of individual parse tasks */
	public int chunk_size;

	/** Total number of columns we would return with no column pagination */
	public int total_filtered_column_count;

}
