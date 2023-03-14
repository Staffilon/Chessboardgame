package it.unicam.cs.pa.chessboardgame.api;

import it.unicam.cs.pa.chessboardgame.api.model.color.CheckersColor;
import it.unicam.cs.pa.chessboardgame.api.model.color.Color;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckersColorTest {
    @Test
    void shouldReturnCorrectNameAndSymbolForBlack() {
        Color color = CheckersColor.BLACK;
        assertEquals("Black", color.getName());
        assertEquals("B", color.getSymbol());
    }

    @Test
    void shouldReturnCorrectNameAndSymbolForWhite() {
        Color color = CheckersColor.WHITE;
        assertEquals("White", color.getName());
        assertEquals("W", color.getSymbol());
    }

    @Test
    void shouldOnlyContainBlackAndWhite() {
        Set<String> colors = new HashSet<>();
        for (CheckersColor color : CheckersColor.values()) {
            colors.add(color.name());
        }
        assertTrue(colors.contains("BLACK"));
        assertTrue(colors.contains("WHITE"));
        assertEquals(2, colors.size());
    }
}