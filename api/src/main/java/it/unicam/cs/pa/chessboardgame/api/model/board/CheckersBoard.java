package it.unicam.cs.pa.chessboardgame.api.model.board;

import it.unicam.cs.pa.chessboardgame.api.model.color.CheckersColor;
import it.unicam.cs.pa.chessboardgame.api.model.exceptions.InvalidBoardPositionException;
import it.unicam.cs.pa.chessboardgame.api.model.exceptions.NullPieceException;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPiece;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

/**
 * The CheckersBoard class, which manages the elements on the board, such as squares and pieces.
 */
public class CheckersBoard implements Board<CheckersPosition, CheckersPiece, CheckersSquare> {
    private final CheckersBoardDimensions boardDimensions;
    private CheckersSquare[][] squares;

    /**
     * Instantiates a new Checkers board.
     */
    public CheckersBoard() {
        this.boardDimensions = new CheckersBoardDimensions(8, 8);
        initializeSquares();
    }

    /**
     * Initializes the squares as if it was the start of the game.
     */
    private void initializeSquares() {
        squares = new CheckersSquare[boardDimensions.rows()][boardDimensions.columns()];

        for (int row = 0; row < boardDimensions.rows(); row++) {
            for (int col = 0; col < boardDimensions.columns(); col++) {
                squares[row][col] = createStartingSquare(row, col);
            }
        }
    }

    /**
     * Creates the starting square of the board based on the selected
     * row and column of the board.
     *
     * @param row    the row on which the square will be created
     * @param column the column on which the square will be created
     * @return the newly created square of the initial board
     */
    private CheckersSquare createStartingSquare(int row, int column) {
        CheckersPosition position = createSquarePosition(row, column);
        CheckersColor color = createSquareColor(position);
        CheckersPiece piece = createInitialPiece(position);
        return new CheckersSquare(piece, position, color);
    }

    /**
     * Creates a starting piece of the board, based on the given position. If the position
     * is not corresponding to a square on which a piece should be, it will return null.
     *
     * @param position the position on which the piece might be created
     * @return the piece of the board if the position was the right one, null otherwise
     */
    private CheckersPiece createInitialPiece(CheckersPosition position) {
        if (isPositionOutOfBounds(position)) {
            return null;
        }
        if ((position.row() + position.column()) % 2 == 1) {
            if (position.row() < 3) {
                return new CheckersPiece(CheckersColor.BLACK);
            } else if (position.row() > 4) {
                return new CheckersPiece(CheckersColor.WHITE);
            }
        }
        return null;
    }

    /**
     * Creates the position of a square, based on the selected row and column
     *
     * @param row    the row of the position
     * @param column the column of the position
     * @return the newly created position
     */
    private CheckersPosition createSquarePosition(int row, int column) {
        return new CheckersPosition(row, column);
    }

    /**
     * Creates the color of the square based on the given position of the board.
     *
     * @param position the position of the square we want to create the color for
     * @return the color of the square
     */
    private CheckersColor createSquareColor(CheckersPosition position) {
        CheckersColor color;
        if ((position.row() + position.column()) % 2 == 0) {
            color = CheckersColor.WHITE;
        } else {
            color = CheckersColor.BLACK;
        }

        return color;
    }

    /**
     * Determines if the given position is out of the bounds of the board.
     *
     * @param position the position we're checking
     * @return true is the position is within bounds, false otherwise
     */
    public boolean isPositionOutOfBounds(CheckersPosition position) {
        return position.row() < 0 || position.row() >= boardDimensions.rows() ||
                position.column() < 0 || position.column() >= boardDimensions.columns();
    }

    @Override
    public CheckersSquare getSquareAt(CheckersPosition position) {
        if (isPositionOutOfBounds(position)) {
            return null;
        }

        return squares[position.row()][position.column()];
    }

    @Override
    public CheckersPiece getPieceAt(CheckersPosition position) {
        CheckersSquare square = getSquareAt(position);
        if (square == null) {
            return null;
        }

        return square.getPiece();
    }

    @Override
    public void setPieceAt(CheckersPiece piece, CheckersPosition position) throws NullPieceException {
        if (piece == null) {
            throw new NullPieceException("Cannot set a null piece on the board");
        }

        CheckersSquare square = getSquareAt(position);
        square.setPiece(piece);
    }

    @Override
    public CheckersPiece removePieceAt(CheckersPosition position) throws NullPieceException {
        CheckersSquare square = getSquareAt(position);
        if (square == null) {
            return null;
        }

        if (!square.isOccupied()) {
            throw new NullPieceException("Cannot remove a null piece from the board at the position " + position);
        }

        CheckersPiece piece = square.getPiece();
        square.clear();
        return piece;
    }

    @Override
    public boolean isOccupiedAt(CheckersPosition position) throws InvalidBoardPositionException {
        CheckersSquare square = getSquareAt(position);

        return square.isOccupied();
    }

    @Override
    public BoardDimensions getDimensions() {
        return boardDimensions;
    }

    @Override
    public Iterator<CheckersPiece> pieceIterator() {
        return stream(squares)
                .flatMap(Arrays::stream)
                .filter(CheckersSquare::isOccupied)
                .map(CheckersSquare::getPiece)
                .iterator();
    }

    @Override
    public List<CheckersSquare> getAccessibleSquares(CheckersSquare square) {
        CheckersPosition position = square.getPosition();

        return Arrays.stream(squares).flatMap(Arrays::stream)
                .filter(adjSquare -> {
                    CheckersPosition adjPos = adjSquare.getPosition();
                    return (Math.abs(position.row() - adjPos.row()) == 1 &&
                            Math.abs(position.column() - adjPos.column()) == 1) ||
                            (Math.abs(position.row() - adjPos.row()) == 2 &&
                                    Math.abs(position.column() - adjPos.column()) == 2);
                })
                .collect(Collectors.toList());
    }

    /**
     * Returns the squares occupied by the pieces. if there's no piece, it returns null
     *
     * @return the list of occupied squares if there is at least one piece on the board, null otherwise
     */
    public List<CheckersSquare> getOccupiedSquares() {
        return Arrays.stream(squares).flatMap(Arrays::stream)
                .filter(CheckersSquare::isOccupied).collect(Collectors.toList());
    }

    /**
     * Removes all the pieces from the board, leaving it empty.
     */
    public void clearBoard() {
        List<CheckersSquare> squares = getOccupiedSquares();
        for (CheckersSquare square : squares) {
            removePieceAt(square.getPosition());
        }
    }
}