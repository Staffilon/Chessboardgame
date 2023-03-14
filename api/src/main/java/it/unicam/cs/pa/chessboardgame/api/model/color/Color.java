package it.unicam.cs.pa.chessboardgame.api.model.color;
/**
 * The Color interface creates an abstraction for the representation of colors in the board
 * based games.
 */
public interface Color {
    /**
     * Returns the name of the color.
     *
     * @return the name of the color
     */
    String getName();

    /**
     * Returns the symbol of the color.
     *
     * @return the symbol of the color
     */
    String getSymbol();
}