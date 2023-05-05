package de.limago.gui.colors;

public class PendelColorConverter implements ColorConverter{
    private final int [] colors = {0, 0x00ff0000, 0x0000ff00, 0x000000ff};
    @Override
    public int convert(final int i) {

        assert i >=0 && i <= 3;
        return colors[i];
    }
}
