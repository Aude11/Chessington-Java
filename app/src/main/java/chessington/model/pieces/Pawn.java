package chessington.model.pieces;

import chessington.model.Board;
import chessington.model.Coordinates;
import chessington.model.Move;
import chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pawn extends AbstractPiece {

    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        ArrayList movesAllowed = new ArrayList<>();
        int colDiff = 0;
        int oneStep = 1;
        int oneStepWithDirection = setMoveDirection(oneStep); // can refactor
        boolean isEmpty;
        boolean isEmpty1;
        Move moveOneSquare = getAMove(from, colDiff, oneStepWithDirection);
        Coordinates to1;
        to1 =  from.plus(oneStepWithDirection, colDiff);
        isEmpty1 = isSquareEmpty(to1, board);
        if (isEmpty1) {
            movesAllowed.add(moveOneSquare);
        }
        //movesAllowed.add(moveOneSquare);
        if (Pawn.this.isFirstMove && isEmpty1) {
            int twoStep = 2;
            int twoStepWithDirection = setMoveDirection(twoStep);
            Move move2Square = getAMove(from, colDiff, twoStepWithDirection);
            Coordinates to;
            Coordinates squareToAsset;
            to =  from.plus(twoStepWithDirection, colDiff);
            isEmpty = isSquareEmpty(to, board);
            if (isEmpty) {
                movesAllowed.add(move2Square);
            }

        }
        return movesAllowed;
    }

    private Move getAMove (Coordinates from, int colDiff, int rowDiff) {
        Coordinates newCoordinates;
        newCoordinates =  from.plus(rowDiff, colDiff); // move up for white pawn
        return new Move(from, newCoordinates);
    }

    private int setMoveDirection(int step) {
        if (Pawn.this.colour.equals(PlayerColour.WHITE)) {
            int stepDown;
            stepDown = -step;
            return stepDown;
        } else {
            return step;
        }
    }

    /*private Move moveOneSquare(Coordinates from, int rowDiffDirection) {
        //check square available
        int colDiff = 0;
        return new Move(from, newCoordinates());
    }

    private Move moveTwoSquare(Coordinates from, int rowDiff) {

    }
*/
    private boolean isSquareEmpty(Coordinates to, Board board){
        if (board.get(to) != null) {
            return false;
        }
        return true;
    }
}
