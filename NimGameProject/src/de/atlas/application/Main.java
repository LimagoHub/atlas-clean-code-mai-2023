package de.atlas.application;

import de.atlas.client.GameClient;
import de.atlas.game.Game;
import de.atlas.game.nim.NimGameImpl;

public class Main {
    public static void main(String[] args) {
        Game game = new NimGameImpl();
        GameClient client = new GameClient(game);
        client.run();
    }
}