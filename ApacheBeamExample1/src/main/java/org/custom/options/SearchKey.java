package org.custom.options;

import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.PipelineOptions;

public interface SearchKey extends PipelineOptions{

    @Description("Key to filter the show")
    @Default.String("bleach")
    String getKey();
    void setKey(String key);
}
