package de.atlas.collection.impl;

import de.atlas.collection.IntArrayFiller;
import de.atlas.generator.IntGenerator;
import de.atlas.generator.IntGeneratorFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ParallelArrayFiller implements IntArrayFiller {

    private final Logger logger = Logger.getLogger(getClass().getName());
    private final IntGeneratorFactory generatorFactory;
    private final int runningThreads;
    private int[] data;

    private ExecutorService service;

    public ParallelArrayFiller(final IntGeneratorFactory generatorFactory, final int runningThreads) {
        this.generatorFactory = generatorFactory;
        this.runningThreads = runningThreads;
    }

    @Override
    public int[] fillArray(final int[] data) {
        this.data = data;
        startThreadpoolToFillArrayParallel();
        return  this.data;
    }

    private void startThreadpoolToFillArrayParallel() {
        try {
            startThreapoolImpl();
        } catch (InterruptedException e) {
            logger.severe(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private void startThreapoolImpl() throws InterruptedException {
        service = Executors.newFixedThreadPool(runningThreads);
        startWorker();
        service.shutdown();
        service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
    }

    private void startWorker() {
        for (int actThread = 0; actThread < runningThreads; actThread++) {
            startSingleWorkerForSegment(actThread);
        }
    }

    private void startSingleWorkerForSegment(final int actThread) {
        final int segmentSize = data.length / runningThreads;
        final int start = actThread * segmentSize;
        final int end = start + segmentSize;
        service.execute(new FillSegmentWorker(start, end));
    }

    class FillSegmentWorker implements Runnable {

        private final IntGenerator generator = generatorFactory.create();
        private final int startIndex;
        private final int endIndex;

        public FillSegmentWorker(final int startIndex, final int endIndeIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndeIndex;
        }

        @Override
        public void run() {
            for(int i = startIndex; i < endIndex; i++)
                data[i] = generator.next();
        }
    }
}
