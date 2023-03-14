package it.unicam.cs.pa.chessboardgame.api.board;

import it.unicam.cs.pa.chessboardgame.api.model.board.BoardPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersPosition;
import it.unicam.cs.pa.chessboardgame.api.model.exceptions.InvalidBoardPositionException;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersBoardDimensions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The CheckersBoardDimensionsTest class contains the unit tests for the {@link CheckersBoardDimensions} class.
 */
public class CheckersBoardDimensionsTest {
    @Test
    public void shouldHaveCorrectRowAndColumn() {
        BoardPosition position = new CheckersPosition(2, 3);
        assertEquals(2, position.row());
        assertEquals(3, position.column());
    }

    @Test
    public void shouldThrowExceptionWithInvalidValuesConstructor() {
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
            new CheckersPosition(9, 8);
        });
        assertThrows(InvalidBoardPositionException.class, () -> {
            new CheckersPosition(8, 9);
        });
        assertThrows(InvalidBoardPositionException.class, () -> {
            new CheckersPosition(9, 9);
        });
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