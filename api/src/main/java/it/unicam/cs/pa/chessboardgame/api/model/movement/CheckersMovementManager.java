package it.unicam.cs.pa.chessboardgame.api.model.movement;

import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersBoard;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersSquare;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPiece;
import it.unicam.cs.pa.chessboardgame.api.model.piece.PieceType;

import java.util.ArrayList;
import java.util.List;

/**
 * The CheckersMovementManager class, which is responsible for the management of the movement of the checkers pieces on
 * the checkers board.
 */
public class CheckersMovementManager implements
        MovementManager<CheckersPosition, CheckersPiece, CheckersSquare, CheckersBoard>,
        PieceCaptureMovement<CheckersPosition, CheckersPiece, CheckersSquare, CheckersBoard> {
    /**
     * The Movement types.
     */
    private List<CheckersPieceMovement<CheckersPosition, CheckersPiece, CheckersSquare, CheckersBoard>> movementTypes;

    /**
     * Instantiates a new CheckersMovementManager, alongside the movement controllers of every checkers' piece type.
     */
    public CheckersMovementManager() {
        movementTypes = new ArrayList<>();
        movementTypes.add(new KingMovement());
        movementTypes.add(new ManMovement());
    }

    @Override
    public boolean isValidMove(CheckersBoard board, Move<CheckersPosition, CheckersPiece, CheckersSquare> move) {
        PieceMovement<CheckersPosition, CheckersPiece, CheckersSquare, CheckersBoard> movementType =
                getMovementType(move.getMovedPiece().getPieceType());
        if (movementType == null)
            return false;
        return movementType.isValidMove(board, move);
    }

    @Override
    public boolean canCaptureFromSquare(CheckersBoard board, CheckersSquare square) {
        CheckersPieceMovement<CheckersPosition, CheckersPiece, CheckersSquare, CheckersBoard> movementType =
                getMovementType(square.getPiece().getPieceType());
        if (movementType == null)
            return false;
        return movementType.canCaptureFromSquare(board, square);
    }

    @Override
    public boolean isCaptureMove(CheckersBoard board, Move<CheckersPosition, CheckersPiece, CheckersSquare> move) {
        CheckersPieceMovement<CheckersPosition, CheckersPiece, CheckersSquare, CheckersBoard> movementType =
                getMovementType(move.getMovedPiece().getPieceType());
        if (movementType == null)
            return false;
        return movementType.isCaptureMove(board, move);
    }

    @Override
    public List<Move<CheckersPosition, CheckersPiece, CheckersSquare>> getValidMoves(CheckersBoard board, CheckersSquare square) {
        PieceMovement<CheckersPosition, CheckersPiece, CheckersSquare, CheckersBoard> movementType =
                getMovementType(square.getPiece().getPieceType());

        if (movementType == null)
            return new ArrayList<>();
        return movementType.getValidMoves(board, square);
    }

    /**
     * Checks the piece type of the piece of a piece and returns the respective movement controller.
     * If there is no respective movement controller, it returns null.
     *
     * @param pieceType the piece type of the piece that's being checked
     * @return the respective movement controller if there is one, null otherwise
     */
    private CheckersPieceMovement<CheckersPosition, CheckersPiece, CheckersSquare, CheckersBoard> getMovementType
    (PieceType pieceType) {
        for (CheckersPieceMovement<CheckersPosition, CheckersPiece, CheckersSquare, CheckersBoard> movementType : movementTypes) {
            if (pieceType == movementType.getPieceType()) {
                return movementType;
            }
        }
        return null;
    }
}