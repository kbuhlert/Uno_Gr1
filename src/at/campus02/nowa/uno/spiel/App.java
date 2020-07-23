package at.campus02.nowa.uno.spiel;


import at.campus02.nowa.uno.karte.Kartenmanager;
import at.campus02.nowa.uno.kartenstapel.TeststapelWunschkarte;

import at.campus02.nowa.uno.FalscheEingabeException;
import at.campus02.nowa.uno.spieler.Spieler;


import java.io.PrintStream;
import java.util.Scanner;

public class App {
    private final Scanner input;
    private final PrintStream output;
    private final SpielerManager spielerManager;

    //TeststapelWunschkarte verteilstapel;  //--> zum Testen mit speziellen Karten

    public App(Scanner input, PrintStream output) {
        this.input = input;
        this.output = output;
        spielerManager = new SpielerManager(input, output);
    }


    //TeststapelWunschkarte verteilstapel = new TeststapelWunschkarte();  //--> zum Testen mit speziellen Karten
    //Kartenstapel ablagestapel = new Kartenstapel();

    public void Run() {

        try {
            initializeGame();
            do {
                initializeRound();
                printState();

                while (!roundEnded()) {
                    readUserInput();
                    updateState();
                    printState();

                    Thread.sleep(100);
                }
            } while (!gameEnded());

            printFinalScore();
        } catch (Exception ex) {
            ex.printStackTrace();
            //todo Exceptions passend erweitern (Bsp. Auswahlzahl von Karte auf Hand ist Größer als Array)
        }
    }

    private void initializeGame() {
        spielerManager.spielerZuweisen();
        spielerManager.startSpielerFestlegen();
        //Reihenfolge der Spieler zufällig festlegen und Startspieler festlegen

    }

    private void initializeRound() {


        //Verteilstack erstellen
        //Verteilstack mischen
        //Karten austeilen -->7 Karten pro Spieler
        //1. Karte von Verteilstack auf Ablagestapel = erstellt den Ablagestapel
        //todo: Rest vom Verteilstack ist Abhebestapel
        //Prüfen ob oberste Karte im Ablagestapel eine +4 ist
        //todo: Prüfen ob Startkarte Aktionskarte ist (Karte Aufnehmen oder Aussetzen/Richtungswechsel oder Farbe festlegen)
        spielerManager.kartenAusteilen();
    }

    private void readUserInput() {

        //Scanner aufrufen für Eingabe des aktuellen Spielers
        spielerManager.abfrageKartenhandZeigen();
        spielerManager.neueKarteHeben();
        spielerManager.karteAblegen();
        spielerManager.spielzugBeendet();

        // boolean der true ausgibt wenn aktueller spieler keine karten mehr hat
        if (spielerManager.letzte) {
            roundEnded();
        }
        //todo: Info an Alle. Habe die Ausgabe der obersten Karte Ablagestapel, die Aufforderung des nächsten Spielers, Abfrage ob Hand angezeigt
        // werden soll und Eingabeaufforderung in PrintState() gegeben

        //Eingabe des aktuellen Spielers (abgelegte Karte Aufruf regeln, Aufruf Punktestand, Ungültige Eingabe)
        // (Befelhle: "Play X" = X.te Karte aus dem Array/ "Take" = Abheben wenn keine Karte gespielt werden kann, "Take2" = 2 Abheben, "Take4" = 4 Abheben, "Help","uno", "challenge", "Punktestand")
        //todo:Nach Befehl "Take" --> Spieler wird gefragt, ob er Karte legen kann/möchte
        //todo:Prüfen ob aktueller Spieler Hilfe braucht/ Befehl "Help" (--> Ausgabe der Regeln)    //todo: Spielregeln in Textdatei einfügen
        //todo:Prüfen ob aktueller Spieler Punktestand ausgegeben haben möchte
        //todo:Wenn Ausgabe von regeln oder Punktestand neuer Aufruf von readUserInput()
        //todo: prüfen ob Ablage korrekt  --> wenn nein Methode strafe();
        //todo: prüfen ob Ablage Aktionskarte --> Methoden
        //todo: speichern welche Karte gelegt -->oberste Karte auf Ablagestack ändern ablagestack.push()
        //wenn falsche Karte gelegt: Methode falscheKartegelegt(); aufrufen
        //Nach Befehl "Play X": Prüfen wieviel Karten hat aktueller Spieler    --> bei 1 Karten Uno-Ausruf abprüfen, wenn false, Methode strafe();
        //Methode beenden wenn Überprüfung beendet, nach Befehl "Play X", "Take2", "Take4", "Take" + "PlayX", "Take" + "Fertig"
    }

