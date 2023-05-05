package de.atlas.collection.impl;

import de.atlas.collection.IntArrayFiller;
import de.atlas.generator.IntGenerator;

public class SequentiellArrayFiller implements IntArrayFiller {

    private final IntGenerator generator;

    public SequentiellArrayFiller(final IntGenerator generator) {
        this.generator = generator;
    }

    @Override
    public int[] fillArray(final int[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] = generator.next();
        }
        return data;
    }
}
