package it.unicam.cs.pa.chessboardgame.api.model.movement;

import it.unicam.cs.pa.chessboardgame.api.model.board.Board;
import it.unicam.cs.pa.chessboardgame.api.model.board.BoardPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.Square;
import it.unicam.cs.pa.chessboardgame.api.model.piece.Piece;

/**
 * The interface PieceCaptureMovement, which creates an abstraction for the handling of the movement in the chessboard games involving
 * captures.
 *
 * @param <B> the position system used by the board game
 * @param <P> the pieces which will populate the board
 * @param <S> the squares used by the board
 * @param <T> the board on which the game will be played
 */
public interface PieceCaptureMovement<B extends BoardPosition, P extends Piece, S extends Square<B, P>,
        T extends Board<B, P, S>> {
    /**
     * Checks whether the given move on the board is a move that involves the capture of a piece or not.
     *
     * @param board the board on which the move is being made
     * @param move  the move that is being checked
     * @return true is the move involves the capture of a piece, false otherwise
     */
    boolean isCaptureMove(T board, Move<B, P, S> move);

    /**
     * Checks whether a capture move can be made from a given square.
     *
     * @param board  the board on which the square is positioned
     * @param square the square we're checking
     * @return true if a capture move can be made from a given square, false otherwise
     */
    boolean canCaptureFromSquare(T board, S square);
}