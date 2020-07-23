
package at.campus02.nowa.uno.spieler;

import at.campus02.nowa.uno.karte.Karte;


import java.util.ArrayList;


public class EchteSpieler extends Spieler {

    /**Die Klasse EchteSpieler managed die Methoden der Spieler die am Spiel teilnehmen und die Ihre Spielzüge selbständig bestimmen
     *
     * @param name
     */

    public EchteSpieler(String name) {
        super(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public void printSpielerHand() {
        super.printSpielerHand();
    }

    @Override
    public ArrayList<Karte> getSpielerHand() {
        return this.spielerHand;
    }

    public void setName () {
        this.name = name;
    }



    @Override
    public String toString() {
        return "EchteSpieler{" +
                "name='" + name + '\'' +
                ", spielerHand=" + spielerHand +
                '}';
    }

    @Override
    public int getPunkteVonSpielerHand() {
        return super.getPunkteVonSpielerHand();
    }

    //Konstruktor zählt static int mit anzahlSpieler hoch, hiermit wird festgelegt wieviele bots mitspielen

    //Name festlegen: im Konstruktor einbauen (Aufruf des Scanners?) und name als Final speichern

    //zusätzliche EchteSpieler-Methoden
    //getAnzahlRealSpieler(){ gibt die static Variable anzahlEchte Spieler aus, damit Bot hieraus berechnen kann, wieviele Bots erstellt werden}
    //spielerHand() erstellen = kartenVerteilen();

}
