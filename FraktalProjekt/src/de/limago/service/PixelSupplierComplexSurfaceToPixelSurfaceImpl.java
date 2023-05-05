package de.limago.service;

import de.limago.gui.colors.ColorConverter;
import de.limago.gui.colors.MandelbrotColorConverter;
import de.limago.gui.colors.PendelColorConverter;
import de.limago.math.Complex;

import java.util.function.Function;

public class PixelSupplierComplexSurfaceToPixelSurfaceImpl implements PixelSupplier{

    private Complex lowerLeftCorner;
    private double width;

    private int size;

    private ColorConverter colorConverter = new PendelColorConverter(); // Ganz doof
    private Function<Complex,Integer> function = new PendelFunction(); // Ganz doof



    public PixelSupplierComplexSurfaceToPixelSurfaceImpl(final int size) {
        this(new Complex(-2.0 /*-2 */, -1.25/* -2 */), 2.5 /* 4 */, size); // doof
    }

    public PixelSupplierComplexSurfaceToPixelSurfaceImpl(final Complex lowerLeftCorner, final double width, final int size) {
        this.lowerLeftCorner = lowerLeftCorner;
        this.width = width;
        this.size = size;
    }

    private Complex generateComplexNumberFromPixel(int x, int y) {
        double delta = width /  size;
        return new Complex(lowerLeftCorner.re() + x * delta, lowerLeftCorner.im() + y * delta);
    }

    @Override
    public int[] get() {
        int feld [] = new int[size*size];
        for(int x = 0; x < size; x ++) {
            for (int y = 0; y < size; y++) {
                feld[x + size * y] = colorConverter.convert(function.apply(generateComplexNumberFromPixel(x,y)));
            }
        }
        return  feld;
    }

    @Override
    public PixelSupplier zoomFromPixels(final int x, final int y, final int x2, final int y2) {
        final Complex upperRightCorner = generateComplexNumberFromPixel(x,y);
        final Complex lowerLeftCorner = generateComplexNumberFromPixel(x2,y2);
        final double hoehe = upperRightCorner.im() - lowerLeftCorner.im();
        final double breite = upperRightCorner.re() - lowerLeftCorner.re();
        return new PixelSupplierComplexSurfaceToPixelSurfaceImpl(lowerLeftCorner,breite > hoehe? breite:hoehe, size);
    }
}
