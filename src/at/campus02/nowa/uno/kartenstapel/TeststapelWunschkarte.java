package at.campus02.nowa.uno.kartenstapel;

import at.campus02.nowa.uno.karte.Karte;

import at.campus02.nowa.uno.karte.Kartenmanager;


import java.util.ArrayList;
import java.util.Collections;

public class TeststapelWunschkarte extends Kartenmanager {
    /**
     * Teststapel kann mit nur zwei Wunschkarten gefüllt werden, so dass im Spiel bestimmte Karten getestet werden können
     */
    public void neuerTeststapel(Karte k, Karte k2) {
        kartenstapel.clear();
        //zu testende Karten als Parameter in den Stapel geben
        //Diese Karten wird 108x in den Stapel gelegt
        for (int i = 1; i <= 54; i++) {
            kartenstapel.add(k);
            kartenstapel.add(k2);
            kartenstapel.add(k2);
        }
        mischen();      //Teststapel wird direkt nach erstellen gemischt,
    }
}
