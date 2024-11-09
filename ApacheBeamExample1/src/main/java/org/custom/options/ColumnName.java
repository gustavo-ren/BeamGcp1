package org.custom.options;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;

public interface ColumnName extends PipelineOptions {
    @Description("Search column for file")
    @Default.String("ID")
    String getColumn();
    void setColumn(String column);
}
