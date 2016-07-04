package SzachyPackage;

import java.util.ArrayList;
import javax.swing.JPanel;

abstract public class Pionek extends JPanel {  
   
    private int pole;
    private ArrayList<Integer> pozycje = new ArrayList<Integer>();
    
    
    public void setPole(int pole) {  
        this.pole = pole;
    }
    
    public int getPole(){            
        return pole;
    }
    
    public void dodajPozycje(int a){  
        pozycje.add(a);
    }
    
    public int sizeKolekcji(){        
        return pozycje.size();
    }
    
    public int[] wyswietl(){         
        int tab[]=new int[pozycje.size()];
        int a=0;
        while(a<pozycje.size()){
            tab[a]=pozycje.get(a);
            a++;
        }
        return tab;
    }

    abstract public void zajetePola(); 
    
   
}
