package at.campus02.nowa.uno;

import java.util.ArrayList;
import java.util.Collections;
import static at.campus02.nowa.uno.Farbe.SCHWARZ;
import java.util.Scanner;

public class SpielerManager {
    Scanner input = new Scanner(System.in);
    //  Spieler in  Liste
    protected ArrayList<Spieler> alleSpieler;
    Kartenstapel verteilstapel;
    //TeststapelWunschkarte verteilstapel;  //--> zum Testen mit speziellen Karten
    Kartenstapel ablagestapel;
    Spieler aktuellerSpieler = null;
    boolean spielrichtung = true;
    //private PrintStream output;
    //private final Scanner input;

    public SpielerManager(Kartenstapel verteilstapel, Kartenstapel ablagestapel, ArrayList<Spieler> alleSpieler) {   //todo: Ablagestapel, Verteilstapel, Scanner, alleSpieler-Array werden dem SpielerManager als Parameter übergeben.
        // todo: So können diese von App erstellt werden un Spielermanager nutzt dann die gleichen Objekte
       this.alleSpieler =  alleSpieler;
       this.verteilstapel = verteilstapel;
       this.ablagestapel = ablagestapel;
       //this.input = input;
       //this.output = output;
    }

    //Eingabe der Zahl der echten Spieler & Eingabe der Spielrnamen per Scanner
    //hier wird alleSpieler-Array befüllt
    public void spielerZuweisen() {
        // todo: Echte und Botspieler erstellen
        System.out.println("Bitte geben Sie die Zahl (0-4) der echten Spieler ein: ");
        //todo: FalscheEingabeException zufügen
        int echteSpieler = input.nextInt();
        Spieler spieler1 = null;
        Spieler spieler2 = null;
        Spieler spieler3 = null;
        Spieler spieler4 = null;
        switch (echteSpieler) {
            case (4):
                spieler1 = new EchteSpieler(input);
                spieler2 = new EchteSpieler(input);
                spieler3 = new EchteSpieler(input);
                spieler4 = new EchteSpieler(input);
                break;

            case (3):
                spieler1 = new EchteSpieler(input);
                spieler2 = new EchteSpieler(input);
                spieler3 = new EchteSpieler(input);
                spieler4 = new BotSpieler();
                break;

            case (2):
                spieler1 = new EchteSpieler(input);
                spieler2 = new EchteSpieler(input);
                spieler3 = new BotSpieler();
                spieler4 = new BotSpieler();
                break;

            case (1):
                spieler1 = new EchteSpieler(input);
                spieler2 = new BotSpieler();
                spieler3 = new BotSpieler();
                spieler4 = new BotSpieler();
                break;

            case (0):
                spieler1 = new BotSpieler();
                spieler2 = new BotSpieler();
                spieler3 = new BotSpieler();
                spieler4 = new BotSpieler();
                break;
        }
        alleSpieler.add(spieler1);  //Spieler werden dem Spielerarray zugefügt
        alleSpieler.add(spieler2);
        alleSpieler.add(spieler3);
        alleSpieler.add(spieler4);
    }

    // zufälligen Startspieler festlegen:
    public void startSpielerFestlegen() {
        Collections.shuffle(alleSpieler);
        System.out.println("Im Spiel sind in dieser Reihenfolge:  ");
        printAlleSpielerNamen();
        System.out.println();
        aktuellerSpieler = alleSpieler.get(0);
        System.out.println("May the odds be ever in your favour");
        System.out.println();
        //return aktuellerSpieler;
    }

    public void printAlleSpielerNamen() {
        for (Spieler spieler : alleSpieler) {
            System.out.print(spieler.getName() + ", ");
        }
    }

