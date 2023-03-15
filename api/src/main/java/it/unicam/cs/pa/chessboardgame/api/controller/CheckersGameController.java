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
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPieceType;
import it.unicam.cs.pa.chessboardgame.api.model.player.CheckersPlayer;
import it.unicam.cs.pa.chessboardgame.api.model.rules.CheckersGameRules;
import it.unicam.cs.pa.chessboardgame.api.util.CheckersStringToPositionParser;
import it.unicam.cs.pa.chessboardgame.api.view.CheckersCLI;

import java.util.List;

public class CheckersGameController implements
        GameController<CheckersPosition, CheckersPiece, CheckersSquare, CheckersBoard>,
        MoveExecutor<CheckersPosition, CheckersPiece, CheckersSquare, CheckersMove> {
    private CheckersBoard board;
    private final CheckersCLI cli;
    private CheckersPlayer blackSidePlayer, whiteSidePlayer;
    private final CheckersMovementManager movementManager;
    private final CheckersGameRules gameRules;
    private boolean isGameFinished;
    private int movesBeforeRepetitionDraw;

    public CheckersGameController() {
        this.gameRules = new CheckersGameRules(4);
        this.board = new CheckersBoard();
        board.clearBoard();
        replicateEndGamePhase();
        this.cli = new CheckersCLI();
        this.movementManager = new CheckersMovementManager();
        this.isGameFinished = false;
        movesBeforeRepetitionDraw = 0;
    }

    public CheckersGameController(CheckersCLI cli) {
        this.gameRules = new CheckersGameRules(40);
        this.board = new CheckersBoard();
        this.movementManager = new CheckersMovementManager();
        this.cli = cli;
        this.isGameFinished = false;
        movesBeforeRepetitionDraw = 0;
    }

    public void start() {
        initializePlayers();
        refreshBoard();
        while (true) {
            if(isGameFinished){break;}
            makeTurn(CheckersColor.WHITE);
            if(isGameFinished){break;}
            makeTurn(CheckersColor.BLACK);
            if(isGameFinished){break;}
        }
        displayEndResult();
    }

    private void turnControls(Color side){
        if(isAnyRuleTrue(side)){
            cli.displayMessage("a\n a\n a\n a\n a\n a\n a\n");
            isGameFinished = true;
        }else if(gameRules.areConditionsForDrawByRepetitionMet(board)){
            movesBeforeRepetitionDraw++;
        }
    }

    private void displayEndResult(){
        refreshBoard();
        Color winningSide = gameRules.getWinnerSide(board);
        if(winningSide != null){
            cli.displayMessage(winningSide + "has won");
        } else if (gameRules.isLossByStalePosition(board, CheckersColor.WHITE)){
            cli.displayMessage(CheckersColor.BLACK.getName() + " side has won, "+ CheckersColor.WHITE.getName() +" side cannot move");
        }else if (gameRules.isLossByStalePosition(board, CheckersColor.BLACK)){
            cli.displayMessage(CheckersColor.WHITE.getName() + " side has won, "+ CheckersColor.BLACK.getName() +" side cannot move");
        }else if(gameRules.isDraw(board) || gameRules.isDrawByRepetition(movesBeforeRepetitionDraw)){
            cli.displayMessage("The game resulted in a draw");
        }
        cli.displayMessage("\n");
    }
    private void whichActionAfterGame(){}

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

    private void refreshBoard(){
        cli.clearScreen();
        displayBoardWithPlayerData();
    }

    private void displayBoardWithPlayerData() {
        printPlayer(blackSidePlayer);
        cli.displayBoard(board);
        printPlayer(whiteSidePlayer);
    }

    @Override
    public void makeTurn(Color side) {
        turnControls(side);
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

    private boolean canCaptureAgain(CheckersMove move){
        if(movementManager.canCaptureFromSquare(board, board.getSquareAt(move.to().getPosition()))){
            return true;
        }
        return false;
    }

    public CheckersMove readMove(Color playerTurn) {
        CheckersPosition startingPosition = getValidStartingPosition(playerTurn);
        refreshBoard();
        CheckersPosition endingPosition = getValidEndingPosition(playerTurn, board.getSquareAt(startingPosition));
        return new CheckersMove(board.getSquareAt(startingPosition), board.getSquareAt(endingPosition));
    }

    private String requestStartingPosition(Color side){
        cli.displayMessage(side.getName() + " side, available pieces are on: " + getAvailableSquaresNames(side) + "\n");
        cli.displayMessage("Choose your piece: ");
        return cli.readInput();
    }

    private String requestEndingPosition(Color side, CheckersSquare startingSquare){
        cli.displayMessage(side.getName() + " side, available landing squares from " + startingSquare.getPosition().getName() +
                " are on: " +
                getAvailableSquaresNames(side, startingSquare) + "\n");
        cli.displayMessage("Choose where to move: ");
        return cli.readInput();
    }

    private String getAvailableSquaresNames(Color side) {
        StringBuilder availableSquares = new StringBuilder();
        List<CheckersSquare> availableSquaresToMove = getMovablePieces(side);

        for (CheckersSquare square : availableSquaresToMove) {
            availableSquares.append(square.getPosition().getName()).append(" ");
        }
        return availableSquares.toString();
    }

    private String getAvailableSquaresNames(Color side, CheckersSquare startingSquare) {
        StringBuilder availableSquares = new StringBuilder();
        List<CheckersSquare> availableLandingSquares = getValidLandingSquares(side, startingSquare);

        for (CheckersSquare square : availableLandingSquares) {
            availableSquares.append(square.getPosition().getName()).append(" ");
        }
        return availableSquares.toString();
    }

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

    private boolean containsCaptureMove(List<CheckersSquare> startingSquares){
       if(startingSquares.stream().anyMatch(square -> movementManager.canCaptureFromSquare(board, square))){
           return true;
       }
       return false;
    }

    private List<CheckersSquare> getCaptureMovesStartingSquares(List<CheckersSquare> startingSquares){
        return startingSquares.stream().filter(square -> movementManager.canCaptureFromSquare(board, square)).toList();
    }

    private List<CheckersSquare> getValidLandingSquares(Color side, CheckersSquare startingSquare){
        List<CheckersSquare> validLandingSquares = movementManager.getValidMoves(board, startingSquare).stream()
                .filter(move -> move.getMovedPiece().getColor() == side)
                .map(Move::to).toList();
        if(movementManager.canCaptureFromSquare(board, startingSquare)){
            return getLandingCaptureSquares(side, startingSquare);
        }
        return validLandingSquares;
    }

    private List<CheckersSquare> getLandingCaptureSquares(Color side, CheckersSquare startingSquare){
        return movementManager.getValidMoves(board, startingSquare).stream()
                .filter(move -> move.getMovedPiece().getColor() == side)
                .filter(move ->move.getMoveLength() == 2)
                .map(Move::to).toList();
    }


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

    private CheckersPosition getValidStartingPosition(Color playerTurn) {
        String startingPositionName = requestStartingPosition(playerTurn);
        while (!isStartingPositionValid(playerTurn, startingPositionName)) {
            refreshBoard();
            cli.displayErrorMessage("Invalid position chosen, try again!");
            startingPositionName = requestStartingPosition(playerTurn);
        }
        return CheckersStringToPositionParser.parse(startingPositionName);
    }


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

    private void replicateEndGamePhase(){
        board.clearBoard();
        CheckersPiece whiteMan = new CheckersPiece(CheckersColor.WHITE);
        CheckersPiece blackMan = new CheckersPiece(CheckersColor.BLACK);
        CheckersPiece whiteKing = new CheckersPiece(CheckersColor.WHITE, CheckersPieceType.KING);
        CheckersPiece blackKing = new CheckersPiece(CheckersColor.BLACK, CheckersPieceType.KING);

        board.setPieceAt(whiteKing, new CheckersPosition(5,2));
        board.setPieceAt(whiteMan, new CheckersPosition(5,4));
        board.setPieceAt(blackKing, new CheckersPosition(2,1));
        board.setPieceAt(blackMan, new CheckersPosition(2,3));
    }
}