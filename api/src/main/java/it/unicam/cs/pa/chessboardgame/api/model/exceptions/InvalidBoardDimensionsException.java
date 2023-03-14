package it.unicam.cs.pa.chessboardgame.api.model.exceptions;

/**
 * The InvalidBoardDimensionsException is thrown when attempting to pass invalid
 * dimensions to a board.
 */
public class InvalidBoardDimensionsException extends IllegalArgumentException {
    /**
     * Instantiates a new InvalidBoardDimensionsException with a custom message.
     *
     * @param message the message of the exception
     */
    public InvalidBoardDimensionsException(String message) {
        super(message);
    }
}