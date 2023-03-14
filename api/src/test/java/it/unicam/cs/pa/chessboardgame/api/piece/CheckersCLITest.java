package it.unicam.cs.pa.chessboardgame.api.piece;


import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersBoard;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersPosition;
import it.unicam.cs.pa.chessboardgame.api.model.color.CheckersColor;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPiece;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPieceType;
import it.unicam.cs.pa.chessboardgame.api.view.CheckersCLI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CheckersCLITest {
    CheckersCLI cli;

    @BeforeEach
    void setup() {
        cli = new CheckersCLI();
    }

    @Test
    void shouldDisplayBoardCorrectly() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CheckersBoard board = new CheckersBoard();
        cli.displayBoard(board);

        String expectedOutput =
                "  |  A  |  B  |  C  |  D  |  E  |  F  |  G  |  H  |\r\n" +
                        "- ------------------------------------------------- -\r\n" +
                        "8 |=====|  BM |=====|  BM |=====|  BM |=====|  BM | 8\r\n" +
                        "- ------------------------------------------------- -\r\n" +
                        "7 |  BM |=====|  BM |=====|  BM |=====|  BM |=====| 7\r\n" +
                        "- ------------------------------------------------- -\r\n" +
                        "6 |=====|  BM |=====|  BM |=====|  BM |=====|  BM | 6\r\n" +
                        "- ------------------------------------------------- -\r\n" +
                        "5 |     |=====|     |=====|     |=====|     |=====| 5\r\n" +
                        "- ------------------------------------------------- -\r\n" +
                        "4 |=====|     |=====|     |=====|     |=====|     | 4\r\n" +
                        "- ------------------------------------------------- -\r\n" +
                        "3 |  WM |=====|  WM |=====|  WM |=====|  WM |=====| 3\r\n" +
                        "- ------------------------------------------------- -\r\n" +
                        "2 |=====|  WM |=====|  WM |=====|  WM |=====|  WM | 2\r\n" +
                        "- ------------------------------------------------- -\r\n" +
                        "1 |  WM |=====|  WM |=====|  WM |=====|  WM |=====| 1\r\n" +
                        "- ------------------------------------------------- -\r\n" +
                        "  |  A  |  B  |  C  |  D  |  E  |  F  |  G  |  H  |\r\n";

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void shouldDisplayModifiedBoardCorrectly() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CheckersBoard board = new CheckersBoard();
        board.removePieceAt(new CheckersPosition(0, 1));
        board.setPieceAt(new CheckersPiece(CheckersColor.BLACK, CheckersPieceType.KING),
                new CheckersPosition(7, 2));
        cli.displayBoard(board);

        String expectedOutput =
                "  |  A  |  B  |  C  |  D  |  E  |  F  |  G  |  H  |\r\n" +
                        "- ------------------------------------------------- -\r\n" +
                        "8 |=====|     |=====|  BM |=====|  BM |=====|  BM | 8\r\n" +
                        "- ------------------------------------------------- -\r\n" +
                        "7 |  BM |=====|  BM |=====|  BM |=====|  BM |=====| 7\r\n" +
                        "- ------------------------------------------------- -\r\n" +
                        "6 |=====|  BM |=====|  BM |=====|  BM |=====|  BM | 6\r\n" +
                        "- ------------------------------------------------- -\r\n" +
                        "5 |     |=====|     |=====|     |=====|     |=====| 5\r\n" +
                        "- ------------------------------------------------- -\r\n" +
                        "4 |=====|     |=====|     |=====|     |=====|     | 4\r\n" +
                        "- ------------------------------------------------- -\r\n" +
                        "3 |  WM |=====|  WM |=====|  WM |=====|  WM |=====| 3\r\n" +
                        "- ------------------------------------------------- -\r\n" +
                        "2 |=====|  WM |=====|  WM |=====|  WM |=====|  WM | 2\r\n" +
                        "- ------------------------------------------------- -\r\n" +
                        "1 |  WM |=====|  BK |=====|  WM |=====|  WM |=====| 1\r\n" +
                        "- ------------------------------------------------- -\r\n" +
                        "  |  A  |  B  |  C  |  D  |  E  |  F  |  G  |  H  |\r\n";

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void shouldDisplayMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        cli.displayMessage("message should be displayed normally");

        String expectedOutput = "message should be displayed normally";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void shouldDisplayErrorMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        cli.displayErrorMessage("message should be displayed normally");

        String expectedOutput = "Error: message should be displayed normally\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testDeletePreviousLine() {
        // Create a mock PrintStream object
        PrintStream mockPrintStream = mock(PrintStream.class);

        // Redirect System.out to the mock PrintStream object
        System.setOut(mockPrintStream);

        // Call the method being tested
        cli.deletePreviousLine();

        // Verify that the mock PrintStream object was used to call System.out.format() with the correct parameter
        verify(mockPrintStream).format("\033[1A\033[2K");

        // Reset System.out to its original value
        System.setOut(System.out);
    }
}