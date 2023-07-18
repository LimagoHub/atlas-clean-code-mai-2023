package de.gothaer.collections;

public class Stapel {

    private boolean empty = true;
    public boolean isEmpty() {
        return empty;
    }

    public void push(final int i) {

        empty = false;
    }
}
