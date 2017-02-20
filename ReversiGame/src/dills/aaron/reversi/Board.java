package dills.aaron.reversi;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Board class is the virtual gameboard that the game is played on which is a 2D
 * array of Space Objects.
 *
 * @author hdesk8
 *
 */
public class Board {

    /**
     * HEIGHT is the number of rows the gameboard will have
     */
    private static final int HEIGHT = 8;
    /**
     * WIDTH is the number of columns the gameboard will have
     */
    private static final int WIDTH = 8;
    /**
     * spaces is a two dimensional array of Space objects that when printed is
     * the gameboard
     */
    private Space[][] spaces;

    /**
     * The constructor for the Board class initializes the gameboard and adds
     * the starting Disc Objects to the gameboard
     */
    public Board() {
        this.spaces = new Space[HEIGHT][WIDTH];
        for (int row = 0, locationY = 0; row < spaces.length; row++, locationY += 50) {
            for (int col = 0, locationX = 0; col < spaces[row].length; col++, locationX += 50) {
                spaces[row][col] = new Space(locationX, locationY);
            }
        }
        
        //Add discs to the center of the board for new game
        spaces[3][3].addDisc("Blue");
        spaces[3][4].addDisc("Red");
        spaces[4][3].addDisc("Red");
        spaces[4][4].addDisc("Blue");

    }

    /**
     * @return the method getSpaces returns the two dimensional array of space
     * objects.
     */
    public Space[][] getSpaces() {
        return this.spaces;
    }

    /**
     * @param g is the Graphics2D object used to draw
     */
    public void draw(Graphics2D g) {
        for (int row = 0; row < spaces.length; row++) {

            for (int col = 0; col < spaces[row].length; col++) {
                spaces[row][col].draw(g);
            }
        }

    }

    /**
     * testsSpaceHoverOver tests to see if the space the user is currently
     * hovering over is a valid move
     *
     * @param x is the current x location of the mouse
     * @param y is the current y location of the mouse
     * @return a boolean value is returned whether or not the space the user has
     * hovered over is an acceptable move
     */
    public boolean testsSpaceHoverOver(int x, int y) {
        for (int row = 0; row < spaces.length; row++) {
            for (int col = 0; col < spaces[row].length; col++) {
                if (!spaces[row][col].hasDisc()) {
                    if (spaces[row][col].testsDimension(x, y)) {
                        if (isValidSpace(row, col, "Blue") != 0) {
                            spaces[row][col].setColor(Color.GREEN);
                            return true;
                        }
                    } else {
                        spaces[row][col].setColor(Color.WHITE);

                    }
                }
            }
        }
        return false;
    }

    /**
     * testsRightDiagonal calls the methods necessary for determining if a right
     * diagonal move is valid.
     *
     * @param initalRow is the row position the user clicked
     * @param initalCol is the column position the user clicked
     * @param color is the color of the player currently making a move.
     * @return an integer value is returned that is the number of discs that can
     * be captured
     */
    public int testsRightDiagonal(int initalRow, int initalCol, String color) {
        if (testsRightUp(initalRow, initalCol, color) != 0) {
            return testsRightUp(initalRow, initalCol, color);
        }
        if (testsRightDown(initalRow, initalCol, color) != 0) {
            return testsRightDown(initalRow, initalCol, color);
        }
        return 0;
    }

    /**
     * testsLeftDiagonal calls the methods necessary for determining if a left
     * diagonal move is valid.
     *
     * @param initalRow is the row position the user clicked
     * @param initalCol is the column position the user clicked
     * @param color is the color of the player currently making a move.
     * @return an integer value is returned that is the number of discs that can
     * be captured
     */
    public int testsLeftDiagonal(int initalRow, int initalCol, String color) {
        if (testsLeftUp(initalRow, initalCol, color) != 0) {
            return testsLeftUp(initalRow, initalCol, color);
        }
        if (testsLeftDown(initalRow, initalCol, color) != 0) {
            return testsLeftDown(initalRow, initalCol, color);
        }
        return 0;
    }

