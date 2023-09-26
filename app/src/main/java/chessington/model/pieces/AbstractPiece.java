package chessington.model.pieces;

import chessington.model.PlayerColour;

public abstract class AbstractPiece implements Piece {

    protected final Piece.PieceType type;
    protected final PlayerColour colour;
    protected Boolean isFirstMove;

    protected AbstractPiece(Piece.PieceType type, PlayerColour colour) {
        this.type = type;
        this.colour = colour;
        this.isFirstMove = true;
    }

    @Override
    public Piece.PieceType getType() {
        return type;
    }

    @Override
    public PlayerColour getColour() {
        return colour;
    }

    @Override
    public void setFirstMoveStatus() {
        this.isFirstMove = false;
    }
    @Override
    public String toString() {
        return colour.toString() + " " + type.toString();
    }
}
