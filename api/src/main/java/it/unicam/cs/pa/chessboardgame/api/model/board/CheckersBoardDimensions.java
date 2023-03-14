package it.unicam.cs.pa.chessboardgame.api.model.board;

import it.unicam.cs.pa.chessboardgame.api.model.exceptions.InvalidBoardDimensionsException;

public record CheckersBoardDimensions(int columns, int rows) implements BoardDimensions {
    public CheckersBoardDimensions {
        validateDimensions(columns, rows);
    }

    private void validateDimensions(int columns, int rows) {
        if (columns <= 0 || rows <= 0 || columns > 8 || rows > 8) {
            throw new InvalidBoardDimensionsException("Board dimensions must be within bounds!");
        }
    }
}