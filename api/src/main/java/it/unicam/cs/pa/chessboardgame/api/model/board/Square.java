package it.unicam.cs.pa.chessboardgame.api.model.board;

import it.unicam.cs.pa.chessboardgame.api.model.color.Color;
import it.unicam.cs.pa.chessboardgame.api.model.piece.Piece;

/**
 * The interface Square creates an abstraction for the representation of the squares
 * in board games. It's parameter extends BoardPosition, in order to be able to handle
 * all sorts of boards.
 *
 * @param <B> the type parameter for the type of pieces allowed on the square
 * @param <P> the type parameter for the type of position used by the square
 */
public interface Square<B extends BoardPosition, P extends Piece> {

    /**
     * Returns the piece present on the square.
     *
     * @return the piece present on the square
     */
    P getPiece();

    /**
     * Sets the given piece on the square.
     *
     * @param piece the piece to be set on the square
     */
    void setPiece(P piece);

    /**
     * Returns the position of the square on the board.
     *
     * @return the position of the square on the board
     */
    B getPosition();

    /**
     * Checks if the square is occupied by a piece. It returns
     * true if the square is occupied false otherwise
     *
     * @return the boolean
     */
    boolean isOccupied();

    /**
     * Clears the square of any piece present on it.
     */
    void clear();

    /**
     * Returns the color of the square
     *
     * @return the color of the square
     */
    Color getColor();
}