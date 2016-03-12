/*
   Christian Cheng
   iChess.java
*/

import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class iChess extends WindowController {

    // Dimensions
    private static final int CANVAS_WIDTH = 720;
    private static final int CANVAS_HEIGHT = 560;
    private static final int NUMBER_LINES = 8;
    private static final int BOARD_WIDTH = 560;
    private static final int SQUARE_WIDTH = BOARD_WIDTH / NUMBER_LINES;
    

    // GUI components
    private JPanel scorePanel;
    private JButton restartButton;

    // Chessboard lines
    private static Line[] horizLines = new Line[NUMBER_LINES];
    private static Line[] vertLines = new Line[NUMBER_LINES];

    // Squares
    private static final String[] boardFiles = {"A", "B", "C", "D", "E", "F",
        "G", "H"};
    private static final String[] boardRanks = {"1", "2", "3", "4", "5", "6",
        "7", "8"};
    private static Square[][] squareArray = new
        Square[NUMBER_LINES][NUMBER_LINES];

    // Pieces
    private static final int NUM_UNIQUE_IMAGES = 12;
    private static final String[] rankGraphics = {"Pawn.png", "Knight.png",
        "Bishop.png", "Rook.png", "Queen.png", "King.png"};
    private Image[] pieceImageArray = new Image[NUM_UNIQUE_IMAGES];

    /**
     * On begin, draw the chessboard and side board.
     */
    public void begin() {

        // Draw the Chessboard and instantiate each Square
        drawBoard();
        instantiateSquares();

        // Set the pieces for the start of the game
        setPieces();
        
    }

    public void drawBoard() {

        double lineIncrement = SQUARE_WIDTH;

        // Draw the chessboard lines
        for (int i = 0; i < NUMBER_LINES; i++, lineIncrement += SQUARE_WIDTH) {

            horizLines[i] = new Line(0, lineIncrement, BOARD_WIDTH,
                    lineIncrement, canvas);
            vertLines[i] = new Line(lineIncrement, 0, lineIncrement,
                    CANVAS_HEIGHT, canvas);
        }
    }
    
    public void instantiateSquares() {

        String currentFile, currentRank;
        Square thisSquare;
        double squareIncrementX, squareIncrementY;
        Location squareLocation;
        boolean shade = false;

        // Instantiate the Squares
        for (int j = 0; j < boardFiles.length; j++) {

            currentFile = boardFiles[j];
            squareIncrementX = j * SQUARE_WIDTH;
            shade = !shade;

            for (int k = 0; k < boardRanks.length; k++) {

                // Calculate rank and Location of this Square
                currentRank = boardRanks[k];
                squareIncrementY = BOARD_WIDTH - ((k+1) * SQUARE_WIDTH);
                squareLocation = new Location(squareIncrementX,
                        squareIncrementY);
                
                // Initialize the Square and add it to the squareArray
                thisSquare = new Square(squareLocation, shade, canvas);
                squareArray[j][k] = thisSquare;

                // Set this Square's properties
                thisSquare.setID(currentFile + currentRank);
                shade = !shade;
            }

            // Next: get Pawn graphics in and put them in as pieces!
        }
    }

    public void setPieces() {

        pieceImageArray[0] = getImage("whitePawn.png");
        Piece pawn = new Pawn("white", squareArray[3][3], pieceImageArray[0],
                canvas);
    }

        





    /**
     * Runs iChess as an Acme.MainFrame application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {

        new Acme.MainFrame(new iChess(), args, CANVAS_WIDTH, CANVAS_HEIGHT);
    }
}
// End of public class iChess
