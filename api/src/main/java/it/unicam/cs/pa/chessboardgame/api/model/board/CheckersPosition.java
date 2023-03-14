package it.unicam.cs.pa.chessboardgame.api.model.board;

import it.unicam.cs.pa.chessboardgame.api.model.exceptions.InvalidBoardPositionException;

/**
 * The CheckersPosition class represents a position on the checkers board.
 * It implements the {@link BoardPosition} interface, providing methods to access
 * the row and column of the position.
 */
public record CheckersPosition(int row, int column) implements BoardPosition {
    /**
     * Instantiates a new CheckersPosition.
     *
     * @param row    the row
     * @param column the column
     */
    public CheckersPosition {
        if (row < 0 || column < 0 || row > 7 || column > 7) {
            throw new InvalidBoardPositionException("Row and column must be with bounds");
        }
    }

    @Override
    public String getName() {
        return Character.toString('A' + column) + (8 - row);
    }
}