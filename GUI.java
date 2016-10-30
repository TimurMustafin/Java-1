
package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.SwingConstants.*;

class GUI extends JFrame {

    Main main;

    GUI() {
        setTitle("TIK TAK TOE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(300, 300, 600, 600);

        JButton btnNew = new JButton("New game");
        btnNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("First");
            }
        });
        JButton btnExit = new JButton("Exit game");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("First");
            }
        });
        
        JPanel bp = new JPanel();
        
        main = new Main();
        main.setBackground(Color.white);
        main.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = e.getX();
                int y = e.getY();
                System.out.println(x + ":" + y);
            }
        });

        bp.setLayout(new GridLayout());
        bp.add(btnNew);
        bp.add(btnExit);
        

        setLayout(new BorderLayout());
        add(bp, BorderLayout.SOUTH);
        add(main, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String args[]) {
        new GUI();
    }
    
    class Main extends JPanel { // for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for(int i = 0; i <= 600; i+=200){
                for(int j = 0; j<= 600; j+=200)
                    g.drawLine(i, j, i, 600);
            }
            for(int i = 0; i <= 600; i+=200){
                for(int j = 0; j<= 600; j+=200)
                    g.drawLine(j, i, 600, i);
            }
        }
    }
}