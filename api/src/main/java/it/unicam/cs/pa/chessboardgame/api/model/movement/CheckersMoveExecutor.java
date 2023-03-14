package it.unicam.cs.pa.chessboardgame.api.model.movement;

import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersBoard;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersPosition;
import it.unicam.cs.pa.chessboardgame.api.model.board.CheckersSquare;
import it.unicam.cs.pa.chessboardgame.api.model.color.Color;
import it.unicam.cs.pa.chessboardgame.api.model.piece.CheckersPiece;

public class CheckersMoveExecutor implements
        MoveExecutor<CheckersPosition, CheckersPiece, CheckersSquare, CheckersBoard, CheckersMove> {
    CheckersMovementManager movementManager;

    public CheckersMoveExecutor() {
        this.movementManager = new CheckersMovementManager();
    }

    @Override
    public void makeMove(CheckersBoard board, CheckersMove move, Color side) {

    }
}