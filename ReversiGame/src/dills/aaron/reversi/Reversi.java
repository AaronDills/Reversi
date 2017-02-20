package dills.aaron.reversi;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.*;
import javax.swing.JOptionPane;

/**
 *
 * @author aarondills
 */
public class Reversi {

    /**
     * height is the vertical length in pixels of the window
     */
    public static final int height = 445;

    /**
     * width is the horizontal length in pixels of the window
     */
    public static final int width = 400;

    /**
     * goesFirst is a random integer that determine whether the computer or the
     * player makes the first move
     */
    public static final int goesFirst = (int) (Math.random() * 2);

    /**
     * main is the execution of the Reversi game
     *
     * @param args
     */
    public static void main(String[] args) {

        MyFrame frame = new MyFrame(width, height);
        frame.setTitle("Reversi");
        frame.setVisible(true);

    }
}
/*
 The Class MyFrame is the window the game is played on
 */

class MyFrame extends JFrame {
    /*
     The panel gamePanel is the panel the game is displayed on
     */

    private MyPanel gamePanel;

    /**
     * The constructor MyFrame initializes the components of the window
     *
     * @param width is the width of the window
     * @param height is the height of the window
     */
    public MyFrame(int width, int height) {
        setLocation(0, 0);
        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        gamePanel = new MyPanel();
        add(gamePanel);
        gamePanel.setFocusable(true);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem newGame = new JMenuItem("New Game");
        menu.add(newGame);
        JMenuItem exitGame = new JMenuItem("Exit");
        menu.add(exitGame);
        newGame.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                gamePanel.setBoard();
            }
        });
        exitGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

}

/**
 * MyPanel is the panel the game is displayed on
 *
 * @author aarondills
 */
class MyPanel extends JPanel {

    /**
     * gameBoard is the object of type Board that contains the information
     * necessary for the game to run
     */
    private Board gameBoard;

    public MyPanel() {
        this.gameBoard = new Board();
        /**
         * The mouseMotionListener calls methods from the Board class in order
         * to display the correct data
         */
        addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseMoved(MouseEvent e) {

                gameBoard.testsSpaceHoverOver(e.getX(), e.getY());
                repaint();
                if (gameBoard.testsIsFinished()) {
                    int computerDiscCount = gameBoard
                            .returnsComputerSpaces();
                    int playerDiscCount = 64 - computerDiscCount;
                    String winner = "";
                    String result = "Computer Disc Count: "
                            + computerDiscCount + "\nPlayer Disc Count: "
                            + playerDiscCount;
                    if (playerDiscCount > computerDiscCount) {
                        winner = "You Win!";
                    }
                    if (playerDiscCount < computerDiscCount) {
                        winner = "You Lose!";
                    }
                    if (playerDiscCount == computerDiscCount) {
                        winner = "Tie!";
                    }

                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    JOptionPane.showMessageDialog(null, result + "\n" + winner,
                            "Final Score", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);

                }
                if (!gameBoard.userMoveAvailable()) {
                    JOptionPane.showMessageDialog(null, "There are no possible moves for you. The computer will now make another move",
                            "No Moves Available", JOptionPane.INFORMATION_MESSAGE);
                    gameBoard.makesMove();

                }

                repaint();
            }

        });

        /**
         * This mouseListener listens for clicks and makes user and computer
         * moves when activated
         */
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 1) {
                    int x = e.getX();
                    int y = e.getY();
                    if (gameBoard.testsSpaceHoverOver(x, y)) {
                        gameBoard.placeGamePiece(x, y, "Blue");
                        gameBoard.makesMove();
                    }
                }
                repaint();

            }
        });

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        setBackground(Color.WHITE);
        gameBoard.draw(g2);
    }

    public Board getBoard() {
        return this.gameBoard;
    }

    public void setBoard() {
        this.gameBoard = new Board();
        repaint();

    }

    public int computerDiscs() {
        return this.gameBoard.returnsComputerSpaces();
    }
}
