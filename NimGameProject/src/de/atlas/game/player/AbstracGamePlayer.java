package de.atlas.game.player;



public abstract class AbstracGamePlayer<BOARD,TURN> implements GamePlayer<BOARD,TURN> {

    private String name = getClass().getSimpleName();

    public AbstracGamePlayer() {
    }

    public AbstracGamePlayer(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