    /**
     * testsLeftDown determines if a left diagonal move is valid.
     *
     * @param initalRow is the row position the user clicked
     * @param initalCol is the column position the user clicked
     * @param color is the color of the player currently making a move.
     * @return an integer value is returned that is the number of discs that can
     * be captured
     */
    public int testsLeftDown(int initalRow, int initalCol, String color) {
        boolean canCapture = false;
        int numberOfDiscs = 0;

        if (initalCol == 0 || initalCol == 1) {
            return 0;
        }
        if (initalRow == spaces.length || initalRow == spaces.length - 1) {
            return 0;
        }
        if (!spaces[initalRow + 1][initalCol - 1].hasDisc()) {
            return 0;
        }

        for (int col = initalCol - 1, row = initalRow + 1; row < spaces.length
                && col >= 0; col--, row++) {

            if (!spaces[row][col].hasDisc()) {
                return 0;
            }
            if (!spaces[row][col].getDiscColor().equals(color)) {
                canCapture = true;
                numberOfDiscs++;
                continue;
            } else {
                if (canCapture == true) {
                    return numberOfDiscs;
                }
                return 0;
            }

        }

        return 0;
    }

    /**
     * testsLeftUp determines if a left diagonal move is valid.
     *
     * @param initalRow is the row position the user clicked
     * @param initalCol is the column position the user clicked
     * @param color is the color of the player currently making a move.
     * @return an integer value is returned that is the number of discs that can
     * be captured
     */
    public int testsLeftUp(int initalRow, int initalCol, String color) {
        boolean canCapture = false;
        int numberOfDiscs = 0;

        if (initalCol == 0 || initalCol == 1) {
            return 0;
        }
        if (initalRow == 0 || initalRow == 1) {
            return 0;

        }
        if (!spaces[initalRow - 1][initalCol - 1].hasDisc()) {
            return 0;

        }

        // tests if capturable
        for (int col = initalCol - 1, row = initalRow - 1; col >= 0 && row >= 0; col--, row--) {

            if (!spaces[row][col].hasDisc()) {
                return 0;
            }
            if (!spaces[row][col].getDiscColor().equals(color)) {
                canCapture = true;
                numberOfDiscs++;
                continue;
            } else {

                if (canCapture = true) {
                    return numberOfDiscs;
                }
                return 0;

            }

        }

        return 0;
    }

    /**
     * testsRightUp determines if a right diagonal move is valid.
     *
     * @param initalRow is the row position the user clicked
     * @param initalCol is the column position the user clicked
     * @param color is the color of the player currently making a move.
     * @return an integer value is returned that is the number of discs that can
     * be captured
     */
    public int testsRightUp(int initalRow, int initalCol, String color) {
        boolean canCapture = false;
        int numberOfDiscs = 0;

        if (initalCol == spaces[initalRow].length - 1
                || initalCol == spaces[initalRow].length) {
            return 0;
        }
        if (initalRow == 0 || initalRow == 1) {
            return 0;

        }
        if (!spaces[initalRow - 1][initalCol + 1].hasDisc()) {
            return 0;

        } else {

            // tests if capturable
            for (int col = initalCol + 1, row = initalRow - 1; col < spaces[row].length
                    && row > 0; col++, row--) {

                if (!spaces[row][col].hasDisc()) {
                    return 0;
                }
                if (!spaces[row][col].getDiscColor().equals(color)) {
                    canCapture = true;
                    numberOfDiscs++;
                    continue;
                } else {

                    if (canCapture == true) {
                        return numberOfDiscs;
                    }
                    return 0;

                }

            }
        }

        return 0;
    }

    /**
     * testsDownUp determines if a right diagonal move is valid.
     *
     * @param initalRow is the row position the user clicked
     * @param initalCol is the column position the user clicked
     * @param color is the color of the player currently making a move.
     * @return an integer value is returned that is the number of discs that can
     * be captured
     */
    public int testsRightDown(int initalRow, int initalCol, String color) {
        boolean canCapture = false;
        int numberOfDiscs = 0;

        if (initalCol == spaces.length - 1 || initalCol == spaces.length) {
            return 0;
        }
        if (initalRow == spaces.length || initalRow == spaces.length - 1) {
            return 0;

        }
        if (!spaces[initalRow + 1][initalCol + 1].hasDisc()) {
            return 0;
        }

        // tests if capturable
        for (int col = initalCol + 1, row = initalRow + 1; row < spaces.length
                && col < spaces.length; col++, row++) {

            if (!spaces[row][col].hasDisc()) {
                return 0;
            }
            if (!spaces[row][col].getDiscColor().equals(color)) {
                canCapture = true;
                numberOfDiscs++;
                continue;
            } else {

                if (canCapture == true) {
                    return numberOfDiscs;
                }
                return 0;

            }

        }

        return 0;
    }

