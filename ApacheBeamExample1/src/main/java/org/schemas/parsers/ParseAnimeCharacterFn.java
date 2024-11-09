package org.schemas.parsers;

import org.antlr.v4.runtime.misc.NotNull;
import org.apache.beam.sdk.metrics.Counter;
import org.apache.beam.sdk.metrics.Metrics;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.DoFn.ProcessElement;

import org.schemas.AnimeCharacter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ParseAnimeCharacterFn extends DoFn<String, AnimeCharacter> {
    private static final Logger LOG = LoggerFactory.getLogger(ParseAnimeCharacterFn.class);
    private final Counter numParseErrors = Metrics.counter("main", "ParseErrors");

    @ProcessElement
    public void processElement(ProcessContext c){
        String[] component = c.element().split(",", -1);
        try{
            int id = Integer.parseInt(component[0].trim());
            String name = component[1].trim();
            String show = component[2].trim();

            AnimeCharacter anime = new AnimeCharacter(id, name, show);
            c.output(anime);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e){
            numParseErrors.inc();
            LOG.info("Parse error on " + c.element() + ", " + e.getMessage());
        }
    }
}
