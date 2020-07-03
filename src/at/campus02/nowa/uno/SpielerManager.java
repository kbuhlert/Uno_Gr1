package at.campus02.nowa.uno;

import java.util.ArrayList;
import java.util.Collections;

public class SpielerManager {

    //  Spieler in  Liste
    protected ArrayList<EchteSpieler> alleSpieler;
    Kartenstapel verteilstapel;


    public SpielerManager() {
        alleSpieler = new ArrayList<EchteSpieler>();
        verteilstapel = new Kartenstapel();
    }

    public void addEchteSpieler (EchteSpieler e) {
        alleSpieler.add(e);
    }

    public void printAlleSpielerNamen() {
        System.out.print("Im Spiel sind: ");
        for (EchteSpieler spieler : alleSpieler) {
            System.out.print(spieler.getName() + ", ");
        }
        System.out.println();
        System.out.println("May the odds be ever in your favour");
    }

    // zufälligen Startspieler festlegen:
    public void startSpieler () {
        System.out.print("Es beginnt: ");
        Collections.shuffle(alleSpieler);

        System.out.println(alleSpieler.get(0).getName());
        printAlleSpielerNamen();
    }
        /*
        int random = 0;
        do {
            random= (int) (Math.random() * 10);
        } while (random>3);
        System.out.println(random);
        System.out.println(alleSpieler.get(random).getName());
        }
         */

    //Verteilstack erstellen
   public void beginneRunde() {
       verteilstapel.neuerVerteilstapel();
       verteilstapel.mischen();
       //Karten austeilen -->7 Karten pro Spieler
       System.out.println("Karten werden ausgeteilt");
       for (EchteSpieler spieler: alleSpieler) {
           while (spieler.spielerHand.size() < 7) {
               spieler.spielerHand.add(verteilstapel.abheben());
           }
           System.out.println(spieler.getName()+ "hat " + spieler.spielerHand.size() + " Handkarten.");
       }
   }




    @Override
    public String toString() {
        return "SpielerManager{" +
                "alleSpieler=" + alleSpieler +
                '}';
    }
}
