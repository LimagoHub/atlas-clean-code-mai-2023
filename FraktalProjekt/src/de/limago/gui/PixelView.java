package de.limago.gui;

import de.limago.gui.presenter.PixelViewPresenter;



public interface PixelView {

    void setPresenter(final PixelViewPresenter presenter);
    PixelViewPresenter getPresenter();
    void setPixels(int [] feld);



    void show();
    void close();

    int getPixelCount();
}
