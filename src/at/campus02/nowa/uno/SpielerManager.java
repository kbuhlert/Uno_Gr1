package at.campus02.nowa.uno;

import java.util.ArrayList;
import java.util.Collections;

public class SpielerManager {

    //  Spieler in  Liste
    protected ArrayList<EchteSpieler> alleSpieler;
    Kartenstapel verteilstapel;
    Kartenstapel ablagestapel;
    EchteSpieler[] spielerReihenfolge = new EchteSpieler[4];


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

        //System.out.println(alleSpieler.get(0).getName());
        EchteSpieler ersterSpieler = alleSpieler.get(0);
        System.out.println(ersterSpieler.getName());

//        spielerReihenfolge[0]=ersterSpieler;
//        spielerReihenfolge[1]=alleSpieler.get(1);
//        spielerReihenfolge[2]=alleSpieler.get(2);
//        spielerReihenfolge[3]=alleSpieler.get(3);

        //printAlleSpielerNamen();
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

       printAlleSpielerNamen();
   }

   public void neuerAblagestapelUndErsteKarteAufgedeckt () {

       ablagestapel.add(verteilstapel.abheben());
       System.out.println("Die erste Karte ist: ");
       System.out.println(ablagestapel.obersteKarte());
   }

   public void kartenHandzeigen(EchteSpieler e){
       System.out.println(e.toString());
   }








    @Override
    public String toString() {
        return "SpielerManager{" +
                "alleSpieler=" + alleSpieler +
                '}';
    }
}