    /**
     * testsHorizontal calls the methods necessary for determining if a
     * horizontal move is valid.
     *
     * @param initalRow is the row position the user clicked
     * @param initalCol is the column position the user clicked
     * @param color is the color of the player currently making a move.
     * @return an integer value is returned that is the number of discs that can
     * be captured
     */
    public int testsHorizontal(int initalRow, int initalCol, String color) {
        // returns if left capture is valid
        if (testLeft(initalRow, initalCol, color) != 0) {
            return testLeft(initalRow, initalCol, color);
        }
        // returns if right capture is valid
        if (testRight(initalRow, initalCol, color) != 0) {
            return testRight(initalRow, initalCol, color);
        }
        // returns if no horizontal move is valid
        return 0;
    }

    /**
     * testRight determines if a right move is valid
     *
     * @param initalRow is the row position the user clicked
     * @param initalCol is the column position the user clicked
     * @param color is the color of the player currently making a move.
     * @return an integer value is returned that is the number of discs that can
     * be captured
     */
    public int testRight(int initalRow, int initalCol, String color) {
        boolean canCapture = false;
        int numberOfDiscs = 0;

        if (initalCol == spaces[initalRow].length - 1
                || initalCol == spaces[initalRow].length) {
            return 0;
        }

        if (!spaces[initalRow][initalCol + 1].hasDisc()) {
            return 0;
        }

        // tests if capturable
        for (int col = initalCol + 1; col < spaces.length; col++) {

            if (!spaces[initalRow][col].hasDisc()) {
                return 0;
            }
            if (!spaces[initalRow][col].getDiscColor().equals(color)) {
                canCapture = true;
                numberOfDiscs++;
                continue;
            } else {

                if (canCapture == true) {
                    return numberOfDiscs;
                }
                return 0;
            }

        }

        return 0;
    }

    /**
     * testsLeft determines if a left move is valid
     *
     * @param initalRow is the row position the user clicked
     * @param initalCol is the column position the user clicked
     * @param color is the color of the player currently making a move.
     * @return an integer value is returned that is the number of discs that can
     * be captured
     */
    public int testLeft(int initalRow, int initalCol, String color) {
        boolean canCapture = false;
        int numberOfDiscs = 0;

        // tests if is Edge of gameboard
        if (initalCol == 1 || initalCol == 0) {
            // tests left
            return 0;
        }

        // tests for disc
        if (!spaces[initalRow][initalCol - 1].hasDisc()) {
            return 0;
        }

        // tests if capturable
        for (int col = initalCol - 1; col >= 0; col--) {

            if (!spaces[initalRow][col].hasDisc()) {
                return 0;
            }
            if (!spaces[initalRow][col].getDiscColor().equals(color)) {
                canCapture = true;
                numberOfDiscs++;
                continue;
            } else {

                if (canCapture == true) {
                    return numberOfDiscs;
                }
                return 0;
            }

        }

        return 0;
    }

    /**
     * testsVertical calls the methods necessary for determining if a vertical
     * move is valid.
     *
     * @param initalRow is the row position the user clicked
     * @param initalCol is the column position the user clicked
     * @param color is the color of the player currently making a move.
     * @return an integer value is returned that is the number of discs that can
     * be captured
     */
    public int testsVertical(int initalRow, int initalCol, String color) {
        // returns if up is capturable
        if (testUp(initalRow, initalCol, color) != 0) {
            return testUp(initalRow, initalCol, color);
        }
        // returns if down is capturable
        if (testDown(initalRow, initalCol, color) != 0) {
            return testDown(initalRow, initalCol, color);
        }
        // returns if no horizontal move is valid
        return 0;

    }

