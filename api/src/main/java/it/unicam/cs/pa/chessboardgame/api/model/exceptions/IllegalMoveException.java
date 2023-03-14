package it.unicam.cs.pa.chessboardgame.api.model.exceptions;

/**
 * The IllegalMoveException, which is thrown when an illegal move is being attempted.
 */
public class IllegalMoveException extends IllegalArgumentException {
    /**
     * Instantiates a new IllegalMoveException with a custom message.
     *
     * @param s the message of the exception
     */
    public IllegalMoveException(String s) {
        super(s);
    }

    /**
     * Instantiates a new IllegalMoveException with a custom message and the cause of the exception.
     *
     * @param message the message of the exception
     * @param cause   the cause representing the other exception
     */
    public IllegalMoveException(String message, Throwable cause) {
        super(message, cause);
    }
}