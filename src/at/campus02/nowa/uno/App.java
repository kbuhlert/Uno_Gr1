package at.campus02.nowa.uno;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private final Scanner input;
    private final PrintStream output;

    public App(Scanner input, PrintStream output){
        this.input = input;
        this.output = output;
    }
    SpielerManager spielerManager = new SpielerManager();

    public void Run() {
        initializeGame();
        try {
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
        }catch (Exception ex){
            output.println(ex);
        }
    }

    private void initializeGame() {
        spielerManager.spielerZuweisen();

        //Reihenfolge der Spieler zufällig festlegen und Startspieler festlegen
        //todo: Arraylist, mit Shuffle
        spielerManager.startSpieler();

    }

    private void initializeRound() {


        /*
        //Verteilstack erstellen
        Kartenstapel verteilstapel = new Kartenstapel();
        verteilstapel.neuerVerteilstapel();

        //Verteilstack mischen
        verteilstapel.mischen();
        //Karten austeilen -->7 Karten pro Spieler



        //1. Karte von Verteilstack auf Ablagestapel = erstellt den Ablagestapel

        //Rest vom Verteilstack ist Abhebestapel
        //Prüfen ob Startkarte Aktionskarte ist (Karte Aufnehmen oder Aussetzen/Richtungswechsel oder Farbe festlegen)


         */
        spielerManager.beginneRunde();
        spielerManager.neuerAblagestapelUndErsteKarteAufgedeckt();
        //spielerManager.kartenHandzeigen();
    }

    private void readUserInput() {

        //Scanner aufrufen für Eingabe des aktuellen Spielers

        //Eingabe des aktuellen Spielers (abgelegte Karte Aufruf regeln, Aufruf Punktestand, Ungültige Eingabe)
        // (Befelhle: "Play X" = X.te Karte aus dem Array/ "Take" = Abheben wenn keine Karte gespielt werden kann, "Take2" = 2 Abheben, "Take4" = 4 Abheben, "Help","uno", "challenge", "Punktestand")
        //Nach Befehl "Take" --> Spieler wird gefragt, ob er Karte legen kann/möchte
        //Prüfen ob aktueller Spieler Hilfe braucht/ Befehl "Help" (--> Ausgabe der Regeln)    //todo: Spielregeln in Textdatei einfügen
        //Prüfen ob aktueller Spieler Punktestand ausgegeben haben möchte
        //Wenn Ausgabe von regeln oder Punktestand neuer Aufruf von readUserInput()


        //prüfen ob Ablage korrekt  --> wenn nein Methode strafe();
        //prüfen ob Ablage Aktionskarte --> Methoden
        //speichern welche Karte gelegt -->oberste Karte auf Ablagestack ändern ablagestack.push()
        //wenn falsche Karte gelegt: Methode falscheKartegelegt(); aufrufen
        //Nach Befehl "Play X": Prüfen wieviel Karten hat aktueller Spieler    --> bei 1 Karten Uno-Ausruf abprüfen, wenn false, Methode strafe();
        //Methode beenden wenn Überprüfung beendet, nach Befehl "Play X", "Take2", "Take4", "Take" + "PlayX", "Take" + "Fertig"

    }

    private void updateState() {
        //Variable obersteKarteAblagestapel neuen Wert zuweisen
        //Punktestand neu berechnen
        //Spielerhand ArrayList aktualisieren (nach Spielzug, nach Strafkarten)
        //Ausgeben welcher Spieler ist als nächstes dran
        //welche Aktion muss als nächstes durchgeführt werden
        //prüfen ob Spielzug fertig ist
        //Prüfen ob Abhebestapel noch genug Karten für nächsten Zug hat (mind. 4) ansonsten Aufruf Methode AblegestapelZuAbhebestapel();


    }

    private void printState() {
        //Ausgeben welche Karte auf Ablagestapel oben liegt (variable obersteKarteAblagestapel)
        //Aufvorderung des nächsten Spielers
        //Spielerhand auf Konsole ausgeben (könnte auch mit SpielerInput abgefragt werden, dann muss Bot die Hand nicht ausgeben -->toString beim echten Spieler)


    }

    private boolean roundEnded(){
        //true = Wenn Spieler keine Karte im Array hat
        //Berechnen der Punkte (Übertrag in Datenbank)
        //Überprüft ob Endspielstand (500 Punkte) von einem Spieler erreicht wurde nach Rundenende, wenn ja, dann Methode gameEnded() aufrufen
        return false;
    }

    private boolean gameEnded(){
        //Ausgabe Rangliste + Gratulation
        //Ausgabe finaler Punktestand
        // Ausgabe GameOver
        //Abfrage "Neues Spiel starten"
        return false;

    }

    private void printFinalScore(){

    }
}
