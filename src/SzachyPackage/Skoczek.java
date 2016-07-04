package SzachyPackage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Skoczek extends Pionek{

    private BufferedImage skoczekObrazek;
    
    public Skoczek() {}
    
    public Skoczek(int miejsce) {
        super.setPole(miejsce);
    }

    public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        try {
            skoczekObrazek = ImageIO.read(getClass().getResource("/Images/knight.jpg"));
        } catch (IOException ex) {
            System.out.println(ex);
        }

        g.drawImage(skoczekObrazek, 0, 0, null);
    }

    public void zajetePola() { 
        int x = super.getPole() % 8;
        int y = (int) Math.floor((super.getPole() / 8));
        super.dodajPozycje(super.getPole());
        if(x-1 > -1 && y-2 >-1)
            super.dodajPozycje(super.getPole()-17);
        if(x-2 > -1 && y-1 >-1)
            super.dodajPozycje(super.getPole()-10);
        if(x-2 >-1 && y+1<8)
            super.dodajPozycje(super.getPole()+6);
        if(x-1 > -1 && y+2<8 )
            super.dodajPozycje(super.getPole()+15);
        if((x+1)%8 >0 && y+2<8)
            super.dodajPozycje(super.getPole()+17);
        if((x+2)%8 >1 && y+1<8)
            super.dodajPozycje(super.getPole()+10);
        if((x+2)%8 >1 && y-1>-1)
            super.dodajPozycje(super.getPole()-6);
        if((x+1)%8 >0 && y-2>-1)
            super.dodajPozycje(super.getPole()-15);
        };
    }