    /**
     * testsDown determines if a down move is valid
     *
     * @param initalRow is the row position the user clicked
     * @param initalCol is the column position the user clicked
     * @param color is the color of the player currently making a move.
     * @return an integer value is returned that is the number of discs that can
     * be captured
     */
    public int testDown(int initalRow, int initalCol, String color) {
        boolean canCapture = false;
        int numberOfDiscs = 0;

        // tests if the space is on the edge of the game board.
        if (initalRow == spaces.length - 1
                || initalRow == spaces.length) {
            return 0;
        }
        // tests if the space below the space selected has an opponent disc.
        if (!spaces[initalRow + 1][initalCol].hasDisc()) {
            return 0;
        }

        // tests if space is capturable
        for (int row = initalRow + 1; row < spaces.length; row++) {

            if (!spaces[row][initalCol].hasDisc()) {
                return 0;
            }

            // tests space for opponent disc.
            if (!spaces[row][initalCol].getDiscColor().equals(color)) {
                canCapture = true;
                numberOfDiscs++;
                continue;
            } else {

                if (canCapture == true) {
                    return numberOfDiscs;
                }
                return 0;
            }

        }

        return 0;

    }

    /**
     * testsUp determines if an up move is valid
     *
     * @param initalRow is the row position the user clicked
     * @param initalCol is the column position the user clicked
     * @param color is the color of the player currently making a move.
     * @return an integer value is returned that is the number of discs that can
     * be captured
     */
    public int testUp(int initalRow, int initalCol, String color) {
        boolean canCapture = false;
        int numberOfDiscs = 0;

        // tests if the space is on the edge of the game board.
        if (initalRow == 0 || initalRow == 1) {
            return 0;
        }
        // tests if the space below the space selected has an opponent disc.
        if (!spaces[initalRow - 1][initalCol].hasDisc()) {
            return 0;
        }

        // tests if space is capturable
        for (int row = initalRow - 1; row >= 0; row--) {

            if (!spaces[row][initalCol].hasDisc()) {
                return 0;
            }

            // tests space for opponent disc.
            if (!spaces[row][initalCol].getDiscColor().equals(color)) {
                canCapture = true;
                numberOfDiscs++;
                continue;
            } else {

                if (canCapture == true) {
                    return numberOfDiscs;
                }
                return 0;
            }

        }

        return 0;
    }

    /**
     * changeLeftSpaces adds discs to the spaces that are being changed
     *
     * @param initalRow is the row position the user clicked
     * @param initalCol is the column position the user clicked
     * @param color is the color of the player currently making a move.
     */
    public void changeLeftSpaces(int row, int initalCol, String color) {
        spaces[row][initalCol].addDisc(color);
        for (int col = initalCol - 1; col >= 0; col--) {
            if (spaces[row][col].getDiscColor().equals(color)) {
                return;
            } else {
                spaces[row][col].addDisc(color);
            }
        }
    }

    /**
     * changeRightSpaces adds discs to the spaces that are being changed
     *
     * @param initalRow is the row position the user clicked
     * @param initalCol is the column position the user clicked
     * @param color is the color of the player currently making a move.
     */
    public void changeRightSpaces(int row, int initalCol, String color) {
        spaces[row][initalCol].addDisc(color);
        for (int col = initalCol + 1; col <= spaces[row].length; col++) {
            if (spaces[row][col].getDiscColor().equals(color)) {
                return;
            } else {
                spaces[row][col].addDisc(color);
            }
        }
    }

    /**
     * changeRightUpDiagonal adds discs to the spaces that are being changed
     *
     * @param initalRow is the row position the user clicked
     * @param initalCol is the column position the user clicked
     * @param color is the color of the player currently making a move.
     */
    public void changeRightUpDiagonal(int initalRow, int initalCol, String color) {
        spaces[initalRow][initalCol].addDisc(color);
        for (int col = initalCol + 1, row = initalRow - 1; col <= spaces.length
                && row >= 0; col++, row--) {
            if (spaces[row][col].getDiscColor().equals(color)) {
                return;
            } else {
                spaces[row][col].addDisc(color);
            }
        }
    }

    /**
     * changeLeftUpDiagonal adds discs to the spaces that are being changed
     *
     * @param initalRow is the row position the user clicked
     * @param initalCol is the column position the user clicked
     * @param color is the color of the player currently making a move.
     */
    public void changeLeftUpDiagonal(int initalRow, int initalCol, String color) {
        spaces[initalRow][initalCol].addDisc(color);
        for (int col = initalCol - 1, row = initalRow - 1; col >= 0 && row >= 0; col--, row--) {
            if (spaces[row][col].getDiscColor().equals(color)) {
                return;
            } else {
                spaces[row][col].addDisc(color);
            }
        }
    }

