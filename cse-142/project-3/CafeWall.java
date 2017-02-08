import java.awt.*;

/**
 * Zach Willson
 * 10/11/2014
 * CSE 142
 * TA: Ryan McMahon
 * Assignment 3b
 *
 * This project will create my project that will draw all the nice box shapes.
 */

public class CafeWall {

    public static final int MORTAR = 2;

    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(650, 400);
        panel.setBackground(Color.GRAY);
        Graphics g = panel.getGraphics();
        drawRow(20, 0, 0, 4, g);
        drawRow(30, 50, 70, 5, g);
        drawGrid(0, 4, 25, 10, 150, 4, g);
        drawGrid(35, 2, 35, 400, 20, 2, g);
        drawGrid(10, 3, 25, 250, 200, 3, g);
        drawGrid(10, 5, 20, 425, 180, 5, g);
    }

    //This method draws one row of square shapes
    public static void drawRow(int size, int xPoint, int yPoint, int rowCount, Graphics g) {
        for (int rowRepeat = 0; rowRepeat < rowCount; rowRepeat++) {
            g.fillRect((xPoint) + (rowRepeat * 2 * size), yPoint, size, size);
            g.setColor(Color.WHITE);
            g.fillRect(xPoint + size + (rowRepeat * 2 * size), yPoint, size, size);
            g.setColor(Color.BLUE);
            g.drawLine(xPoint + (rowRepeat * 2 * size), yPoint,
                        (xPoint + (rowRepeat * 2 * size) + size), yPoint + size);
            g.drawLine(xPoint + (rowRepeat * 2 * size), yPoint + size,
                        xPoint + (2 * size * (rowRepeat + 1)) - size, yPoint);
            g.setColor(Color.BLACK);
        }
    }

    //This method draws the grid after calling the drawRow method to draw rows
    public static void drawGrid(int offset, int rows, int size, int xPoint,
                                    int yPoint, int rowCount, Graphics g) {
        for (int row = 0; row < rows; row++) {
            drawRow(size, xPoint, yPoint + (2 * row * (size + MORTAR)), rowCount, g);
            drawRow(size, xPoint + offset,
                        yPoint + (2 * row * (size + MORTAR)) + size + MORTAR, rowCount, g);
        }
    }
}