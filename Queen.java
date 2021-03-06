/*
   Christian Cheng
   Queen.java
*/

import objectdraw.*;
import java.awt.*;

/**
 * Queen implements a Piece with the rank of Queen.
 */

public class Queen implements Piece {

    private static final String RANK = "QUEEN";
    private static final String PIECE_PNG = "Queen.png";
    private static final int DIMENSION = 70;
    
    private String name, color;
    private Square square;
    private FramedRect containmentBox;
    private VisibleImage pieceImage;
    private Location location;
    private boolean removedFromBoard;

    public Queen(String color, Square initialSquare, Image image, DrawingCanvas
            canvas) {

        setLocation(initialSquare.getLocation());
        setSquare(initialSquare);
        setColor(color);

        // Create a new FramedRect for this Queen's containment box
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


