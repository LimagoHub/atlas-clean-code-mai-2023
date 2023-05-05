package de.limago.application;

import de.limago.gui.PixelView;
import de.limago.gui.PixelViewImpl;
import de.limago.gui.presenter.PixelViewPresenter;
import de.limago.gui.presenter.PixelViewPresenterImpl;

public class Main {
    public static void main(String[] args) {
        PixelView view = new PixelViewImpl();
        PixelViewPresenter presenter = new PixelViewPresenterImpl();

        view.setPresenter(presenter);
        presenter.setPixelView(view);

        presenter.updateView();
        view.show();
    }
}