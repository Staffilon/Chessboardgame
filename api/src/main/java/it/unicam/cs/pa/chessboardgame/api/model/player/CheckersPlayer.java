package it.unicam.cs.pa.chessboardgame.api.model.player;

import it.unicam.cs.pa.chessboardgame.api.model.color.CheckersColor;

/**
 * The CheckersPlayer class, which represent a player, and it's core attributes, like name and color.
 */
public record CheckersPlayer(String name, CheckersColor color) implements Player {
}