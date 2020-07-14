package at.campus02.nowa.uno;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Scanner;

public class EchteSpieler extends Spieler{
    //private static int anzahlEchteSpieler = 0;
      //todo: müsste das ein Final sein?


    public EchteSpieler(Scanner scanner) {
        scanner = new Scanner(System.in);
        System.out.println("Bitte geben Sie Ihren Namen ein: ");
        name = scanner.nextLine();
        System.out.println("Ihr Name ist: " + name +".");
        System.out.println("Willkommen und viel Spaß!");
        System.out.println();
    }


    public String getName() {
        return name;
    }


    public void setName () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte geben Sie Ihren Namen ein: ");
        name = scanner.nextLine();
        System.out.println("Ihr Name ist: " + name +".");
        System.out.println("Willkommen und viel Spaß!");
        System.out.println();
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
