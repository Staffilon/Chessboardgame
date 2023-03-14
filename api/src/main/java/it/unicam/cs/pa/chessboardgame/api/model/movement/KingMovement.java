package it.unicam.cs.pa.chessboardgame.api.model.movement;

import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersBoard;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersSquare;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPiece;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPieceType;
import it.unicam.cs.pa.chessboardgame.api.model.piece.PieceType;

import java.util.ArrayList;
import java.util.List;

/**
 * The KingMovement class which is responsible for the control of the movement of the King piece type in checkers.
 */
public class KingMovement implements
        CheckersPieceMovement<CheckersPosition, CheckersPiece, CheckersSquare, CheckersBoard> {
    private final CheckersPieceType pieceType;

    /**
     * Instantiates a new KingMovement class with the King piece type.
     */
    public KingMovement() {
        this.pieceType = CheckersPieceType.KING;
    }

    @Override
    public PieceType getPieceType() {
        return pieceType;
    }

    @Override
    public boolean isValidMove(CheckersBoard board, Move<CheckersPosition, CheckersPiece, CheckersSquare> move) {
        if (!arePiecesOnSquaresValid(move)) {
            return false;
        }
        if (board.isPositionOutOfBounds(move.to().getPosition()) || board.isPositionOutOfBounds(move.from().getPosition())) {
            return false;
        }
        if (!isValidLength(move)) {
            return false;
        }
        if (move.getMoveLength() == 2 && !isCaptureMove(board, move)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isCaptureMove(CheckersBoard board, Move<CheckersPosition, CheckersPiece, CheckersSquare> move) {
        if (move.getMoveLength() != 2) {
            return false;
        }
        CheckersPosition fromPosition = move.from().getPosition();
        CheckersPosition toPosition = move.to().getPosition();
        int inBetweenRow = (fromPosition.row() + toPosition.row()) / 2;
        int inBetweenColumn = (fromPosition.column() + toPosition.column()) / 2;
        CheckersPiece capturedPiece = board.getPieceAt(new CheckersPosition(inBetweenRow, inBetweenColumn));

        if (capturedPiece == null || capturedPiece.getColor() == move.getMovedPiece().getColor()) {
            return false;
        }
        return true;
    }

    @Override
    public List<Move<CheckersPosition, CheckersPiece, CheckersSquare>> getValidMoves
            (CheckersBoard board, CheckersSquare square) {
        List<Move<CheckersPosition, CheckersPiece, CheckersSquare>> validMoves = new ArrayList<>();

        getAllMoves(board, square).stream()
                .filter(move -> this.isValidMove(board, move))
                .forEach(validMoves::add);
        return validMoves;
    }

    /**
     * Returns the list of all possible moves the piece can make, even the illegal ones.
     *
     * @param board  the board on which the piece is positioned
     * @param square the square on which the piece is positioned
     * @return the list of all the possible moves the piece can make
     */
    private List<Move<CheckersPosition, CheckersPiece, CheckersSquare>> getAllMoves
    (CheckersBoard board, CheckersSquare square) {
        List<Move<CheckersPosition, CheckersPiece, CheckersSquare>> allMoves = new ArrayList<>();
        for (CheckersSquare adjacentSquare : board.getAccessibleSquares(square)) {
            allMoves.add(new CheckersMove(square, adjacentSquare));
        }
        return allMoves;
    }

    /**
     * Checks whether the pieces on squares on which the move is being made are valid.
     *
     * @param move the move which is being made
     * @return true if the pieces are valid, false otherwise
     */
    private boolean arePiecesOnSquaresValid(Move<CheckersPosition, CheckersPiece, CheckersSquare> move) {
        if (move.getMovedPiece() == null) {
            return false;
        }
        if (move.getMovedPiece().getPieceType() != pieceType) {
            return false;
        }
        if (move.to().isOccupied()) {
            return false;
        }
        return true;
    }

    /**
     * Checks whether the length of the move is in the bounds, so > 0 and < 3.
     *
     * @param move the move which is being checked
     * @return true if the length is valid, false otherwise
     */
    private boolean isValidLength(Move<CheckersPosition, CheckersPiece, CheckersSquare> move) {
        return move.getMoveLength() > 0 && move.getMoveLength() < 3;
    }

    /**
     * Checks whether a piece is able to capture another one
     *
     * @param board  the board on which the piece is located
     * @param square the square on which the piece is located
     * @return true if the piece can capture another, false otherwise
     */
    public boolean canCaptureFromSquare(CheckersBoard board, CheckersSquare square) {
        List<Move<CheckersPosition, CheckersPiece, CheckersSquare>> validMoves = getValidMoves(board, square);

        if (validMoves.size() == 0) {
            return false;
        }
        for (Move<CheckersPosition, CheckersPiece, CheckersSquare> move : validMoves) {
            if (move.getMoveLength() == 2)
                return true;
        }
        return false;
    }
}