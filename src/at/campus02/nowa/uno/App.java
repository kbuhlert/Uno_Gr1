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

                    Thread.sleep(100);
                }
            } while (!gameEnded());

            printFinalScore();
        }catch (Exception ex){
            output.println(ex);
        }
    }

    private void initializeGame() {
        //Spieler Erstellen (Namen eingeben)
        //erstelleBots();
        //get Alle Spieler () = Ausgabe: "Es spielen mit:" getBotName() + getSpielerName();
        //Stack erstellen
        //Stack mischen

    }

    private void initializeRound() {
        //Karten austeilen

    }

    private void readUserInput() {
        //prüfen wieviel Karten hat user
        //prüfen ob Ablage korrekt

    }

    private void updateState() {

    }

    private void printState() {

    }

    private boolean roundEnded(){
        return false;
    }

    private boolean gameEnded(){
        return false;
    }

    private void printFinalScore(){

    }
}
