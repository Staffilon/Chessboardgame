package it.unicam.cs.pa.chessboardgame.api.model.movement;

import it.unicam.cs.pa.chessboardgame.api.model.board.Board;
import it.unicam.cs.pa.chessboardgame.api.model.board.Square;
import it.unicam.cs.pa.chessboardgame.api.model.piece.Piece;
import it.unicam.cs.pa.chessboardgame.api.model.board.BoardPosition;

/**
 * The interface CheckersPieceMovement, which extends the interfaces {@link PieceMovement} and {@link PieceCaptureMovement}
 * in order to represent the movement of a checkers piece.
 *
 * @param <B> the position system used by the board game
 * @param <P> the pieces which will populate the board
 * @param <S> the squares used by the board
 * @param <T> the board on which the game will be played
 */
public interface CheckersPieceMovement<B extends BoardPosition, P extends Piece, S extends Square<B, P>, T extends Board<B, P, S>>
        extends PieceMovement<B, P, S, T>, PieceCaptureMovement<B, P, S, T> {
}