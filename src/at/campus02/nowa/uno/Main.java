package at.campus02.nowa.uno;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Test des Kartenstapels/Verteilstapel
      /*  Kartenstapel teststapel = new Kartenstapel();       //Neuer Teststapel erstellt
        teststapel.neuerVerteilstapel();                //Test ob Verteilstapel erstellt werden kann
        teststapel.getKartenImStapel();                     //Anzeigen wieviele Karten im Verteilstabel liegen

        teststapel.mischen();

       */
      /*
        teststapel.obersteKarte();
        teststapel.abheben();
        teststapel.obersteKarte();



        teststapel.getKartenImStapel();
        teststapel.abheben();
        teststapel.obersteKarte();


        teststapel.getKartenImStapel();



/*
        System.out.println("Karten werden ausgeteilt");
        for (EchteSpieler spieler: alleSpieler) {
            while (spieler.spielerHand.size() < 7) {
                spieler.spielerHand.add(teststapel.abheben());
            }
          //  System.out.println(spieler.spielerHand.size());
        }

 */
        /*
        System.out.println("Handkarten: ");

        for (EchteSpieler spieler : alleSpieler) {
            System.out.println(spieler);

        }

         */
/*
        teststapel.getKartenImStapel();

        // lerer Ablegestapel wird erstellt:
        Kartenstapel ablageStapel = new Kartenstapel();

        //oberste Karte des testStapels ist jetzt auf Ablagestapel und bildet
        // die Startkarte
        ablageStapel.add(teststapel.abheben());

        System.out.println("Die Anfangskarte ist: " + ablageStapel.obersteKarte());
        teststapel.getKartenImStapel();
        ablageStapel.getKartenImStapel();



 */


        /*
        teststapel.obersteKarte();

        Karte testkarte1 = new Zahlenkarte(Farbe.BLAU, Wert.ACHT);
        Karte testkarte2 = new Zahlenkarte(Farbe.GELB, Wert.ACHT);
        Karte testkarte3 = new Zahlenkarte(Farbe.ROT, Wert.NEUN);
        System.out.println(teststapel.karteAblegen(testkarte1));
        System.out.println(teststapel.karteAblegen(testkarte2));
        System.out.println(teststapel.karteAblegen(testkarte3));
        System.out.println(teststapel.abheben());

        */


        Scanner input = new Scanner(System.in);

        App app = new App(input, System.out);

        System.out.println("UNO wird nun gestartet ...");
        app.Run();

        input.close();
        System.out.println("UNO wird beendet ...");



    }
}
