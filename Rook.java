/*
   Christian Cheng
   Rook.java
*/

import objectdraw.*;
import java.awt.*;

/**
 * Rook implements a Piece with the rank of Rook.
 */

public class Rook implements Piece {

    private static final String RANK = "ROOK";
    private static final String PIECE_PNG = "Rook.png";
    private static final int DIMENSION = 70;
    
    private String name, color;
    private Square square;
    private FramedRect containmentBox;
    private VisibleImage pieceImage;
    private Location location;
    private boolean removedFromBoard;

    public Rook(String color, Square initialSquare, Image image, DrawingCanvas
            canvas) {

        setLocation(initialSquare.getLocation());
        setSquare(initialSquare);
        setColor(color);

        // Create a new FramedRect for this Pawn's containment box
        containmentBox = new FramedRect(initialSquare.getLocation(), DIMENSION,
                DIMENSION, canvas);
        pieceImage = new VisibleImage(image, getLocation(), canvas);
                
    }
    
    public boolean contains(Location point) {
        return containmentBox.contains(point);
    }

    public String getColor() {
        return color;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    //public Square[] getPossibleMoves() {
    //}

    public String getRank() {
        return RANK;
    }

    public Square getSquare() {
        return square;
    }

    public void removeFromBoard() {
        removedFromBoard = true;
    }

    public void setColor(String colorToSet) {
        color = colorToSet;
    }

    public void setLocation(Location locationToSet) {
        location = locationToSet;
    }

    public void setName(String nameToSet) {
        name = nameToSet;
    }

    public void setSquare(Square squareToSet) {
        square = squareToSet;
    }
    
}


