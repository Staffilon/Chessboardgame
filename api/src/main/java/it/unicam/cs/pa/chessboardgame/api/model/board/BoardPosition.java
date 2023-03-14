package it.unicam.cs.pa.chessboardgame.api.model.board;

/**
 * The BoardPosition interface represents a position on a board,
 * defined by a row and a column.
 */
public interface BoardPosition {

    /**
     * Returns the row of the position on the board
     *
     * @return the row of the position on the board
     */
    int row();

    /**
     * Returns the column of the position on the board
     *
     * @return the column of the position
     */
    int column();

    /**
     * Returns the name of the position on the board.
     *
     * @return the name of the position
     */
    String getName();

    /**
     * Returns a string representation of the position on the board
     *
     * @return the string representation of the position
     */
    @Override
    String toString();

    /**
     * Determines whether the specified object is equal to this position on the board
     *
     * @param o the object to compare to this position
     * @return true if the object is equal to this position, false otherwise
     */
    @Override
    boolean equals(Object o);

    /**
     * Returns a hash code for this position on the board
     *
     * @return a hash code for this position
     */
    @Override
    int hashCode();
}