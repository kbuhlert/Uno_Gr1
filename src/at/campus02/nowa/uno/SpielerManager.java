package at.campus02.nowa.uno;

import java.util.ArrayList;
import java.util.Collections;

public class SpielerManager {

    //  Spieler in  Liste
    protected ArrayList<EchteSpieler> alleSpieler;
    Kartenstapel verteilstapel;
    Kartenstapel ablagestapel;


    public SpielerManager() {
        alleSpieler = new ArrayList<EchteSpieler>();
        verteilstapel = new Kartenstapel();
        ablagestapel = new Kartenstapel();
    }

    public void addEchteSpieler (EchteSpieler e) {
        alleSpieler.add(e);
    }

    public void printAlleSpielerNamen() {
        for (EchteSpieler spieler : alleSpieler) {
            System.out.print(spieler.getName() + ", ");
        }
    }

    // zufälligen Startspieler festlegen:
    public void startSpieler () {
        Collections.shuffle(alleSpieler);
        System.out.println("Im Spiel sind in dieser Reihenfolge:  ");
        printAlleSpielerNamen();
        System.out.println();
        System.out.print("Es beginnt: ");
        System.out.println(alleSpieler.get(0).getName());
        System.out.println("May the odds be ever in your favour");
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
           System.out.println(spieler.getName()+ " hat " + spieler.spielerHand.size() + " Handkarten.");
       }
   }

   public void neuerAblagestapelUndErsteKarteAufgedeckt () {

       ablagestapel.add(verteilstapel.abheben());
       System.out.println("Die erste Karte ist: ");
       System.out.println(ablagestapel.obersteKarte());
   }








    @Override
    public String toString() {
        return "SpielerManager{" +
                "alleSpieler=" + alleSpieler +
                '}';
    }
}
