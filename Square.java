/*
   Christian Cheng
   Square.java
*/

import objectdraw.*;
import java.awt.*;

/**
 * Squares comprise the game board and keep track of their properties.
 */

public class Square {

    // Dimensions
    private static final double SQUARE_WIDTH = 80;

    private Location location;
    private Piece piece;
    private Square adjacentSquare;
    private boolean occupied;
    private FilledRect squareRect;
    private String file, rank;

    public Square(Location initLocation, boolean shaded, DrawingCanvas canvas)
    {
        squareRect = new FilledRect(initLocation, SQUARE_WIDTH,
                SQUARE_WIDTH, canvas);
        
        setLocation(initLocation);

        if (shaded) {
            squareRect.setColor(LIGHT_GRAY);
        }
    }

    public boolean equals(Square square) {
        return (location == square.getLocation()) ? true : false;
    }
    
    public Location getLocation() {
        return location;
    }

    public boolean getOccupancy() {
        return occupied;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setFile(String fileToSet) {
        file = fileToSet;
    }

    public void setLocation(Location locationToSet) {
        location = locationToSet;
    }

    public void setOccupancy(boolean occupancy) {
        occupied = occupancy;
    }

    public void setPiece(Piece pieceToSet) {
        piece = pieceToSet;
    }

    public void setRank(String rankToSet) {
        rank = rankToSet;
    }


}
