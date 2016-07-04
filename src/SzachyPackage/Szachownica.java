package SzachyPackage;

import java.awt.*;
import javax.swing.*;

public class Szachownica extends JPanel{
      
    private Color Whity = new Color(255,206,158); 
    private Color Darky = new Color(209,139,71);
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int plus=1;
        for(int a=0; a<8; a++){
            plus++;
            for(int b=0; b<8; b++){

                if(plus%2==0)
                    
                    g.setColor(Whity);
                else
                    g.setColor(Darky);
        g.fillRect(60*b,60*a,60,60);
        plus++;
        
            }
            
        }
    }
}
