package it.unicam.cs.pa.chessboardgame.api.model.rules;

import it.unicam.cs.pa.chessboardgame.api.model.board.Board;
import it.unicam.cs.pa.chessboardgame.api.model.board.BoardPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.Square;
import it.unicam.cs.pa.chessboardgame.api.model.piece.Piece;

/**
 * The interface DrawByRepetitionRule, which is used to create an abstraction for the management of the rules involving a draw by
 * repetition.
 *
 * @param <B> the position system used by the chessboard game
 * @param <P> the pieces used by the chessboard game
 * @param <S> the squares used by the board in the chessboard game
 * @param <T> the board used by the chessboard game
 */
public interface DrawByRepetitionRule<B extends BoardPosition, P extends Piece, S extends Square<B, P>,
        T extends Board<B, P, S>> {

    /**
     * Checks if the conditions of a draw by repetition are being met in the current state of the board.
     *
     * @param board the board analyzed
     * @return true if the conditions are being met, false otherwise
     */
    boolean areConditionsForDrawByRepetitionMet(T board);

    /**
     * Is draw by repetition boolean.
     *
     * @param movesMade the moves made
     * @return the boolean
     */
    boolean isDrawByRepetition(int movesMade);
}