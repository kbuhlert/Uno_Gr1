
package at.campus02.nowa.uno.spieler;


import at.campus02.nowa.uno.karte.Karte;


import java.util.ArrayList;

public abstract class Spieler {


  public String name;
  public ArrayList<Karte> spielerHand;

  private boolean uno;
  public int punkte;



    public Spieler(String name) {
        this.name = name;
        this.spielerHand = new ArrayList<>();
        this.uno = false;
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


    public boolean isUno() {
        return uno;
    }

    public void setUno(boolean uno) {
        this.uno = uno;

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


    public void printSpielerHand (){
        int index = 0;
        for (Karte k : spielerHand){
            System.out.println("  " + (index++) + k);
        }
    }



    //Methoden:



    //Ausgabe auf Console der ArrayListe (Kartenhand) damit Spieler weiß welche Karten er auf der Hand hat
    //

}
