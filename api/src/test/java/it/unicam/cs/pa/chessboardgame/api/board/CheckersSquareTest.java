package it.unicam.cs.pa.chessboardgame.api.board;


import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersSquare;
import it.unicam.cs.pa.chessboardgame.api.model.color.CheckersColor;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPiece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The CheckersSquareTest class contains the unit tests for the {@link CheckersSquare} class.
 */
public class CheckersSquareTest {
    private CheckersSquare square;
    private CheckersPiece piece;

    @BeforeEach
    void setUp() {
        square = new CheckersSquare(new CheckersPosition(1, 1), CheckersColor.WHITE);
        piece = new CheckersPiece(CheckersColor.WHITE);
    }

    @Test
    void shouldReturnCorrectPosition() {
        assertEquals(new CheckersPosition(1, 1), square.getPosition());
    }

    @Test
    void shouldReturnTrueIfSquareIsOccupied() {
        assertFalse(square.isOccupied());
        square.setPiece(piece);
        assertTrue(square.isOccupied());
    }

    @Test
    void shouldReturnFalseIfSquareIsNotOccupied() {
        assertFalse(square.isOccupied());
    }

    @Test
    void shouldReturnCorrectPiece() {
        square.setPiece(piece);
        assertEquals(piece, square.getPiece());
    }

    @Test
    void shouldSetPieceCorrectly() {
        square.setPiece(piece);
        assertEquals(piece, square.getPiece());
    }

    @Test
    void shouldClearPieceFromSquare() {
        square.setPiece(piece);
        square.clear();
        assertNull(square.getPiece());
    }

    @Test
    void shouldReturnCorrectColor() {
        assertEquals(CheckersColor.WHITE, square.getColor());
    }
}