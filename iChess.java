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
    private static final String WHITE = "white", BLACK = "black";
    private static final int NUM_UNIQUE_IMAGES = 12;
    private static final int NUM_TOTAL_PIECES = 32;
    private static final int PIECES_PER_ROW = 8;
    private static final int NUM_KNIGHTS = 2;

    private static final int PAWN_INDEX = 0;
    private static final int KNIGHT_INDEX = 1;
    private static final int BISHOP_INDEX = 2;
    private static final int ROOK_INDEX = 3;
    private static final int QUEEN_INDEX = 4;
    private static final int KING_INDEX = 5;
    private static final int BLACK_OFFSET = 6;
    private static final String[] RANK_GRAPHICS = {"Pawn.png", "Knight.png",
        "Bishop.png", "Rook.png", "Queen.png", "King.png"};
    private static final int[] SECOND_ROW_ORDER = {3, 1, 2, 4, 5, 2, 1, 3}; 
    private Image[] pieceImageArray = new Image[NUM_UNIQUE_IMAGES];
    private Piece[] blackPieces = new Piece[NUM_TOTAL_PIECES / 2];
    private Piece[] whitePieces = new Piece[NUM_TOTAL_PIECES / 2];

    /**
     * On begin, draw the chessboard and side board.
     */
    public void begin() {

        // Draw the Chessboard and instantiate each Square
        drawBoard();
        instantiateSquares();

        // Set the pieces for the start of the game
        storePieceImages();
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
        }
    }

    public void setPieces() {

        // Set the pieces on the board, starting with Pawns
        for (int i = 0; i < PIECES_PER_ROW; i++) {
            whitePieces[i] = new Pawn(WHITE, squareArray[i][1],
                    pieceImageArray[PAWN_INDEX], canvas);
            blackPieces[i] = new Pawn(BLACK, squareArray[i][6],
                    pieceImageArray[PAWN_INDEX + BLACK_OFFSET], canvas);
        }

        // Set Rooks on the board
        whitePieces[8] = new Rook(WHITE, squareArray[0][0],
                pieceImageArray[ROOK_INDEX], canvas);
        whitePieces[15] = new Rook(WHITE, squareArray[7][0],
                pieceImageArray[ROOK_INDEX], canvas);
        blackPieces[8] = new Rook(BLACK, squareArray[0][7],
                pieceImageArray[ROOK_INDEX + BLACK_OFFSET], canvas);
        blackPieces[15] = new Rook(BLACK, squareArray[7][7],
                pieceImageArray[ROOK_INDEX + BLACK_OFFSET], canvas);



    }

    public void storePieceImages() {

        String pieceColor = WHITE;
        int rankGraphicsIndex = 0;

        // Store the piece images
        for (int i = 0; i < pieceImageArray.length; i++) {

            pieceImageArray[i] = getImage(pieceColor +
                    RANK_GRAPHICS[rankGraphicsIndex % 6]);            
            rankGraphicsIndex++;

            // Change the piece colors halfway through the iteration
            if (i == 5) pieceColor = BLACK;
          
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
