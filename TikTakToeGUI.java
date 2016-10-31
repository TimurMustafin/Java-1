
package tiktaktoegui;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.*;


class TikTakToeGUI extends JFrame {
    final String TITLE_OF_PROGRAM = "Tic Tac Toe";
    final int START_POSITION = 300;
    final int WINDOW_SIZE = 450;
    final int WINDOW_DX = 18;
    final int WINDOW_DY = 65;
    final int FIELD_SIZE = 3;
    final int CELL_SIZE = WINDOW_SIZE/FIELD_SIZE;
    final String BTN_INIT = "NEW GAME";
    final String BTN_EXIT = "EXIT GAME";
    final char HUMAN_DOT = 'x';
    final char AI_DOT = 'o';
    Random rand = new Random();
    Canvas canvas;
    GameField field = new GameField();
    Human human = new Human(HUMAN_DOT);
    AI ai = new AI(AI_DOT);
    
    public static void main(String args[]) {
        new TikTakToeGUI();
    }
    

    TikTakToeGUI() {
        setTitle("Test Window");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_POSITION, START_POSITION, WINDOW_SIZE + WINDOW_DX, WINDOW_SIZE + WINDOW_DY);
        Dimension size = new Dimension();
        size = this.getContentPane().getSize();
        /*contentSizeX = size.width*100;
        contentSizeY = size.height*100;*/
        JButton init = new JButton(BTN_INIT);
        init.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                field = new GameField();
                canvas.repaint();
            }
        });
        init.setSize(200, 10);
         
        JButton exit = new JButton(BTN_EXIT);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        exit.setSize(200, 10);
        
        JPanel bp = new JPanel();
        canvas = new Canvas();
        canvas.setBackground(Color.white);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int y = e.getX()/CELL_SIZE + 1;
                int x = e.getY()/CELL_SIZE + 1;
                human.turn(x, y);
            }
        });

        bp.setLayout(new GridLayout());
        bp.add(init);
        bp.add(exit);

        setLayout(new BorderLayout());
        add(bp, BorderLayout.SOUTH);
        add(canvas, BorderLayout.CENTER);
        setVisible(true);
    }

    
    
    class GameField {
    public static final char EMPTY_DOT = '.';
    public static final char PLAYER_DOT = 'X';
    public static final char AI_DOT = '0';
    boolean gameOver;
    public char[][] field = new char[FIELD_SIZE][FIELD_SIZE];
    
    GameField() {        
            field = new char[FIELD_SIZE][FIELD_SIZE];
            for(int i = 0; i < FIELD_SIZE; i++){
                for(int j = 0; j < FIELD_SIZE; j++)
                    field[i][j] = EMPTY_DOT;
            }
            gameOver = false;
    }
    
    void setDot(int x, int y, char ch) { // set dot and check fill and win
            field[x][y] = ch;
            if (isFull()) {
                System.out.println("Draw...");
                gameOver = true;
            }
            if (isWin(human.getDot())) {
                System.out.println("YOU WON!");
                gameOver = true;
            }
            if (isWin(ai.getDot())) {
                System.out.println("AI WON!");
                gameOver = true;
            }
     }

    boolean isCellEmpty(int x, int y) {
            if (x < 0 || y < 0 || x > FIELD_SIZE - 1 || y > FIELD_SIZE - 1) return false;
            if (field[x][y] == EMPTY_DOT) return true;
            return false;
    }
    boolean isFull() {
            for (int i = 0; i < FIELD_SIZE; i++)
                for (int j = 0; j < FIELD_SIZE; j++)
                    if (field[i][j] == EMPTY_DOT) return false;
            return true;
    }
    boolean isWin(char ch) {
            // checking horizontals / verticals
            for (int i = 0; i < FIELD_SIZE; i++) {
                if (field[i][0] == ch && field[i][1] == ch && field[i][2] == ch) return true;
                if (field[0][i] == ch && field[1][i] == ch && field[2][i] == ch) return true;
            }
            // checking diagonals
            if(field[0][0] == ch && field[1][1] == ch && field[2][2] == ch) return true;
            if(field[2][0] == ch && field[1][1] == ch && field[0][2] == ch) return true;
            return false;
    }
    void paint(Graphics g){
         g.setColor(Color.LIGHT_GRAY);
         for(int i = 0; i < FIELD_SIZE; i++){
            g.drawLine(0, i*CELL_SIZE, WINDOW_SIZE, i*CELL_SIZE);
            g.drawLine(i*CELL_SIZE, 0, i*CELL_SIZE, WINDOW_SIZE);
            }
         g.drawLine(WINDOW_SIZE, 0, WINDOW_SIZE, WINDOW_SIZE);
         g.drawLine(0, WINDOW_SIZE, WINDOW_SIZE, WINDOW_SIZE);
        Graphics2D g2 = (Graphics2D) g; // use Graphics2D
            g2.setStroke(new BasicStroke(5));
            for (int y = 0; y < FIELD_SIZE; y++) {
                for (int x = 0; x < FIELD_SIZE; x++) {
                    if (field[x][y] == human.getDot()) {
                        g.setColor(Color.blue);
                        g2.draw(new Line2D.Float(x*CELL_SIZE+CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4, (x+1)*CELL_SIZE-CELL_SIZE/4, (y+1)*CELL_SIZE-CELL_SIZE/4));
                        g2.draw(new Line2D.Float(x*CELL_SIZE+CELL_SIZE/4, (y+1)*CELL_SIZE-CELL_SIZE/4, (x+1)*CELL_SIZE-CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4));
                    }
                    if (field[x][y] == ai.getDot()) {
                        g.setColor(Color.red);
                        g2.draw(new Ellipse2D.Float(x*CELL_SIZE+CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4, CELL_SIZE/2, CELL_SIZE/2));
                    }
                }
            }
    }
    boolean isGameOver() { return gameOver; }
    void setOver(){gameOver = true;}
    }
  
    abstract class Player{
        char DOT;
        abstract void turn(int x, int y);
        //void turn() {}
        char getDot() { return DOT; }
    
    }
    
    class Human extends Player{
        Human(char ch) { DOT = ch; }

        @Override
        void turn(int x, int y) {
            if (field.isCellEmpty(x, y)) {
                if (!field.isGameOver()) field.setDot(x, y, DOT);
                if (!field.isGameOver()) ai.turn();
            }
        }
    
    }
    
    class AI extends Player{
    
    }
    
    class Canvas extends JPanel { // for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            field.paint(g);
            
            
        }
    }
}
