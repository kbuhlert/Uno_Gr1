package at.campus02.nowa.uno;

import java.util.ArrayList;
import java.util.Collections;

import static at.campus02.nowa.uno.Farbe.SCHWARZ;

public class Kartenstapel {

    //Ablegestapel und Aufnehmstapel sind beides Objekte der Klasse Kartenstapel

    //im Verteilstapel gibt es anfangs 108 Karten:
    // Farben Rot, Gruen, Gelb, Blau mit jeweils:
    // je 1x: 0 (4 Karten)
    // je 2x: 1-9 (72 Karten)
    // je 2x: 2+ (8 Karten)
    // je 2x: Aussetzen (8 KArten)
    // je 2x: Richtungswechsel (8 Karten)
    // dazu Schwarze Karten:
    // 4x: Farbwechsel (4 Karten)
    // 4x: 4+ (4 Karten)


    private ArrayList<Karte> kartenstapel;     //die Arraylist wird im Konstruktor initialisiert
    private int kartenImStapel;             //zählt wieviele Karten im Stapel sind

    public Kartenstapel() {
        kartenstapel = new ArrayList<>();
    }

    public ArrayList<Karte> getKartenstapel() {
        return kartenstapel;
    }

    public void setKartenstapel(ArrayList<Karte> kartenstapel) {
        this.kartenstapel = kartenstapel;
    }

    public int getKartenImStapel() {
        kartenImStapel = kartenstapel.size();
        System.out.println("Anzahl der Karten im Stapel: " + kartenImStapel);
        return kartenImStapel;
    }

    public void add(Karte k) {
        kartenstapel.add(k);
    }

    //Methode Stapel erstellen: Stack mit allen verfügbaren Karten befüllen (108Karten)
    public void neuerVerteilstapel() {
        kartenImStapel = 0;                     //zu Beginn sind keine Karten im Stapel
        for (Farbe f : Farbe.values()) {       //for-Schleife durch alle Farbenums
            if (!f.equals(SCHWARZ))          //Geht durch alle Farben, außer Schwarz
            {
                kartenstapel.add(new Zahlenkarte(f, Wert.NULL));    //Fügt die NULLer Karten der Farben Gelb, Blau, Rot, Grün zu, da es diese nur 1x je Farbe gibt
                kartenImStapel++;           //Zählt die Karten im Stapel, eine Karte mehr
                for (Wert w : Wert.values()) {
                    if (!w.equals(Wert.NULL) && !w.equals(Wert.PLUSVIER) && !w.equals(Wert.FARBWAHL)) {  //Null gibt es nur einmal je Farbe und wurde bereits oben zugefügt, Schwarze Aktionskarten werden später zugefügt
                        for(int i=1; i<=2; i++) {                    //Das Zufügen wird 2x wiederholt, da von den Zahlenkarten von jeder Farbe jeder Wert zweimal enthalten ist
                            kartenstapel.add(new Zahlenkarte(f, w));     //Fügt die Karte mit der Farbe f und dem Wert w dem KArtenstapel zu (Außer Null und Aktionskarten)
                            kartenImStapel++;
                        }
                    }
                }
            }
            if (f.equals(SCHWARZ)) {  //jetzt werden die schwarzen Aktionskarten zugefügt
                for (Wert w : Wert.values()) {
                    if (w.equals(Wert.PLUSVIER) || w.equals(Wert.FARBWAHL)) {  //Es werden wieder alle Enumwerte durchlaufen und dann die Plusvier und Farbwahl zugefügt
                        for(int i=1; i<=4; i++){
                        kartenstapel.add(new Zahlenkarte(f, w));     //da es jede Aktionskarte 4x gibt, wird das 4x wiederholt
                        kartenImStapel++;}
                    }
                }
            }
        }
        // System.out.println(kartenstapel);    //Zur Kontrolle, ob alle Karten korrekt erstellt wurden
    }

    //Methode mischen : Collections shuffle
    public ArrayList<Karte> mischen(){
        Collections.shuffle(kartenstapel);
        //for(Karte k:kartenstapel){    //Kartenstapel ausgeben zum Testen ob Mischen funktioniert
        //    System.out.println(k);}
        return kartenstapel;
    }


    //Methode oberste Karte abheben (pull)
    public Karte abheben (){
        Karte abgehobeneKarte = kartenstapel.remove(kartenstapel.size()-1);
        return abgehobeneKarte;
    }

    //Methode oberste Karte auf Stapel ausgeben
    public Karte obersteKarte(){
        Karte obersteKarte = kartenstapel.get(kartenstapel.size()-1);
     //   System.out.println(obersteKarte);
        return kartenstapel.get(kartenstapel.size()-1);
    }

    //Methode Karte drauflegen (put) + Aufruf Prüfung Kartenablage gültig
    //todo: Prüfung erweitern auf Aktionskarten, bisher wird nur geprüft ob gleiche Farbe oder gleicher Wert gelegt wurden
    public boolean karteAblegen(Karte k){
        boolean gültigeAblage = false;      //boolean gibt zurück ob Ablage gültig ist
        kartenstapel.add(k);
        Karte neueKarte = kartenstapel.get(kartenstapel.size()-1);  //neueKarte ist die gerade abgelegte (oberste Karte) auf dem Stapel
        Karte letzteKarte = kartenstapel.get(kartenstapel.size()-2);    //letzteKarte ist die obersteKarte auf dem Stapel, auf die die neue Karte gelegt werden soll
        if(neueKarte.getWert().equals(letzteKarte.getWert()) || neueKarte.getFarbe().equals(letzteKarte.getFarbe()))    //Test ob gleiche Farbe oder gleicher Kartenwert
        {gültigeAblage = true;}                 //Wenn abgelegte den passenden Wert oder die passende Farbe hat ist die Ablage gültig
        return gültigeAblage;
    }

    //Größe des Stacks überprüfen/ Abfrage ob leer

}
