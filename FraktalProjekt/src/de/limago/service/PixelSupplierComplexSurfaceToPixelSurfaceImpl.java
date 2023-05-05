package de.limago.service;

import de.limago.gui.colors.ColorConverter;
import de.limago.gui.colors.PendelColorConverter;
import de.limago.math.Complex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PixelSupplierComplexSurfaceToPixelSurfaceImpl implements PixelSupplier{

    private Complex lowerLeftCorner = new Complex(-2.0, -2.0);
    private double width = 4;

    private int size=500;

    private ColorConverter colorConverter = new PendelColorConverter(); // Ganz doof
    private ComplexToIntFunction function = new PendelFunction(); // Ganz doof

    private PixelSupplierComplexSurfaceToPixelSurfaceImpl() {
    }

    private Complex generateComplexNumberFromPixel(int x, int y) {
        double delta = width /  size;
        return new Complex(lowerLeftCorner.re() + x * delta, lowerLeftCorner.im() + y * delta);
    }

    @Override
    public int[] get() {
        try {
            return getImpl();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    private int[] getImpl() throws InterruptedException {
        int [] feld = new int[size*size];
        calculatePixelsParallel(feld);
        return feld;
    }

    private void calculatePixelsParallel(final int[] feld) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int x = 0; x < size; x ++) {
            createColumnsParallel(service, feld, x);
        }
        service.shutdown();
        service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
    }

    private void createColumnsParallel(final ExecutorService service, final int[] feld, final int x) {
        service.execute(new PixelColumnCreator(feld,x));
    }

    @Override
    public PixelSupplier zoomFromPixels(final int x, final int y, final int x2, final int y2) {
        final Complex upperRightCorner = generateComplexNumberFromPixel(x2,y2);
        final Complex lowerLeftCornerLocal = generateComplexNumberFromPixel(x,y);
        final double hoehe = upperRightCorner.im() - lowerLeftCornerLocal.im();
        final double breite = upperRightCorner.re() - lowerLeftCornerLocal.re();
        return PixelSupplierComplexSurfaceToPixelSurfaceImpl
                .builder()
                .pixelSize(size)
                .width(hoehe > breite? hoehe : breite)
                .lowerLeftCorner(lowerLeftCornerLocal)
                .colorConverter(colorConverter)
                .function(function)
                .build();
    }

    public static PixelSupplierComplexSurfaceToPixelSurfaceImplBuilder builder() {
        return new PixelSupplierComplexSurfaceToPixelSurfaceImplBuilder();
    }

    public static class PixelSupplierComplexSurfaceToPixelSurfaceImplBuilder {
        private PixelSupplierComplexSurfaceToPixelSurfaceImpl instance = new PixelSupplierComplexSurfaceToPixelSurfaceImpl();



        public PixelSupplierComplexSurfaceToPixelSurfaceImplBuilder lowerLeftCorner(Complex lowerLeftCorner) {
            instance.lowerLeftCorner = lowerLeftCorner;
            return this;
        }
        public PixelSupplierComplexSurfaceToPixelSurfaceImplBuilder width(double width) {
            instance.width = width;
            return this;
        }

        public PixelSupplierComplexSurfaceToPixelSurfaceImplBuilder pixelSize(int pixelSize) {
            instance.size = pixelSize;
            return this;
        }

        public PixelSupplierComplexSurfaceToPixelSurfaceImplBuilder colorConverter(ColorConverter colorConverter) {
            instance.colorConverter = colorConverter;
            return this;
        }

        public PixelSupplierComplexSurfaceToPixelSurfaceImplBuilder function(ComplexToIntFunction function) {
            instance.function = function;
            return this;
        }

        public PixelSupplierComplexSurfaceToPixelSurfaceImpl build() {
             // Validate
            return instance;
        }

    }

    class PixelColumnCreator implements Runnable {

        private final int [] feld;
        private final int x;

        public PixelColumnCreator(final int[] feld, final int x) {
            this.feld = feld;
            this.x = x;
        }

        @Override
        public void run() {
            for (int y = 0; y < size; y++) {
                feld[x + size * y] = colorConverter.convert(function.apply(generateComplexNumberFromPixel(x,y)));
            }
        }
    }
}
