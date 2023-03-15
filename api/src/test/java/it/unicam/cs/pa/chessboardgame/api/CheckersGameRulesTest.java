package it.unicam.cs.pa.chessboardgame.api;

import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersBoard;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersPosition;
import it.unicam.cs.pa.chessboardgame.api.model.color.CheckersColor;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPiece;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPieceType;
import it.unicam.cs.pa.chessboardgame.api.model.rules.CheckersGameRules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckersGameRulesTest {
    CheckersBoard board;
    CheckersGameRules rules = new CheckersGameRules(40);

    @BeforeEach
    void setup() {
        board = new CheckersBoard();
    }

    @Test
    public void shouldNotGetAWinner() {
        assertNull(rules.getWinnerSide(board));
        board.clearBoard();
        assertNull(rules.getWinnerSide(board));
        CheckersPiece whiteKing = new CheckersPiece(CheckersColor.WHITE, CheckersPieceType.KING);
        CheckersPiece blackKing = new CheckersPiece(CheckersColor.BLACK, CheckersPieceType.KING);
        board.setPieceAt(whiteKing, new CheckersPosition(0, 0));
        board.setPieceAt(blackKing, new CheckersPosition(5, 2));
        assertNull(rules.getWinnerSide(board));
    }

    @Test
    public void shouldGetWinnerSide() {
        board.clearBoard();
        CheckersPiece whiteKing = new CheckersPiece(CheckersColor.WHITE, CheckersPieceType.KING);
        CheckersPiece blackKing = new CheckersPiece(CheckersColor.BLACK, CheckersPieceType.KING);
        board.setPieceAt(whiteKing, new CheckersPosition(5, 2));
        assertEquals(CheckersColor.WHITE, rules.getWinnerSide(board));

        board.clearBoard();
        board.setPieceAt(blackKing, new CheckersPosition(5, 2));
        assertEquals(CheckersColor.BLACK, rules.getWinnerSide(board));
    }

    @Test
    public void shouldNotBeALossByStalePosition() {
        board.clearBoard();
        CheckersPiece whiteMan = new CheckersPiece(CheckersColor.WHITE);
        CheckersPiece blackKing = new CheckersPiece(CheckersColor.BLACK, CheckersPieceType.KING);

        board.setPieceAt(blackKing, new CheckersPosition(0, 1));
        board.setPieceAt(whiteMan, new CheckersPosition(1, 0));
        board.setPieceAt(whiteMan, new CheckersPosition(1, 2));
        assertFalse(rules.isLossByStalePosition(board, CheckersColor.WHITE));
        assertFalse(rules.isLossByStalePosition(board, CheckersColor.BLACK));
    }

    @Test
    public void shouldBeALossByStalePosition() {
        board.clearBoard();
        CheckersPiece whiteMan = new CheckersPiece(CheckersColor.WHITE);
        CheckersPiece blackKing = new CheckersPiece(CheckersColor.BLACK, CheckersPieceType.KING);

        board.setPieceAt(blackKing, new CheckersPosition(0, 1));
        board.setPieceAt(whiteMan, new CheckersPosition(1, 0));
        assertTrue(rules.isLossByStalePosition(board, CheckersColor.WHITE));
        assertFalse(rules.isLossByStalePosition(board, CheckersColor.BLACK));
    }

    @Test
    public void shouldBeADraw() {
        CheckersPiece whiteMan = new CheckersPiece(CheckersColor.WHITE);
        CheckersPiece blackMan = new CheckersPiece(CheckersColor.BLACK);

        board.setPieceAt(whiteMan, new CheckersPosition(4, 1));
        board.setPieceAt(whiteMan, new CheckersPosition(4, 3));
        board.setPieceAt(whiteMan, new CheckersPosition(4, 5));
        board.setPieceAt(whiteMan, new CheckersPosition(4, 7));

        board.setPieceAt(blackMan, new CheckersPosition(3, 0));
        board.setPieceAt(blackMan, new CheckersPosition(3, 2));
        board.setPieceAt(blackMan, new CheckersPosition(3, 4));
        board.setPieceAt(blackMan, new CheckersPosition(3, 6));

        assertTrue(rules.isDraw(board));
    }

    @Test
    public void shouldNotBeADraw() {
        board.clearBoard();
        CheckersPiece whiteMan = new CheckersPiece(CheckersColor.WHITE);
        CheckersPiece blackMan = new CheckersPiece(CheckersColor.BLACK);

        board.setPieceAt(whiteMan, new CheckersPosition(4, 1));
        board.setPieceAt(whiteMan, new CheckersPosition(4, 3));
        board.setPieceAt(whiteMan, new CheckersPosition(4, 5));
        board.setPieceAt(whiteMan, new CheckersPosition(4, 7));
        board.setPieceAt(whiteMan, new CheckersPosition(5, 0));
        board.setPieceAt(whiteMan, new CheckersPosition(5, 2));
        board.setPieceAt(whiteMan, new CheckersPosition(5, 4));
        board.setPieceAt(whiteMan, new CheckersPosition(5, 6));
        board.setPieceAt(blackMan, new CheckersPosition(3, 0));
        board.setPieceAt(blackMan, new CheckersPosition(3, 2));
        board.setPieceAt(blackMan, new CheckersPosition(3, 4));
        board.setPieceAt(blackMan, new CheckersPosition(3, 6));
        board.setPieceAt(blackMan, new CheckersPosition(2, 1));
        board.setPieceAt(blackMan, new CheckersPosition(2, 3));
        board.setPieceAt(blackMan, new CheckersPosition(2, 5));
        board.setPieceAt(blackMan, new CheckersPosition(2, 7));
        //not right square, it still has no moves
        board.setPieceAt(whiteMan, new CheckersPosition(0, 1));
        assertTrue(rules.isDraw(board));
        //right square, it has moves
        board.setPieceAt(whiteMan, new CheckersPosition(7, 1));
        assertFalse(rules.isDraw(board));
    }

    @Test
    public void shouldMeetDrawByRepetitionPositionConditions() {
        board.clearBoard();
        CheckersPiece whiteKing = new CheckersPiece(CheckersColor.WHITE, CheckersPieceType.KING);
        CheckersPiece blackKing = new CheckersPiece(CheckersColor.BLACK, CheckersPieceType.KING);

        board.setPieceAt(whiteKing, new CheckersPosition(7, 0));
        board.setPieceAt(blackKing, new CheckersPosition(7, 4));
        assertTrue(rules.areConditionsForDrawByRepetitionMet(board));
    }

    @Test
    public void shouldNotMeetDrawByRepetitionPositionConditions() {
        assertFalse(rules.areConditionsForDrawByRepetitionMet(board));
        board.clearBoard();
        CheckersPiece whiteKing = new CheckersPiece(CheckersColor.WHITE, CheckersPieceType.KING);
        CheckersPiece blackKing = new CheckersPiece(CheckersColor.BLACK, CheckersPieceType.KING);
        CheckersPiece whiteMan = new CheckersPiece(CheckersColor.WHITE);
        CheckersPiece blackMan = new CheckersPiece(CheckersColor.BLACK);

        board.setPieceAt(whiteMan, new CheckersPosition(3, 4));
        board.setPieceAt(blackMan, new CheckersPosition(7, 4));
        assertFalse(rules.areConditionsForDrawByRepetitionMet(board));

        board.setPieceAt(blackKing, new CheckersPosition(5, 4));
        assertFalse(rules.areConditionsForDrawByRepetitionMet(board));

        board.setPieceAt(whiteKing, new CheckersPosition(7, 0));
        assertFalse(rules.areConditionsForDrawByRepetitionMet(board));
    }

    @Test
    public void shouldBeDrawByRepetition() {
        assertTrue(rules.isDrawByRepetition(40));
        assertTrue(rules.isDrawByRepetition(41));
    }

    @Test
    public void shouldNotBeDrawByRepetition() {
        assertFalse(rules.isDrawByRepetition(0));
        assertFalse(rules.isDrawByRepetition(39));
    }

    @Test
    public void shouldBeAPromotion(){
        board.clearBoard();
        CheckersPiece whiteMan = new CheckersPiece(CheckersColor.WHITE);
        CheckersPiece blackMan = new CheckersPiece(CheckersColor.BLACK);
        board.setPieceAt(whiteMan,new CheckersPosition(0,1));
        board.setPieceAt(blackMan, new CheckersPosition(7,0));

        assertTrue(rules.canPromote(board, new CheckersPosition(0,1)));
        assertTrue(rules.canPromote(board, new CheckersPosition(7,0)));
    }

    @Test
    public void shouldNotBeAPromotion(){
        board.clearBoard();
        CheckersPiece whiteKing = new CheckersPiece(CheckersColor.WHITE, CheckersPieceType.KING);
        CheckersPiece blackKing = new CheckersPiece(CheckersColor.BLACK, CheckersPieceType.KING);
        CheckersPiece whiteMan = new CheckersPiece(CheckersColor.WHITE);
        CheckersPiece blackMan = new CheckersPiece(CheckersColor.BLACK);
        CheckersPosition bottomRow = new CheckersPosition(7,0);
        CheckersPosition topRow = new CheckersPosition(0,1);
        CheckersPosition bottomRow2 = new CheckersPosition(7,2);
        CheckersPosition topRow2 = new CheckersPosition(0,3);

        assertFalse(rules.canPromote(board, bottomRow2));
        assertFalse(rules.canPromote(board, bottomRow));
        assertFalse(rules.canPromote(board, topRow));
        assertFalse(rules.canPromote(board, topRow2));

        board.setPieceAt(whiteKing,topRow);
        board.setPieceAt(blackKing, bottomRow);
        board.setPieceAt(blackMan,topRow);
        board.setPieceAt(whiteMan, bottomRow);

        assertFalse(rules.canPromote(board, bottomRow2));
        assertFalse(rules.canPromote(board, bottomRow));
        assertFalse(rules.canPromote(board, topRow));
        assertFalse(rules.canPromote(board, topRow2));
    }
}