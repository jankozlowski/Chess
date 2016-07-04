package SzachyPackage;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Goniec extends Pionek {

    private BufferedImage goniecObrazek;
    
    
    public Goniec() {}
    
    public Goniec(int miejsce) {
        super.setPole(miejsce);
    }

    public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        try {
            goniecObrazek = ImageIO.read(getClass().getResource("/Images/bishop.jpg"));
        } catch (IOException ex) {
            System.out.println(ex);
        }

        g.drawImage(goniecObrazek, 0, 0, null);
    }

    public void zajetePola() { 
        super.dodajPozycje(super.getPole());
        int y = (int) Math.floor((super.getPole() / 8));
        int x = super.getPole() % 8;
        int a=1;
        while (((x - 1) % 8 > -1) && y-1 > -1 && super.getPole()>7) {
            super.dodajPozycje(super.getPole() - 9*a);
            x--;
            y--;
            a++;
        }
     
        y = (int) Math.floor((super.getPole() / 8));
        x = super.getPole() % 8;
        a=1;
        while (((x + 1) % 8 > 0) && y < 7) {
            super.dodajPozycje(super.getPole() + 9*a);
            x++;
            y++;
            a++;
        }
        
        y = (int) Math.floor((super.getPole() / 8));
        x = super.getPole() % 8;
        a=1;
        while (((x + 1) % 8 > 0) && y-1 > -1  && super.getPole()>7) {
            super.dodajPozycje(super.getPole() - 7*a);
            x++;
            y--;
            a++;
        }
        
        x = super.getPole() % 8;
        y = (int) Math.floor((super.getPole() / 8));
        a=1;
        while (((x - 1) % 8 > -1) &&  y < 7) {
            super.dodajPozycje(super.getPole() + 7*a);
            x--;
            y++;
            a++;
        }
    }
}
