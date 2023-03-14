package it.unicam.cs.pa.chessboardgame.api.controller;


import it.unicam.cs.pa.chessboardgame.api.model.board.Board;
import it.unicam.cs.pa.chessboardgame.api.model.board.BoardPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.Square;
import it.unicam.cs.pa.chessboardgame.api.model.color.Color;
import it.unicam.cs.pa.chessboardgame.api.model.piece.Piece;

/**
 * The interface GameController, which creates an abstraction for the management of the execution of a chessboard game.
 *
 * @param <B> the position system used by the chessboard game
 * @param <P> the pieces used by the chessboard game
 * @param <S> the squares used by the board in the chessboard game
 * @param <T> the board used by the chessboard game
 */
public interface GameController<B extends BoardPosition, P extends Piece, S extends Square<B, P>, T extends Board<B, P, S>> {
    /**
     * Gets the name of a player by the player himself, by using the input.
     *
     * @param side the side that the player will be on     *
     * @return the string containing the name of the player
     */
    String getPlayerNameFromInput(Color side);

    /**
     * Makes a turn in the game for the given side.
     *
     * @param side the side which has to make a turn
     */
    void makeTurn(Color side);

    /**
     * Resets the game and the game state to the initial position.
     */
    void resetGame();

    /**
     * Exits the game.
     */
    void exitGame();

    /**
     * Returns the game state,
     *
     * @return the game state
     */
    boolean getGameState();
}