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
        //Verteilstack erstellen


    }

    private void initializeRound() {
        //Verteilstack mischen
        //1. Karte von Verteilstack auf Ablagestapel
        //Karten austeilen

    }

    private void readUserInput() {
        //prüfen wieviel Karten hat user
        //prüfen ob Ablage korrekt
        //speichern welche Karte gelegt -->oberste Karte auf Ablagestack ändern ablagestack.push()
        //wenn falsche Karte gelegt: Methode falscheKartegelegt(); aufrufen

    }

    private void updateState() {
        //todo: welche Informationen gehören zu "State"
        //Punktestand neu berechnen
        //Spielerhand ArrayList aktualisieren (nach Spielzug, nach Strafkarten)
        //welcher Spieler ist aktuell dran
        //welche Aktion muss als nächstes durchgeführt werden
        //prüfen ob Spielzug fertig ist


    }

    private void printState() {
        //todo: Ausgabe Punktestand? --> Was ist mit "State" gemeint, was gehört dazu

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
