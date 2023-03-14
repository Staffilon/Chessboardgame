package it.unicam.cs.pa.chessboardgame.api.model.piece;

/**
 * The Promotable interface represents the core functionalities of a piece
 * that can be promoted.
 */
public interface Promotable {

    /**
     * Promotes the current piece to the new piece type.
     *
     * @param newPieceType the new piece type
     */
    void promote(PieceType newPieceType);

    /**
     * Determines whether the piece has been promoted.
     *
     * @return true if the piece has been promoted, false otherwise
     */
    boolean hasBeenPromoted();
}