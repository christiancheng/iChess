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
    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 640;
    private static final int NUMBER_LINES = 8;
    private static final int SQUARE_WIDTH = 80;
    private static final int BOARD_WIDTH = 640;

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

    /**
     * On begin, draw the chessboard and side board.
     */
    public void begin() {

        drawBoard();
        instantiateSquares();
        
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

        // Instantiate the Squares
        for (int j = 0; j < boardFiles.length; j++) {

            currentFile = boardFiles[j];

            for (int k = 0; k < boardRanks.length; k++) {

                squareArray[j][k] = new Square();
            }
        }
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
