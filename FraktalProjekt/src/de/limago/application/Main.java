package de.limago.application;

import de.limago.common.BenchmarkProxy;
import de.limago.gui.PixelView;
import de.limago.gui.PixelViewImpl;
import de.limago.gui.colors.MandelbrotColorConverter;
import de.limago.gui.presenter.PixelViewPresenter;
import de.limago.gui.presenter.PixelViewPresenterImpl;

import de.limago.math.Complex;
import de.limago.service.MandelbrotFunction;
import de.limago.service.PixelSupplier;
import de.limago.service.PixelSupplierComplexSurfaceToPixelSurfaceImpl;

public class Main {
    public static void main(String[] args) {

        PixelSupplier supplier = PixelSupplierComplexSurfaceToPixelSurfaceImpl
                .builder()
                .pixelSize(500)
                .width(2.5)
                .lowerLeftCorner(new Complex(-2.0, -1.25))
                .colorConverter(new MandelbrotColorConverter())
                .function(new MandelbrotFunction())
                .build();

        supplier = (PixelSupplier) BenchmarkProxy.newInstance(supplier);

        PixelView view = new PixelViewImpl();
        PixelViewPresenter presenter = new PixelViewPresenterImpl(supplier);

        view.setPresenter(presenter);
        presenter.setPixelView(view);

        presenter.updateView();
        view.show();
    }
}