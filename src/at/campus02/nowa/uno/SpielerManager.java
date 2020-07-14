package at.campus02.nowa.uno;

import java.util.ArrayList;
import java.util.Collections;

import static at.campus02.nowa.uno.Farbe.SCHWARZ;

import java.util.Scanner;
import java.util.jar.JarOutputStream;

public class SpielerManager {
    Scanner input = new Scanner(System.in);

    //  Spieler in  Liste
    protected ArrayList<Spieler> alleSpieler;
    Kartenstapel verteilstapel;
    //TeststapelWunschkarte verteilstapel;  //--> zum Testen mit speziellen Karten
    Kartenstapel ablagestapel;
    //Kartenstapel abhebestapel;
    Spieler aktuellerSpieler = null;
    boolean spielrichtung = true;


    public SpielerManager() {
        alleSpieler = new ArrayList<Spieler>();
        verteilstapel = new Kartenstapel();
        ablagestapel = new Kartenstapel();
    }

    public void addSpieler(Spieler e) {
        alleSpieler.add(e);
    }

    public void printAlleSpielerNamen() {
//        System.out.print("Im Spiel sind: ");
        for (Spieler spieler : alleSpieler) {
            System.out.print(spieler.getName() + ", ");
        }
//        System.out.println();
//        System.out.println("May the odds be ever in your favour");
    }

    // zufälligen Startspieler festlegen:
    public void startSpieler() {
        Collections.shuffle(alleSpieler);
        System.out.println("Im Spiel sind in dieser Reihenfolge:  ");
        printAlleSpielerNamen();
        System.out.println();
        //System.out.print("Es beginnt: ");     //Angabe wird nicht mehr benötigt, die kommt bei Spieleraufruf nochmal
        aktuellerSpieler = alleSpieler.get(0);
        //System.out.println(aktuellerSpieler.getName());       //Ausgabe des aktuellen Spieler kommt bei Spieleraufruf
        System.out.println("May the odds be ever in your favour");
        System.out.println();
    }


    //Verteilstack erstellen & austeilen der Karten auf die Spielerhand
    public void beginneRunde() {
        verteilstapel.neuerVerteilstapel(); //Erstellt den Verteilstack
  //      verteilstapel.neuerTeststapel(new Zahlenkarte(Farbe.BLAU, Wert.RICHTUNGSWECHSEL), new Zahlenkarte(Farbe.BLAU, Wert.ACHT));
        //verteilstapel.neuerTeststapel(new Zahlenkarte(Farbe.SCHWARZ, Wert.PLUSVIER), new Zahlenkarte(Farbe.BLAU, Wert.ACHT));
        //hier wurde ein Teststapel nur mit den Karten +4 und Blau 8 erstellt um zu testen, ob wenn +4 auf Ablagestapel liegt, diese neu in Stapel
        // gelegt wird, gemischt wird und eine neue Karte aufgelegt wird
        verteilstapel.mischen();
        //Karten austeilen -->7 Karten pro Spieler
        System.out.println("Karten werden ausgeteilt");
        for (Spieler spieler : alleSpieler) {
            while (spieler.spielerHand.size() < 7) {
                spieler.spielerHand.add(verteilstapel.abheben());
            }
            System.out.println(spieler.getName() + " hat " + spieler.spielerHand.size() + " Handkarten.");
        }
        System.out.println();
    }

