package chessington.model.pieces;

import chessington.model.Board;
import chessington.model.Coordinates;
import chessington.model.Move;
import chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {

    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        ArrayList movesAllowed = new ArrayList<>();
        int direction = setMoveDirection();
        boolean isEmpty1;
        // add
        addMoveVerticallyIfAllowed(from, direction, board, movesAllowed);
        // if first move and can perform one step move then check for 2 step move
        if (Pawn.this.isFirstMove && !movesAllowed.isEmpty()) {
            int twoStep = direction * 2;
            addMoveVerticallyIfAllowed(from, twoStep, board, movesAllowed);
        }
        return movesAllowed;
    }


    private int setMoveDirection() {
        if (Pawn.this.colour.equals(PlayerColour.WHITE)) {
            return -1 ;
        } else {
            return 1;
        }
    }

    private void addMoveVerticallyIfAllowed(Coordinates from, int rowDiffDirection,
                                   Board board, ArrayList movesAllowed) {
        int colDiff = 0;
        boolean isEmpty;
        Coordinates newCoordinates;
        newCoordinates =  from.plus(rowDiffDirection , colDiff);
        //check square available
        isEmpty = isSquareEmpty(newCoordinates, board);
        if (isEmpty) {
            movesAllowed.add(new Move(from, newCoordinates));
        }
    }

    private boolean isSquareEmpty(Coordinates coords, Board board){
        if (board.get(coords) != null) {
            return false;
        }
        return true;
    }
}
