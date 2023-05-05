package de.limago.gui;

import de.limago.gui.presenter.PixelViewPresenter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PixelViewImpl implements PixelView {
    private static final int SIZE = 500;
    private final BufferedImage image = new BufferedImage(SIZE,SIZE, BufferedImage.TYPE_INT_RGB);
    private PixelViewPresenter presenter;

    private Frame window = new Frame() {
        @Override
        public void paint(final Graphics g) {
            g.drawImage(image,0,0,this);
        }
    };

    public PixelViewImpl() {
        window.addMouseListener(new MyMouseListener());
        window.setSize(SIZE,SIZE);
        window.setResizable(false);
    }

    @Override
    public void setPresenter(final PixelViewPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public PixelViewPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void setPixels(final int[] feld) {

        assert feld.length == SIZE * SIZE;
        image.setRGB(0,0,SIZE,SIZE, feld,0, SIZE);
        window.repaint();
    }

    @Override
    public void show() {
        window.setVisible(true);
    }

    @Override
    public void close() {
        window.dispose();
    }

    @Override
    public int getPixelCount() {
        return SIZE;
    }

    class MyMouseListener extends MouseAdapter {
        int startX;
        int startY;

        @Override
        public void mousePressed(final MouseEvent e) {
            startX = e.getX();
            startY = e.getY();
        }

        @Override
        public void mouseReleased(final MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON1) {
                presenter.onZoomIn(startX,startY,e.getX(),e.getY());

            } else {
                presenter.onZoomOut();
            }

        }
    }
}
