package at.campus02.nowa.uno;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Test des Kartenstapels/Verteilstapel
        Kartenstapel teststapel = new Kartenstapel();       //Neuer Teststapel erstellt
        teststapel.neuerVerteilstapel();                //Test ob Verteilstapel erstellt werden kann
        teststapel.getKartenImStapel();                     //Anzeigen wieviele Karten im Verteilstabel liegen

        teststapel.mischen();
        teststapel.obersteKarte();
        teststapel.abheben();
        teststapel.obersteKarte();



        teststapel.getKartenImStapel();
        teststapel.abheben();
        teststapel.obersteKarte();
        teststapel.getKartenImStapel();

        EchteSpieler spieler1 = new EchteSpieler();
        while (spieler1.spielerHand.size()<7) {
            spieler1.spielerHand.add(teststapel.abheben());
        }
        System.out.println("Handkarten: ");
        for (Karte k :spieler1.spielerHand) {
            System.out.println(k);
        }

        System.out.println(spieler1.spielerHand.size());
        teststapel.getKartenImStapel();


        /*
        teststapel.obersteKarte();

        Karte testkarte1 = new Zahlenkarte(Farbe.BLAU, Wert.ACHT);
        Karte testkarte2 = new Zahlenkarte(Farbe.GELB, Wert.ACHT);
        Karte testkarte3 = new Zahlenkarte(Farbe.ROT, Wert.NEUN);
        System.out.println(teststapel.karteAblegen(testkarte1));
        System.out.println(teststapel.karteAblegen(testkarte2));
        System.out.println(teststapel.karteAblegen(testkarte3));
        System.out.println(teststapel.abheben());



        Scanner input = new Scanner(System.in);

        App app = new App(input, System.out);

        System.out.println("UNO wird nun gestartet ...");
        app.Run();

        input.close();
        System.out.println("UNO wird beendet ...");
*/

    }
}
