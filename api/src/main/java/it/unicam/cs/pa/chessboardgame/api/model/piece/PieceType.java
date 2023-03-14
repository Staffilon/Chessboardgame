package it.unicam.cs.pa.chessboardgame.api.model.piece;

/**
 * The PieceType interface represent an abstraction for all
 * types of pieces, which should contain a name and a symbol.
 */
public interface PieceType {
    /**
     * Returns the name of the piece.
     *
     * @return the name of the piece
     */
    String getName();

    /**
     * Returns the symbol of the piece.
     *
     * @return the symbol of the piece
     */
    String getSymbol();
}