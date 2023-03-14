package it.unicam.cs.pa.chessboardgame.api.model.exceptions;

/**
 * The InvalidBoardPositionException is thrown when attempting to use an invalid
 * position on a board.
 */
public class InvalidBoardPositionException extends IllegalArgumentException {
    /**
     * Instantiates a new InvalidBoardPositionException with a custom message.
     *
     * @param message the message of the exception
     */
    public InvalidBoardPositionException(String message) {
        super(message);
    }

    /**
     * Instantiates a new InvalidBoardPositionException with a custom message and cause.
     *
     * @param message the message of the exception
     * @param cause   the cause of the exception
     */
    public InvalidBoardPositionException(String message, Throwable cause) {
        super(message, cause);
    }
}