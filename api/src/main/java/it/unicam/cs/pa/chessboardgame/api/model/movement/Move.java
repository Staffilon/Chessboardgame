package it.unicam.cs.pa.chessboardgame.api.model.movement;

import it.unicam.cs.pa.chessboardgame.api.model.board.BoardPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.Square;
import it.unicam.cs.pa.chessboardgame.api.model.piece.Piece;

/**
 * The interface Move, which provides an abstraction for the representation of a move on the board.
 *
 * @param <B> the type parameter of a position on the board
 * @param <P> the type parameter of a piece on the board
 * @param <S> the type parameter of a square on the board
 */
public interface Move<B extends BoardPosition, P extends Piece, S extends Square<B, P>> {
    /**
     * Returns the square on the board where the piece begins the movement
     *
     * @return the square where the piece begins the movement
     */
    S from();

    /**
     * Returns the square on the board where the piece lands.
     *
     * @return the square where the piece should land
     */
    S to();

    /**
     * Returns the length of the move in terms of squares traveled
     *
     * @return the length of the move
     */
    int getMoveLength();

    /**
     * Returns the piece that is being moved during the move
     *
     * @return the moved piece
     */
    P getMovedPiece();
}