    public void spielerZuweisen() {
        Spieler spieler;

        int anzBot = 1;
//        input = new Scanner(System.in);
        while(alleSpieler.size() < 4) {
            System.out.println("Bitte geben Sie Ihren Namen ein: ");
            String name = input.nextLine();
            if (name.toLowerCase().equals("bot")) {
                spieler = new BotSpieler("bot" + String.valueOf(anzBot));
                System.out.println("bot" + String.valueOf(anzBot) + " spielt mit.");
                System.out.println("Willkommen und viel Spaß!");
                System.out.println();
                anzBot++;
            } else {
                spieler = new EchteSpieler(name);
                System.out.println("Ihr Name ist: " + name + ".");
                System.out.println("Willkommen und viel Spaß!");
                System.out.println();
            }
            alleSpieler.add(spieler);
        }


//        // 4 echte Spieler können Namen eingeben
//        // todo: Echte und Botspieler erstellen
//        //Abfrage: Wieviel echte Spieler muss eingegeben werden, dann mit Switch entsprechend erstellen
//        System.out.println("Bitte geben Sie die Zahl (0-4) der echten Spieler ein: ");
//        //todo: FalscheEingabeException zufügen
//        int echteSpieler = input.nextInt();
//        Spieler spieler1 = null;
//        Spieler spieler2 = null;
//        Spieler spieler3 = null;
//        Spieler spieler4 = null;
//
//        //todo: switch für Botspieler erweitern
//        switch (echteSpieler) {
//            case (4):
//                spieler1 = new EchteSpieler(input);
//                spieler2 = new EchteSpieler(input);
//                spieler3 = new EchteSpieler(input);
//                spieler4 = new EchteSpieler(input);
//                break;
//
//            case (3):
//                spieler1 = new EchteSpieler(input);
//                spieler2 = new EchteSpieler(input);
//                spieler3 = new EchteSpieler(input);
//                spieler4 = new BotSpieler();
//                break;
//
//            case (2):
//                spieler1 = new EchteSpieler(input);
//                spieler2 = new EchteSpieler(input);
//                spieler3 = new BotSpieler();
//                spieler4 = new BotSpieler();
//                break;
//
//            case (1):
//                spieler1 = new EchteSpieler(input);
//                spieler2 = new BotSpieler();
//                spieler3 = new BotSpieler();
//                spieler4 = new BotSpieler();
//                break;
//
//            case (0):
//                spieler1 = new BotSpieler();
//                spieler2 = new BotSpieler();
//                spieler3 = new BotSpieler();
//                spieler4 = new BotSpieler();
//                break;
//        }
//        alleSpieler.add(spieler1);  //Spieler werden dem Spielerarray zugefügt
//        alleSpieler.add(spieler2);
//        alleSpieler.add(spieler3);
//        alleSpieler.add(spieler4);
    }

    //Methode erstellt den Ablagestapel und mischt nochmal wenn +4 oben liegt
    public void neuerAblagestapelUndErsteKarteAufgedeckt() {
        ablagestapel.add(verteilstapel.abheben());
        System.out.println("Die erste Karte ist: ");
        System.out.println(ablagestapel.obersteKarte());
        //Test ob +4 Aufliegt, wenn ja Karte zurück, mischen und neuer Aufruf der Methode
        if (ablagestapel.obersteKarte().getFarbe() == Farbe.SCHWARZ && ablagestapel.obersteKarte().getWert() == Wert.PLUSVIER) {
            System.out.println("Es liegt eine +4 auf, nochmal mischen, eine neue Karte wird aufgelegt");
            verteilstapel.add(ablagestapel.obersteKarte());
            verteilstapel.mischen();
            neuerAblagestapelUndErsteKarteAufgedeckt();
        }
    }




    public void ausgabeaktuellerSpieler() {
        System.out.println();
        System.out.println("-----");
        System.out.println(aktuellerSpieler.getName() + "  ist an der Reihe!");
        aktuellerSpieler.getSpielerHand();
        Karte k = ablagestapel.obersteKarte();
        System.out.println("Bitte spielen Sie eine Karte, die auf FARBE:" + k.getFarbe() + " oder WERT: " + k.getWert() + " gelegt werden darf.");

    }

