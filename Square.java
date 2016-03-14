/*
   Christian Cheng
   Square.java
*/

import objectdraw.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Squares comprise the game board and keep track of their properties.
 */

public class Square implements MouseListener {

    // Dimensions
    private static final double SQUARE_WIDTH = 70;
    private static final String WHITE = "white";
    private static final String BLACK = "black";

    private Location location;
    private Piece piece;
    private Square adjacentSquare;
    private boolean occupied;
    private FilledRect squareRect;
    private String id;
    private boolean selected;

    public Square(Location initLocation, boolean shaded, DrawingCanvas canvas)
    {
        squareRect = new FilledRect(initLocation, SQUARE_WIDTH,
                SQUARE_WIDTH, canvas);
        canvas.addMouseListener(this);
        
        setLocation(initLocation);

        if (shaded) squareRect.setColor(Color.LIGHT_GRAY);
        else squareRect.setColor(Color.WHITE);
    }

    public boolean equals(Square square) {
        return (location == square.getLocation()) ? true : false;
    }

    public String getID() {
        return id;
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

    public void mousePressed(MouseEvent evt) {

        // Get location of mouse press
        int evtX = evt.getX();
        int evtY = evt.getY();
        Location point = new Location(evtX, evtY);

        // Indicate whether this Square was selected
        selected = squareRect.contains(point);

        
    }

    public void setID(String idToSet) {
        id = idToSet;
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

    public void mouseClicked(MouseEvent evt) {}
    public void mouseEntered(MouseEvent evt) {}
    public void mouseExited(MouseEvent evt) {}
    public void mouseReleased(MouseEvent evt) {}

}
