package at.campus02.nowa.uno;

import java.util.ArrayList;
import java.util.Collections;

public class TeststapelWunschkarte extends Kartenstapel{

    private ArrayList<Karte> teststapel;     //die Arraylist wird im Konstruktor initialisiert
    private int kartenImStapel;             //zählt wieviele Karten im Stapel sind

    public TeststapelWunschkarte() {
        teststapel = new ArrayList<>();
    }

    public ArrayList<Karte> getTeststapel() {
        return teststapel;
    }

    public void setTeststapel(ArrayList<Karte> teststapel) {
        this.teststapel = teststapel;
    }

    public int getKartenImStapel() {
        kartenImStapel = teststapel.size();
        System.out.println("Anzahl der Karten im Stapel: " + kartenImStapel);
        return kartenImStapel;
    }

    public void add(Karte k) {
        teststapel.add(k);
    }

    public void neuerTeststapel(Karte k, Karte k2){
        //zu testende Karten als Parameter in den Stapel geben
        //Diese Karten wird 108x in den Stapel gelegt
        for(int i=1; i<=54; i++){
            teststapel.add(k);
            teststapel.add(k2);
        }
        mischen();      //Teststapel wird direkt nach erstellen gemischt,
    }

    public void mischen(){
        Collections.shuffle(teststapel);
    }


    //Methode oberste Karte abheben (pull)
    public Karte abheben (){
        Karte abgehobeneKarte = teststapel.remove(teststapel.size()-1);
        return abgehobeneKarte;
    }

    //Methode oberste Karte auf Stapel ausgeben
    public Karte obersteKarte(){
        Karte obersteKarte = teststapel.get(teststapel.size()-1);
        //   System.out.println(obersteKarte);
        return teststapel.get(teststapel.size()-1);
    }

    //Methode Karte drauflegen (put) + Aufruf Prüfung Kartenablage gültig
    //todo: Prüfung erweitern auf Aktionskarten, bisher wird nur geprüft ob gleiche Farbe oder gleicher Wert gelegt wurden
    public boolean karteAblegen(Karte k){
        boolean gültigeAblage = false;      //boolean gibt zurück ob Ablage gültig ist
        teststapel.add(k);
        Karte neueKarte = teststapel.get(teststapel.size()-1);  //neueKarte ist die gerade abgelegte (oberste Karte) auf dem Stapel
        Karte letzteKarte = teststapel.get(teststapel.size()-2);    //letzteKarte ist die obersteKarte auf dem Stapel, auf die die neue Karte gelegt werden soll
        if(neueKarte.getWert().equals(letzteKarte.getWert()) || neueKarte.getFarbe().equals(letzteKarte.getFarbe()))    //Test ob gleiche Farbe oder gleicher Kartenwert
        {gültigeAblage = true;}                 //Wenn abgelegte den passenden Wert oder die passende Farbe hat ist die Ablage gültig
        return gültigeAblage;
    }


}
