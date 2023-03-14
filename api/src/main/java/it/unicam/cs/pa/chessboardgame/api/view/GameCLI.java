package it.unicam.cs.pa.chessboardgame.api.view;

import it.unicam.cs.pa.chessboardgame.api.model.board.Board;
import it.unicam.cs.pa.chessboardgame.api.model.board.BoardPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.Square;
import it.unicam.cs.pa.chessboardgame.api.model.piece.Piece;

/**
 * The interface GameCLI, which creates an abstraction for representing the board and the core I/O functionalities
 * of a board game created with the CLI.
 *
 * @param <B> the position system used by the board game
 * @param <P> the piece type used by the board game
 * @param <S> the square type used bt the board game
 * @param <T> the board used by the board game
 */
public interface GameCLI<B extends BoardPosition, P extends Piece, S extends Square<B, P>, T extends Board<B, P, S>> {
    /**
     * Clears the screen of the CLI.
     */
    void clearScreen();

    /**
     * Deletes the previous line on the CLI.
     */
    void deletePreviousLine();

    /**
     * Displays the game board in its current state.
     *
     * @param board the board that is being displayed
     */
    void displayBoard(T board);

    /**
     * Displays a given message on the CLI.
     *
     * @param message the message to be displayed
     */
    void displayMessage(String message);

    /**
     * Displays a given error message on the CLI.
     *
     * @param errorMessage the error message to be displayed
     */
    void displayErrorMessage(String errorMessage);

    /**
     * Reads the input from the player, such as a name or a move.
     *
     * @return the string containing the input from the player
     */
    String readInput();
}