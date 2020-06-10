package at.campus02.nowa.uno;

import java.io.PrintStream;
import java.util.Scanner;

public class App {
    private final Scanner input;
    private final PrintStream output;

    public App(Scanner input, PrintStream output){
        this.input = input;
        this.output = output;
    }

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

                    Thread.sleep(100);  //todo: wofür wird hier die Verzögerung eingebaut, was ist ein "Thread"
                }
            } while (!gameEnded());

            printFinalScore();
        }catch (Exception ex){
            output.println(ex);
        }
    }

    private void initializeGame() {
        //Spieler Erstellen
        // Namen eingeben
        //erstelleBots();
        //get Alle Spieler () = Ausgabe: "Es spielen mit:" getBotName() + getSpielerName();
        //Reihenfolge der Spieler zufällig festlegen und Startspieler festlegen
        //todo: Arraylist, mit Shuffle
        //Verteilstack erstellen


    }

    private void initializeRound() {
        //Verteilstack mischen
        //Karten austeilen -->7 Karten pro Spieler
        //1. Karte von Verteilstack auf Ablagestapel = erstellt den Ablagestapel
        //Rest vom Verteilstack ist Abhebestapel
        //Prüfen ob Startkarte Aktionskarte ist

    }

    private void readUserInput() {

        //Scanner aufrufen für Eingabe des aktuellen Spielers

        //Eingabe des aktuellen Spielers (abgelegte Karte, Aufruf regeln, Aufruf Punktestand, Ungültige Eingabe)

        //Prüfen ob aktueller Spieler Hilfe braucht (--> Ausgabe der Regeln)    //todo: Spielregeln in Textdatei einfügen
        //Prüfen ob aktueller Spieler Punktestand ausgegeben haben möchte
        //Wenn Ausgabe von regeln oder Punktestand neuer Aufruf von readUserInput()

        //prüfen ob Ablage korrekt
        //prüfen ob Ablage Aktionskarte --> Methoden
        //speichern welche Karte gelegt -->oberste Karte auf Ablagestack ändern ablagestack.push()
        //wenn falsche Karte gelegt: Methode falscheKartegelegt(); aufrufen
        //Prüfen wieviel Karten hat user    --> bei 2 Karten Uno-Ausruf abprüfen

    }

    private void updateState() {
        //todo: welche Informationen gehören zu "State"
        //Variable obersteKarteAblagestapel neuen Wert zuweisen
        //Punktestand neu berechnen
        //Spielerhand ArrayList aktualisieren (nach Spielzug, nach Strafkarten)
        //welcher Spieler ist aktuell dran
        //welche Aktion muss als nächstes durchgeführt werden
        //prüfen ob Spielzug fertig ist


    }

    private void printState() {
        //todo: Ausgabe Punktestand? --> Was ist mit "State" gemeint, was gehört dazu
        //Ausgeben welche Karte auf Ablöagestapel oben liegt (variable obersteKarteAblagestapel)
        //Aufvorderung des nächsten Spielers
        //Spielerhand auf Konsole ausgeben (könnte auch mit SpielerInput abgefragt werden, dann muss Bot die Hand nicht ausgeben -->toString beim echten Spieler)
        //todo: wie bekommt Spieler die Karten die er auf der Hand hat ausgegeben? Soll das immer mit Aufruf des Spielers auf Konsole ausgegeben werden?
        // (In dem Fall sehen alle Spieler was jeder Spieler auf der Hand hat)

    }

    private boolean roundEnded(){
        return false;
    }

    private boolean gameEnded(){
        return false;
        //Überprüft ob Endspielstand (500 Punkte) von einem Spieler erreicht wurde nach Rundenende, wenn ja, dann return true
    }

    private void printFinalScore(){

    }
}
