package SzachyPackage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Hetman extends Pionek {

    private BufferedImage hetmanObrazek;

    public Hetman() {
    }

    public Hetman(int miejsce) {
        super.setPole(miejsce);
    }

    public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        try {
            hetmanObrazek = ImageIO.read(getClass().getResource("/Images/queen.jpg"));
        } catch (IOException ex) {
            System.out.println(ex);
        }

        g.drawImage(hetmanObrazek, 0, 0, null);
    }

    public void zajetePola() { 

        int x = 7;
        int y = (int) Math.floor((super.getPole() / 8));
        while (x % 8 > -1) {
            super.dodajPozycje(x + y * 8);
            x--;
        }

        y = 7;
        x = (int) Math.floor((super.getPole() % 8));
        while (y % 8 > -1) {
            super.dodajPozycje(x + y * 8);
            y--;
        }

        y = (int) Math.floor((super.getPole() / 8));
        x = super.getPole() % 8;
        int a = 1;
        while (((x - 1) % 8 > -1) && y - 1 > -1 && super.getPole() > 7) {
            super.dodajPozycje(super.getPole() - 9 * a);
            x--;
            y--;
            a++;
        }

        y = (int) Math.floor((super.getPole() / 8));
        x = super.getPole() % 8;
        a = 1;
        while (((x + 1) % 8 > 0) && y < 7) {
            super.dodajPozycje(super.getPole() + 9 * a);
            x++;
            y++;
            a++;
        }

        y = (int) Math.floor((super.getPole() / 8));
        x = super.getPole() % 8;
        a = 1;
        while (((x + 1) % 8 > 0) && y - 1 > -1 && super.getPole() > 7) {
            super.dodajPozycje(super.getPole() - 7 * a);
            x++;
            y--;
            a++;
        }

        x = super.getPole() % 8;
        y = (int) Math.floor((super.getPole() / 8));
        a = 1;
        while (((x - 1) % 8 > -1) && y < 7) {
            super.dodajPozycje(super.getPole() + 7 * a);
            x--;
            y++;
            a++;
        }



    }
}