    /**
     * changeRightDownDiagonal adds discs to the spaces that are being changed
     *
     * @param initalRow is the row position the user clicked
     * @param initalCol is the column position the user clicked
     * @param color is the color of the player currently making a move.
     */
    public void changeRightDownDiagonal(int initalRow, int initalCol,
            String color) {
        spaces[initalRow][initalCol].addDisc(color);
        for (int col = initalCol + 1, row = initalRow + 1; col <= spaces.length
                && row <= spaces.length; col++, row++) {
            if (spaces[row][col].getDiscColor().equals(color)) {
                return;
            } else {
                spaces[row][col].addDisc(color);
            }
        }
    }

    /**
     * changeLeftDownDiagnoal adds discs to the spaces that are being changed
     *
     * @param initalRow is the row position the user clicked
     * @param initalCol is the column position the user clicked
     * @param color is the color of the player currently making a move.
     */
    public void changeLeftDownDiagonal(int initalRow, int initalCol,
            String color) {
        spaces[initalRow][initalCol].addDisc(color);
        for (int col = initalCol - 1, row = initalRow + 1; col >= 0
                && row <= spaces.length; col--, row++) {
            if (spaces[row][col].getDiscColor().equals(color)) {
                return;
            } else {
                spaces[row][col].addDisc(color);
            }
        }
    }

    /**
     * changeUpSpaces adds discs to the spaces that are being changed
     *
     * @param initalRow is the row position the user clicked
     * @param initalCol is the column position the user clicked
     * @param color is the color of the player currently making a move.
     */
    public void changeUpSpaces(int initalRow, int col, String color) {
        spaces[initalRow][col].addDisc(color);

        for (int row = initalRow - 1; row >= 0; row--) {
            if (spaces[row][col].getDiscColor().equals(color)) {
                return;
            } else {
                spaces[row][col].addDisc(color);
            }
        }
    }

    /**
     * changeDownSpaces adds discs to the spaces that are being changed
     *
     * @param initalRow is the row position the user clicked
     * @param initalCol is the column position the user clicked
     * @param color is the color of the player currently making a move.
     */
    public void changeDownSpaces(int initalRow, int col, String color) {
        spaces[initalRow][col].addDisc(color);

        for (int row = initalRow + 1; row <= spaces.length; row++) {
            if (spaces[row][col].getDiscColor().equals(color)) {
                return;
            } else {
                spaces[row][col].addDisc(color);
            }
        }
    }

    /**
     * placeGamePiece iterates through the possible moves and places discs if
     * the move is valid
     *
     * @param initalRow is the row position the user clicked
     * @param initalCol is the column position the user clicked
     * @param color is the color of the player currently making a move.
     */
    public void placeGamePiece(int x, int y, String color) {
        for (int row = 0; row < spaces.length; row++) {

            for (int col = 0; col < spaces[row].length; col++) {
                if (!spaces[row][col].hasDisc()) {
                    if (spaces[row][col].testsDimension(x, y)) {
                        if (testLeft(row, col, color) != 0) {
                            changeLeftSpaces(row, col, color);
                        }
                        if (testRight(row, col, color) != 0) {
                            changeRightSpaces(row, col, color);
                        }
                        if (testUp(row, col, color) != 0) {
                            changeUpSpaces(row, col, color);
                        }
                        if (testDown(row, col, color) != 0) {
                            changeDownSpaces(row, col, color);
                        }
                        if (testsRightUp(row, col, color) != 0) {
                            changeRightUpDiagonal(row, col, color);
                        }
                        if (testsRightDown(row, col, color) != 0) {
                            changeRightDownDiagonal(row, col, color);
                        }
                        if (testsLeftUp(row, col, color) != 0) {
                            changeLeftUpDiagonal(row, col, color);
                        }
                        if (testsLeftDown(row, col, color) != 0) {
                            changeLeftDownDiagonal(row, col, color);
                        }

                    }
                }
            }
        }
    }