    //Verteilstack erstellen & austeilen der Karten auf die Spielerhand
    public void kartenAusteilen() {
        verteilstapel.stapelErstellen();
        //verteilstapel.neuerTeststapel(new Zahlenkarte(Farbe.BLAU,Wert.ACHT), new Zahlenkarte(Farbe.BLAU,Wert.RICHTUNGSWECHSEL));  //--> Wenn mit Teststapel gespielt wird
        System.out.println("Karten werden ausgeteilt");     //Karten austeilen -->7 Karten pro Spieler
        for (Spieler spieler : alleSpieler) {
            while (spieler.spielerHand.size() < 7) {
                spieler.spielerHand.add(verteilstapel.abheben());
            }
            System.out.println(spieler.getName() + " hat " + spieler.spielerHand.size() + " Handkarten.");
        }
        System.out.println();
    }

    //Fragt aktuellen Spieler ob er vor dem Ablegen der Karte seine eigene Hand auf der Konsole sehen möchte
    public void abfrageKartenhandZeigen() {
        //diese Methode prüft zunächst ob der aktuelle Spieler ein Bot ist oder ein echter Spieler,
        //wenn echter Spieler, wird dieser gefragt, ob er seine Hand einsehen möchte
        //die Abfrage wird über Konsole mit y oder n getätigt, bei falscher Eingabe gibt es eine Exception
        if (aktuellerSpieler instanceof EchteSpieler) {
            System.out.println("Möchten sie ihre Hand angezeigt bekommen?");
            System.out.println("Bitte Y (YES) oder N (No) eingeben");
            try {
                Scanner scanner = new Scanner(System.in);
                String c = scanner.nextLine();
                if (c.equalsIgnoreCase("y")) {
                    System.out.println(aktuellerSpieler.spielerHand);//todo: hier soll Methode printSpielerhand aufgerufen werden
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
                abfrageKartenhandZeigen();
                //e.printStackTrace();
            }
        }
    }

    public void WerIstDranUndWelcheKarte() {    //todo: wird zu wer ist dran
        System.out.println();
        System.out.println("-----");
        System.out.println(aktuellerSpieler.getName() + "  ist an der Reihe!");
        abfrageKartenhandZeigen();      //todo: die Methoden direkt in der App aufrufen
        ablagestapel.AusgabeObersteKarteAblagestapel(ablagestapel);
    }

    public void karteAblegen() {
        //per Eingabe Karte spielen
        //check if chosen card matches one available in the Kartenhand-array
        //todo: check if after playing the card there is only 1 left > UNO
        Scanner scanner = new Scanner(System.in);
        int position = scanner.nextInt();
        Karte handKarte = aktuellerSpieler.spielerHand.get(position);

        if (!passendeKarte(handKarte, ablagestapel.obersteKarte())) {
            System.out.println("Falsche Karte gelegt. Bitte legen Sie eine passende Karte ab");
            System.out.println("Es liegt die " + ablagestapel.obersteKarte() + " oben auf!");
        } else {
            System.out.println("Spielzug korrekt. Diese Karte wurde abgelegt:");
            System.out.println(aktuellerSpieler.spielerHand.get(position));
            ablagestapel.add(aktuellerSpieler.spielerHand.get(position));
            aktuellerSpieler.spielerHand.remove(position);
        }
        spielerWechsel();
    }

    public boolean passendeKarte(Karte handKarte, Karte ablageStapel) {
        //todo Abfrage ob passende Karte gelegt wurde erweiter
        if (ablageStapel.getFarbe().equals(SCHWARZ)) {
            return true;
        } else if (handKarte.getFarbe().equals(ablageStapel.getFarbe()) || handKarte.getWert() == ablageStapel.getWert() || handKarte.getFarbe().equals(SCHWARZ)) {
            return true;
        } else return false;
        // TODO: 06.07.2020 aktionskarten ?
    }

    public Spieler spielerWechsel() {
        // Spielerwechsel nach Beendigung des Spielzuges
        //Richtungswechsel wenn auf Stapel "Richtungswechsel" liegt
        //todo: Aussetzenkarte ist Spieler überspringen

        if (ablagestapel.obersteKarte().getWert().equals(Wert.RICHTUNGSWECHSEL)) {
            spielrichtung = !spielrichtung;
        }
        if (spielrichtung == true) {
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
        if (spielrichtung == false) {
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
