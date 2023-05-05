package de.limago.service;

import java.util.function.Supplier;

public interface PixelSupplier  {

    int [] get();

    PixelSupplier zoomFromPixels(int x, int y, int x2, int y2);
}