    private void updateState() {
        // TODO: 09.07.2020


//         if(spielerManager.kartenstapel.size() < 4){
//             System.out.println(spielerManager.kartenstapel.size());
//             spielerManager.kartenstapel.stapelZusammenMischen();
//         }
//         spielerManager.spielerWechsel();


        spielerManager.spielerWechsel();

        //Variable obersteKarteAblagestapel neuen Wert zuweisen
        //Punktestand neu berechnen
        //Spielerhand ArrayList aktualisieren (nach Spielzug, nach Strafkarten)
        //welche Aktion muss als nächstes durchgeführt werden
        //prüfen ob Spielzug fertig ist
        //Prüfen ob Abhebestapel noch genug Karten für nächsten Zug hat (mind. 4) ansonsten Aufruf Methode AblegestapelZuAbhebestapel();
    }

    private void printState() {
        //Ausgeben welcher Spieler ist als nächstes dran
        spielerManager.WerIstDranUndWelcheKarte();
    }


    //Ausgeben welche Karte auf Ablagestapel oben liegt (variable obersteKarteAblagestapel)
    //Aufforderung des nächsten Spielers
    //Spielerhand auf Konsole ausgeben (könnte auch mit SpielerInput abgefragt werden, dann muss Bot die Hand nicht ausgeben -->toString beim echten Spieler)


    private boolean roundEnded() {
        //check whether anyone's spielerhand is empty
        if (spielerManager.aktuellerSpieler.spielerHand.isEmpty()) {
            output.println();
            output.println();
            output.println("------");
            output.println("Die Runde ist zu Ende. " + spielerManager.aktuellerSpieler.getName() + " hat " + spielerManager.getPunkteVonAllenSpielern() + " Punkte gewonnen.");
            spielerManager.aktuellerSpieler.setRundenPunkte(spielerManager.getPunkteVonAllenSpielern());
            output.println(spielerManager.aktuellerSpieler.getName() + " hat insgesamt schon " + spielerManager.aktuellerSpieler.getRundenPunkte() + " Punkte.");
            output.println();
            return true;
        }
        return false;
    }

    private boolean weiterSpielerAbfrage() {
        output.println("Noch eine Runde?");
        String c;
        do {
            c = input.nextLine();
            if (c.equalsIgnoreCase("y")) {
                return true;
            } else if (c.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Falsche Eingabe!");
            }
        } while (true);
    }

    private boolean gameEnded() {
        for (Spieler s : spielerManager.alleSpieler) {
            while (s.getRundenPunkte() < 500) {
                return !weiterSpielerAbfrage();
            }
            return true;
        }
        return false;
    }

    private void printFinalScore() {
        Spieler gewinner = null;
        for (Spieler s : spielerManager.alleSpieler) {
            if (s.getRundenPunkte() >= 500) {
                gewinner = s;
                output.println("GRATULATION!");
                output.println(gewinner.getName() + " hat das Spiel mit " + gewinner.getRundenPunkte() + " Punkten gewonnen!");
                output.println();
                output.println();
                return;
            }
            else {
                gewinner = null;
                output.println("Das Spiel wird vorzeitig abgebrochen!");
                output.println();
                output.println();
                return;
            }
        }
    }
}
