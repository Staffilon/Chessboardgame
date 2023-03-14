package it.unicam.cs.pa.chessboardgame.api.movement;

import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersSquare;
import it.unicam.cs.pa.chessboardgame.api.model.color.CheckersColor;
import it.unicam.cs.pa.chessboardgame.api.model.movement.CheckersMove;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPiece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckersMoveTest {
    @Test
    void shouldCreateNewCheckersMove() {

        CheckersSquare from = new CheckersSquare(new CheckersPiece(CheckersColor.BLACK), new CheckersPosition(0, 0), CheckersColor.BLACK);
        CheckersSquare to = new CheckersSquare(new CheckersPosition(1, 1), CheckersColor.WHITE);
        CheckersMove move = new CheckersMove(from, to);
        assertEquals(from, move.from());
        assertEquals(to, move.to());
    }

    @Test
    void shouldReturnFromSquare() {
        CheckersSquare from = new CheckersSquare(new CheckersPiece(CheckersColor.BLACK), new CheckersPosition(0, 0), CheckersColor.BLACK);
        CheckersSquare to = new CheckersSquare(new CheckersPosition(1, 1), CheckersColor.WHITE);
        CheckersMove move = new CheckersMove(from, to);
        assertEquals(from, move.from());
    }

    @Test
    void shouldReturnToSquare() {
        CheckersSquare from = new CheckersSquare(new CheckersPiece(CheckersColor.BLACK), new CheckersPosition(0, 0), CheckersColor.BLACK);
        CheckersSquare to = new CheckersSquare(new CheckersPosition(1, 1), CheckersColor.WHITE);
        CheckersMove move = new CheckersMove(from, to);
        assertEquals(to, move.to());
    }

    @Test
    public void shouldGiveValidLength() {
        CheckersSquare from = new CheckersSquare(new CheckersPiece(CheckersColor.BLACK), new CheckersPosition(1, 1), CheckersColor.BLACK);
        CheckersSquare to = new CheckersSquare(new CheckersPosition(2, 2), CheckersColor.BLACK);
        CheckersMove move = new CheckersMove(from, to);
        assertEquals(1, move.getMoveLength());
    }

    @Test
    public void shouldReturnMovedPiece() {
        CheckersPiece piece = new CheckersPiece(CheckersColor.BLACK);
        CheckersSquare from = new CheckersSquare(piece, new CheckersPosition(1, 1), null);
        CheckersSquare to = new CheckersSquare(new CheckersPosition(2, 2), null);
        CheckersMove move = new CheckersMove(from, to);
        assertEquals(piece, move.getMovedPiece());
    }

    @Test
    public void shouldReturnZeroMoveLength() {
        CheckersSquare from = new CheckersSquare(new CheckersPiece(CheckersColor.WHITE),
                new CheckersPosition(1, 2), null);
        CheckersSquare to = new CheckersSquare(new CheckersPosition(2, 4), null);
        CheckersMove move = new CheckersMove(from, to);
        int length = move.getMoveLength();
        assertEquals(0, length);
    }

    @Test
    public void shouldReturnMoveLengthOfTwo() {
        CheckersSquare from = new CheckersSquare(new CheckersPiece(CheckersColor.WHITE),
                new CheckersPosition(1, 2), null);
        CheckersSquare to = new CheckersSquare(new CheckersPosition(3, 4), null);
        CheckersMove move = new CheckersMove(from, to);
        int length = move.getMoveLength();
        assertEquals(2, length);
    }

    @Test
    public void shouldReturnMoveLengthOfOne() {
        CheckersSquare from = new CheckersSquare(new CheckersPiece(CheckersColor.WHITE),
                new CheckersPosition(1, 2), null);
        CheckersSquare to = new CheckersSquare(new CheckersPosition(2, 3), null);
        CheckersMove move = new CheckersMove(from, to);
        int length = move.getMoveLength();
        assertEquals(1, length);
    }
}