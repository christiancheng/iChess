/*
   Christian Cheng
   Piece.java
*/

import objectdraw.*;
import java.awt.Image;

/**
 * Piece interface allows interaction and manipulation of Piece objects.
 */

public interface Piece {

    /**
     * Returns whether the mouse is inside this Piece.
     */
    public abstract boolean contains(Location point);

    /**
     * Get the Location on the canvas of this Piece.
     */
    //public abstract getLocation();

    /**
     * Gets the name of this Piece.
     */
    public abstract String getName();

    /**
     * Gets the possible moves for this Piece.
     */
    public abstract Square[] getPossibleMoves();

    /**
     * Get the Rank of this Piece.
     */
    public abstract String getRank();

    /**
     * Gets the Square location of this Piece.
     */
    public abstract Square getSquare();

    /**
     * Move to a Square on the Board.
     */
    public abstract boolean moveTo(Square square);

    /**
     * Remove this Piece from play.
     */
    public abstract void removeFromBoard();

    /**
     * Set the name of this Piece.
     */
    public abstract void setName();

}
    
