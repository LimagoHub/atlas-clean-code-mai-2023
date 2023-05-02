package de.atlas.game.nim;

import de.atlas.game.Game;

import java.util.Scanner;

public class NimGameImpl implements Game {
    private Scanner scanner = new Scanner(System.in);
    private int stones;
    private boolean gameover;

    public NimGameImpl() {
        stones = 23;
        gameover = false;
    }

    @Override
    public void play() {
        while(! gameover) {
            playSingleRound();
        }
    }

    private void playSingleRound() {
        humanTurn();
        computerTurn();
    }

    private void humanTurn() {
        int turn;

        while(true) {
            System.out.println(String.format("Es gibt %s Steine. Bitte nehmen Sie 1, 2 oder 3!", stones));
            turn = scanner.nextInt();
            if(turn >= 1 && turn <= 3) break;
            System.out.println("Ungueltiger Zug.");
        }
        stones -= turn;
    }

    private void computerTurn() {
        final int turns[] = {3,1,1,2};

        if(stones < 1) {
            System.out.println("Du Loser");
            gameover = true;
            return;
        }
        if(stones == 1) {
            System.out.println("Du hast nur Glueck gehabt");
            gameover = true;
            return;
        }

        int turn = turns[stones % 4];
        System.out.println(String.format("Computer nimmt %s Steine.", turn));
        stones -= turn;
    }
}
