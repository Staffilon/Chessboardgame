package it.unicam.cs.pa.chessboardgame.api.model.color;

/**
 * The enum CheckersColor, which represents the colors of the checkers
 * game that is being implemented.
 */
public enum CheckersColor implements Color {
    /**
     * Black checkers piece color.
     */
    BLACK("Black", "B"),
    /**
     * White checkers piece color.
     */
    WHITE("White", "W");

    private final String name;
    private final String symbol;

    CheckersColor(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
}