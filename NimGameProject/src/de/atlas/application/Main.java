package de.atlas.application;

import de.atlas.client.GameClient;
import de.atlas.game.AbstractGame;
import de.atlas.game.nim.NimGameImpl;
import de.atlas.game.nim.player.ComputerPlayer;
import de.atlas.game.nim.player.HumanPlayer;
import de.atlas.writer.ConsoleWriter;


public class Main {
    public static void main(String[] args) {
        AbstractGame game = new NimGameImpl(new ConsoleWriter());
        game.addPlayer(new HumanPlayer());
        game.addPlayer(new ComputerPlayer());
        GameClient client = new GameClient(game);
        client.run();
    }
}