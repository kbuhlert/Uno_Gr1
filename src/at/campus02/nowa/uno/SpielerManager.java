package at.campus02.nowa.uno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static at.campus02.nowa.uno.Farbe.SCHWARZ;

public class SpielerManager {

    //  Spieler in  Liste
    protected ArrayList<EchteSpieler> alleSpieler;
    Kartenstapel verteilstapel;
    Kartenstapel ablagestapel;
  //  EchteSpieler[] spielerReihenfolge = new EchteSpieler[4];
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
//        EchteSpieler ersterSpieler = alleSpieler.get(0);
//        System.out.println(ersterSpieler.getName());


        aktuellerSpieler = alleSpieler.get(0);
        System.out.println(aktuellerSpieler.getName());

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

   public void aktuelleKarteAblagestapel(){
       System.out.println("Die aktuelle Karte ist: ");
       System.out.println(ablagestapel.obersteKarte());
   }

   public void kartenHandzeigen(){
       System.out.println(aktuellerSpieler.spielerHand);
       //TODO Methode muss noch fertig gemacht werden
   }

   public void spielzug (){
       System.out.println(aktuellerSpieler.getName() + "  ist an der Reihe!");
       kartenHandzeigen(); //TODO Methode muss noch fertig gemacht werden
       //per Eingabe Karte spielen
       //check if chosen card matches one available in the Kartenhand-array
       //check if after playing the card there is only 1 left > UNO
       System.out.println("Bitte spielen Sie eine Karte!");
       Scanner scanner = new Scanner(System.in);
       int position = scanner.nextInt();
       Karte handKarte = aktuellerSpieler.spielerHand.get(position);
       if(!passendeKarte(handKarte, ablagestapel.obersteKarte())){
           System.out.println("Bitte legen Sie eine passende Karte ab");
           System.out.println("Es liegt die " + ablagestapel.obersteKarte() + " oben auf!");
       } else {

           System.out.println("Diese Karte wird abgelegt:");
           System.out.println(aktuellerSpieler.spielerHand.get(position));
           ablagestapel.add(aktuellerSpieler.spielerHand.get(position));
           aktuellerSpieler.spielerHand.remove(position);
//           System.out.println("diese karte liegt oben auf:");         ueberpruefung ob handkarte nun am stapel oben liegt
//           System.out.println(ablagestapel.obersteKarte());
           spielerWechsel();
           spielzug();

       }


   }

   public boolean passendeKarte(Karte handKarte, Karte ablageStapel){

       if (ablageStapel.getFarbe().equals(SCHWARZ)) {
           return true;
       } else if (handKarte.getFarbe().equals(ablageStapel.getFarbe()) || handKarte.getWert() == ablageStapel.getWert() || handKarte.getFarbe().equals(SCHWARZ)) {return true;}
       else return false;
       // TODO: 06.07.2020 aktionskarten ?
   }

   public EchteSpieler spielerWechsel(){
       // TODO: 06.07.2020 richtungswechsel? 

       switch(alleSpieler.indexOf(aktuellerSpieler)){
           case 0:
               aktuellerSpieler = alleSpieler.get(1);
               spielzug();
               break;
           case 1:
               aktuellerSpieler = alleSpieler.get(2);
               spielzug();
               break;
           case 2:
               aktuellerSpieler = alleSpieler.get(3);
               spielzug();
               break;
           case 3:
               aktuellerSpieler = alleSpieler.get(0);
               spielzug();
               break;

       }

       return aktuellerSpieler;



   }




    @Override
    public String toString() {
        return "SpielerManager{" +
                "alleSpieler=" + alleSpieler +
                '}';
    }
}
