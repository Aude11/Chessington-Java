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
        // add one step move
        addMoveVerticallyIfAllowed(from, direction, board, movesAllowed);
        // if first move and can perform one step move then check for 2 step move
        if (Pawn.this.isFirstMove && !movesAllowed.isEmpty()) {
            int twoStep = direction * 2;
            addMoveVerticallyIfAllowed(from, twoStep, board, movesAllowed);
        }
        addDiagonalMoveIfAllowed(from, direction, direction, board, movesAllowed);
        addDiagonalMoveIfAllowed(from, direction, -direction, board, movesAllowed);
        return movesAllowed;
    }

    private int setMoveDirection() {
        if (Pawn.this.colour.equals(PlayerColour.WHITE)) {
            return -1 ;
        } else {
            return 1;
        }
    }

    private boolean isOpponent(Coordinates coords, Board board) {
        if (Pawn.this.colour.equals(board.get(coords).getColour())) {
            return false ;
        }
        return true;
    }
    private void addDiagonalMoveIfAllowed(Coordinates from, int rowDiffDirection, int colDiffDirection,
                                          Board board, ArrayList movesAllowed){
        boolean isEmpty;
        boolean isInBoard;
        boolean isOpponent;
        Coordinates newCoordinates;
        newCoordinates =  from.plus(rowDiffDirection, colDiffDirection);
        isInBoard = isWithinBoardBoundary(newCoordinates);
        //check square available
        if (isInBoard) {
            isEmpty = isSquareEmpty(newCoordinates, board);
            if (!isEmpty) {
                isOpponent = isOpponent(newCoordinates, board);
                if (isOpponent) {
                    movesAllowed.add(new Move(from, newCoordinates));
                }
            }
        }
    }




    private void addMoveVerticallyIfAllowed(Coordinates from, int rowDiffDirection,
                                            Board board, ArrayList movesAllowed) {
        int colDiff = 0;
        boolean isEmpty;
        boolean isInBoard;
        Coordinates newCoordinates;
        newCoordinates =  from.plus(rowDiffDirection , colDiff);
        // check coordinates within the board
        isInBoard = isWithinBoardBoundary(newCoordinates);
        //check square available
        if (isInBoard) {
            isEmpty = isSquareEmpty(newCoordinates, board);
            if (isEmpty) {
                movesAllowed.add(new Move(from, newCoordinates));
            }
        }
    }

    private boolean isWithinBoardBoundary(Coordinates coords) {
        // should in board instance
        final int rowLimitTop = 0;
        final int rowLimitBottom = 7;
        final int colLimitLeft = 0;
        final int colLimitRight = 7;
        int row = coords.getRow();
        int col = coords.getCol();
        //boolean isWithinBoard;
        if (0 <= row && row <= 7 && 0 <= col && col <= 7) {
            return true;
        }
        return false;
    }

    private boolean isSquareEmpty(Coordinates coords, Board board){
        if (board.get(coords) != null) {
            return false;
        }
        return true;
    }
}
