package it.unicam.cs.pa.chessboardgame.api.model.board;

import it.unicam.cs.pa.chessboardgame.api.model.color.CheckersColor;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPiece;

/**
 * The CheckersSquare class is responsible for the representation of a square on a checkers board.
 */
public class CheckersSquare implements Square<CheckersPosition, CheckersPiece> {
    private final CheckersColor color;
    private final CheckersPosition position;
    private CheckersPiece piece;

    /**
     * Instantiates a new Checkers square with the given position.
     *
     * @param position the position of the square
     */
    public CheckersSquare(CheckersPosition position) {
        this.position = position;
        color = null;
    }

    /**
     * Instantiates a new Checkers square with the given position and color.
     *
     * @param position the checkers square position
     * @param color    the color of the square
     */
    public CheckersSquare(CheckersPosition position, CheckersColor color) {
        this.position = position;
        this.piece = null;
        this.color = color;
    }

    /**
     * Instantiates a new Checkers square with the given position, color and piece.
     *
     * @param piece    the piece on the square
     * @param position the checkers square position
     * @param color    the color of the square
     */
    public CheckersSquare(CheckersPiece piece, CheckersPosition position, CheckersColor color) {
        this.piece = piece;
        this.position = position;
        this.color = color;
    }

    public CheckersColor getColor() {
        return color;
    }

    @Override
    public CheckersPiece getPiece() {
        return piece;
    }

    @Override
    public void setPiece(CheckersPiece piece) {
        this.piece = piece;
    }

    @Override
    public CheckersPosition getPosition() {
        return position;
    }

    /**
     * Checks whether a piece is currently on the square.
     *
     * @return true if a piece is present on the square, false otherwise
     */
    @Override
    public boolean isOccupied() {
        return piece != null;
    }

    /**
     * Clears the square of any piece present on it.
     */
    @Override
    public void clear() {
        piece = null;
    }
}