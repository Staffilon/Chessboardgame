package it.unicam.cs.pa.chessboardgame.api.model.exceptions;

/**
 * The InvalidPositionNameException is thrown when attempting to use an invalid name
 * for a BoardPosition.
 */
public class InvalidPositionNameException extends IllegalArgumentException {
    /**
     * Instantiates a new InvalidPositionNameException with a custom message.
     *
     * @param message the message of the exception
     */
    public InvalidPositionNameException(String message) {
        super(message);
    }
}