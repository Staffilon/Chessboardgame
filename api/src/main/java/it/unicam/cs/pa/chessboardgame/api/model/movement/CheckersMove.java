package it.unicam.cs.pa.chessboardgame.api.model.movement;

import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersSquare;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPiece;

/**
 * The CheckersMove class, which represent the move of a CheckersPiece on a CheckersSquare.
 */
public record CheckersMove(CheckersSquare from,
                           CheckersSquare to) implements Move<CheckersPosition, CheckersPiece, CheckersSquare> {
    /**
     * Instantiates a new CheckersMove.
     *
     * @param from the square where the move begins
     * @param to   the square where the piece should land
     */
    public CheckersMove(CheckersSquare from, CheckersSquare to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public int getMoveLength() {
        int rowDiff = Math.abs(to.getPosition().row() - from.getPosition().row());
        int colDiff = Math.abs(to.getPosition().column() - from.getPosition().column());
        if (!((rowDiff == 1 && colDiff == 1) || (rowDiff == 2 && colDiff == 2))) {
            return 0;
        }
        return rowDiff;
    }

    @Override
    public CheckersPiece getMovedPiece() {
        return from.getPiece();
    }
}