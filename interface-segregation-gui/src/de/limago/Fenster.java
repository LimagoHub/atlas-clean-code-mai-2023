package de.limago;

import java.awt.*;
import java.awt.event.*;


public class Fenster extends Frame   {


    public Fenster()  {

        setSize(300, 300);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
              beenden();

            }
        });
        Button button = new Button("Drück mich");
        button.addActionListener(e->ausgabe());
        add(button);
    }

    public static void main(String[] args) {
	    new Fenster().setVisible(true);
    }

    private void ausgabe() {
        System.out.println("Button wurde gedrückt");
    }

    private void beenden() {
        // speichern
        dispose();
    }


}
