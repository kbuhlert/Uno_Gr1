package at.campus02.nowa.uno.spiel;


import at.campus02.nowa.uno.Datenbank.SqliteClient;
import at.campus02.nowa.uno.karte.Kartenmanager;
import at.campus02.nowa.uno.kartenstapel.TeststapelWunschkarte;
import at.campus02.nowa.uno.FalscheEingabeException;
import at.campus02.nowa.uno.spieler.Spieler;

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class App {
    private final Scanner input;
    private final PrintStream output;
    private final SpielerManager spielerManager;
    private int roundcount = 0;
    private int sessions = 0;

    private static final String CREATETABLE = "CREATE TABLE Sessions (Player varchar(100) NOT NULL, Session int NOT NULL, Round int NOT NULL, Score int NOT NULL, CONSTRAINT PK_Sessions PRIMARY KEY (Player, Session, Round));";
    private static final String INSERT_TEMPLATE = "INSERT INTO Sessions (Player, Session, Round, Score) VALUES ('%1s', %2d, %3d, %4d);";
    private static final String SELECT_BYPLAYERANDSESSION = "SELECT Player, SUM(Score) AS Score FROM Sessions WHERE Player = '%1s' AND Session = %2d;";
    private static final String SELECTMAX = "select max(Session) as sessions from sessions;";
    private ArrayList<HashMap<String, String>> results = new ArrayList<>();
    SqliteClient client;

    public App(Scanner input, PrintStream output) {
        this.input = input;
        this.output = output;
        spielerManager = new SpielerManager(input, output);
    }

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
        }
    }

    private void initializeGame() throws SQLException {
        client = new SqliteClient("UNO.sqlite");
        if (!client.tableExists("Sessions")) {
            client.executeStatement(CREATETABLE);
        }
        spielerManager.spielerZuweisen();
        spielerManager.startSpielerFestlegen();
        //Reihenfolge der Spieler zufällig festlegen und Startspieler festlegen
        ArrayList<HashMap<String, String>> result = client.executeQuery(SELECTMAX);
        sessions = Integer.valueOf(result.get(0).get("sessions"));
        sessions++;
    }

    private void initializeRound() {
        roundcount++;
        spielerManager.kartenAusteilen();
    }

    private void readUserInput() {
        spielerManager.abfrageKartenhandZeigen();
        spielerManager.neueKarteHeben();
        spielerManager.karteAblegen();
        spielerManager.spielzugBeendet();
    }

    private void updateState() {
        spielerManager.spielerWechsel();
    }

    private void printState() throws SQLException {
        spielerManager.ausgabeAktuellerSpieler();
        punkteInDatenbank();
    }

    private boolean roundEnded() {
        Spieler rundenGewinner = null;
        for (Spieler s : spielerManager.alleSpieler) {
            if (s.spielerHand.isEmpty()) {
                rundenGewinner = s;
                rundenGewinner.setRundenPunkte(spielerManager.getPunkteVonAllenSpielern());
                output.println();
                output.println();
                output.println("------");
                output.println("Die Runde ist zu Ende. ");
                output.println("GRATULIERE, Gewinner ist: " + rundenGewinner.getName());
                output.println(rundenGewinner.getName() + " hat in dieser Runde " + spielerManager.getPunkteVonAllenSpielern() + " Punkte gewonnen. GESAMTPUNKTZAHL " + rundenGewinner.getName() + ": " + rundenGewinner.getRundenPunkte() + ".");
                output.println("Es wurden " + roundcount + " Runden gespielt.");
                output.println();
                return true;

            }
        }
        return false;
    }

    private boolean weiterSpielenAbfrage() {
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
            if (s.getRundenPunkte() >= 500) {
                return true;
            }
        }
        return !weiterSpielenAbfrage();
    }

    private void punkteInDatenbank() throws SQLException {
        if (roundEnded()) {
            output.println("Die Punkte werden in die Datenbank eingelesen");
            for (int i = 0; i < 4; i++) {
                Spieler sp = spielerManager.alleSpieler.get(i);
                int score = sp.getPunkteVonSpielerHand();
                client.executeStatement(String.format(INSERT_TEMPLATE, sp.getName(), sessions, roundcount, score));
                results = client.executeQuery(String.format(SELECT_BYPLAYERANDSESSION, sp.getName(), 1));
                for (HashMap<String, String> map : results) {
                    System.out.println(map.get("Player") + " hat derzeit:  " + map.get("Score") + " Punkte");
                }
            }
        } else return;
    }

    private void printFinalScore() {
        Spieler gewinner = null;
        for (Spieler s : spielerManager.alleSpieler) {
            if (s.getRundenPunkte() >= 500) {
                gewinner = s;
                output.println();
                output.println();
                output.println("*******************************************************");
                output.println();
                output.println("GRATULATION!");
                output.println(gewinner.getName() + " hat das Spiel mit " + gewinner.getRundenPunkte() + " Punkten gewonnen!");
                output.println();
                output.println("*******************************************************");
                output.println();
                output.println();
                output.println();
                return;
            }
        }
    }
}
