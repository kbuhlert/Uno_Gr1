package at.campus02.nowa.uno.karte;

import java.util.ArrayList;
import java.util.Collections;

import static at.campus02.nowa.uno.karte.Farbe.SCHWARZ;

public class Kartenmanager {

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


    protected ArrayList<Karte> kartenstapel;     //die Arraylist wird im Konstruktor initialisiert

    protected ArrayList<Karte> ablagestapel;


    public Kartenmanager() {
        kartenstapel = new ArrayList<>();
        ablagestapel = new ArrayList<>();
    }

    //Methode Stapel erstellen: Stack mit allen verfügbaren Karten befüllen (108Karten)
    private void neuerVerteilstapel() {
        kartenstapel.clear();
        ablagestapel.clear();
        for (Farbe f : Farbe.values()) {       //for-Schleife durch alle Farbenums
            if (!f.equals(SCHWARZ))          //Geht durch alle Farben, außer Schwarz
            {
                kartenstapel.add(new Zahlenkarte(f, Wert.NULL));    //Fügt die NULLer Karten der Farben Gelb, Blau, Rot, Grün zu, da es diese nur 1x je Farbe gibt

                for (Wert w : Wert.values()) {
                    if (!w.equals(Wert.NULL) && !w.equals(Wert.PLUSVIER) && !w.equals(Wert.FARBWAHL)) {  //Null gibt es nur einmal je Farbe und wurde bereits oben zugefügt, Schwarze Aktionskarten werden später zugefügt
                        for(int i=1; i<=2; i++) {                    //Das Zufügen wird 2x wiederholt, da von den Zahlenkarten von jeder Farbe jeder Wert zweimal enthalten ist
                            kartenstapel.add(new Zahlenkarte(f, w));     //Fügt die Karte mit der Farbe f und dem Wert w dem KArtenstapel zu (Außer Null und Aktionskarten)

                        }
                    }
                }
            }
            if (f.equals(SCHWARZ)) {  //jetzt werden die schwarzen Aktionskarten zugefügt
                for (Wert w : Wert.values()) {
                    if (w.equals(Wert.PLUSVIER) || w.equals(Wert.FARBWAHL)) {  //Es werden wieder alle Enumwerte durchlaufen und dann die Plusvier und Farbwahl zugefügt
                        for(int i=1; i<=4; i++){
                        kartenstapel.add(new Zahlenkarte(f, w));     //da es jede Aktionskarte 4x gibt, wird das 4x wiederholt
                        }
                    }
                }
            }
        }
        // System.out.println(kartenstapel);    //Zur Kontrolle, ob alle Karten korrekt erstellt wurden
    }

    //Methode mischen : Collections shuffle
    public void mischen(){
        Collections.shuffle(kartenstapel);
        //for(Karte k:kartenstapel){    //Kartenstapel ausgeben zum Testen ob Mischen funktioniert
        //    System.out.println(k);}
    }

    public void stapelErstellen(){
        neuerVerteilstapel();
        mischen();
    }


    //Methode oberste Karte abheben (pull)
    public Karte abheben (){
        Karte abgehobeneKarte = kartenstapel.remove(kartenstapel.size()-1);
        return abgehobeneKarte;
    }

    //Methode oberste Karte auf Stapel ausgeben
    public Karte obersteKarte(){
        Karte obersteKarte = ablagestapel.get(ablagestapel.size()-1);
     //   System.out.println(obersteKarte);
        return obersteKarte;
    }

    //Methode Karte drauflegen (put) + Aufruf Prüfung Kartenablage gültig
    //todo: Prüfung erweitern auf Aktionskarten, bisher wird nur geprüft ob gleiche Farbe oder gleicher Wert gelegt wurden
    public void karteAblegen(Karte k){
        ablagestapel.add(k);
    }

    //Methode erstellt den Ablagestapel und mischt nochmal wenn +4 oben liegt
    //todo: Methode LÖSCHEN und NEU Ablagestapel isst eigentlich leere ArrayList, Methode muss nur erste Karte auflegen können, das geht im Spielermanager


    public void AusgabeObersteKarteAblagestapel() {
        Karte k = obersteKarte();
        if(k.getFarbe().equals(SCHWARZ)){
            System.out.println("Bitte spielen Sie die soeben gewünschte Farbe");
        } else
        System.out.println("Bitte spielen Sie eine Karte, die auf FARBE:" + k.getFarbe() + " oder WERT: " + k.getWert() + " gelegt werden darf.");
    }

}
