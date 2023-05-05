package de.limago.gui.presenter;

import de.limago.gui.PixelView;
import de.limago.service.PixelSupplier;

import java.util.ArrayDeque;
import java.util.Deque;

public class PixelViewPresenterImpl implements PixelViewPresenter{

    private final Deque<PixelSupplier> supplierStack = new ArrayDeque<>();
    private PixelSupplier supplier ;
    private PixelView view;

    public PixelViewPresenterImpl(final PixelSupplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public PixelView getPixelView() {
        return view;
    }

    @Override
    public void setPixelView(final PixelView pixelView) {
        this.view = pixelView;
    }

    @Override
    public void updateView() {

        view.setPixels(supplier.get());
    }

    @Override
    public void onZoomIn(final int x, final int y, final int x2, final int y2) {
        supplierStack.push(supplier);
        supplier = supplier.zoomFromPixels(x,y,x2,y2);
        updateView();
    }

    @Override
    public void onZoomOut() {
        if( ! supplierStack.isEmpty()) {
            supplier = supplierStack.pop();
        }
        updateView();
    }
}
