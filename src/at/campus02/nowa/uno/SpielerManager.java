package at.campus02.nowa.uno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SpielerManager {

    //  Spieler in  Liste
    protected ArrayList<EchteSpieler> alleSpieler;
    Kartenstapel verteilstapel;
    //TeststapelWunschkarte verteilstapel;  --> zum Testen mit speziellen Karten
    Kartenstapel ablagestapel;
    EchteSpieler[] spielerReihenfolge = new EchteSpieler[4];
    EchteSpieler aktuellerSpieler = null;


    public SpielerManager() {
        alleSpieler = new ArrayList<EchteSpieler>();
        verteilstapel = new Kartenstapel();
        ablagestapel = new Kartenstapel();
    }

    public void addEchteSpieler (EchteSpieler e) {
        alleSpieler.add(e);
    }

    public void printAlleSpielerNamen() {
        //System.out.print("Im Spiel sind: ");
        for (EchteSpieler spieler : alleSpieler) {
            System.out.print(spieler.getName() + ", ");
        }
        //System.out.println();
        //System.out.println("May the odds be ever in your favour");
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

   public void spielerZuweisen(){
       // 4 echte Spieler können Namen eingeben
       EchteSpieler spieler1 = new EchteSpieler();
       spieler1.setName();
       EchteSpieler spieler2 = new EchteSpieler();
       spieler2.setName();
       EchteSpieler spieler3 = new EchteSpieler();
       spieler3.setName();
       EchteSpieler spieler4 = new EchteSpieler();
       spieler4.setName();

       alleSpieler.add(spieler1);
       alleSpieler.add(spieler2);
       alleSpieler.add(spieler3);
       alleSpieler.add(spieler4);
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

   public void aktuelleKarteAblagestapel(){
       System.out.println("Die aktuelle Karte ist: ");
       System.out.println(ablagestapel.obersteKarte());
   }

   public void kartenHandzeigen(){
       System.out.println(alleSpieler.get(0).spielerHand);
       //TODO Methode muss noch fertig gemacht werden
   }

   public void spielzug (){

       kartenHandzeigen(); //TODO Methode muss noch fertig gemacht werden
       //per Eingabe Karte spielen
       //check if chosen card matches one available in the Kartenhand-array
       //check if after playing the card there is only 1 left > UNO
       System.out.println("Bitte spielen Sie eine Karte!");
       Scanner scanner = new Scanner(System.in);
       int position = scanner.nextInt();
       ablagestapel.add(aktuellerSpieler.spielerHand.get(position));
       aktuelleKarteAblagestapel();
       System.out.println(ablagestapel.obersteKarte());
   }








    @Override
    public String toString() {
        return "SpielerManager{" +
                "alleSpieler=" + alleSpieler +
                '}';
    }
}
