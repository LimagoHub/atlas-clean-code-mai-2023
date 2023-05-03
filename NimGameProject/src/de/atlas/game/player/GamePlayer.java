package de.atlas.game.player;

public interface GamePlayer<BOARD,TURN> {
    String getName();
    TURN doTurn(final BOARD board);
}
