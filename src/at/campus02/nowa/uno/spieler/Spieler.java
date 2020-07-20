
package at.campus02.nowa.uno.spieler;


import at.campus02.nowa.uno.karte.Karte;


import java.util.ArrayList;

public abstract class Spieler {


  public String name;
  public ArrayList<Karte> spielerHand;
  public int punkte;


    public Spieler(String name) {
        this.name = name;
        this.spielerHand = new ArrayList<>();
        this.punkte = 0;
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

    public int getPunkte() {
        return punkte;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }

    public int getPunkteVonSpielerHand (){
        int summeSpielerHand = 0;
        for (int i = 0; i < spielerHand.size() ; i++) {
            summeSpielerHand += spielerHand.get(i).getPunkte();
            i++;
        }
        return summeSpielerHand;
    }

    public abstract void spielen();




    //Methoden:



    //Ausgabe auf Console der ArrayListe (Kartenhand) damit Spieler weiß welche Karten er auf der Hand hat
    //

}
