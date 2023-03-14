package it.unicam.cs.pa.chessboardgame.api.controller;

import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersBoard;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersSquare;
import it.unicam.cs.pa.chessboardgame.api.model.color.CheckersColor;
import it.unicam.cs.pa.chessboardgame.api.model.color.Color;
import it.unicam.cs.pa.chessboardgame.api.model.movement.CheckersMove;
import it.unicam.cs.pa.chessboardgame.api.model.movement.MoveExecutor;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPiece;
import it.unicam.cs.pa.chessboardgame.api.model.player.CheckersPlayer;
import it.unicam.cs.pa.chessboardgame.api.view.CheckersCLI;

public class CheckersGameController implements
        GameController<CheckersPosition, CheckersPiece, CheckersSquare, CheckersBoard>,
        MoveExecutor<CheckersPosition, CheckersPiece, CheckersSquare, CheckersBoard, CheckersMove> {
    private CheckersBoard board;
    private CheckersCLI cli;
    private CheckersPlayer blackSidePlayer, whiteSidePlayer;

    public CheckersGameController() {
        this.board = new CheckersBoard();
        this.cli = new CheckersCLI();
    }

    public CheckersGameController(CheckersCLI cli) {
        this.cli = cli;
    }

    public void start() {
        initializePlayers();
        displayBoardWithPlayerData();
        //cli.displayBoard(board);
    }

    @Override
    public String getPlayerNameFromInput(Color side) {
        cli.displayMessage("Insert name for " + side.getName() + " player: ");
        return cli.readInput();
    }

    private void initializePlayers() {
        String whiteSidePlayerName = getValidPlayerName(CheckersColor.WHITE);
        whiteSidePlayer = new CheckersPlayer(whiteSidePlayerName, CheckersColor.WHITE);
        String blackSidePlayerName = getValidPlayerName(CheckersColor.BLACK);
        blackSidePlayer = new CheckersPlayer(blackSidePlayerName, CheckersColor.BLACK);
    }

    private boolean isPlayerNameValid(String playerName) {
        if (playerName.isBlank()) {
            return false;
        }
        return true;
    }

    private String getValidPlayerName(CheckersColor side) {
        String playerName = getPlayerNameFromInput(side);
        while (!isPlayerNameValid(playerName)) {
            cli.deletePreviousLine();
            cli.displayErrorMessage("Player name cannot be blank, please try again!");
            playerName = getPlayerNameFromInput(side);
        }
        return playerName;
    }

    private void printPlayer(CheckersPlayer player) {
        String playerData = "Color: " + player.color() + " - Name: " + player.name() + "\n";
        cli.displayMessage(playerData);
    }

    private void displayBoardWithPlayerData() {
        printPlayer(blackSidePlayer);
        cli.displayBoard(board);
        printPlayer(whiteSidePlayer);
    }

    @Override
    public void makeTurn(Color side) {

    }

    @Override
    public void resetGame() {

    }

    @Override
    public void exitGame() {

    }

    @Override
    public boolean getGameState() {
        return false;
    }

    @Override
    public void makeMove(CheckersBoard board, CheckersMove move, Color side) {

    }



    /*@Override
    public CheckersMove readMove(CheckersBoard board, Color playerTurn) {
        displayBoard(board);
        CheckersPosition startingPosition = getValidStartingPosition(board, playerTurn);
        CheckersPosition endingPosition = getValidEndingPosition(board, board.getSquareAt(startingPosition));
        return new CheckersMove(board.getSquareAt(startingPosition), board.getSquareAt(endingPosition));
    }

    *//**
     * Returns a valid position as the starting point of a move by interacting with the player.
     *
     * @param board      the board on which the players are playing
     * @param playerTurn the color of the side of the player who has to make a move
     * @return returna a valid starting position of a move
     *//*
    private CheckersPosition getValidStartingPosition(CheckersBoard board, Color playerTurn) {
        CheckersPosition startingPosition = choosePieceToMove(board, playerTurn);
        while (startingPosition == null) {
            clearScreen();
            displayBoard(board);
            System.out.println("Invalid starting position!");
            startingPosition = choosePieceToMove(board, playerTurn);
        }
        return startingPosition;
    }

    private CheckersPosition getValidEndingPosition(CheckersBoard board, CheckersSquare startingSquare) {
        CheckersPosition endingPosition = chooseLandingSquare(board, startingSquare);
        while (endingPosition == null) {
            clearScreen();
            displayBoard(board);
            System.out.println("Invalid ending position!");
            System.out.println("Starting position: " + startingSquare.getPosition().getName());
            endingPosition = chooseLandingSquare(board, startingSquare);
        }
        return endingPosition;
    }

    private CheckersPosition choosePieceToMove(CheckersBoard board, Color playerTurn) {
        System.out.println("Choose a " + playerTurn + " piece to move, the available ones are:");
        printAvailableSquaresNames(board, playerTurn);

        System.out.print("Your choice: ");
        String position = scanner.nextLine();
        return CheckersStringToPositionParser.parse(position);
    }

    private void printAvailableSquaresNames(CheckersBoard board, Color playerTurn) {
        List<CheckersSquare> availableSquaresToMove = board.getOccupiedSquares().stream()
                .filter(square -> square.getPiece().getColor() == playerTurn)
                .filter(square -> movementManager.getValidMoves(board, square).size() > 0)
                .toList();

        for (CheckersSquare square : availableSquaresToMove) {
            System.out.print(square.getPosition().getName() + " ");
        }
        System.out.println();
    }

    private CheckersPosition chooseLandingSquare(CheckersBoard board, CheckersSquare startingSquare) {
        System.out.println("Choose a square for your piece to land on, the available squares are: ");
        printAvailableSquaresNames(board, startingSquare);

        System.out.print("Your choice: ");
        String position = scanner.nextLine();
        return CheckersStringToPositionParser.parse(position);
    }

    private void printAvailableSquaresNames(CheckersBoard board, CheckersSquare startingSquare) {
        List<Move<CheckersPosition, CheckersPiece, CheckersSquare>> availableMoves =
                movementManager.getValidMoves(board, startingSquare);

        for (Move<CheckersPosition, CheckersPiece, CheckersSquare> move : availableMoves) {
            System.out.print(move.to().getPosition().getName() + " ");
        }
        System.out.println();
    }*/

}