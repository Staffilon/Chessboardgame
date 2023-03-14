package it.unicam.cs.pa.chessboardgame.api.model.movement;

import it.unicam.cs.pa.chessboardgame.api.model.board.Board;
import it.unicam.cs.pa.chessboardgame.api.model.board.BoardPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.Square;
import it.unicam.cs.pa.chessboardgame.api.model.piece.Piece;
import it.unicam.cs.pa.chessboardgame.api.model.piece.PieceType;

import java.util.List;

/**
 * The interface PieceMovement, which creates an abstraction for the representation of the
 * movement of a certain piece on the board of a board game.
 *
 * @param <B> the position system used by the board game
 * @param <P> the pieces which will populate the board
 * @param <S> the squares used by the board
 * @param <T> the board on which the game will be played
 */
public interface PieceMovement<B extends BoardPosition, P extends Piece, S extends Square<B, P>,
        T extends Board<B, P, S>> {
    /**
     * Returns the piece type that the movement is based on.
     *
     * @return the piece type that the movement is based on
     */
    PieceType getPieceType();

    /**
     * Checks whether a move is valid for the piece type. Returns true if the move is valid,
     * false otherwise.
     *
     * @param board the board on which the move was made
     * @param move  the move which is being checked
     * @return true if the move is valid, false otherwise
     */
    boolean isValidMove(T board, Move<B, P, S> move);

    /**
     * Returns all the valid moves that can be made from the given square on the board.
     *
     * @param board  the board on which the square is positioned
     * @param square the square from which we want to get the valid moves
     * @return a List with al the valid moves that can be made from the given square
     */
    List<Move<B, P, S>> getValidMoves(T board, S square);
}