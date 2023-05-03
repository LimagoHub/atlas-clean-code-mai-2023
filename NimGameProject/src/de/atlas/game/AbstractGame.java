package de.atlas.game;

import de.atlas.game.player.GamePlayer;
import de.atlas.writer.Writer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractGame<BOARD, TURN> implements Game{



    private final Writer writer;

    private BOARD board;
    private TURN turn;

    public AbstractGame(final Writer writer) {
        this.writer = writer;
    }

    protected BOARD getBoard() {
        return board;
    }

    protected void setBoard(final BOARD board) {
        this.board = board;
    }

    protected TURN getTurn() {
        return turn;
    }

    protected void setTurn(final TURN turn) {
        this.turn = turn;
    }

    private GamePlayer<BOARD,TURN> currentPlayer;

    private List<GamePlayer<BOARD,TURN>> players = new ArrayList<>();


    /**
     *
     * @param player
     */
    public void addPlayer(GamePlayer<BOARD,TURN> player) {
        players.add(player); // nicht formaler Kommentar
    }

    public void removePlayer(GamePlayer<BOARD,TURN> player) {
        players.remove(player);
    }

    protected GamePlayer<BOARD, TURN> getCurrentPlayer() {
        return currentPlayer;
    }

    private void setCurrentPlayer(final GamePlayer<BOARD, TURN> currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    protected List<GamePlayer<BOARD, TURN>> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    @Override
    public void play() {
        while(! isGameover()) playSingleRound();

    }

    private void playSingleRound() {// Integration
        for(GamePlayer<BOARD, TURN> player: players) prepareSingleTurn(player);

    }

    private void prepareSingleTurn(final GamePlayer<BOARD, TURN> player) {
        // TODO irgendwas wichtiges
        setCurrentPlayer(player);
        playSingleTurnWithCurrentPlayer();
    }

    private void playSingleTurnWithCurrentPlayer() {
        if(isGameover()) return;
        executeTurn();
        terminateTurn();
    }

    private void executeTurn() {
        do turn = getCurrentPlayer().doTurn(board); while(turnIsInvalid());

    }

    private void terminateTurn( ) { // Integration (ruft Unterprogramme auf)
        updateBoard();
        printGameOverMessageIfGameIsOver();
    }

    private boolean turnIsInvalid() {
        if(!isTurnValid()) {
            write("Ungueltiger Zug.");
            return true;
        }
        return false;
    }
    private void printGameOverMessageIfGameIsOver() { // Operation
        if(isGameover()) write(String.format("%s hat verloren", currentPlayer.getName()));

    }

    protected void write(String message) {
        writer.write(message);
    }

    protected abstract boolean isTurnValid();
    protected abstract void updateBoard() ;

    protected abstract boolean isGameover();
}
