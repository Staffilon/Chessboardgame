package it.unicam.cs.pa.chessboardgame.api.piece;

import it.unicam.cs.pa.chessboardgame.api.model.color.CheckersColor;
import it.unicam.cs.pa.chessboardgame.api.model.player.CheckersPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckersPlayerTest {
    @Test
    public void shouldCreatePlayerWithNameAndColor() {
        CheckersPlayer player = new CheckersPlayer("Alice", CheckersColor.BLACK);
        assertEquals("Alice", player.name());
        assertEquals(CheckersColor.BLACK, player.color());
    }

    @Test
    public void shouldReturnStringRepresentationOfPlayer() {
        CheckersPlayer player = new CheckersPlayer("Bob", CheckersColor.WHITE);
        String expectedString = "CheckersPlayer[name=Bob, color=WHITE]";
        assertEquals(expectedString, player.toString());
    }

    @Test
    public void shouldImplementEqualsAndHashCode() {
        CheckersPlayer player1 = new CheckersPlayer("Charlie", CheckersColor.BLACK);
        CheckersPlayer player2 = new CheckersPlayer("Charlie", CheckersColor.BLACK);
        CheckersPlayer player3 = new CheckersPlayer("Dave", CheckersColor.WHITE);
        assertTrue(player1.equals(player2) && player2.equals(player1));
        assertEquals(player1.hashCode(), player2.hashCode());
        assertNotEquals(player1, player3);
    }
}