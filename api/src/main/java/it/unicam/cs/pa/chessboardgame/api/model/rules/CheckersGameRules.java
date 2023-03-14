package it.unicam.cs.pa.chessboardgame.api.model.rules;

import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersBoard;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersSquare;
import it.unicam.cs.pa.chessboardgame.api.model.color.CheckersColor;
import it.unicam.cs.pa.chessboardgame.api.model.color.Color;
import it.unicam.cs.pa.chessboardgame.api.model.movement.CheckersMovementManager;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPiece;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPieceType;

import java.util.Iterator;

/**
 * The CheckersGameRules, which manages the rules of the Checkers board game. It defines the logic the victory, draw and loss
 * conditions.
 */
public class CheckersGameRules implements
        GameRules<CheckersPosition, CheckersPiece, CheckersSquare, CheckersBoard>,
        DrawByRepetitionRule<CheckersPosition, CheckersPiece, CheckersSquare, CheckersBoard>,
        LossByStalePositionRule<CheckersPosition, CheckersPiece, CheckersSquare, CheckersBoard> {
    private final int movesBeforeDrawByRepetition;
    private final CheckersMovementManager movementManager;

    /**
     * Instantiates a new Checkers game rules.
     *
     * @param movesBeforeDrawByRepetition the moves before draw by repetition
     */
    public CheckersGameRules(int movesBeforeDrawByRepetition) {
        this.movesBeforeDrawByRepetition = movesBeforeDrawByRepetition;
        movementManager = new CheckersMovementManager();
    }

    @Override
    public Color getWinnerSide(CheckersBoard board) {
        int blackPieces = 0;
        int whitePieces = 0;
        Iterator<CheckersPiece> pieces = board.pieceIterator();
        while (pieces.hasNext()) {
            CheckersPiece piece = pieces.next();
            if (piece.getColor() == CheckersColor.WHITE) {
                whitePieces++;
            } else if (piece.getColor() == CheckersColor.BLACK) {
                blackPieces++;
            }
        }

        return getSideWithPiecesLeft(blackPieces, whitePieces);
    }

    private Color getSideWithPiecesLeft(int blackPieces, int whitePieces) {
        if ((blackPieces != 0 && whitePieces != 0) || (blackPieces == 0 && whitePieces == 0)) {
            return null;
        }
        if (blackPieces == 0) {
            return CheckersColor.WHITE;
        }
        return CheckersColor.BLACK;
    }

    @Override
    public boolean isDraw(CheckersBoard board) {
        boolean canBlackSideMove = canSideMakeMoves(board, CheckersColor.BLACK);
        boolean canWhiteSideMove = canSideMakeMoves(board, CheckersColor.WHITE);

        if (!canBlackSideMove && !canWhiteSideMove) {
            return true;
        }
        return false;
    }

    @Override
    public boolean areConditionsForDrawByRepetitionMet(CheckersBoard board) {
        int manCount = (int) board.getOccupiedSquares().stream()
                .filter(square -> square.getPiece().getPieceType() == CheckersPieceType.MAN).count();

        int whiteKingCount = (int) board.getOccupiedSquares().stream()
                .filter(square -> square.getPiece().getPieceType() == CheckersPieceType.KING &&
                        square.getPiece().getColor() == CheckersColor.WHITE).count();

        int blackKingCount = (int) board.getOccupiedSquares().stream()
                .filter(square -> square.getPiece().getPieceType() == CheckersPieceType.KING &&
                        square.getPiece().getColor() == CheckersColor.BLACK).count();

        return manCount == 0 && whiteKingCount == 1 && blackKingCount == 1;
    }

    @Override
    public boolean isDrawByRepetition(int movesMade) {
        if (movesMade < movesBeforeDrawByRepetition) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isLossByStalePosition(CheckersBoard board, Color turn) {
        boolean canBlackMove = canSideMakeMoves(board, CheckersColor.BLACK);
        boolean canWhiteMove = canSideMakeMoves(board, CheckersColor.WHITE);

        if (!canBlackMove && !canWhiteMove) {
            return false;
        }

        if (canBlackMove && !canWhiteMove && turn == CheckersColor.WHITE) {
            return true;
        }
        if (!canBlackMove && canWhiteMove && turn == CheckersColor.BLACK) {
            return true;
        }
        return false;
    }

    private boolean canSideMakeMoves(CheckersBoard board, Color side) {
        int movesAvailable = (int) board.getOccupiedSquares().stream()
                .filter(square -> square.getPiece().getColor() == side)
                .filter(square -> movementManager.getValidMoves(board, square).size() > 0)
                .count();

        if (movesAvailable > 0) {
            return true;
        }
        return false;
    }
}