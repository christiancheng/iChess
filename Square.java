/*
   Christian Cheng
   Square.java
*/

import objectdraw.*;

/**
 * Squares comprise the game board and keep track of their properties.
 */

public class Square {

    private Location location;
    private Piece piece;
    private Square adjacentSquare;
    private boolean occupied;

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

    public void setLocation(Location locationToSet) {
        location = locationToSet;
    }

    public void setOccupancy(boolean occupancy) {
        occupied = occupancy;
    }

    public void setPiece(Piece pieceToSet) {
        piece = pieceToSet;
    }


}
