package de.atlas.main;

import de.atlas.client.Client;
import de.atlas.collection.IntArrayFiller;
import de.atlas.collection.impl.ParallelArrayFiller;
import de.atlas.common.BenchmarkProxy;
import de.atlas.generator.IntGeneratorFactory;
import de.atlas.generator.impl.RandomNumberGeneratorFactory;

public class Main {

    private int [] data = new int[Integer.MAX_VALUE / 2];
    public static void main(String[] args) {
        new Main().go();
    }

    private void go() {

        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        for (int runnningThreads = 1; runnningThreads <= availableProcessors + 1 ; runnningThreads++) {
            IntGeneratorFactory factory = new RandomNumberGeneratorFactory();
            IntArrayFiller arrayFiller = new ParallelArrayFiller(factory, runnningThreads);
            arrayFiller = (IntArrayFiller) BenchmarkProxy.newInstance(arrayFiller);


            Client client = new Client(arrayFiller.fillArray(data));
        }


    }
}