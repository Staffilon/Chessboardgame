package it.unicam.cs.pa.chessboardgame.api.util;

import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersPosition;

/**
 * The CheckersStringToPositionParser class, which is used to parse Strings containing the names of positions into CheckersPosition
 * records.
 */
public class CheckersStringToPositionParser {
    /**
     * Parses the string containing the name of a position into the corresponding position.
     *
     * @param positionName the string containing the name of the position
     * @return the CheckersPosition corresponding to the given name if the name is valid, null otherwise
     */
    public static CheckersPosition parse(String positionName) {
        if (positionName == null || positionName.length() != 2) {
            return null;
        }

        char letter = positionName.charAt(0);
        int number = Character.getNumericValue(positionName.charAt(1));
        if (letter < 'A' || letter > 'H' || number < 1 || number > 8) {
            return null;
        }

        int row = 8 - number;
        int column = letter - 'A';
        return new CheckersPosition(row, column);
    }
}