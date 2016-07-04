package SzachyPackage;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;


public class Zegar implements ActionListener, Runnable{
   
   private JFrame okienko;
   private Timer licznik;
   private int sekundy, minuty,godziny;
   private JLabel wyswietlacz;
   private DecimalFormat dFormat = new DecimalFormat("00");
   private Font Czcionka = new Font("Arial", Font.PLAIN, 30);
   private Thread thr;
   private boolean czas;
   
   public Zegar(){    
   czas=false;
   thr = new Thread(this);
   thr.start();
   }
   
   public void zamknij(){    
       okienko.dispose();
   }
   public void zatrzymaj(){  
       czas=true;
   }
      
   public void run(){
       
       okienko = new JFrame();
       sekundy = 0;
       minuty = 0;
       godziny = 0;
       
       okienko.setTitle("Licznik");
       okienko.setLocation(500, 0);
       okienko.setSize(300, 200);
       okienko.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

       wyswietlacz = new JLabel();
       wyswietlacz.setFont(Czcionka);
       wyswietlacz.setHorizontalAlignment(JLabel.CENTER);
       okienko.add(wyswietlacz,BorderLayout.CENTER);
       
       licznik=new Timer(1000,this);                                    
       licznik.start();                                                
       okienko.getContentPane().setBackground(new Color(236,245,235));
       okienko.setVisible(true);
   }
   
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
    
    if (source == licznik){     
        sekundy++;
    }
    
    if(sekundy == 60){
        sekundy=0;
        minuty++;
                
    }
    
    if(minuty==60){
        minuty=0;
        godziny++;
    }
    
    wyswietlacz.setText(dFormat.format(godziny) + ":" + dFormat.format(minuty) + ":" + dFormat.format(sekundy));
    
    if(czas==true){ 
        licznik.stop();
    }
    
}
}

   

