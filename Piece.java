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
     * Gets the possible moves for this Piece.
     */
    public abstract Square[] getPossibleMoves();

    /**
     * Get the Rank of this Piece.
     */
    public String getRank();

    /**
     * Move to a Square on the Board.
     */
    public abstract boolean moveTo(Square square);

}
    
