package de.atlas.game.nim;

import de.atlas.game.Game;

import java.util.Scanner;

public class NimGameImpl implements Game {
    private Scanner scanner = new Scanner(System.in);
    private int stones;
    private int turn;

    public NimGameImpl() {
        stones = 23;

    }

    @Override
    public void play() {
        while(! isGameover()) {
            playSingleRound();
        }
    }

    private void playSingleRound() {
        humanTurn();
        computerTurn();
    }

    private void humanTurn() {
        if(isGameover()) return;
        executeTurn();
        terminateTurn("Mensch");
    }

    private void executeTurn() {
        while(true) {
            write(String.format("Es gibt %s Steine. Bitte nehmen Sie 1, 2 oder 3!", stones));
            turn = scanner.nextInt();
            if(turn >= 1 && turn <= 3) break;
            write("Ungueltiger Zug.");
        }
    }

    private void computerTurn() {
        if(isGameover()) return;
        final int turns[] = {3,1,1,2};

        turn = turns[stones % 4];
        write(String.format("Computer nimmt %s Steine.", turn));
        terminateTurn( "Computer");
    }

    private void terminateTurn( String playerName) { // Integration (ruft Unterprogramme auf)
        updateBoard();
        printGameOverMessageIfGameIsOver(playerName);
    }

    private void printGameOverMessageIfGameIsOver(final String playerName) { // Operation
        if(isGameover()) {
           write(String.format("%s hat verloren", playerName));
        }
    }

    private void write(String message) {
        System.out.println(message);
    }

    private void updateBoard() {
        stones -= turn;
    }

    private boolean isGameover() {
        return stones < 1;
    }
}
