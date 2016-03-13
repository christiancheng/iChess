/*
   Christian Cheng
   Pawn.java
*/

import objectdraw.*;
import java.awt.*;
import java.util.*;

/**
 * Pawn implements a Piece with the rank of Pawn.
 */

public class Pawn implements Piece {

    private static final String RANK = "PAWN";
    private static final String PIECE_PNG = "Pawn.png";
    private static final String WHITE = "white";
    private static final String BLACK = "black";
    private static final int DIMENSION = 70;
    
    private String name, color;
    private Square square;
    private FramedRect containmentBox;
    private VisibleImage pieceImage;
    private Location location;
    private boolean removedFromBoard;

    public Pawn(String color, Square initialSquare, Image image, DrawingCanvas
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

    public Square[] getPossibleMoves(String squareID, String pieceColor) {

        char squareFile, squareRank;
        ArrayList<String> possibleMoveIds = new ArrayList<String>();

        squareFile = squareID.charAt(0);
        squareRank = squareID.charAt(1);

        if (pieceColor == WHITE) {

            // Should be upranking later
            if (squareRank == '8') return null;

            possibleMoveIds.add((squareFile + (char)((int)(squareRank)
                            + 1)) + "");

        }

        Square[] possibleMoves = new Square[possibleMoveIds.size()];

        for (int i = 0; i < possibleMoves.length; i++) {

            possibleMoves[i] = iChess.getSquare(possibleMoveIds.get(i));
        }

        return possibleMoves;
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


