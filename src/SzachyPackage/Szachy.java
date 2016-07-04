package SzachyPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Szachy implements Runnable, ActionListener, MouseListener, ChangeListener {

    private JMenuItem nowyMenuItem, rozwiazanieMenuItem, exitMenuItem, oProgramieMenuItem;      //Tweorzenie zmiennych wykorzystywanych w projekcie
    private JCheckBoxMenuItem zegarMenuItem;
    private JFrame okno, opro, oknoBledow;
    private JButton OK;
    private int xpos, ypos, dodanychPionkow, dodanychPustychPol;
    private ArrayList<Pionek> pionki = new ArrayList<Pionek>();
    private ArrayList<zajete> zajentepola = new ArrayList<zajete>();
    private JLayeredPane pola1, pola2, pola3;
    private Szachownica kratka1, kratka2, kratka3;
    private JTabbedPane naglowki;
    private Zegar czas;

    public void mouseClicked(MouseEvent e) {                        
        xpos = e.getX();                                            
        ypos = e.getY();
        int panel = naglowki.getSelectedIndex();                    
        if (panel == 0) {                                           
            try {                                                    
                dodawaniePionka(pola1, 5);
            } catch (IllegalArgumentException i) {
                JOptionPane.showMessageDialog(oknoBledow, "Liczba pionkow nie moze przekroczyc 5", "Za duzo Pionkow", JOptionPane.ERROR_MESSAGE);
            }
        } else if (panel == 1) {
            try {
                dodawaniePionka(pola2, 12);
            } catch (IllegalArgumentException i) {
                JOptionPane.showMessageDialog(oknoBledow, "Liczba pionkow nie moze przekroczyc 12", "Za duzo Pionkow", JOptionPane.ERROR_MESSAGE);
            }
        } else if (panel == 2) {
            try {
                dodawaniePionka(pola3, 8);
            } catch (IllegalArgumentException i) {
                JOptionPane.showMessageDialog(oknoBledow, "Liczba pionkow nie moze przekroczyc 8", "Za duzo Pionkow", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void dodawaniePionka(JLayeredPane wyborPola, int LiczbaPionkow) {        
        if (dodanychPionkow > LiczbaPionkow - 1) {                                  
            throw new IllegalArgumentException("Przekroczono liczbe pionkow");
        }
        int x = (int) Math.ceil((double) (xpos / 60) );                             
        int y = (int) Math.ceil((double) (ypos / 60) );                             
        int place = (x) + ((y) * 8);                                                
        int panel = naglowki.getSelectedIndex();                                    
        if (panel == 0) {
            pionki.add(new Hetman(place));
        } else if (panel == 1) {
            pionki.add(new Skoczek(place));
        } else {
            pionki.add(new Goniec(place));
        }
        pionki.get(dodanychPionkow).zajetePola();                                   
        pionki.get(dodanychPionkow).setBounds( x * 60 , y * 60 , 60 ,  60 );       
        wyborPola.add(pionki.get(dodanychPionkow), new Integer(2));                
        int tablica[] = new int[pionki.get(dodanychPionkow).sizeKolekcji()];        
        tablica = pionki.get(dodanychPionkow).wyswietl();                          
        for (int j = 0; j < pionki.get(dodanychPionkow).sizeKolekcji(); j++) {     
            zajentepola.add(new zajete());                                         
            int pozx = tablica[j] % 8;                                             
            int pozy = (int) Math.floor(tablica[j] / 8);                            
            zajentepola.get(dodanychPustychPol).setBounds(pozx * 60,pozy * 60, 60 , 60 );   
            wyborPola.add(zajentepola.get(dodanychPustychPol), new Integer(1));             
            dodanychPustychPol++;
        }
        dodanychPionkow++;
        if (dodanychPionkow == LiczbaPionkow) {             
            if (zegarMenuItem.getState() == true)
                czas.zatrzymaj();
            zwyciestwo();
            
       
        }
    }

    public void zwyciestwo() {                                         
        HashSet<Integer> wszystkieZajetePola = new HashSet<Integer>(); 
        for (int i = 0; i < pionki.size(); i++) {
            int[] tablicaPol = new int[pionki.get(i).sizeKolekcji()];
            tablicaPol = pionki.get(i).wyswietl();
            for (int k = 0; k < pionki.get(i).sizeKolekcji(); k++) {
                wszystkieZajetePola.add(tablicaPol[k]);
            }
        }
        if (wszystkieZajetePola.size() == 64) {
            JOptionPane.showMessageDialog(oknoBledow, "Wszystkie pola sa atakowane gratulacje wygrales", "Wygrales", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(oknoBledow, "NIe wszystkie pola sa atakowane i nie masz juz wiecej ruchow niestety przegrales", "Przegrales", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void dodawaniePionka(JLayeredPane wyborPola, int LiczbaPionkow, int polePionka) {   
        if (dodanychPionkow > LiczbaPionkow - 1) {
            throw new IllegalArgumentException("Przekroczono liczbe pionkow");
        }
        int panel = naglowki.getSelectedIndex();
        if (panel == 0) {
            pionki.add(new Hetman(polePionka));
        } else if (panel == 1) {
            pionki.add(new Skoczek(polePionka));
        } else {
            pionki.add(new Goniec(polePionka));
        }
        pionki.get(dodanychPionkow).zajetePola();
        pionki.get(dodanychPionkow).setBounds((polePionka % 8) * 60,(int) Math.floor(polePionka / 8) * 60,60, 60);
        wyborPola.add(pionki.get(dodanychPionkow), new Integer(2));
        int tablica[] = new int[pionki.get(dodanychPionkow).sizeKolekcji()];
        tablica = pionki.get(dodanychPionkow).wyswietl();
        for (int j = 0; j < pionki.get(dodanychPionkow).sizeKolekcji(); j++) {
            zajentepola.add(new zajete());
            int pozx = tablica[j] % 8;
            int pozy = (int) Math.floor(tablica[j] / 8);
            zajentepola.get(dodanychPustychPol).setBounds( pozx * 60, pozy * 60, 60 , 60 );
            wyborPola.add(zajentepola.get(dodanychPustychPol), new Integer(1));
            dodanychPustychPol++;
        }
        dodanychPionkow++;


    }

    public void nowy() {                                
        pionki.clear();                                 
        zajentepola.clear();
        dodanychPionkow = 0;                            
        dodanychPustychPol = 0;
        pola1.removeAll();                                
        pola1.repaint();                                
        pola1.add(kratka1, new Integer(0));             
        pola2.removeAll();
        pola2.repaint();
        pola2.add(kratka2, new Integer(0));
        pola3.removeAll();
        pola3.repaint();
        pola3.add(kratka3, new Integer(0));
        if (zegarMenuItem.getState() == true){         
            czas.zamknij();
            czas = new Zegar();
        }

    }

    public void rozwiazanie() {                         
        int panel = naglowki.getSelectedIndex();
        nowy();                                         
        if (panel == 0) {                               
            dodawaniePionka(pola1, 5, 8);
            dodawaniePionka(pola1, 5, 19);
            dodawaniePionka(pola1, 5, 38);
            dodawaniePionka(pola1, 5, 49);
            dodawaniePionka(pola1, 5, 60);
            if (zegarMenuItem.getState() == true)       
                czas.zamknij();
        } else if (panel == 1) {
            dodawaniePionka(pola2, 12, 13);
            dodawaniePionka(pola2, 12, 17);
            dodawaniePionka(pola2, 12, 18);
            dodawaniePionka(pola2, 12, 20);
            dodawaniePionka(pola2, 12, 21);
            dodawaniePionka(pola2, 12, 26);
            dodawaniePionka(pola2, 12, 37);
            dodawaniePionka(pola2, 12, 42);
            dodawaniePionka(pola2, 12, 43);
            dodawaniePionka(pola2, 12, 45);
            dodawaniePionka(pola2, 12, 46);
            dodawaniePionka(pola2, 12, 50);
            if (zegarMenuItem.getState() == true)
                czas.zamknij();
        } else if (panel == 2) {
            dodawaniePionka(pola3, 8, 4);
            dodawaniePionka(pola3, 8, 12);
            dodawaniePionka(pola3, 8, 20);
            dodawaniePionka(pola3, 8, 28);
            dodawaniePionka(pola3, 8, 36);
            dodawaniePionka(pola3, 8, 44);
            dodawaniePionka(pola3, 8, 52);
            dodawaniePionka(pola3, 8, 60);
            if (zegarMenuItem.getState() == true)
                czas.zamknij();
        }

    }

    public void mousePressed(MouseEvent e) {                    
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
    
    public void stateChanged(ChangeEvent e) {                 
        Object source = e.getSource();
        if (source == naglowki) {
            nowy();
        }
    }

    public class zajete extends JPanel {                    

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(135, 0, 0));
            g.fillRect(0,0,60,60);
        }
    }

    public static void main(String args[]) {

        SwingUtilities.invokeLater(new Szachy());

    }

    public void run() {

        okno = new JFrame("Szachy");
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                

        JMenuBar menuBar = new JMenuBar();                          

        JMenu plikMenu = new JMenu("Plik");                         
        plikMenu.setMnemonic(KeyEvent.VK_P);

        JMenu zegarMenu = new JMenu("Licznik");
        zegarMenu.setMnemonic(KeyEvent.VK_L);
        
        JMenu pomocMenu = new JMenu("Pomoc");
        pomocMenu.setMnemonic(KeyEvent.VK_H);
        
        nowyMenuItem = new JMenuItem("Nowy", KeyEvent.VK_N);                                        
        nowyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK)); 
        nowyMenuItem.addActionListener(this);                                                       
        plikMenu.add(nowyMenuItem);                                                                 


        rozwiazanieMenuItem = new JMenuItem("Rozwiazanie", KeyEvent.VK_R);
        rozwiazanieMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        rozwiazanieMenuItem.addActionListener(this);
        plikMenu.add(rozwiazanieMenuItem);

        exitMenuItem = new JMenuItem("Wyjscie", KeyEvent.VK_X);
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK | ActionEvent.CTRL_MASK));
        exitMenuItem.addActionListener(this);
        plikMenu.add(exitMenuItem);
        
        zegarMenuItem = new JCheckBoxMenuItem("Wlacz/Wylacz Zegar");                
        zegarMenuItem.addActionListener(this);
        zegarMenuItem.setState(true);
        czas = new Zegar();                                                         

        
        zegarMenu.add(zegarMenuItem);

        oProgramieMenuItem = new JMenuItem("O programie", KeyEvent.VK_F1);
        oProgramieMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.CTRL_MASK));
        oProgramieMenuItem.addActionListener(this);
        pomocMenu.add(oProgramieMenuItem);

        menuBar.add(plikMenu);
        menuBar.add(pomocMenu);
        menuBar.add(zegarMenu);
        okno.setJMenuBar(menuBar);

        dodanychPionkow = 0;
        dodanychPustychPol = 0;

        naglowki = new JTabbedPane();                               
        naglowki.addChangeListener(this);
        pola1 = new JLayeredPane();                                 
        pola1.addMouseListener(this);
        pola2 = new JLayeredPane();
        pola2.addMouseListener(this);
        pola3 = new JLayeredPane();
        pola3.addMouseListener(this);

        kratka1 = new Szachownica();                                
        kratka1.setBounds(0, 0, 480, 480);
        kratka2 = new Szachownica();
        kratka2.setBounds(0, 0, 480, 480);
        kratka3 = new Szachownica();
        kratka3.setBounds(0, 0, 480, 480);

        pola1.add(kratka1, new Integer(0));                         
        pola2.add(kratka2, new Integer(0));
        pola3.add(kratka3, new Integer(0));
        
        naglowki.addTab("Hetmani", pola1);                           
        naglowki.addTab("Skoczkowie", pola2);
        naglowki.addTab("Goncy", pola3);

        okno.setLayout(new BorderLayout());

        okno.add(naglowki, BorderLayout.CENTER);

        okno.setSize(500, 570);
        okno.setVisible(true);
    }

    public void oProgramie() {                                  
        opro = new JFrame("O Programie");
        opro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        opro.setLayout(new BoxLayout(opro.getContentPane(), BoxLayout.Y_AXIS));

        Font Czcionka = new Font("Arial", Font.PLAIN, 16);

        JTextField wersja = new JTextField("Szachy 1.0");
        wersja.setEditable(false);
        wersja.setOpaque(false);
        wersja.setHorizontalAlignment(JTextField.CENTER);
        wersja.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        JTextArea opis = new JTextArea("\nNiniejszy program to prosta gra logiczna ktora pozwala na sprawdzenie swego intelektu poprzez umiesczenie 5 hetmanow lub 12 skoczkow lub 8 goncow tak aby wszystkie pola na szachowniczy byly atakowane przez jedna figure.");
        opis.setEditable(false);
        opis.setLineWrap(true);
        opis.setOpaque(false);
        opis.setWrapStyleWord(true);

        JTextArea autor = new JTextArea("Autor: Jan Kozlowski\n\nTelefon: 514393032\n\nE-mail: jankozlowsk@gmail.com");
        autor.setEditable(false);
        autor.setLineWrap(true);
        autor.setOpaque(false);
        autor.setWrapStyleWord(true);

        OK = new JButton("Zamknij");
        OK.addActionListener(this);

        wersja.setFont(new Font("Arial", Font.BOLD, 32));
        opis.setFont(Czcionka);
        autor.setFont(Czcionka);
        opro.add(wersja);
        opro.add(opis);
        opro.add(autor);
        opro.add(OK);

        opro.setSize(700, 350);
        opro.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {            
        Object source = e.getSource();
        if (source == nowyMenuItem) {
            nowy();
        } else if (source == rozwiazanieMenuItem) {
            rozwiazanie();
        } else if (source == exitMenuItem) {
            System.exit(0);
        } 
        else if (source == zegarMenuItem) {
            if (zegarMenuItem.getState() == true) {         
                czas = new Zegar();

            }
            else{
                czas.zamknij();                             
            }
        }
          else if (source == oProgramieMenuItem) {
            oProgramie();
        } else if (source == OK) {
            opro.dispose();
        }

    }
}

