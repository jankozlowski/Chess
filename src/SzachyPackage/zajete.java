package SzachyPackage;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

     public class zajete extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(135, 0, 0));
            g.fillRect(0,0,60,60);
        }
    }