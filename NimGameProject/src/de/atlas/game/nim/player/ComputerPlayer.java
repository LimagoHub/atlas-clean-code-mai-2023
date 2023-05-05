package de.atlas.game.nim.player;

public class ComputerPlayer extends AbstractNimGamePlayer{

    private static final int[] TURNS = {3,1,1,2};
    public ComputerPlayer() {
    }

    public ComputerPlayer(final String name) {
        super(name);
    }


    @Override
    public Integer doTurn(final Integer stones) {
        final int turn = TURNS[stones % 4];
        System.out.println(String.format("Computer nimmt %s Steine.", turn));

        return turn;
    }
}
