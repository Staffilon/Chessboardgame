package it.unicam.cs.pa.chessboardgame.api;

import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersPosition;
import it.unicam.cs.pa.chessboardgame.api.util.CheckersStringToPositionParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CheckersStringToPositionParserTest {
    @Test
    public void shouldConvertNameIntoPosition() {
        String position1 = "A1";
        String position2 = "A8";
        String position3 = "H1";
        String position4 = "H8";
        String position5 = "C6";

        assertEquals(new CheckersPosition(7, 0), CheckersStringToPositionParser.parse(position1));
        assertEquals(new CheckersPosition(0, 0), CheckersStringToPositionParser.parse(position2));
        assertEquals(new CheckersPosition(7, 7), CheckersStringToPositionParser.parse(position3));
        assertEquals(new CheckersPosition(0, 7), CheckersStringToPositionParser.parse(position4));
        assertEquals(new CheckersPosition(2, 2), CheckersStringToPositionParser.parse(position5));
    }

    @Test
    public void shouldNotConvertNameIntoPosition() {
        String position1 = "A0";
        String position2 = "A9";
        String position3 = "H13";
        String position4 = "7";
        String position5 = "C";

        assertNull(CheckersStringToPositionParser.parse(position1));
        assertNull(CheckersStringToPositionParser.parse(position2));
        assertNull(CheckersStringToPositionParser.parse(position3));
        assertNull(CheckersStringToPositionParser.parse(position4));
        assertNull(CheckersStringToPositionParser.parse(position5));
    }
}