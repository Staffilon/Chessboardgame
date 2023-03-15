package it.unicam.cs.pa.chessboardgame.api.model.movement;

import it.unicam.cs.pa.chessboardgame.api.model.board.Board;
import it.unicam.cs.pa.chessboardgame.api.model.board.BoardPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.Square;
import it.unicam.cs.pa.chessboardgame.api.model.color.Color;
import it.unicam.cs.pa.chessboardgame.api.model.piece.Piece;

/**
 * The interface MoveExecutor, which creates an abstraction for the management of the execution of moves.
 *
 * @param <B> the position system used by the chessboard game
 * @param <P> the pieces used by the chessboard game
 * @param <S> the squares used by the board in the chessboard game
 * @param <M> the type of moves that the chessboard game uses
 */
public interface MoveExecutor<B extends BoardPosition, P extends Piece, S extends Square<B, P>,
        M extends Move<B, P, S>> {
    /**
     * Makes a move on the board for the given side.
     *
     * @param move  the move which is going to be made
     * @param side  the side of the player that wants to make the move
     */
    void makeMove(M move, Color side);
}