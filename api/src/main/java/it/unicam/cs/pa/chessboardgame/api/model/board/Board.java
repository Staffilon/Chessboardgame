package it.unicam.cs.pa.chessboardgame.api.model.board;

import it.unicam.cs.pa.chessboardgame.api.model.piece.Piece;

import java.util.Iterator;
import java.util.List;

/**
 * the interface Board creates an abstraction for the representation of a generic board, with
 * utility methods used to manage the pieces on the board.
 *
 * @param <B> the position system used on the board
 * @param <P> the pieces used on the board
 * @param <T> the squares used by the board
 */

public interface Board<B extends BoardPosition, P extends Piece, T extends Square<B, P>> {
    /**
     * Returns the square at the specified position on the board.
     *
     * @param position the position of the square to retrieve
     * @return the square at the specified position
     * @throws IllegalArgumentException if the position is invalid for this board
     */
    T getSquareAt(B position) throws IllegalArgumentException;

    /**
     * Returns the piece at the specified position on the board.
     *
     * @param position the position of the piece to retrieve
     * @return the piece at the specified position, or null if no piece is present
     * @throws IllegalArgumentException if the position is invalid for this board
     */
    P getPieceAt(B position) throws IllegalArgumentException;

    /**
     * Sets the specified piece at the specified position on the board.
     *
     * @param piece    the piece to be placed on the board
     * @param position the position where the piece will be placed
     * @throws IllegalArgumentException if the position is invalid for this board
     */
    void setPieceAt(P piece, B position) throws IllegalArgumentException;

    /**
     * Removes the piece at the specified position on the board.
     *
     * @param position the position of the piece to be removed
     * @return the removed piece, or null if no piece was present
     * @throws IllegalArgumentException if the position is invalid for this board
     */
    P removePieceAt(B position) throws IllegalArgumentException;

    /**
     * Returns whether the specified position on the board is occupied by a piece.
     *
     * @param position the position to check for occupation
     * @return true if the position is occupied by a piece, false otherwise
     * @throws IllegalArgumentException if the position is invalid for this board
     */
    boolean isOccupiedAt(B position) throws IllegalArgumentException;

    /**
     * Returns the dimensions of the board.
     *
     * @return the dimensions of the board
     */
    BoardDimensions getDimensions();

    /**
     * Returns a list with all the adjacent squares to the one given as argument
     *
     * @param square the square whose adjacent squares we want to get
     * @return the adjacent squares to the one given as argument
     */
    List<T> getAccessibleSquares(T square);

    /**
     * Returns an iterator over the pieces on the board.
     *
     * @return an iterator over the pieces on the board
     */
    Iterator<P> pieceIterator();
}