package chessington.model.pieces;

import chessington.model.Board;
import chessington.model.Coordinates;
import chessington.model.Move;
import chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class King extends AbstractPiece {
    public King(PlayerColour colour) {
        super(PieceType.KING, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        ArrayList movesAllowed = new ArrayList<>();
        for (int col = -1; col <= 1; col++) {
            for (int row = -1; row <= 1; row++) {
                boolean isEmpty;
                boolean isInBoard;
                boolean isOpponent;
                Coordinates newCoordinates;
                newCoordinates =  from.plus(row, col);
                isInBoard = isWithinBoardBoundary(newCoordinates);
                if (isInBoard) {
                    isEmpty = isSquareEmpty(newCoordinates, board);
                    isOpponent = isOpponent(newCoordinates, board);
                    if (isEmpty || isOpponent) {
                        movesAllowed.add(new Move(from, newCoordinates));
                    }
                }
            }
        }
        return movesAllowed;
    }

    private boolean isOpponent(Coordinates coords, Board board) {
        if (King.this.colour.equals(board.get(coords).getColour())) {
            return false ;
        }
        return true;
    }

    private boolean isWithinBoardBoundary(Coordinates coords) {
        // should in board instance
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
