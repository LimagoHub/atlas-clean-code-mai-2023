package de.limago.gui.colors;

public class PendelColorConverter implements ColorConverter{
    private final int [] colors = {0, 0x00ff0000, 0x0000ff00, 0x000000ff};
    @Override
    public int convert(final int i) {

        if(! (i >=0 && i <= 3)) throw new IllegalArgumentException("Upps");
        return colors[i];
    }
}
