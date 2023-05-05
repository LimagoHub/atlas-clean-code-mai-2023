package de.limago.gui.colors;

public class MandelbrotColorConverter implements ColorConverter{

    @Override
    public int convert(final int i) {
        final int red = ((i * 3) % 256) << 16;
        final int green = ((i * 5) % 256) << 8;
        final int blue = (i * 11) % 256;

        return red | green | blue;
    }
}
