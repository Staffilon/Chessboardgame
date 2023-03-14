package it.unicam.cs.pa.chessboardgame.api.model.piece;

import it.unicam.cs.pa.chessboardgame.api.model.color.Color;

/**
 * The Piece interface represents the core elements of a piece,
 * his color and the type of the piece.
 */
public interface Piece {
    /**
     * Returns the color of the piece.
     *
     * @return the color of the piece
     */
    Color getColor();

    /**
     * returns the type of the piece.
     *
     * @return the piece of the piece
     */
    PieceType getPieceType();
}