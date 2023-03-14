package it.unicam.cs.pa.chessboardgame.api.model.movement;

import it.unicam.cs.pa.chessboardgame.api.model.board.Board;
import it.unicam.cs.pa.chessboardgame.api.model.board.BoardPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.Square;
import it.unicam.cs.pa.chessboardgame.api.model.piece.Piece;

import java.util.List;

/**
 * The interface MovementManager, which creates an abstraction for the management of the movement on the
 * board of a possible board game.
 *
 * @param <B> the position system used by the board game
 * @param <P> the pieces which will populate the board
 * @param <S> the squares used by the board
 * @param <T> the board on which the game will be played
 */
public interface MovementManager<B extends BoardPosition, P extends Piece, S extends Square<B, P>,
        T extends Board<B, P, S>> {
    /**
     * Checks whether the given move, made on the given board, is valid or not.
     *
     * @param board the board on which the move was made
     * @param move  the move which is being checked for validity
     * @return true ifi the move is valid, false otherwise
     */
    boolean isValidMove(T board, Move<B, P, S> move);

    /**
     * Returns a list with all the valid moves which can be made from a given square on the board.
     *
     * @param board  the board which contains the given square
     * @param square the square from which we're checking the valid moves that can be made
     * @return returns a list with all the valid moves, if there is no valid move it returns an empty list
     */
    List<Move<B, P, S>> getValidMoves(T board, S square);
}