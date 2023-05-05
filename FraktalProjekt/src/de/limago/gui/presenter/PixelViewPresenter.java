package de.limago.gui.presenter;

import de.limago.gui.PixelView;

public interface PixelViewPresenter {



    PixelView getPixelView();

    void setPixelView(final PixelView pixelView);

    void updateView();
    void onZoomIn(int x, int y, int x2, int y2 );
    void onZoomOut( );


}
