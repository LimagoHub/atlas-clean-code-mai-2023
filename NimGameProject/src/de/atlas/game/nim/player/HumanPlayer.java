package de.atlas.game.nim.player;

import java.util.Scanner;

public class HumanPlayer extends AbstractNimGamePlayer{
    private Scanner scanner = new Scanner(System.in);
    public HumanPlayer() {
    }

    public HumanPlayer(final String name) {
        super(name);
    }


    @Override
    public Integer doTurn(final Integer stones) {
        System.out.println(String.format("Es gibt %s Steine. Bitte nehmen Sie 1, 2 oder 3!", stones));
        return scanner.nextInt();
    }
}
