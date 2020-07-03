package at.campus02.nowa.uno;

import java.util.ArrayList;

public class EchteSpieler extends Spieler{
    //private static int anzahlEchteSpieler = 0;
   private String name;    //todo: müsste das ein Final sein?

    protected ArrayList<Karte> spielerHand;

    public EchteSpieler() {
        this.spielerHand = new ArrayList<Karte>();
    }

    //Konstruktor zählt static int mit anzahlSpieler hoch, hiermit wird festgelegt wieviele bots mitspielen

    //Name festlegen: im Konstruktor einbauen (Aufruf des Scanners?) und name als Final speichern

    //zusätzliche EchteSpieler-Methoden
    //getAnzahlRealSpieler(){ gibt die static Variable anzahlEchte Spieler aus, damit Bot hieraus berechnen kann, wieviele Bots erstellt werden}
    //spielerHand() erstellen = kartenVerteilen();

}
