/*
   Christian Cheng
   iChess.java
*/

import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class iChess extends WindowController implements MouseListener {

    // Dimensions
    private static final int CANVAS_WIDTH = 720;
    private static final int CANVAS_HEIGHT = 560;
    private static final int NUMBER_LINES = 8;
    private static final int BOARD_WIDTH = 560;
    private static final int SQUARE_WIDTH = BOARD_WIDTH / NUMBER_LINES;

    // Teams
    private String currentPlayer;
    

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
    private static final int ROW_1 = 0;
    private static final int ROW_2 = 1;
    private static final int ROW_7 = 6;
    private static final int ROW_8 = 7;
    private static Square[][] squareArray = new
        Square[NUMBER_LINES][NUMBER_LINES];
    private Square originSquare, destSquare;
    private boolean originSquareSelected, destSquareSelected;

    // Pieces
    private static final String WHITE = "white", BLACK = "black";
    private static final int NUM_UNIQUE_IMAGES = 12;
    private static final int NUM_TOTAL_PIECES = 32;
    private static final int PIECES_PER_ROW = 8;

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

        canvas.addMouseListener(this);

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

    public static Square getSquare(String squareID) {

        int currentFile = 0;

        while (currentFile < NUMBER_LINES) {

            for (int i = 0; i < NUMBER_LINES; i++) {
                if ((squareArray[currentFile][i].getID()).equals(squareID)) {
                    return squareArray[currentFile][i];
                }
            }

            currentFile++;
        }

        return null;
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

    public void mousePressed(MouseEvent evt) {

        Square selectedSquare;
        String selectedColor;

        // Get location of mouse press
        int evtX = evt.getX();
        int evtY = evt.getY();
        Location point = new Location(evtX, evtY);

        // Determine which square was selected
        for (int i = 0; i < squareArray.length; i++) {

            for (int j = 0; j < squareArray.length; j++) {

                selectedSquare = squareArray[i][j];

                if (selectedSquare.contains(point)) {

                    if (originSquareSelected &&
                            selectedSquare.equals(originSquare)) {

                        originSquare = null;
                        originSquareSelected = false;
                        selectedSquare.deselect();

                    } else if (destSquareSelected &&
                            selectedSquare.equals(destSquare)) {

                        destSquare = null;
                        destSquareSelected = false;
                        selectedSquare.deselect();                    

                    } else if (selectedSquare.isOccupied()) {

                        selectedColor = selectedSquare.getPiece().getColor();

                        // If the selected square is of the person's team
                        if (selectedColor.equals(currentPlayer)) {

                            if (originSquareSelected) originSquare.deselect();
                            originSquare = selectedSquare;
                            originSquareSelected = true;
                            selectedSquare.select();

                        } else {

                            if (originSquareSelected) {

                                if (destSquareSelected) destSquare.deselect();
                                destSquare = selectedSquare;
                                destSquareSelected = true;
                                selectedSquare.select();
                            }
                        }

                    } else if (originSquareSelected) {

                        destSquare = selectedSquare;
                        destSquareSelected = true;
                        selectedSquare.select();
                    }
                }
            }
        }

        if (originSquareSelected) System.out.println(originSquare.getID());
        if (destSquareSelected) System.out.println(destSquare.getID());
        
    }



    public void setPieces() {

        setDefaultPieces();

    }

    public void setDefaultPieces() {

        int pieceIndex;

        // Set the pieces on the board, starting with Pawns
        for (int i = 0; i < PIECES_PER_ROW; i++) {
            whitePieces[i] = new Pawn(WHITE, squareArray[i][ROW_2],
                    pieceImageArray[PAWN_INDEX], canvas);
            blackPieces[i] = new Pawn(BLACK, squareArray[i][ROW_7],
                    pieceImageArray[PAWN_INDEX + BLACK_OFFSET], canvas);
            squareArray[i][ROW_2].setPiece(whitePieces[i]);
            squareArray[i][ROW_7].setPiece(blackPieces[i]);
        }

        // Set Rooks on the board
        whitePieces[8] = new Rook(WHITE, squareArray[0][ROW_1],
                pieceImageArray[ROOK_INDEX], canvas);
        whitePieces[15] = new Rook(WHITE, squareArray[7][ROW_1],
                pieceImageArray[ROOK_INDEX], canvas);
        blackPieces[8] = new Rook(BLACK, squareArray[0][ROW_8],
                pieceImageArray[ROOK_INDEX + BLACK_OFFSET], canvas);
        blackPieces[15] = new Rook(BLACK, squareArray[7][ROW_8],
                pieceImageArray[ROOK_INDEX + BLACK_OFFSET], canvas);

        // Set Knights on the board
        whitePieces[9] = new Knight(WHITE, squareArray[1][ROW_1],
                pieceImageArray[KNIGHT_INDEX], canvas);
        whitePieces[14] = new Knight(WHITE, squareArray[6][ROW_1],
                pieceImageArray[KNIGHT_INDEX], canvas);
        blackPieces[9] = new Knight(BLACK, squareArray[1][ROW_8],
                pieceImageArray[KNIGHT_INDEX + BLACK_OFFSET], canvas);
        blackPieces[14] = new Knight(BLACK, squareArray[6][ROW_8],
                pieceImageArray[KNIGHT_INDEX + BLACK_OFFSET], canvas);

        // Set Bishops on the board
        whitePieces[10] = new Bishop(WHITE, squareArray[2][ROW_1],
                pieceImageArray[BISHOP_INDEX], canvas);
        whitePieces[13] = new Bishop(WHITE, squareArray[5][ROW_1],
                pieceImageArray[BISHOP_INDEX], canvas);
        blackPieces[10] = new Bishop(BLACK, squareArray[2][ROW_8],
                pieceImageArray[BISHOP_INDEX + BLACK_OFFSET], canvas);
        blackPieces[13] = new Bishop(BLACK, squareArray[5][ROW_8],
                pieceImageArray[BISHOP_INDEX + BLACK_OFFSET], canvas);

       
        // Set Queens on the board
        whitePieces[11] = new Queen(WHITE, squareArray[3][ROW_1],
                pieceImageArray[QUEEN_INDEX], canvas);
        blackPieces[11] = new Queen(BLACK, squareArray[3][ROW_8],
                pieceImageArray[QUEEN_INDEX + BLACK_OFFSET], canvas);
        
        // Set Kings on the board
        whitePieces[12] = new King(WHITE, squareArray[4][ROW_1],
                pieceImageArray[KING_INDEX], canvas);
        blackPieces[12] = new King(BLACK, squareArray[4][ROW_8],
                pieceImageArray[KING_INDEX + BLACK_OFFSET], canvas);

        pieceIndex = 8;

        // Assign Pieces to each square
        for (int j = 0; j < 8; j++) {
            squareArray[j][ROW_1].setPiece(whitePieces[pieceIndex]);
            squareArray[j][ROW_8].setPiece(blackPieces[pieceIndex]);
            pieceIndex++;
        }

        System.out.println(whitePieces[1].getPossibleMoves(
                    whitePieces[1].getSquare().getID(), WHITE));
        System.out.println(getSquare("B3"));

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

    public void mouseClicked(MouseEvent evt) {}
    public void mouseEntered(MouseEvent evt) {}
    public void mouseExited(MouseEvent evt) {}
    public void mouseReleased(MouseEvent evt) {}
}
// End of public class iChess
