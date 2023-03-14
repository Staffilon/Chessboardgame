package it.unicam.cs.pa.chessboardgame.api.piece;

import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPieceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckersPieceTypeTest {
    @Test
    void shouldReturnName() {
        assertEquals("Man", CheckersPieceType.MAN.getName());
        assertEquals("King", CheckersPieceType.KING.getName());
    }

    @Test
    void shouldReturnSymbol() {
        assertEquals("M", CheckersPieceType.MAN.getSymbol());
        assertEquals("K", CheckersPieceType.KING.getSymbol());
    }
}