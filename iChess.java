/*
   Christian Cheng
   iChess.java
*/

import objectdraw.*;
import java.awt.*;

public class iChess extends WindowController {

    // Window canvas dimensions
    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 640;

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
