package it.unicam.cs.pa.chessboardgame.api.model.rules;

import it.unicam.cs.pa.chessboardgame.api.model.board.Board;
import it.unicam.cs.pa.chessboardgame.api.model.board.BoardPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.Square;
import it.unicam.cs.pa.chessboardgame.api.model.movement.MovementManager;
import it.unicam.cs.pa.chessboardgame.api.model.piece.Piece;

/**
 * The interface CheckGameRules, which creates an abstraction for the handling of the rules involving the check mechanic
 * in chessboard games.
 *
 * @param <B> the position system used by the chessboard game
 * @param <P> the pieces used by the chessboard game
 * @param <S> the squares used by the board in the chessboard game
 * @param <T> the board used by the chessboard game
 * @param <M> the movement manager used to in the chessboard game
 */
public interface CheckGameRules<B extends BoardPosition, P extends Piece, S extends Square<B, P>,
        T extends Board<B, P, S>, M extends MovementManager<B, P, S, T>> {
    /**
     * Is check boolean.
     *
     * @param board           the board
     * @param movementManager the movement manager
     * @return the boolean
     */
    boolean isCheck(T board, M movementManager);

    /**
     * Is check mate boolean.
     *
     * @param board           the board
     * @param movementManager the movement manager
     * @return the boolean
     */
    boolean isCheckMate(T board, M movementManager);
}