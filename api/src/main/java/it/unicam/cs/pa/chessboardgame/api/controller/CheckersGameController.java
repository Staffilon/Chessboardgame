package it.unicam.cs.pa.chessboardgame.api.controller;

import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersBoard;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersSquare;
import it.unicam.cs.pa.chessboardgame.api.model.color.CheckersColor;
import it.unicam.cs.pa.chessboardgame.api.model.color.Color;
import it.unicam.cs.pa.chessboardgame.api.model.movement.CheckersMove;
import it.unicam.cs.pa.chessboardgame.api.model.movement.CheckersMovementManager;
import it.unicam.cs.pa.chessboardgame.api.model.movement.Move;
import it.unicam.cs.pa.chessboardgame.api.model.movement.MoveExecutor;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPiece;
import it.unicam.cs.pa.chessboardgame.api.model.player.CheckersPlayer;
import it.unicam.cs.pa.chessboardgame.api.model.rules.CheckersGameRules;
import it.unicam.cs.pa.chessboardgame.api.util.CheckersStringToPositionParser;
import it.unicam.cs.pa.chessboardgame.api.view.CheckersCLI;

import java.util.List;

/**
 * The CheckersGameController class, which manages the input and output between the model and the view of the
 * Checkers board game.
 */
public class CheckersGameController implements
        GameController<CheckersPosition, CheckersPiece, CheckersSquare, CheckersBoard>,
        MoveExecutor<CheckersPosition, CheckersPiece, CheckersSquare, CheckersMove> {
    private CheckersBoard board;
    private final CheckersCLI cli;
    private CheckersPlayer blackSidePlayer, whiteSidePlayer;
    private final CheckersMovementManager movementManager;
    private final CheckersGameRules gameRules;
    private int movesBeforeRepetitionDraw;

    /**
     * Instantiates a new Checkers game controller.
     */
    public CheckersGameController() {
        this.gameRules = new CheckersGameRules(40);
        this.cli = new CheckersCLI();
        this.movementManager = new CheckersMovementManager();
        this.board = new CheckersBoard();
        movesBeforeRepetitionDraw = 0;
    }

    /**
     * Instantiates a new Checkers game controller, with a CLI as an argument. It is used mostly during testing.
     *
     * @param cli the CLI which is going to be used to display output on the terminal and take input from the player
     */
    public CheckersGameController(CheckersCLI cli) {
        this.gameRules = new CheckersGameRules(40);
        this.board = new CheckersBoard();
        this.movementManager = new CheckersMovementManager();
        this.cli = cli;
        movesBeforeRepetitionDraw = 0;
    }
    @Override
    public void resetGame() {
        this.board = new CheckersBoard();
        movesBeforeRepetitionDraw = 0;
        refreshBoard();
        start();
    }

    @Override
    public void exitGame() {
        System.exit(0);
    }

    @Override
    public boolean getGameState() {
        if(isAnyRuleTrue(CheckersColor.BLACK) || isAnyRuleTrue(CheckersColor.WHITE)) {
            return true;
        }
        return false;
    }

    /**
     * Starts the game.
     */
    public void start() {
        initializePlayers();
        refreshBoard();
        while (true) {
            if(isAnyRuleTrue(CheckersColor.WHITE)){break;}
            makeTurn(CheckersColor.WHITE);
            if(isAnyRuleTrue(CheckersColor.BLACK)){break;}
            makeTurn(CheckersColor.BLACK);
        }
        displayEndResult();
        whichActionAfterGame();
    }

    /**
     * Updates the move counter towards the draw by stale position.
     */
    private void updateStaleMoveCounter(){
        if(gameRules.areConditionsForDrawByRepetitionMet(board)){
            movesBeforeRepetitionDraw++;
        }
    }

    /**
     * Displays the end result of the game.
     */
    private void displayEndResult(){
        refreshBoard();
        Color winningSide = gameRules.getWinnerSide(board);
        if(winningSide != null){
            cli.displayMessage(winningSide + " has won!");
        } else if (gameRules.isLossByStalePosition(board, CheckersColor.WHITE)){
            cli.displayMessage(CheckersColor.BLACK.getName() + " side has won, "+ CheckersColor.WHITE.getName() +" side cannot move.");
        }else if (gameRules.isLossByStalePosition(board, CheckersColor.BLACK)){
            cli.displayMessage(CheckersColor.WHITE.getName() + " side has won, "+ CheckersColor.BLACK.getName() +" side cannot move.");
        }else if(gameRules.isDraw(board)){
            cli.displayMessage("The game resulted in a draw, no piece can be moved!");
        }else if(gameRules.isDrawByRepetition(movesBeforeRepetitionDraw)){
            cli.displayMessage("The game resulted in a draw, "+ movesBeforeRepetitionDraw +" moves have been made!");
        }
        cli.displayMessage("\n");
    }

    /**
     * Manages the next action after the ending of a game.
     * The players can play again or exit the game
     */
    private void whichActionAfterGame() {
        cli.displayMessage("Do you want to play again? (y/n): ");
        String reply = cli.readInput();
        while(!reply.equals("y") && !reply.equals("n")){
            cli.deletePreviousLine();
            cli.displayErrorMessage("reply not valid!");
            cli.displayMessage("Do you want to play again? (y/n): ");
            reply = cli.readInput();
        }
        if(reply.equals("y")){
            resetGame();
        } else {
            exitGame();
        }
    }

    @Override
    public String getPlayerNameFromInput(Color side) {
        cli.displayMessage("Insert name for " + side.getName() + " player: ");
        return cli.readInput();
    }

    /**
     * Creates the players that will play the match.
     */
    private void initializePlayers() {
        String whiteSidePlayerName = getValidPlayerName(CheckersColor.WHITE);
        whiteSidePlayer = new CheckersPlayer(whiteSidePlayerName, CheckersColor.WHITE);
        String blackSidePlayerName = getValidPlayerName(CheckersColor.BLACK);
        blackSidePlayer = new CheckersPlayer(blackSidePlayerName, CheckersColor.BLACK);
    }

    /**
     * Checks is a given name is valid for a player.
     *
     * @param playerName the string containing the name
     * @return true if the name is valid, false otherwise
     */
    private boolean isPlayerNameValid(String playerName) {
        if (playerName.isBlank()) {
            return false;
        }
        return true;
    }

    /**
     * Asks for the name of a player until is doesn't get a valid input.
     *
     * @param side the side on which the player will play
     * @return the string containing a valid name
     */
    private String getValidPlayerName(CheckersColor side) {
        String playerName = getPlayerNameFromInput(side);
        while (!isPlayerNameValid(playerName)) {
            cli.deletePreviousLine();
            cli.displayErrorMessage("Player name cannot be blank, please try again!");
            playerName = getPlayerNameFromInput(side);
        }
        return playerName;
    }

    /**
     * Prints on the console the data of the given player.
     *
     * @param player the player which will have its data printed
     */
    private void printPlayer(CheckersPlayer player) {
        String playerData = "Color: " + player.color() + " - Name: " + player.name() + "\n";
        cli.displayMessage(playerData);
    }

    /**
     * Refreshes the screen on the console, printing out the data of the players and the board.
     */
    private void refreshBoard(){
        cli.clearScreen();
        displayBoardWithPlayerData();
    }

    /**
     * Prints on the console the board alongside the data of the players.
     */
    private void displayBoardWithPlayerData() {
        printPlayer(blackSidePlayer);
        cli.displayBoard(board);
        printPlayer(whiteSidePlayer);
    }

    /**
     * Manages a turn during the game, making the necessary moves and updating the moves towards the stale position draw
     * if needed.
     *
     * @param side the side which has to make a turn
     */
    @Override
    public void makeTurn(Color side) {
        updateStaleMoveCounter();
        CheckersMove move = readMove(side);
        makeMove(move, side);
        refreshBoard();
        while(move.getMoveLength() == 2 && canCaptureAgain(move)){
            CheckersSquare startingSquare = board.getSquareAt(move.to().getPosition());

            move = new CheckersMove(startingSquare,
                        board.getSquareAt(getValidEndingPosition(side, startingSquare)));
            makeMove(move, side);
            refreshBoard();
        }

    }

    /**
     * Checks is any game ending rule has become true.
     *
     * @param side the side which will start the turn after the controls
     * @return true if a game ending rule has become true, false otherwise
     */
    public boolean isAnyRuleTrue(Color side){
        if(gameRules.isDraw(board) || gameRules.isDrawByRepetition(movesBeforeRepetitionDraw) ||
        gameRules.isLossByStalePosition(board, side) || gameRules.getWinnerSide(board) != null){
            return true;
        }
        return false;
    }

    @Override
    public void makeMove(CheckersMove move, Color side) {
        if(move.getMovedPiece().getColor() == side) {
            if (movementManager.isCaptureMove(board, move)) {
                CheckersPosition capturedPiecePosition = new CheckersPosition(
                        (move.from().getPosition().row() + move.to().getPosition().row()) / 2,
                        (move.from().getPosition().column() + move.to().getPosition().column()) / 2);
                board.removePieceAt(capturedPiecePosition);
            }

            board.setPieceAt(move.getMovedPiece(), move.to().getPosition());
            board.removePieceAt(move.from().getPosition());
            if(gameRules.canPromote(board, move.to().getPosition())){
                board.getPieceAt(move.to().getPosition()).promote();
            }
        }
    }

    /**
     * Checks whether another capture can be made.
     *
     * @param move the past move involving a capture
     * @return true if another capture can be made, false otherwise
     */
    private boolean canCaptureAgain(CheckersMove move){
        if(movementManager.canCaptureFromSquare(board, board.getSquareAt(move.to().getPosition()))){
            return true;
        }
        return false;
    }

    /**
     * Gets a move from the player input and returns it.
     *
     * @param playerTurn the side which is currently inserting a move
     * @return the move that has been made
     */
    public CheckersMove readMove(Color playerTurn) {
        CheckersPosition startingPosition = getValidStartingPosition(playerTurn);
        refreshBoard();
        CheckersPosition endingPosition = getValidEndingPosition(playerTurn, board.getSquareAt(startingPosition));
        return new CheckersMove(board.getSquareAt(startingPosition), board.getSquareAt(endingPosition));
    }

    /**
     * Requests from player input the starting position of a move.
     *
     * @param side the side which is currently moving
     * @return the string containing the position
     */
    private String requestStartingPosition(Color side){
        cli.displayMessage(side.getName() + " side, available pieces are on: " + getAvailableSquaresNames(side) + "\n");
        cli.displayMessage("Choose your piece: ");
        return cli.readInput();
    }

    /**
     * Requests from player input the ending position of a move.
     *
     * @param side the side which is currently moving
     * @param startingSquare the starting square of the ongoing move creation
     * @return the string containing the position
     */
    private String requestEndingPosition(Color side, CheckersSquare startingSquare){
        cli.displayMessage(side.getName() + " side, available landing squares from " + startingSquare.getPosition().getName() +
                " are on: " +
                getAvailableSquaresNames(side, startingSquare) + "\n");
        cli.displayMessage("Choose where to move: ");
        return cli.readInput();
    }

    /**
     * Returns a string containing the names of the positions of the squares that are occupied by pieces that can be moved.
     *
     * @param side the side which is looking for moves
     * @return the string containing the available positions
     */
    private String getAvailableSquaresNames(Color side) {
        StringBuilder availableSquares = new StringBuilder();
        List<CheckersSquare> availableSquaresToMove = getMovablePieces(side);

        for (CheckersSquare square : availableSquaresToMove) {
            availableSquares.append(square.getPosition().getName()).append(" ");
        }
        return availableSquares.toString();
    }

    /**
     * Returns the string containing the names of the positions of the squares that can be reached with a valid move
     * from a starting square given as input.
     *
     * @param side the side which is looking for moves
     * @param startingSquare the square from which we are looking for available valid ending squares of a move
     * @return the string containing the names of the positions of the available squares
     */
    private String getAvailableSquaresNames(Color side, CheckersSquare startingSquare) {
        StringBuilder availableSquares = new StringBuilder();
        List<CheckersSquare> availableLandingSquares = getValidLandingSquares(side, startingSquare);

        for (CheckersSquare square : availableLandingSquares) {
            availableSquares.append(square.getPosition().getName()).append(" ");
        }
        return availableSquares.toString();
    }

    /**
     * Returns a list with the squares that contain pieces which can make valid moves.
     *
     * @param side the side which is looking for movable pieces
     * @return the list with the valid squares
     */
    private List<CheckersSquare> getMovablePieces(Color side){
        List<CheckersSquare> movablePieces = board.getOccupiedSquares().stream()
                .filter(square -> square.getPiece().getColor() == side)
                .filter(square -> movementManager.getValidMoves(board, square).size() > 0)
                .toList();
        if(containsCaptureMove(movablePieces)){
            return getCaptureMovesStartingSquares(movablePieces);
        }
        return movablePieces;
    }

    /**
     * Checks if a list of given squares can be part of moves that involve the capture of pieces.
     *
     * @param startingSquares the starting squares from which we're checking for capture moves
     * @return true if capture moves can be made from those squares, false otherwise
     */
    private boolean containsCaptureMove(List<CheckersSquare> startingSquares){
       if(startingSquares.stream().anyMatch(square -> movementManager.canCaptureFromSquare(board, square))){
           return true;
       }
       return false;
    }

    /**
     * Returns the starting squares that can be a part of a valid move involving a capture form a list of squares.
     *
     * @param startingSquares the list from which we're going to check for squares involved in capture moves
     * @return the list of starting squares involved in capture moves
     */
    private List<CheckersSquare> getCaptureMovesStartingSquares(List<CheckersSquare> startingSquares){
        return startingSquares.stream().filter(square -> movementManager.canCaptureFromSquare(board, square)).toList();
    }

    /**
     * Returns the valid landing squares that can be accessed from a given starting square.
     *
     * @param side the side which is making the move
     * @param startingSquare the square from which the move will be made
     * @return the list of valid landing squares that can be accessed from the given starting square
     */
    private List<CheckersSquare> getValidLandingSquares(Color side, CheckersSquare startingSquare){
        List<CheckersSquare> validLandingSquares = movementManager.getValidMoves(board, startingSquare).stream()
                .filter(move -> move.getMovedPiece().getColor() == side)
                .map(Move::to).toList();
        if(movementManager.canCaptureFromSquare(board, startingSquare)){
            return getLandingCaptureSquares(side, startingSquare);
        }
        return validLandingSquares;
    }

    /**
     * Returns a list with the landing squares that are part of a move involving a capture from a given starting square.
     *
     * @param side the side which is currently making the move
     * @param startingSquare the square from which the move will begin
     * @return the list of valid landing squares involving a capture
     */
    private List<CheckersSquare> getLandingCaptureSquares(Color side, CheckersSquare startingSquare){
        return movementManager.getValidMoves(board, startingSquare).stream()
                .filter(move -> move.getMovedPiece().getColor() == side)
                .filter(move ->move.getMoveLength() == 2)
                .map(Move::to).toList();
    }


    /**
     * Checks whether a starting position name is valid, given the side which is trying to make the move
     *
     * @param side the side which is trying to make the move
     * @param positionName the name of the position
     * @return true if the name is valid, false otherwise
     */
    private boolean isStartingPositionValid(Color side, String positionName){
        CheckersPosition startingPosition = CheckersStringToPositionParser.parse(positionName);
        if(startingPosition == null){
            return false;
        }

        List<CheckersSquare> availablePieces = getMovablePieces(side);
        if(!availablePieces.contains(board.getSquareAt(startingPosition))){
            return false;
        }
        return true;
    }

    /**
     * Checks whether the name of an ending position is valid or not, given the starting square and the side which is
     * trying to make the move.
     *
     * @param side the side which is trying to make the move
     * @param positionName the name of the position
     * @param startingSquare the starting square of the possible move
     * @return true if the name of the ending position is valid, false otherwise
     */
    private boolean isEndingPositionValid(Color side, String positionName, CheckersSquare startingSquare){
        CheckersPosition endingPosition = CheckersStringToPositionParser.parse(positionName);
        if(endingPosition == null){
            return false;
        }

        List<CheckersSquare> availableSquares = getValidLandingSquares(side, startingSquare);
        if(!availableSquares.contains(board.getSquareAt(endingPosition))){
            return false;
        }
        return true;
    }

    /**
     * Returns a valid starting position for a side, after checking its validity through many possible inputs from the player.
     *
     * @param playerTurn the side which is trying to make a move
     * @return a valid starting position for the given side
     */
    private CheckersPosition getValidStartingPosition(Color playerTurn) {
        String startingPositionName = requestStartingPosition(playerTurn);
        while (!isStartingPositionValid(playerTurn, startingPositionName)) {
            refreshBoard();
            cli.displayErrorMessage("Invalid position chosen, try again!");
            startingPositionName = requestStartingPosition(playerTurn);
        }
        return CheckersStringToPositionParser.parse(startingPositionName);
    }

    /**
     * Returns a valid ending position for a side, after checking its validity through many possible inputs from the player.
     *
     * @param playerTurn the side which is trying to make a move
     * @param startingSquare the starting square alongside which a move could be created
     * @return a valid ending position for the given side
     */
    private CheckersPosition getValidEndingPosition(Color playerTurn, CheckersSquare startingSquare) {
        String endingPositionName = requestEndingPosition(playerTurn, startingSquare);
        cli.displayMessage("\n");
        while (!isEndingPositionValid(playerTurn,endingPositionName,startingSquare)) {
            cli.clearScreen();
            displayBoardWithPlayerData();
            cli.displayErrorMessage("Invalid position chosen, try again!");
            endingPositionName = requestEndingPosition(playerTurn, startingSquare);
        }
        return CheckersStringToPositionParser.parse(endingPositionName);
    }
}