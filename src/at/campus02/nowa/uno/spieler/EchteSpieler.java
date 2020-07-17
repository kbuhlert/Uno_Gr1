
package at.campus02.nowa.uno.spieler;

import at.campus02.nowa.uno.FalscheEingabeException;
import at.campus02.nowa.uno.karte.Karte;


import java.util.ArrayList;
import java.util.Scanner;

public class EchteSpieler extends Spieler {



    public EchteSpieler(String name) {
        super(name);


    }

    public String getName() {
        return name;
    }

    @Override
    public void spielen() {

    }
    public void printSpielerHand (){
        int index = 0;
        for (Karte k : spielerHand){
            System.out.println("  " + (index++) + k);
        }
    }

    @Override
    public ArrayList<Karte> getSpielerHand() {
        //    //Fragt aktuellen Spieler ob er vor dem Ablegen der Karte seine eigene Hand auf der Konsole sehen möchte
//    public void kartenHandzeigen() {
//        //diese Methode prüft zunächst ob der aktuelle Spieler ein Bot ist oder ein echter Spieler,
//        //wenn echter Spieler, wird dieser gefragt, ob er seine Hand einsehen möchte
//        //die Abfrage wird über Konsole mit y oder n getätigt, bei falscher Eingabe gibt es eine Exception

            System.out.println("Möchten sie ihre Hand angezeigt bekommen?");
            System.out.println("Bitte Y (YES) oder N (No) eingeben");
            try {
                Scanner scanner = new Scanner(System.in);
                String c = scanner.nextLine();
                if (c.equalsIgnoreCase("y")) {
                    System.out.println(spielerHand);
                    return spielerHand;
                }
                if (c.equalsIgnoreCase("n")) {
                    return null;
                }
                while (!c.equalsIgnoreCase("y") || !c.equalsIgnoreCase("n")) {
                    System.out.println("Falsche Eingabe!");
                    throw new FalscheEingabeException("Falsche Eingabe");
                }
            } catch (FalscheEingabeException e) {
                return super.getSpielerHand();
                //e.printStackTrace();
            }
            return null;
    }



    //Fragt aktuellen Spieler ob er vor dem Ablegen der Karte seine eigene Hand auf der Konsole sehen möchte
    public void abfrageKartenhandZeigen() {
        //diese Methode prüft zunächst ob der aktuelle Spieler ein Bot ist oder ein echter Spieler,
        //wenn echter Spieler, wird dieser gefragt, ob er seine Hand einsehen möchte
        //die Abfrage wird über Konsole mit y oder n getätigt, bei falscher Eingabe gibt es eine Exception

            System.out.println("Möchten sie ihre Hand angezeigt bekommen?");
            System.out.println("Bitte Y (YES) oder N (No) eingeben");
            try {
                Scanner scanner = new Scanner(System.in);
                String c = scanner.nextLine();
                if (c.equalsIgnoreCase("y")) {
                    printSpielerHand();//todo: hier soll Methode printSpielerhand aufgerufen werden
                    return;
                }
                if (c.equalsIgnoreCase("n")) {
                    return;
                }
                while (!c.equalsIgnoreCase("y") || !c.equalsIgnoreCase("n")) {
                    System.out.println("Falsche Eingabe!");
                    throw new FalscheEingabeException("Falsche Eingabe");
                }
            } catch (FalscheEingabeException e) {
                abfrageKartenhandZeigen();
                //e.printStackTrace();
            }
        }




    public void setName () {
        this.name = name;
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Bitte geben Sie Ihren Namen ein: ");
//        name = scanner.nextLine();
//        System.out.println("Ihr Name ist: " + name +".");
//        System.out.println("Willkommen und viel Spaß!");
//        System.out.println();
    }



    @Override
    public String toString() {
        return "EchteSpieler{" +
                "name='" + name + '\'' +
                ", spielerHand=" + spielerHand +
                '}';
    }



    //Konstruktor zählt static int mit anzahlSpieler hoch, hiermit wird festgelegt wieviele bots mitspielen

    //Name festlegen: im Konstruktor einbauen (Aufruf des Scanners?) und name als Final speichern

    //zusätzliche EchteSpieler-Methoden
    //getAnzahlRealSpieler(){ gibt die static Variable anzahlEchte Spieler aus, damit Bot hieraus berechnen kann, wieviele Bots erstellt werden}
    //spielerHand() erstellen = kartenVerteilen();

}
