package it.unicam.cs.pa.chessboardgame.api.board;

import it.unicam.cs.pa.chessboardgame.api.model.board.BoardPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersPosition;
import it.unicam.cs.pa.chessboardgame.api.model.exceptions.InvalidBoardPositionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The CheckersPositionTest class contains the unit tests for the {@link CheckersPosition} class.
 */
public class CheckersPositionTest {
    @Test
    public void shouldHaveCorrectRowAndColumnAndName() {
        BoardPosition position = new CheckersPosition(2, 3);
        assertEquals(2, position.row());
        assertEquals(3, position.column());
        assertEquals("D6", position.getName());
    }

    @Test
    public void shouldThrowExceptionWithOutOfBoundsConstructor() {
        assertThrows(InvalidBoardPositionException.class, () -> {
            new CheckersPosition(-1, 0);
        });
        assertThrows(InvalidBoardPositionException.class, () -> {
            new CheckersPosition(0, -1);
        });
        assertThrows(InvalidBoardPositionException.class, () -> {
            new CheckersPosition(-1, -1);
        });

        assertThrows(InvalidBoardPositionException.class, () -> {
            new CheckersPosition(8, 0);
        });
        assertThrows(InvalidBoardPositionException.class, () -> {
            new CheckersPosition(0, 8);
        });
        assertThrows(InvalidBoardPositionException.class, () -> {
            new CheckersPosition(8, 8);
        });
    }

    @Test
    public void shouldReturnCorrectPositionName() {
        assertEquals("A8", new CheckersPosition(0, 0).getName());
        assertEquals("B7", new CheckersPosition(1, 1).getName());
        assertEquals("C6", new CheckersPosition(2, 2).getName());
        assertEquals("D5", new CheckersPosition(3, 3).getName());
        assertEquals("E4", new CheckersPosition(4, 4).getName());
        assertEquals("F3", new CheckersPosition(5, 5).getName());
        assertEquals("G2", new CheckersPosition(6, 6).getName());
        assertEquals("H1", new CheckersPosition(7, 7).getName());
        assertEquals("D4", new CheckersPosition(4, 3).getName());
        assertEquals("G1", new CheckersPosition(7, 6).getName());
    }

    @Test
    public void shouldReturnTrueIfPositionsAreEqual() {
        BoardPosition position1 = new CheckersPosition(2, 3);
        BoardPosition position2 = new CheckersPosition(2, 3);
        assertEquals(position1, position2);
    }

    @Test
    public void shouldReturnFalseIfPositionsAreNotEqual() {
        BoardPosition position1 = new CheckersPosition(2, 3);
        BoardPosition position2 = new CheckersPosition(3, 3);
        assertNotEquals(position1, position2);
    }

    @Test
    public void shouldHaveSameHashCodeIfPositionsAreEqual() {
        BoardPosition position1 = new CheckersPosition(2, 3);
        BoardPosition position2 = new CheckersPosition(2, 3);
        assertEquals(position1.hashCode(), position2.hashCode());
    }

    @Test
    public void shouldHaveDifferentHashCodeIfPositionsAreNotEqual() {
        BoardPosition position1 = new CheckersPosition(2, 3);
        BoardPosition position2 = new CheckersPosition(3, 3);
        assertNotEquals(position1.hashCode(), position2.hashCode());
    }
}