package at.campus02.nowa.uno;

import java.util.ArrayList;

import static at.campus02.nowa.uno.Farbe.SCHWARZ;

public class Kartenstapel {

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


    private ArrayList<Karte> kartenstapel;
    private int kartenImStapel;

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
        System.out.println("Anzahl der Karten im Stapel: " + kartenImStapel);
        return kartenImStapel;
    }

    public void add(Karte k) {
        kartenstapel.add(k);
    }

    public void verteilstapelErstellen() {
        kartenImStapel = 0;                     //zu Beginn sind keine Karten im Stapel
        for (Farbe f : Farbe.values()) {       //for-Schleife durch alle Farbenums
            if (!f.equals(SCHWARZ))          //Geht durch alle Farben, außer Schwarz
            {
                kartenstapel.add(new Zahlenkarte(f, Wert.NULL));    //Fügt die 0er Karten der Farben Gelb, Blau, Rot, Grün zu
                kartenImStapel++;           //Zählt die Karten im Stapel
                for (Wert w : Wert.values()) {
                    if (!w.equals(Wert.NULL) && !w.equals(Wert.PLUSVIER) && !w.equals(Wert.FARBWAHL)) {  //Null gibt es nur einmal je Farbe und wurde bereits oben zugefügt, Schwarze Aktionskarten werden später zugefügt
                        kartenstapel.add(new Zahlenkarte(f, w));     //Fügt die Karte mit der Farbe f und dem Wert w dem KArtenstapel zu (Außer Null und Aktionskarten)
                        kartenImStapel++;
                        kartenstapel.add(new Zahlenkarte(f, w));     //Das Zufügen wird wiederholt, da von den Zahlenkarten von jeder Farbe jeder Wert zweimal enthalten ist
                        kartenImStapel++;
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

    //Ablegestapel und Aufnehmstapel sind beides Objekte der Klasse Kartenstapel

    //Methode Stack erstellen: neuer Stack und dann Stack mit allen verfügbaren Karten befüllen (108Karten)
    // (neue Karten: Zahelenwerte und Farben über Schleife, +2 über Schleife, restliche Aktionskarten manuell)

    //Methode mischen : Collections shuffle -->mit Klasse Random geht das

    //Methode oberste Karte abheben (pull)

    //Methode Karte drauflegen (put) + Aufruf Prüfung Kartenablage gültig

    //Größe des Stacks überprüfen/ Abfrage ob leer

    //Methode Überprüfung ob Kartenablage gültig
}
