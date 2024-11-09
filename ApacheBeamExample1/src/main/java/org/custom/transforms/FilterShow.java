package org.custom.transforms;

import org.apache.beam.repackaged.core.org.antlr.v4.runtime.misc.NotNull;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.Filter;
import org.apache.beam.sdk.transforms.SerializableFunction;
import org.apache.beam.sdk.values.PCollection;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.schemas.AnimeCharacter;

public class FilterShow extends PTransform<PCollection<AnimeCharacter>, PCollection<AnimeCharacter>> {

    private String show;

    public FilterShow(String show){
        this.show = show;
    }

    @Override
    public PCollection<AnimeCharacter> expand(PCollection<AnimeCharacter> anime){
        return anime.apply(Filter.by(new FilterByShow(this.show)));
    }
}

