package de.gothaer.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StapelTest {

    private Stapel objectUnderTest;

    @BeforeEach
    void setUp() {
        objectUnderTest = new Stapel();
    }

    @Test
    void isEmpty_BlaBlubb() {
        assertTrue(objectUnderTest.isEmpty());
    }

    @Test
    void isEmpty_2() {
        objectUnderTest.push(1);
        assertFalse(objectUnderTest.isEmpty());
    }
}