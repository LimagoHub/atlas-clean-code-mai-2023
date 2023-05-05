package de.atlas.time;

public interface StopWatch {

    void start();
    void stop();

    long durationInMillis();
}
