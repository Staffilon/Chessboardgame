package it.unicam.cs.pa.chessboardgame.api.piece;

import it.unicam.cs.pa.chessboardgame.api.model.color.CheckersColor;
import it.unicam.cs.pa.chessboardgame.api.model.exceptions.PieceAlreadyPromotedException;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPiece;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPieceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckersPieceTest {

    @Test
    void shouldCreateManPiece() {
        CheckersPiece manPiece = new CheckersPiece(CheckersColor.WHITE);
        assertEquals(CheckersPieceType.MAN, manPiece.getPieceType());
        assertFalse(manPiece.hasBeenPromoted());
    }

    @Test
    void shouldPromoteToKing() throws PieceAlreadyPromotedException {
        CheckersPiece manPiece = new CheckersPiece(CheckersColor.WHITE);
        manPiece.promote();
        assertEquals(CheckersPieceType.KING, manPiece.getPieceType());
        assertTrue(manPiece.hasBeenPromoted());
    }

    @Test
    void shouldThrowExceptionIfAlreadyPromoted() throws PieceAlreadyPromotedException {
        CheckersPiece kingPiece = new CheckersPiece(CheckersColor.WHITE, CheckersPieceType.KING);
        PieceAlreadyPromotedException e = assertThrows(PieceAlreadyPromotedException.class, kingPiece::promote);
        assertEquals("checkers piece: " + kingPiece + " is already promoted", e.getMessage());
    }

    @Test
    void shouldThrowExceptionIfPromotingToNullPiece() {
        CheckersPiece manPiece = new CheckersPiece(CheckersColor.WHITE);
        NullPointerException e = assertThrows(NullPointerException.class, () -> {
            manPiece.promote(null);
        });
        assertEquals("The promotion type is null", e.getMessage());
    }

    @Test
    void shouldReturnCorrectColor() {
        CheckersPiece blackPiece = new CheckersPiece(CheckersColor.BLACK);
        assertEquals(CheckersColor.BLACK, blackPiece.getColor());
    }

    @Test
    void shouldSetColor() {
        CheckersPiece whitePiece = new CheckersPiece(CheckersColor.WHITE);
        whitePiece.setColor(CheckersColor.BLACK);
        assertEquals(CheckersColor.BLACK, whitePiece.getColor());
    }
}