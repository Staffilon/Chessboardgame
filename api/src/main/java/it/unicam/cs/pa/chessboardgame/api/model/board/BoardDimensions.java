package it.unicam.cs.pa.chessboardgame.api.model.board;

/**
 * The BoardDimensions interface creates an abstraction for the representation the dimensions of a board.
 * It provides methods to get the number of rows and columns in the board.
 */
public interface BoardDimensions {
    /**
     * Returns the number of rows in the board.
     *
     * @return the number of rows in the board
     */
    int rows();

    /**
     * Returns the number of columns in the board.
     *
     * @return the number of columns in the board
     */
    int columns();
}