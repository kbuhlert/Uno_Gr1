package at.campus02.nowa.uno.karte;

import java.util.ArrayList;
import java.util.Collections;

import static at.campus02.nowa.uno.karte.Farbe.SCHWARZ;

public class Kartenmanager {

    protected ArrayList<Karte> kartenstapel;
    protected ArrayList<Karte> ablagestapel;

    public Kartenmanager() {
        kartenstapel = new ArrayList<>();
        ablagestapel = new ArrayList<>();
    }

    /**
     * Methode Stapel erstellen: Stack mit allen verfügbaren Karten befüllen (108Karten)
     */
    private void neuerVerteilstapel() {
        kartenstapel.clear();
        ablagestapel.clear();
        for (Farbe f : Farbe.values()) {       //for-Schleife durch alle Farbenums
            if (!f.equals(SCHWARZ))          //Geht durch alle Farben, außer Schwarz
            {
                kartenstapel.add(new Zahlenkarte(f, Wert.NULL));    //Fügt die NULLer Karten der Farben Gelb, Blau, Rot, Grün zu, da es diese nur 1x je Farbe gibt
                for (Wert w : Wert.values()) {
                    if (!w.equals(Wert.NULL) && !w.equals(Wert.PLUSVIER) && !w.equals(Wert.FARBWAHL)) {  //Null gibt es nur einmal je Farbe und wurde bereits oben zugefügt, Schwarze Aktionskarten werden später zugefügt
                        for (int i = 1; i <= 2; i++) {                    //Das Zufügen wird 2x wiederholt, da von den Zahlenkarten von jeder Farbe jeder Wert zweimal enthalten ist
                            kartenstapel.add(new Zahlenkarte(f, w));     //Fügt die Karte mit der Farbe f und dem Wert w dem KArtenstapel zu (Außer Null und Aktionskarten)
                        }
                    }
                }
            }
            if (f.equals(SCHWARZ)) {  //jetzt werden die schwarzen Aktionskarten zugefügt
                for (Wert w : Wert.values()) {
                    if (w.equals(Wert.PLUSVIER) || w.equals(Wert.FARBWAHL)) {  //Es werden wieder alle Enumwerte durchlaufen und dann die Plusvier und Farbwahl zugefügt
                        for (int i = 1; i <= 4; i++) {
                            kartenstapel.add(new Zahlenkarte(f, w));     //da es jede Aktionskarte 4x gibt, wird das 4x wiederholt
                        }
                    }
                }
            }
        }
    }

    /**
     * Methode mischen mit Collections shuffle
     */
    public void mischen() {
        Collections.shuffle(kartenstapel);
    }

    public void stapelErstellen() {
        neuerVerteilstapel();
        mischen();
    }

    /**
     * Methode hebt oberste Karte vom Kartenstapel
     */
    public Karte abheben() {
        Karte abgehobeneKarte = kartenstapel.remove(kartenstapel.size() - 1);
        return abgehobeneKarte;
    }

    /**
     * Methode gibt oberste Karte auf Stapel aus
     */
    public Karte obersteKarte() {
        Karte obersteKarte = ablagestapel.get(ablagestapel.size() - 1);
        return obersteKarte;
    }

    /**
     * entfernt Farbwahlhilfskarte wieder vom Stapel. Hilfskarte wird für wahl der Farbe kurzzeitig eingefügt und muss dann beim nächsten Zug wieder entfernt werden
     */
    public void farbwahlKarteWeg() {
        ablagestapel.remove(ablagestapel.size() - 1);
    }

    /**
     * Methode legt Karte oben auf Ablegestapel
     */
    public void karteAblegen(Karte k) {
        ablagestapel.add(k);
    }

    /**
     * Methode gibt oberste Karte von Ablegestapel aus und sagt was gespielt werden soll
     */
    public void ausgabeObersteKarteAblagestapel() {
        Karte k = obersteKarte();
        if (k.getFarbe().equals(SCHWARZ)) {
            System.out.println("Bitte spielen Sie die soeben gewünschte Farbe");
        } else
            System.out.println("Bitte spielen Sie eine Karte, die auf FARBE:" + k.getFarbe() + " oder WERT: " + k.getWert() + " gelegt werden darf.");
    }

    public void stapelZusammenMischen() {
        for (Karte k : ablagestapel) {
            kartenstapel.add(k);
            mischen();
        }
        System.out.println("Verteilstapel wurde soeben neu gemischt!");
    }

    public void genugKartenAmStapel() {
        if (kartenstapel.size() <= 4) {
            System.out.println("Ablagestapel ist fast leer");
            stapelZusammenMischen();
        }
    }
}
