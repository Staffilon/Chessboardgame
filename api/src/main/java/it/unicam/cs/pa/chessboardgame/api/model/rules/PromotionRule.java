package it.unicam.cs.pa.chessboardgame.api.model.rules;

import it.unicam.cs.pa.chessboardgame.api.model.board.Board;
import it.unicam.cs.pa.chessboardgame.api.model.board.BoardPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.Square;
import it.unicam.cs.pa.chessboardgame.api.model.piece.Piece;

/**
 * The interface PromotionRule, which creates an abstraction for the management of promotions in the chessboard game.
 *
 * @param <B> the position system used by the chessboard game
 * @param <P> the pieces used by the chessboard game
 * @param <S> the squares used by the board in the chessboard game
 * @param <T> the board used by the chessboard game
 */
public interface PromotionRule <B extends BoardPosition, P extends Piece, S extends Square<B, P>,
        T extends Board<B, P, S>>{
    /**
     * Checks whether the piece on the given square is eligible for a promotion or not.
     *
     * @param board  the board on which the game is being played
     * @param position the position on the board where the piece is situated
     * @return true if the piece is eligible for a promotion, false otherwise
     */
    boolean canPromote(T board, B position);
}