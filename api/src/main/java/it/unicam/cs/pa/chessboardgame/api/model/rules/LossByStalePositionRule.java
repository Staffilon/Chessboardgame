package it.unicam.cs.pa.chessboardgame.api.model.rules;

import it.unicam.cs.pa.chessboardgame.api.model.board.Board;
import it.unicam.cs.pa.chessboardgame.api.model.board.BoardPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.Square;
import it.unicam.cs.pa.chessboardgame.api.model.color.Color;
import it.unicam.cs.pa.chessboardgame.api.model.piece.Piece;

/**
 * The interface LossByStalePositionRule, which creates an abstraction for the management of the rule involving the loss by
 * the absence of valid modes to be made by a side during its turn.
 *
 * @param <B> the position system used by the chessboard game
 * @param <P> the pieces used by the chessboard game
 * @param <S> the squares used by the board in the chessboard game
 * @param <T> the board used by the chessboard game
 */
public interface LossByStalePositionRule<B extends BoardPosition, P extends Piece, S extends Square<B, P>,
        T extends Board<B, P, S>> {
    /**
     * Check whether the player whose current turn has valid moves.
     * If no valid moves are available, the other side wins.
     *
     * @param board the board on which the game is currently being played
     * @param turn  the turn of the player whose valid moves are being checked
     * @return true if the player has no moves available, false otherwise
     */
    boolean isLossByStalePosition(T board, Color turn);
}