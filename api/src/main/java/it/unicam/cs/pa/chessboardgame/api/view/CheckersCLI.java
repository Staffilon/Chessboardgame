package it.unicam.cs.pa.chessboardgame.api.view;

import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersBoard;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersSquare;
import it.unicam.cs.pa.chessboardgame.api.model.color.CheckersColor;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPiece;

import java.util.Scanner;

/**
 * The CheckersCLI class, which is responsible for the handling of the I/O of the game.
 */
public class CheckersCLI implements GameCLI<CheckersPosition, CheckersPiece, CheckersSquare, CheckersBoard> {
    private final Scanner scanner;

    /**
     * Instantiates a new Checkers CLI, initializing the scanner for the input and the movementManager.
     */
    public CheckersCLI() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public void deletePreviousLine() {
        System.out.format("\033[1A\033[2K");
    }

    @Override
    public void displayMessage(String message) {
        System.out.print(message);
    }

    @Override
    public void displayErrorMessage(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    @Override
    public String readInput() {
        return scanner.nextLine();
    }

    @Override
    public void displayBoard(CheckersBoard board) {
        int boardWidth = 49;

        printBoardPositionLetters();
        printBoardHorizontalOutline(boardWidth);

        for (int row = 0; row < 8; row++) {
            System.out.print((8 - row) + " "); // Rappresentazione dei numeri delle righe
            for (int col = 0; col < 8; col++) {
                printSquare(board, row, col);
            }
            System.out.print("|");
            System.out.print(" " + (8 - row));
            System.out.println();
            printBoardHorizontalOutline(boardWidth);
        }
        printBoardPositionLetters();
    }

    /**
     * Returns a string representation of the name of a piece. A white man for example would be called WM, and a
     * black king would be called WK.
     *
     * @param piece the piece that the name of is going to be returned
     * @return the string representation of the name of the given piece
     */
    private String getPieceName(CheckersPiece piece) {
        return "  " + piece.getColor().getSymbol() + piece.getPieceType().getSymbol() + " ";
    }

    /**
     * Print a horizontal line that separates the rows of the board.
     *
     * @param boardWidth the width of the board
     */
    private void printBoardHorizontalOutline(int boardWidth) {
        System.out.print("- ");
        for (int i = 0; i < boardWidth; i++) {
            System.out.print("-");
        }
        System.out.print(" -");
        System.out.println();
    }

    /**
     * Prints the letters that show the position on the board
     */
    private void printBoardPositionLetters() {
        System.out.print("  ");
        for (char c = 'A'; c < 'A' + 8; c++) {
            System.out.print("|  " + c + "  ");
        }
        System.out.print("|");
        System.out.println();
    }

    /**
     * Prints a square on the CLI, given the board and the row and column of the square.
     *
     * @param board  the board from which the square will be printed
     * @param row    the row on which the square is situated
     * @param column the column on which the square is situated
     */
    private void printSquare(CheckersBoard board, int row, int column) {
        System.out.print("|");
        CheckersSquare square = board.getSquareAt(new CheckersPosition(row, column));
        if (square.getColor() == CheckersColor.WHITE) {
            System.out.print("=====");
        } else if (!square.isOccupied()) {
            System.out.print("     ");
        } else {
            System.out.print(getPieceName(square.getPiece()));
        }
    }
}