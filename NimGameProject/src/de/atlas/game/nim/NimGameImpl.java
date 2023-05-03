package de.atlas.game.nim;

import de.atlas.game.AbstractGame;
import de.atlas.writer.Writer;

public class NimGameImpl extends AbstractGame<Integer, Integer> {


    public NimGameImpl(final Writer writer) {
        super(writer);
        setBoard(23);
    }


    protected boolean isTurnValid() {
        return getTurn() >= 1 && getTurn() <= 3;
    }
    protected void updateBoard() {
        setBoard(getBoard() - getTurn());
    }

    protected boolean isGameover() {
        return getBoard() < 1 || getPlayers().isEmpty();
    }
}
