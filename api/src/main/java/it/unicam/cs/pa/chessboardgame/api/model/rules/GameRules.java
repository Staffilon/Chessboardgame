package it.unicam.cs.pa.chessboardgame.api.model.rules;

import it.unicam.cs.pa.chessboardgame.api.model.board.Board;
import it.unicam.cs.pa.chessboardgame.api.model.board.BoardPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.Square;
import it.unicam.cs.pa.chessboardgame.api.model.color.Color;
import it.unicam.cs.pa.chessboardgame.api.model.piece.Piece;

/**
 * The interface GameRules, which creates an abstraction for the management of the game rules of a chessboard game.
 *
 * @param <B> the position system used by the chessboard game
 * @param <P> the pieces used by the chessboard game
 * @param <S> the squares used by the board in the chessboard game
 * @param <T> the board used by the chessboard game
 */
public interface GameRules<B extends BoardPosition, P extends Piece, S extends Square<B, P>,
        T extends Board<B, P, S>> {
    /**
     * Returns the color of the side that has won, if no side has won it returns null.
     *
     * @param board the board on which the game is being played
     * @return the color of the winner's side, null otherwise
     */
    Color getWinnerSide(T board);

    /**
     * Checks whether the game resulted in a draw and returns true if it did, false otherwise.
     *
     * @param board the board on which the game is being played
     * @return true if the game resulted in a draw, false otherwise
     */
    boolean isDraw(T board);
}