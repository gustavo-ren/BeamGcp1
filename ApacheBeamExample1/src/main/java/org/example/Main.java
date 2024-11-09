package org.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;

import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.io.csv.CsvIO;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.custom.options.ColumnName;
import org.custom.options.InputLocalFile;

import org.custom.options.SearchKey;
import org.custom.transforms.FilterShow;
import org.schemas.AnimeCharacter;
import org.schemas.parsers.ParseAnimeCharacterFn;

import java.io.IOException;
import java.io.StringReader;

public class Main {

    public static void main(String[] args) {

        PipelineOptionsFactory.register(InputLocalFile.class);
        PipelineOptionsFactory.register(ColumnName.class);
        PipelineOptionsFactory.register(SearchKey.class);

        InputLocalFile inputLocalFile = PipelineOptionsFactory
                .fromArgs(args)
                .withValidation()
                .as(InputLocalFile.class);

        SearchKey key = PipelineOptionsFactory
                .fromArgs(args)
                .withValidation()
                .as(SearchKey.class);

        PipelineOptions options = PipelineOptionsFactory.create();
        Pipeline pipeline = Pipeline.create(options);

        PCollection<AnimeCharacter> lines = pipeline.apply("Read CSV", TextIO.read().from(inputLocalFile.getInputLocalFile()))
                .apply("ReadAnime", ParDo.of(new ParseAnimeCharacterFn()))
                .apply("Filter Bleach", new FilterShow(key.getKey()));

        lines.apply(CsvIO.<AnimeCharacter>write(inputLocalFile.getOutput(), CSVFormat.DEFAULT));

        //PCollection<KV<String, String>> parseData = lines.apply("Parse CSV", ParDo.of(new ParseCSVDoFn()));
        pipeline.run().waitUntilFinish();
    }
}

class ParseCSVDoFn extends DoFn<String, KV<String, String>>{
    @ProcessElement
    public void processElement(ProcessContext c) throws IOException{
        String line = c.element();

        try(StringReader reader = new StringReader(line);
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(reader)){
            for(CSVRecord record : parser){
                String key = record.get("City");
                String value = record.get("Price");
                c.output(KV.of(key, value));
            }
        }
    }
}





























































