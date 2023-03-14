package it.unicam.cs.pa.chessboardgame.api.model.exceptions;

/**
 * The PieceAlreadyPromotedException is thrown when attempting to promote
 * an already promoted piece.
 */
public class PieceAlreadyPromotedException extends RuntimeException {
    /**
     * Instantiates a new PieceAlreadyPromotedException with a custom message.
     *
     * @param message the message of the exception
     */
    public PieceAlreadyPromotedException(String message) {
        super(message);
    }
}