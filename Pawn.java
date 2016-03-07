/*
   Christian Cheng
   Pawn.java
*/

import objectdraw.*;
import java.awt.Image;

/**
 * Pawn implements a Piece with the rank of Pawn.
 */

public class Pawn implements Piece {

    private static final String RANK = "PAWN";
    private static final int DIMENSION = 80;
    
    private String name;
    private Square square;
    private FramedRect containmentBox;
    private boolean removedFromBoard;

    public Pawn(Square initialSquare, DrawingCanvas canvas) {

        // Create a new FramedRect for this Pawn's containment box
        containmentBox = new FramedRect(initialSquare.getLocation(), DIMENSION,
                DIMENSION, canvas);
                
    }
    
    public boolean contains(Location point) {
        return containmentBox.contains(point);
    }

    public Location getLocation() {
        return getSquare().getLocation();
    }

    public String getName() {
        return name;
    }

    public Square[] getPossibleMoves() {

    }

    public String getRank() {
        return RANK;
    }

    public Square getSquare() {
        return square;
    }

    public void removeFromBoard() {
        removedFromBoard = true;
    }

    public void setName(String nameToSet) {
        name = nameToSet;
    }
    
}


