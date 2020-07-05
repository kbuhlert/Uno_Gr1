package at.campus02.nowa.uno;

import java.util.ArrayList;
import java.util.Collections;

public class SpielerManager {

    //  Spieler in  Liste
    protected ArrayList<EchteSpieler> alleSpieler;
    Kartenstapel verteilstapel;
    //TeststapelWunschkarte verteilstapel;  --> zum Testen mit speziellen Karten
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

       //verteilstapel.neuerTeststapel(new Zahlenkarte(Farbe.SCHWARZ, Wert.PLUSVIER), new Zahlenkarte(Farbe.BLAU, Wert.ACHT));
       //hier wurde ein Teststapel nur mit den Karten +4 und Blau 8 erstellt um zu testen, ob wenn +4 auf Ablagestapel liegt, diese neu in Stapel
       // gelegt wird, gemischt wird und eine neue Karte aufgelegt wird
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
     if(ablagestapel.obersteKarte().getFarbe()==Farbe.SCHWARZ && ablagestapel.obersteKarte().getWert() == Wert.PLUSVIER){
           System.out.println("Es liegt eine +4 auf, eine neue Karte wird aufgelegt");
           verteilstapel.add(ablagestapel.obersteKarte());
           verteilstapel.mischen();
         neuerAblagestapelUndErsteKarteAufgedeckt();
       }
   }








    @Override
    public String toString() {
        return "SpielerManager{" +
                "alleSpieler=" + alleSpieler +
                '}';
    }
}
