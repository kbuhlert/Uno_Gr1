package at.campus02.nowa.uno.spiel;



import at.campus02.nowa.uno.spieler.Spieler;

import java.io.PrintStream;
import java.util.Scanner;

public class App {
    private final Scanner input;
    private final PrintStream output;

    //TeststapelWunschkarte verteilstapel;  //--> zum Testen mit speziellen Karten

    public App(Scanner input, PrintStream output){
        this.input = input;
        this.output = output;
    }


    SpielerManager spielerManager = new SpielerManager();
    //TeststapelWunschkarte verteilstapel = new TeststapelWunschkarte();  //--> zum Testen mit speziellen Karten
    //Kartenstapel ablagestapel = new Kartenstapel();

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
        spielerManager.karteAblegen();
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
        //Variable obersteKarteAblagestapel neuen Wert zuweisen
        //Punktestand neu berechnen
        //Spielerhand ArrayList aktualisieren (nach Spielzug, nach Strafkarten)
        //welche Aktion muss als nächstes durchgeführt werden
        //prüfen ob Spielzug fertig ist
        //Prüfen ob Abhebestapel noch genug Karten für nächsten Zug hat (mind. 4) ansonsten Aufruf Methode AblegestapelZuAbhebestapel();
    }

    private void printState() {
        //Ausgeben welcher Spieler ist als nächstes dran
        if (!roundEnded()){
            spielerManager.WerIstDranUndWelcheKarte();
        }
        return;
        //Ausgeben welche Karte auf Ablagestapel oben liegt (variable obersteKarteAblagestapel)
        //Aufvorderung des nächsten Spielers
        //Spielerhand auf Konsole ausgeben (könnte auch mit SpielerInput abgefragt werden, dann muss Bot die Hand nicht ausgeben -->toString beim echten Spieler)
    }

    private boolean roundEnded(){
        //check whether anyone's spielerhand is empty
        if(spielerManager.aktuellerSpieler.spielerHand.isEmpty()){
            output.println("Die Runde ist zu Ende. Es hat gewonnen: " + spielerManager.aktuellerSpieler.getName());
            // if one spielerhand is empty, there is a winner, so all remaining hands need to be counted up and the sum added to a database
            for (Spieler s : spielerManager.alleSpieler){
                int summeSpielerHand = 0;
                for (int i = 0; i < s.spielerHand.size() ; i++) {
                    summeSpielerHand += s.getSpielerHand().get(i).getPunkte();
                    i++;
                }
                //summeSpielerHand add to database
                spielerManager.aktuellerSpieler.punkte += summeSpielerHand;
                output.println(spielerManager.aktuellerSpieler.getName() + " hat die folgenden Punkte gewonnen: " + summeSpielerHand);
            }
            initializeRound();
            return true;
        }
        else {
            return false;}

        //true = Wenn Spieler keine Karte im Array hat
        //Berechnen der Punkte (Übertrag in Datenbank)
        //Überprüft ob Endspielstand (500 Punkte) von einem Spieler erreicht wurde nach Rundenende, wenn ja, dann Methode gameEnded() aufrufen
    }

    private boolean gameEnded(){
        if (spielerManager.aktuellerSpieler.punkte > 500){
            return true;
        }
        else {
            return false;
        }
        //Ausgabe Rangliste + Gratulation
        //Ausgabe finaler Punktestand
        // Ausgabe GameOver
        //Abfrage "Neues Spiel starten"
    }

    private void printFinalScore(){

    }
}
