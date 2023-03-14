package it.unicam.cs.pa.chessboardgame.api.model.piece;

import it.unicam.cs.pa.chessboardgame.api.model.color.CheckersColor;
import it.unicam.cs.pa.chessboardgame.api.model.color.Color;
import it.unicam.cs.pa.chessboardgame.api.model.exceptions.PieceAlreadyPromotedException;

/**
 * The CheckersPiece class represents a checkers piece on the game board.
 */
public class CheckersPiece implements Piece, Promotable {
    private CheckersColor color;
    private CheckersPieceType pieceType;
    private boolean hasBeenPromoted;

    /**
     * Constructs a new checkers piece with the given color,
     * piece type, and promotion status.
     *
     * @param color     the color of the checkers piece
     * @param pieceType the type of the checkers piece
     */
    public CheckersPiece(CheckersColor color, CheckersPieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
        this.hasBeenPromoted = true;
    }

    /**
     * Instantiates a new "Man" Checkers piece with the given color.
     *
     * @param color the color
     */
    public CheckersPiece(CheckersColor color) {
        this.color = color;
        this.pieceType = CheckersPieceType.MAN;
        this.hasBeenPromoted = false;
    }

    @Override
    public Color getColor() {
        return color;
    }

    public void setColor(CheckersColor color) {
        this.color = color;
    }

    @Override
    public PieceType getPieceType() {
        return pieceType;
    }

    @Override
    public boolean hasBeenPromoted() {
        return hasBeenPromoted;
    }

    /**
     * Promotes the current checkers piece to a given new checkers type.
     * If the piece is already promoted, it will throw a PieceAlreadyPromotedException,
     * if the piece to be promoted into is not a valid checkers piece type it will throw a IllegalArgumentException.
     *
     * @param newPieceType the new piece type to be promoted to
     * @throws PieceAlreadyPromotedException if the piece has already been promoted
     * @throws IllegalArgumentException      if the given piece is not a valid checkers piece type
     */
    @Override
    public void promote(PieceType newPieceType) throws PieceAlreadyPromotedException,
            IllegalArgumentException, NullPointerException {
        if (hasBeenPromoted) {
            throw new PieceAlreadyPromotedException("checkers piece: " + this +
                    " is already promoted");
        }

        if (newPieceType == null) {
            throw new NullPointerException("The promotion type is null");
        }

        if (!(newPieceType instanceof CheckersPieceType)) {
            throw new IllegalArgumentException("Wrong piece type: " + newPieceType.getName() +
                    " has been given as promotion type!");
        }
        this.pieceType = (CheckersPieceType) newPieceType;
        this.hasBeenPromoted = true;
    }

    /**
     * Promotes the piece to the king checkers type.
     *
     * @throws PieceAlreadyPromotedException if the piece has already been promoted
     */
    public void promote() throws PieceAlreadyPromotedException {
        if (hasBeenPromoted) {
            throw new PieceAlreadyPromotedException("checkers piece: " + this +
                    " is already promoted");
        }
        this.pieceType = CheckersPieceType.KING;
        this.hasBeenPromoted = true;
    }
}