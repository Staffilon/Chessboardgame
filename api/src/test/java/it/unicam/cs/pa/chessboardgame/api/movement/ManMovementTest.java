package it.unicam.cs.pa.chessboardgame.api.movement;


import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersBoard;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersSquare;
import it.unicam.cs.pa.chessboardgame.api.model.color.CheckersColor;
import it.unicam.cs.pa.chessboardgame.api.model.movement.CheckersMove;
import it.unicam.cs.pa.chessboardgame.api.model.movement.ManMovement;
import it.unicam.cs.pa.chessboardgame.api.model.movement.Move;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPiece;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPieceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ManMovementTest {
    private final ManMovement manMovement = new ManMovement();
    CheckersBoard board;

    @BeforeEach
    void setup() {
        board = new CheckersBoard();
    }

    @Test
    public void shouldReturnValidPieceType() {
        assertEquals(CheckersPieceType.MAN, manMovement.getPieceType());
    }

    @Test
    public void shouldReturnTrueWhenMoveWithoutCaptureIsValid() {
        CheckersPosition fromPosition = new CheckersPosition(2, 1);
        CheckersPosition toPosition = new CheckersPosition(3, 2);

        CheckersSquare square = board.getSquareAt(fromPosition);
        CheckersSquare adjacentSquare = board.getSquareAt(toPosition);
        Move<CheckersPosition, CheckersPiece, CheckersSquare> move = new CheckersMove(square, adjacentSquare);

        assertTrue(manMovement.isValidMove(board, move));
    }

    @Test
    public void shouldReturnFalseWhenCapturingSameColorPiece() {
        CheckersPosition fromPosition = new CheckersPosition(2, 1);
        CheckersPosition toPosition = new CheckersPosition(4, 3);
        CheckersPosition inBetweenPosition = new CheckersPosition(3, 2);

        CheckersPiece piece = new CheckersPiece(CheckersColor.BLACK);
        board.setPieceAt(piece, inBetweenPosition);

        CheckersSquare square = board.getSquareAt(fromPosition);
        CheckersSquare adjacentSquare = board.getSquareAt(toPosition);
        Move<CheckersPosition, CheckersPiece, CheckersSquare> move = new CheckersMove(square, adjacentSquare);

        assertFalse(manMovement.isValidMove(board, move));
    }

    @Test
    public void shouldReturnFalseWhenCapturingKingPiece() {
        CheckersPosition fromPosition = new CheckersPosition(2, 1);
        CheckersPosition toPosition = new CheckersPosition(4, 3);
        CheckersPosition inBetweenPosition = new CheckersPosition(3, 2);

        CheckersPiece piece = new CheckersPiece(CheckersColor.WHITE, CheckersPieceType.KING);
        board.setPieceAt(piece, inBetweenPosition);

        CheckersSquare square = board.getSquareAt(fromPosition);
        CheckersSquare adjacentSquare = board.getSquareAt(toPosition);
        Move<CheckersPosition, CheckersPiece, CheckersSquare> move = new CheckersMove(square, adjacentSquare);

        assertFalse(manMovement.isValidMove(board, move));
    }

    @Test
    public void shouldReturnTrueWhenCapturingDifferentColorPiece() {
        CheckersPosition fromPosition = new CheckersPosition(2, 1);
        CheckersPosition toPosition = new CheckersPosition(4, 3);
        CheckersPosition inBetweenPosition = new CheckersPosition(3, 2);

        CheckersPiece piece = new CheckersPiece(CheckersColor.WHITE);
        board.setPieceAt(piece, inBetweenPosition);

        CheckersSquare square = board.getSquareAt(fromPosition);
        CheckersSquare adjacentSquare = board.getSquareAt(toPosition);
        Move<CheckersPosition, CheckersPiece, CheckersSquare> move = new CheckersMove(square, adjacentSquare);

        assertTrue(manMovement.isValidMove(board, move));
    }

    @Test
    public void shouldReturnFalseWhenIsNotCaptureMove() {
        CheckersPosition fromPosition = new CheckersPosition(5, 2);
        CheckersPosition toPosition = new CheckersPosition(4, 1);


        CheckersSquare square = board.getSquareAt(fromPosition);
        CheckersSquare adjacentSquare = board.getSquareAt(toPosition);
        Move<CheckersPosition, CheckersPiece, CheckersSquare> move = new CheckersMove(square, adjacentSquare);

        assertFalse(manMovement.isCaptureMove(board, move));
    }

    @Test
    public void shouldReturnFalseWhenPiecesOnSquaresAreInvalid() {
        CheckersSquare square = board.getSquareAt(new CheckersPosition(3, 2));
        CheckersSquare adjacentSquare = board.getSquareAt(new CheckersPosition(2, 3));

        Move<CheckersPosition, CheckersPiece, CheckersSquare> move = new CheckersMove(square, adjacentSquare);
        assertFalse(manMovement.isValidMove(board, move));
    }

    @Test
    public void shouldReturnFalseWhenMoveLengthIsInvalid() {
        CheckersSquare square = board.getSquareAt(new CheckersPosition(2, 1));
        CheckersSquare adjacentSquare = board.getSquareAt(new CheckersPosition(5, 4));
        board.removePieceAt(adjacentSquare.getPosition());

        Move<CheckersPosition, CheckersPiece, CheckersSquare> move = new CheckersMove(square, adjacentSquare);
        assertFalse(manMovement.isValidMove(board, move));
    }

    @Test
    public void shouldReturnAllValidMoves() {
        CheckersPosition fromPosition1 = new CheckersPosition(5, 0);
        CheckersPosition fromPosition2 = new CheckersPosition(5, 2);
        CheckersPosition fromPosition3 = new CheckersPosition(7, 0);
        CheckersPosition fromPosition4 = new CheckersPosition(2, 7);

        assertEquals(1, manMovement.getValidMoves(board, board.getSquareAt(fromPosition1)).size());
        assertEquals(2, manMovement.getValidMoves(board, board.getSquareAt(fromPosition2)).size());
        assertEquals(0, manMovement.getValidMoves(board, board.getSquareAt(fromPosition3)).size());
        assertEquals(1, manMovement.getValidMoves(board, board.getSquareAt(fromPosition4)).size());
    }

    @Test
    public void shouldBeAbleToCapture() {
        CheckersPosition fromPosition = new CheckersPosition(5, 2);
        CheckersPosition inBetweenPosition = new CheckersPosition(4, 3);

        CheckersPiece piece = new CheckersPiece(CheckersColor.BLACK);
        board.setPieceAt(piece, inBetweenPosition);
        assertTrue(manMovement.canCaptureFromSquare(board, board.getSquareAt(fromPosition)));
    }

    @Test
    public void shouldNotBeAbleToCapture() {
        CheckersPosition fromPosition = new CheckersPosition(5, 2);
        CheckersPosition inBetweenPosition = new CheckersPosition(4, 3);

        CheckersPiece piece = new CheckersPiece(CheckersColor.WHITE);
        board.setPieceAt(piece, inBetweenPosition);
        assertFalse(manMovement.canCaptureFromSquare(board, board.getSquareAt(fromPosition)));

        CheckersPiece kingPiece = new CheckersPiece(CheckersColor.BLACK, CheckersPieceType.KING);
        board.setPieceAt(kingPiece, inBetweenPosition);
        assertFalse(manMovement.canCaptureFromSquare(board, board.getSquareAt(fromPosition)));

        board.removePieceAt(inBetweenPosition);
        assertFalse(manMovement.canCaptureFromSquare(board, board.getSquareAt(fromPosition)));
    }
}