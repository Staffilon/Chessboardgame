package it.unicam.cs.pa.chessboardgame.api.movement;


import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersBoard;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersPosition;
import it.unicam.cs.pa.chessboardgame.api.model.color.CheckersColor;
import it.unicam.cs.pa.chessboardgame.api.model.movement.CheckersMove;
import it.unicam.cs.pa.chessboardgame.api.model.movement.CheckersMovementManager;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPiece;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPieceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckersMovementManagerTest {
    private final CheckersMovementManager movementManager = new CheckersMovementManager();
    CheckersBoard board;

    @BeforeEach
    void setup() {
        board = new CheckersBoard();
    }

    @Test
    public void shouldReturnTrueWhenManWhitePieceMovesAreValid() {
        CheckersPosition fromPosition1 = new CheckersPosition(5, 0);
        CheckersPosition toPosition1 = new CheckersPosition(4, 1);
        CheckersPosition fromPosition2 = new CheckersPosition(5, 4);
        CheckersPosition toPosition21 = new CheckersPosition(4, 3);
        CheckersPosition toPosition22 = new CheckersPosition(4, 5);

        CheckersMove move1 = new CheckersMove(board.getSquareAt(fromPosition1), board.getSquareAt(toPosition1));
        CheckersMove move2 = new CheckersMove(board.getSquareAt(fromPosition2), board.getSquareAt(toPosition21));
        CheckersMove move3 = new CheckersMove(board.getSquareAt(fromPosition2), board.getSquareAt(toPosition22));

        assertTrue(movementManager.isValidMove(board, move1));
        assertTrue(movementManager.isValidMove(board, move2));
        assertTrue(movementManager.isValidMove(board, move3));
    }

    @Test
    public void shouldReturnFalseWhenManWhitePieceMovesAreInvalid() {
        CheckersPosition fromPosition1 = new CheckersPosition(7, 0);
        CheckersPosition toPosition1 = new CheckersPosition(6, 1);
        CheckersPosition fromPosition2 = new CheckersPosition(6, 3);
        CheckersPosition toPosition21 = new CheckersPosition(5, 2);
        CheckersPosition toPosition22 = new CheckersPosition(5, 4);

        CheckersMove move1 = new CheckersMove(board.getSquareAt(fromPosition1), board.getSquareAt(toPosition1));
        CheckersMove move2 = new CheckersMove(board.getSquareAt(fromPosition2), board.getSquareAt(toPosition21));
        CheckersMove move3 = new CheckersMove(board.getSquareAt(fromPosition2), board.getSquareAt(toPosition22));

        assertFalse(movementManager.isValidMove(board, move1));
        assertFalse(movementManager.isValidMove(board, move2));
        assertFalse(movementManager.isValidMove(board, move3));
    }

    @Test
    public void shouldReturnTrueWhenManBlackPieceMovesAreValid() {
        CheckersPosition fromPosition1 = new CheckersPosition(2, 7);
        CheckersPosition toPosition1 = new CheckersPosition(3, 6);
        CheckersPosition fromPosition2 = new CheckersPosition(2, 5);
        CheckersPosition toPosition21 = new CheckersPosition(3, 6);
        CheckersPosition toPosition22 = new CheckersPosition(3, 4);

        CheckersMove move1 = new CheckersMove(board.getSquareAt(fromPosition1), board.getSquareAt(toPosition1));
        CheckersMove move2 = new CheckersMove(board.getSquareAt(fromPosition2), board.getSquareAt(toPosition21));
        CheckersMove move3 = new CheckersMove(board.getSquareAt(fromPosition2), board.getSquareAt(toPosition22));

        assertTrue(movementManager.isValidMove(board, move1));
        assertTrue(movementManager.isValidMove(board, move2));
        assertTrue(movementManager.isValidMove(board, move3));
    }

    @Test
    public void shouldReturnFalseWhenManBlackPieceMovesAreInvalid() {
        CheckersPosition fromPosition1 = new CheckersPosition(0, 7);
        CheckersPosition toPosition1 = new CheckersPosition(1, 6);
        CheckersPosition fromPosition2 = new CheckersPosition(1, 4);
        CheckersPosition toPosition21 = new CheckersPosition(2, 3);
        CheckersPosition toPosition22 = new CheckersPosition(2, 5);

        CheckersMove move1 = new CheckersMove(board.getSquareAt(fromPosition1), board.getSquareAt(toPosition1));
        CheckersMove move2 = new CheckersMove(board.getSquareAt(fromPosition2), board.getSquareAt(toPosition21));
        CheckersMove move3 = new CheckersMove(board.getSquareAt(fromPosition2), board.getSquareAt(toPosition22));

        assertFalse(movementManager.isValidMove(board, move1));
        assertFalse(movementManager.isValidMove(board, move2));
        assertFalse(movementManager.isValidMove(board, move3));
    }

    @Test
    public void shouldBeAbleToCaptureWhiteMan() {
        CheckersPosition fromPosition = new CheckersPosition(2, 1);
        CheckersPosition inBetweenPosition = new CheckersPosition(3, 2);

        CheckersPiece piece = new CheckersPiece(CheckersColor.WHITE);
        board.setPieceAt(piece, inBetweenPosition);
        assertTrue(movementManager.canCaptureFromSquare(board, board.getSquareAt(fromPosition)));
    }

    @Test
    public void shouldNotBeAbleToCapture() {
        CheckersPosition fromPosition1 = new CheckersPosition(2, 1);
        CheckersPosition fromPosition2 = new CheckersPosition(5, 4);

        assertFalse(movementManager.canCaptureFromSquare(board, board.getSquareAt(fromPosition1)));
        assertFalse(movementManager.canCaptureFromSquare(board, board.getSquareAt(fromPosition2)));
    }

    @Test
    public void shouldNotBeAbleToCaptureWhiteMan() {
        CheckersPosition fromPosition = new CheckersPosition(5, 2);
        CheckersPosition inBetweenPosition = new CheckersPosition(4, 1);

        CheckersPiece piece = new CheckersPiece(CheckersColor.WHITE);
        board.setPieceAt(piece, inBetweenPosition);
        assertFalse(movementManager.canCaptureFromSquare(board, board.getSquareAt(fromPosition)));
    }

    @Test
    public void shouldBeAbleToCaptureBlackMan() {
        CheckersPosition fromPosition = new CheckersPosition(5, 4);
        CheckersPosition inBetweenPosition = new CheckersPosition(4, 5);

        CheckersPiece piece = new CheckersPiece(CheckersColor.BLACK);
        board.setPieceAt(piece, inBetweenPosition);
        assertTrue(movementManager.canCaptureFromSquare(board, board.getSquareAt(fromPosition)));
    }


    @Test
    public void shouldNotBeAbleToCaptureBlackMan() {
        CheckersPosition fromPosition = new CheckersPosition(2, 1);
        CheckersPosition inBetweenPosition = new CheckersPosition(3, 2);

        CheckersPiece piece = new CheckersPiece(CheckersColor.BLACK);
        board.setPieceAt(piece, inBetweenPosition);
        assertFalse(movementManager.canCaptureFromSquare(board, board.getSquareAt(fromPosition)));
    }

    @Test
    public void shouldBeAbleToCaptureKing() {
        CheckersPosition fromPosition1 = new CheckersPosition(5, 2);
        CheckersPosition inBetweenPosition1 = new CheckersPosition(4, 3);
        CheckersPosition fromPosition2 = new CheckersPosition(2, 5);
        CheckersPosition inBetweenPosition2 = new CheckersPosition(3, 6);

        CheckersPiece blackKing = new CheckersPiece(CheckersColor.BLACK, CheckersPieceType.KING);
        CheckersPiece whiteKing = new CheckersPiece(CheckersColor.WHITE, CheckersPieceType.KING);

        board.removePieceAt(fromPosition1);
        board.setPieceAt(whiteKing, fromPosition1);
        board.setPieceAt(blackKing, inBetweenPosition1);
        board.removePieceAt(fromPosition2);
        board.setPieceAt(blackKing, fromPosition2);
        board.setPieceAt(whiteKing, inBetweenPosition2);
        assertTrue(movementManager.canCaptureFromSquare(board, board.getSquareAt(fromPosition1)));
        assertTrue(movementManager.canCaptureFromSquare(board, board.getSquareAt(fromPosition2)));
    }

    @Test
    public void shouldNotBeAbleToCaptureKing() {
        CheckersPosition fromPosition1 = new CheckersPosition(5, 2);
        CheckersPosition inBetweenPosition1 = new CheckersPosition(4, 3);
        CheckersPosition fromPosition2 = new CheckersPosition(2, 5);
        CheckersPosition inBetweenPosition2 = new CheckersPosition(3, 6);

        CheckersPiece blackKing = new CheckersPiece(CheckersColor.BLACK, CheckersPieceType.KING);
        CheckersPiece whiteKing = new CheckersPiece(CheckersColor.WHITE, CheckersPieceType.KING);

        board.setPieceAt(blackKing, inBetweenPosition1);
        board.setPieceAt(whiteKing, inBetweenPosition2);

        assertFalse(movementManager.canCaptureFromSquare(board, board.getSquareAt(fromPosition1)));
        assertFalse(movementManager.canCaptureFromSquare(board, board.getSquareAt(fromPosition2)));
    }

    @Test
    public void shouldBeCaptureMove() {
        CheckersPosition fromPosition1 = new CheckersPosition(5, 2);
        CheckersPosition inBetweenPosition1 = new CheckersPosition(4, 3);
        CheckersPosition toPosition1 = new CheckersPosition(3, 4);
        CheckersPosition fromPosition2 = new CheckersPosition(2, 5);
        CheckersPosition inBetweenPosition2 = new CheckersPosition(3, 6);
        CheckersPosition toPosition2 = new CheckersPosition(4, 7);

        CheckersPiece whitePiece = new CheckersPiece(CheckersColor.WHITE);
        CheckersPiece blackPiece = new CheckersPiece(CheckersColor.BLACK);

        board.setPieceAt(blackPiece, inBetweenPosition1);
        board.setPieceAt(whitePiece, inBetweenPosition2);

        CheckersMove move1 = new CheckersMove(board.getSquareAt(fromPosition1), board.getSquareAt(toPosition1));
        CheckersMove move2 = new CheckersMove(board.getSquareAt(fromPosition2), board.getSquareAt(toPosition2));

        assertTrue(movementManager.isCaptureMove(board, move1));
        assertTrue(movementManager.isCaptureMove(board, move2));
    }

    @Test
    public void shouldNotBeCaptureMove() {
        CheckersPosition fromPosition1 = new CheckersPosition(5, 2);
        CheckersPosition inBetweenPosition1 = new CheckersPosition(4, 3);
        CheckersPosition toPosition1 = new CheckersPosition(3, 4);
        CheckersPosition fromPosition2 = new CheckersPosition(2, 5);
        CheckersPosition inBetweenPosition2 = new CheckersPosition(3, 6);
        CheckersPosition toPosition2 = new CheckersPosition(4, 7);

        CheckersPiece whitePiece = new CheckersPiece(CheckersColor.WHITE, CheckersPieceType.KING);
        CheckersPiece blackPiece = new CheckersPiece(CheckersColor.BLACK, CheckersPieceType.KING);

        board.setPieceAt(blackPiece, inBetweenPosition1);
        board.setPieceAt(whitePiece, inBetweenPosition2);

        CheckersMove move1 = new CheckersMove(board.getSquareAt(fromPosition1), board.getSquareAt(toPosition1));
        CheckersMove move2 = new CheckersMove(board.getSquareAt(fromPosition2), board.getSquareAt(toPosition2));

        assertFalse(movementManager.isCaptureMove(board, move1));
        assertFalse(movementManager.isCaptureMove(board, move2));
    }

    @Test
    public void shouldReturnCorrectValidMovesNumber() {
        CheckersPosition fromBlackPosition1 = new CheckersPosition(0, 1);
        CheckersPosition fromBlackPosition2 = new CheckersPosition(2, 1);
        CheckersPosition fromBlackPosition3 = new CheckersPosition(2, 7);
        CheckersPosition fromBlackPosition4 = new CheckersPosition(2, 3);
        CheckersPosition inBetweenBlackPosition1 = new CheckersPosition(3, 6);
        CheckersPosition inBetweenBlackPosition2 = new CheckersPosition(3, 4);
        CheckersPosition fromWhitePosition1 = new CheckersPosition(7, 0);
        CheckersPosition fromWhitePosition2 = new CheckersPosition(5, 2);
        CheckersPosition fromWhitePosition3 = new CheckersPosition(5, 0);
        CheckersPosition fromWhitePosition4 = new CheckersPosition(5, 4);
        CheckersPosition inBetweenWhitePosition1 = new CheckersPosition(4, 1);
        CheckersPosition inBetweenWhitePosition2 = new CheckersPosition(4, 3);

        CheckersPiece whiteMan = new CheckersPiece(CheckersColor.WHITE);
        CheckersPiece blackMan = new CheckersPiece(CheckersColor.BLACK);
        CheckersPiece whiteKing = new CheckersPiece(CheckersColor.WHITE, CheckersPieceType.KING);
        CheckersPiece blackKing = new CheckersPiece(CheckersColor.BLACK, CheckersPieceType.KING);

        board.setPieceAt(whiteMan, inBetweenBlackPosition1);
        board.setPieceAt(whiteKing, inBetweenBlackPosition2);
        board.setPieceAt(blackMan, inBetweenWhitePosition1);
        board.setPieceAt(blackKing, inBetweenWhitePosition2);

        assertEquals(0, movementManager.getValidMoves(board, board.getSquareAt(fromBlackPosition1)).size());
        assertEquals(2, movementManager.getValidMoves(board, board.getSquareAt(fromBlackPosition2)).size());
        assertEquals(1, movementManager.getValidMoves(board, board.getSquareAt(fromBlackPosition3)).size());
        assertEquals(1, movementManager.getValidMoves(board, board.getSquareAt(fromBlackPosition4)).size());
        assertEquals(0, movementManager.getValidMoves(board, board.getSquareAt(fromWhitePosition1)).size());
        assertEquals(1, movementManager.getValidMoves(board, board.getSquareAt(fromWhitePosition2)).size());
        assertEquals(1, movementManager.getValidMoves(board, board.getSquareAt(fromWhitePosition3)).size());
        assertEquals(1, movementManager.getValidMoves(board, board.getSquareAt(fromWhitePosition4)).size());
    }
}