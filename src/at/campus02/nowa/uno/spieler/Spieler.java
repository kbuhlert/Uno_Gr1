
package at.campus02.nowa.uno.spieler;


import at.campus02.nowa.uno.karte.Karte;


import java.util.ArrayList;

public abstract class Spieler {

    public String name;
    public ArrayList<Karte> spielerHand;
    private boolean uno;
    public int rundenPunkte;


    public Spieler(String name) {
        this.name = name;
        this.spielerHand = new ArrayList<>();
        this.uno = false;
        this.rundenPunkte = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Karte> getSpielerHand() {
        return spielerHand;
    }

    public boolean isUno() {
        return uno;
    }

    public void setUno(boolean uno) {
        this.uno = uno;
    }

    public int getRundenPunkte() {
        return rundenPunkte;
    }

    public void setRundenPunkte(int punkte) {
        rundenPunkte = rundenPunkte + punkte;
    }

    /**
     * Methode summiert alle Punkte der Karten auf der Spielerhand auf, die Methode wird nach rundenenede aufgerufen
     *
     * @return
     */
    public int getPunkteVonSpielerHand() {
        int summeSpielerHand = 0;
        for (Karte k : spielerHand) {
            summeSpielerHand += k.getPunkte();
        }
        return summeSpielerHand;
    }

    /**
     * Methode gibt sämtliche Karten auf der Hand des Spielers aus, inklusive einer Indexnummer,
     * die der Position im Array entspricht, als Hilfestellung bei der Auswahl der Karte die ngespielt werden soll.
     */
    public void printSpielerHand() {
        int index = 0;
        for (Karte k : spielerHand) {
            System.out.println("  " + (index++) + k);
        }
    }
}