    public void spielzug() {
        //per Eingabe Karte spielen
        //check if chosen card matches one available in the Kartenhand-array
        //todo: check if after playing the card there is only 1 left > UNO

        Karte k = null;
        if (aktuellerSpieler instanceof EchteSpieler) {
            int position = input.nextInt();
            Karte handKarte = aktuellerSpieler.spielerHand.get(position);
            if (!passendeKarte(handKarte, ablagestapel.obersteKarte())) {
                System.out.println("Falsche Karte gelegt. Bitte legen Sie eine passende Karte ab");
                System.out.println("Es liegt die " + ablagestapel.obersteKarte() + " oben auf!");
            } else {
                System.out.println("Spielzug korrekt. Diese Karte wurde abgelegt:");
                System.out.println(aktuellerSpieler.spielerHand.get(position));
                ablagestapel.add(aktuellerSpieler.spielerHand.get(position));
                aktuellerSpieler.spielerHand.remove(position);
                //spielzug();
            }
        } else {
            if(aktuellerSpieler.spielerHand.contains(ablagestapel.obersteKarte())){
//                System.out.println("Karte gefunden!");

            for (int i = 0; i < aktuellerSpieler.spielerHand.size(); i++){
                k = aktuellerSpieler.spielerHand.get(i);
                System.out.println(k);
                if (k.equals(ablagestapel.obersteKarte())) {
                    ablagestapel.add(k);
                    System.out.println(k + " wird ausgespielt");
                    System.out.println(k + " wurde soeben abgelegt!");
                    aktuellerSpieler.spielerHand.remove(k);
                    System.out.println(aktuellerSpieler.spielerHand);
                    break; }

                }

                } else {
                    System.out.println(aktuellerSpieler.getSpielerHand());
                    System.out.println("Keine passende Karte - " + aktuellerSpieler.getName() + " muss abheben");
                    Karte neu = verteilstapel.abheben();
                    if(passendeKarte(neu, ablagestapel.obersteKarte())){
                        ablagestapel.add(neu);
                        System.out.println(neu + " wird ausgespielt");
                        System.out.println(neu + " wurde soeben abgelegt!");
                    } else {
                    aktuellerSpieler.spielerHand.add(neu);
                    System.out.println("Spieler bekommt neue karte: " + neu);}


//                if (passendeKarte(k,ablagestapel.obersteKarte())) {
//                    System.out.println(k + " wird ausgespielt");
//                    ablagestapel.add(k);
//                    System.out.println("wurde abgelegt!");
//                    aktuellerSpieler.spielerHand.remove(k);
//                    System.out.println("geht do jetz no wos?");
//                    continue;
//                } else {
//                    System.out.println("Keine passende Karte - " + aktuellerSpieler.getName() + " muss abheben");
//                    Karte neu = verteilstapel.abheben();
//                    System.out.println(neu);
//                    if (!passendeKarte(neu, ablagestapel.obersteKarte())) {
//                        System.out.println(aktuellerSpieler.getSpielerHand());
//                        aktuellerSpieler.spielerHand.add(neu);
//                        System.out.println("Spieler bekommt neue karte: " + neu);
//                    } else {
//                        System.out.println(neu + " wird ausgespielt");
//                        ablagestapel.add(k);
//                        System.out.println(aktuellerSpieler.getSpielerHand());
//
//                    }
//                }
                }


            }
            spielerWechsel();
        }


    public boolean passendeKarte(Karte handKarte, Karte ablageStapel) {
        boolean ausgabe;
        if (ablageStapel.getFarbe().equals(SCHWARZ)) {
            ausgabe = true;
        } else if (handKarte.getFarbe().equals(ablageStapel.getFarbe()) || handKarte.getWert() == ablageStapel.getWert() || handKarte.getFarbe().equals(SCHWARZ)) {
            ausgabe = true;
        } else ausgabe = false;

        return ausgabe;
        // TODO: 06.07.2020 aktionskarten ?
    }

    public Spieler spielerWechsel() {
        // Spielerwechsel nach Beendigung des Spielzuges
        //Richtungswechsel wenn auf Stapel "Richtungswechsel" liegt
        //todo: Aussetzenkarte ist Spieler überspringen


        if (ablagestapel.obersteKarte().getWert().equals(Wert.RICHTUNGSWECHSEL)) {
            spielrichtung = !spielrichtung;
        }

        if (spielrichtung) {
            switch (alleSpieler.indexOf(aktuellerSpieler)) {
                case 0:
                    aktuellerSpieler = alleSpieler.get(1);
                    break;
                case 1:
                    aktuellerSpieler = alleSpieler.get(2);
                    break;
                case 2:
                    aktuellerSpieler = alleSpieler.get(3);
                    break;
                case 3:
                    aktuellerSpieler = alleSpieler.get(0);
                    break;
            }
        }
        if (!spielrichtung) {
            switch (alleSpieler.indexOf(aktuellerSpieler)) {
                case 0:
                    aktuellerSpieler = alleSpieler.get(3);
                    break;
                case 1:
                    aktuellerSpieler = alleSpieler.get(0);
                    break;
                case 2:
                    aktuellerSpieler = alleSpieler.get(1);
                    break;
                case 3:
                    aktuellerSpieler = alleSpieler.get(2);
                    break;
            }
        }

        return aktuellerSpieler;
    }


    @Override
    public String toString() {
        return "SpielerManager{" +
                "alleSpieler=" + alleSpieler +
                '}';
    }

}