    /**
     * makesMove finds a possible move and makes it.
     */
    public void makesMove() {
        int largerRow = 0;
        int largerCol = 0;
        String color = "Red";
        if ((isValidSpace(0, 0, color) != 0)) {
            placeGamePieceComputer(0, 0, color);
            return;
        }
        if ((isValidSpace(0, spaces.length - 1, color) != 0)) {
            placeGamePieceComputer(0, spaces.length-1, color);
            return;
        }
        if ((isValidSpace(spaces.length-1, 0, color) != 0)) {
            placeGamePieceComputer(spaces.length-1, 0, color);
            return;
        }
        if(isValidSpace(0,spaces.length - 1, color) != 0){
            placeGamePieceComputer(0, spaces.length - 1, color);
            return;
        }
        if((isValidSpace(spaces.length - 1, spaces.length - 1, color) != 0)){
            placeGamePieceComputer(spaces.length - 1, spaces.length - 1, color);
            return;
        }
        
        for (int row = 0; row < spaces.length; row++) {
            for (int col = 0; col < spaces.length; col++) {
                if (!spaces[row][col].hasDisc()) {
                    if (isValidSpace(row, col, color) > 0 && isValidSpace(row, col, color) > isValidSpace(largerRow, largerCol, color)) {
                        largerRow = row;
                        largerCol = col;

                    }
                }
            }
        }
        placeGamePieceComputer(largerRow, largerCol, color);
        return;

    }

    /**
     * @param row
     * @param col
     * @param color
     */
    public void placeGamePieceComputer(int row, int col, String color) {

        if (testLeft(row, col, color) != 0) {
            changeLeftSpaces(row, col, color);
        }
        if (testRight(row, col, color) != 0) {
            changeRightSpaces(row, col, color);
        }
        if (testUp(row, col, color) != 0) {
            changeUpSpaces(row, col, color);
        }
        if (testDown(row, col, color) != 0) {
            changeDownSpaces(row, col, color);
        }
        if (testsRightUp(row, col, color) != 0) {
            changeRightUpDiagonal(row, col, color);
        }
        if (testsRightDown(row, col, color) != 0) {
            changeRightDownDiagonal(row, col, color);
        }
        if (testsLeftUp(row, col, color) != 0) {
            changeLeftUpDiagonal(row, col, color);
        }
        if (testsLeftDown(row, col, color) != 0) {
            changeLeftDownDiagonal(row, col, color);
        }
    }

    public int isValidSpace(int row, int col, String color) {
        // checks horizontal movement
        if (testsHorizontal(row, col, color) != 0) {
            return testsHorizontal(row, col, color);

        }
        // checks vertical movement
        if (testsVertical(row, col, color) != 0) {
            return testsVertical(row, col, color);
        }

        if (testsRightDiagonal(row, col, color) != 0) {
            return testsRightDiagonal(row, col, color);
        }

        if (testsLeftDiagonal(row, col, color) != 0) {
            return testsLeftDiagonal(row, col, color);
        }
        // returns if space is not a valid move
        return 0;
    }

    /**
     * @return returnsComputerSpaces returns the amount of spaces that the
     * computer player controls
     */
    public int returnsComputerSpaces() {
        int computerDiscCount = 0;
        for (int row = 0; row < spaces.length; row++) {
            for (int col = 0; col < spaces.length; col++) {
                if (spaces[row][col].hasDisc()) {
                    if (spaces[row][col].getDiscColor().equals("Red")) {
                        computerDiscCount++;
                    }
                }
            }
        }
        return computerDiscCount;
    }

    /**
     * @return userMoveAvailable returns whether the user has a valid move
     */
    public boolean userMoveAvailable() {
        for (int row = 0; row < spaces.length; row++) {
            for (int col = 0; col < spaces[row].length; col++) {
                if (!spaces[row][col].hasDisc()) {
                    if (isValidSpace(row, col, "Blue") != 0) {
                        return true;
                    }

                }
            }
        }
        return false;
    }

    /**
     * @return computerMoveAvailable returns whether the computer has a valid
     * move
     */
    public boolean computerMoveAvailable() {
        for (int row = 0; row < spaces.length; row++) {
            for (int col = 0; col < spaces[row].length; col++) {
                if (!spaces[row][col].hasDisc()) {
                    if (isValidSpace(row, col, "Red") != 0) {
                        return true;

                    }
                }
            }
        }
        return false;
    }

    public boolean testsIsFinished() {
        for (int row = 0; row < spaces.length; row++) {
            for (int col = 0; col < spaces.length; col++) {
                if (!spaces[row][col].hasDisc()) {
                    return false;
                }
            }
        }

        return true;
    }

}
