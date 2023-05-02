package de.atlas.client;

import de.atlas.game.Game;

public class GameClient {

    private final Game game;

    public GameClient(final Game game) {
        this.game = game;
    }

    public void run() {
        game.play();
    }
}
