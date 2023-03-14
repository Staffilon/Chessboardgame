package it.unicam.cs.pa.chessboardgame.api.model.player;

import it.unicam.cs.pa.chessboardgame.api.model.color.Color;

/**
 * The interface Player, which creates an abstraction for the representation of a player in a board game.
 */
public interface Player {
    /**
     * Returns the name of the player.
     *
     * @return the string representing the name of the player
     */
    String name();

    /**
     * Returns the color with which the player will play.
     *
     * @return the color with which the player will play
     */
    Color color();
}