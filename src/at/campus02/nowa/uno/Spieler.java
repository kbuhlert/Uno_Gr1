package at.campus02.nowa.uno;

import java.util.ArrayList;

public abstract class Spieler {

  String name;
  ArrayList<Karte> spielerHand;

    public Spieler(String name) {
        this.name = name;
        this.spielerHand = new ArrayList<>();
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

    public abstract void spielen();




    //Methoden:



    //Ausgabe auf Console der ArrayListe (Kartenhand) damit Spieler weiß welche Karten er auf der Hand hat
    //

}
