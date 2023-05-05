package de.atlas.generator.impl;

import de.atlas.generator.IntGenerator;
import de.atlas.generator.IntGeneratorFactory;

public class RandomNumberGeneratorFactory implements IntGeneratorFactory {
    @Override
    public IntGenerator create() {
        return new RandomNumberGenerator();
    }
}
