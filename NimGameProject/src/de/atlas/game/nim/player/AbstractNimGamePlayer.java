package de.atlas.game.nim.player;

import de.atlas.game.player.AbstracGamePlayer;

public abstract class AbstractNimGamePlayer extends AbstracGamePlayer<Integer,Integer> {

    public AbstractNimGamePlayer() {
    }

    public AbstractNimGamePlayer(final String name) {
        super(name);
    }
}
