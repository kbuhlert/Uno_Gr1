package at.campus02.nowa.uno;

import java.util.ArrayList;
import java.util.Collections;

import static at.campus02.nowa.uno.Farbe.SCHWARZ;

import java.util.Scanner;

public class SpielerManager {

    //  Spieler in  Liste
    protected ArrayList<EchteSpieler> alleSpieler;
    Kartenstapel verteilstapel;
    //TeststapelWunschkarte verteilstapel;  --> zum Testen mit speziellen Karten
    Kartenstapel ablagestapel;
    Kartenstapel abhebestapel;
    EchteSpieler aktuellerSpieler = null;


    public SpielerManager() {
        alleSpieler = new ArrayList<EchteSpieler>();
        verteilstapel = new Kartenstapel();
        ablagestapel = new Kartenstapel();
    }

    public void addEchteSpieler(EchteSpieler e) {
        alleSpieler.add(e);
    }

    public void printAlleSpielerNamen() {
//        System.out.print("Im Spiel sind: ");
        for (EchteSpieler spieler : alleSpieler) {
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
        System.out.print("Es beginnt: ");
        aktuellerSpieler = alleSpieler.get(0);
        System.out.println(aktuellerSpieler.getName());
        System.out.println("May the odds be ever in your favour");
    }
        /*
        int random = 0;
        do {
            random= (int) (Math.random() * 10);
        } while (random>3);
        System.out.println(random);
        System.out.println(alleSpieler.get(random).getName());
        }
         */

    //Verteilstack erstellen
    public void beginneRunde() {
        verteilstapel.neuerVerteilstapel();

        //verteilstapel.neuerTeststapel(new Zahlenkarte(Farbe.SCHWARZ, Wert.PLUSVIER), new Zahlenkarte(Farbe.BLAU, Wert.ACHT));
        //hier wurde ein Teststapel nur mit den Karten +4 und Blau 8 erstellt um zu testen, ob wenn +4 auf Ablagestapel liegt, diese neu in Stapel
        // gelegt wird, gemischt wird und eine neue Karte aufgelegt wird
        verteilstapel.mischen();
        //Karten austeilen -->7 Karten pro Spieler
        System.out.println("Karten werden ausgeteilt");
        for (EchteSpieler spieler : alleSpieler) {
            while (spieler.spielerHand.size() < 7) {
                spieler.spielerHand.add(verteilstapel.abheben());
            }
            System.out.println(spieler.getName() + " hat " + spieler.spielerHand.size() + " Handkarten.");
        }
    }

    public void spielerZuweisen() {
        // 4 echte Spieler können Namen eingeben
        EchteSpieler spieler1 = new EchteSpieler();
        spieler1.setName();
        EchteSpieler spieler2 = new EchteSpieler();
        spieler2.setName();
        EchteSpieler spieler3 = new EchteSpieler();
        spieler3.setName();
        EchteSpieler spieler4 = new EchteSpieler();
        spieler4.setName();

        alleSpieler.add(spieler1);
        alleSpieler.add(spieler2);
        alleSpieler.add(spieler3);
        alleSpieler.add(spieler4);

        //printAlleSpielerNamen();
    }

    public void neuerAblagestapelUndErsteKarteAufgedeckt() {

        ablagestapel.add(verteilstapel.abheben());
        System.out.println("Die erste Karte ist: ");
        System.out.println(ablagestapel.obersteKarte());
        if (ablagestapel.obersteKarte().getFarbe() == Farbe.SCHWARZ && ablagestapel.obersteKarte().getWert() == Wert.PLUSVIER) {
            System.out.println("Es liegt eine +4 auf, eine neue Karte wird aufgelegt");
            verteilstapel.add(ablagestapel.obersteKarte());
            verteilstapel.mischen();
            neuerAblagestapelUndErsteKarteAufgedeckt();
        }
    }

    public void aktuelleKarteAblagestapel() {
        System.out.println("Die aktuelle Karte ist: ");
        System.out.println(ablagestapel.obersteKarte());
    }

    public void kartenHandzeigen() {
        //diese Methode prüft zunächst ob der aktuelle Spieler ein Bot ist oder ein echter Spieler,
        //wenn echter Spieler, wird dieser gefragt, ob er seine Hand einsehen möchte
        //die Abfrage wird über Konsole mit y oder n getätigt, bei falscher Eingabe gibt es eine Exception
        if (aktuellerSpieler instanceof EchteSpieler) {
            System.out.println("Möchten sie ihre Hand angezeigt bekommen?");
            System.out.println("Bitte Y (YES) oder N (No) eingeben");
            try {
                Scanner scanner = new Scanner(System.in);
                String c = scanner.nextLine();
                //char c = (char) scanner.nextInt();
                if (c.equalsIgnoreCase("y")) {
                    System.out.println(aktuellerSpieler.spielerHand);
                    return;
                }
                if (c.equalsIgnoreCase("n")) {
                    return;
                }
                while (!c.equalsIgnoreCase("y") || !c.equalsIgnoreCase("n")) {
                    System.out.println("Falsche Eingabe!");
                    throw new FalscheEingabeException("Falsche Eingabe");
                }
            } catch (FalscheEingabeException e) {
                kartenHandzeigen();
                //e.printStackTrace();
            }
            //TODO Methode muss noch fertig gemacht werden
        }
    }

    public void karteAblegen() {

    }

    public void ausgabeaktuellerSpieler() {
        System.out.println(aktuellerSpieler.getName() + "  ist an der Reihe!");
        kartenHandzeigen(); //TODO Methode muss noch fertig gemacht werden
        Karte k = ablagestapel.obersteKarte();
        System.out.println("Bitte spielen Sie eine Karte, die auf FARBE:" + k.getFarbe() + " oder WERT: " + k.getWert() + " gelegt werden darf.");

    }

    public void spielzug() {

        //per Eingabe Karte spielen
        //check if chosen card matches one available in the Kartenhand-array
        //check if after playing the card there is only 1 left > UNO

        Scanner scanner = new Scanner(System.in);
        int position = scanner.nextInt();
        Karte handKarte = aktuellerSpieler.spielerHand.get(position);
        if (!passendeKarte(handKarte, ablagestapel.obersteKarte())) {
            System.out.println("Bitte legen Sie eine passende Karte ab");
            System.out.println("Es liegt die " + ablagestapel.obersteKarte() + " oben auf!");
        } else {

            System.out.println("Diese Karte wird abgelegt:");
            System.out.println(aktuellerSpieler.spielerHand.get(position));
            //ablagestapel.add(aktuellerSpieler.spielerHand.get(position));

            //todo: INFO: Prüfung ob Spielzug gültig ist wurde zugefügt
            ablagestapel.karteAblegen(aktuellerSpieler.spielerHand.get(position));

            //todo: HIER IST NOCH EIN FEHLER, METHODE WIRD NACH PRÜFUNG AUF KORREKTE ABLAGE NOCH NICHT WEITER AUSGEFÜHRT; SPIELERWEHSEL WIRD NICHT AUFGERUFEN

            aktuellerSpieler.spielerHand.remove(position);
//           System.out.println("diese karte liegt oben auf:");         ueberpruefung ob handkarte nun am stapel oben liegt
//           System.out.println(ablagestapel.obersteKarte());
            spielerWechsel();
            spielzug();

        }


    }

    public boolean passendeKarte(Karte handKarte, Karte ablageStapel) {

        if (ablageStapel.getFarbe().equals(SCHWARZ)) {
            return true;
        } else if (handKarte.getFarbe().equals(ablageStapel.getFarbe()) || handKarte.getWert() == ablageStapel.getWert() || handKarte.getFarbe().equals(SCHWARZ)) {
            return true;
        } else return false;
        // TODO: 06.07.2020 aktionskarten ?
    }

    public EchteSpieler spielerWechsel() {
        // TODO: 06.07.2020 richtungswechsel?

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
        spielzug();
        return aktuellerSpieler;


    }


    @Override
    public String toString() {
        return "SpielerManager{" +
                "alleSpieler=" + alleSpieler +
                '}';
    }

}
