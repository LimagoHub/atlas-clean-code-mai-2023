package de.atlas.time.impl;

import de.atlas.time.StopWatch;

import java.time.Duration;
import java.time.Instant;

public class StopWatchImpl implements StopWatch {
    private Instant start = Instant.now();
    private Instant ende = Instant.now();
    @Override
    public void start() {
        start = Instant.now();
    }

    @Override
    public void stop() {
        ende = Instant.now();
    }

    @Override
    public long durationInMillis() {
        return Duration.between(start, ende ).toMillis();
    }
}
