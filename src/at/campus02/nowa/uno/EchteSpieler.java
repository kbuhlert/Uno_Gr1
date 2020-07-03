package at.campus02.nowa.uno;

import java.util.ArrayList;
import java.util.Scanner;

public class EchteSpieler extends Spieler{
    //private static int anzahlEchteSpieler = 0;
   private String name;    //todo: müsste das ein Final sein?

    protected ArrayList<Karte> spielerHand;

    public String getName() {
        return name;
    }

    public EchteSpieler() {

        spielerHand = new ArrayList<Karte>();
    }

    public void setName () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte geben Sie Ihren Namen ein: ");
        name = scanner.nextLine();
        System.out.println("Ihr Name ist: " + name);
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
