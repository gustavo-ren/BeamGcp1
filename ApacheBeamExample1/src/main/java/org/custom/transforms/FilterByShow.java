package org.custom.transforms;

import org.apache.beam.sdk.transforms.SerializableFunction;
import org.schemas.AnimeCharacter;

public class FilterByShow implements SerializableFunction<AnimeCharacter, Boolean> {

    private final String show;

    FilterByShow(String show){
        this.show = show;
    }
    @Override
    public Boolean apply(AnimeCharacter input) {
        return input.getShow().equalsIgnoreCase(this.show);
    }
}
