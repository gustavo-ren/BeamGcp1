package org.custom.options;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;

public interface InputLocalFile extends PipelineOptions {
    @Description("Input local file address")
    @Default.String("inputfile/input.csv")
    String getInputLocalFile();
    void setInputLocalFile(String input);

    @Description("Output file address")
    @Default.String("inputfile/output.csv")
    String getOutput();
    void setOutput(String input);
}
