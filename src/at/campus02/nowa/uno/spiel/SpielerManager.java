package at.campus02.nowa.uno.spiel;

import at.campus02.nowa.uno.*;

import at.campus02.nowa.uno.karte.*;

import at.campus02.nowa.uno.kartenstapel.TeststapelWunschkarte;
import at.campus02.nowa.uno.spieler.BotSpieler;
import at.campus02.nowa.uno.spieler.EchteSpieler;
import at.campus02.nowa.uno.spieler.Spieler;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;

import static at.campus02.nowa.uno.karte.Farbe.SCHWARZ;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SpielerManager {
    private final Scanner input;
    private final PrintStream output;
    //  Spieler in  Liste
    protected ArrayList<Spieler> alleSpieler;

    Kartenmanager kartenstapel;
//        TeststapelWunschkarte kartenstapel;  //--> zum Testen mit speziellen Karten

    Spieler aktuellerSpieler = null;
    boolean spielrichtung = true;
    boolean aussetzten = false;
    boolean uno = false;





    public SpielerManager(Scanner input, PrintStream output) {
        //todo: Ablagestapel, Verteilstapel, Scanner, alleSpieler-Array werden dem SpielerManager als Parameter übergeben.
        // todo: So können diese von App erstellt werden un Spielermanager nutzt dann die gleichen Objekte

        this.kartenstapel = new TeststapelWunschkarte();
        this.alleSpieler = new ArrayList<>();
        this.input = input;
        this.output = output;
    }

    public void spielerZuweisen() {
        Spieler spieler;

        int anzBot = 1;
//        input = new Scanner(System.in);
        while (alleSpieler.size() < 4) {
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

        kartenstapel.stapelErstellen();
//        kartenstapel.neuerTeststapel(new Zahlenkarte(Farbe.BLAU,Wert.ACHT), new Zahlenkarte(Farbe.BLAU,Wert.RICHTUNGSWECHSEL));  //--> Wenn mit Teststapel gespielt wird
//        kartenstapel.neuerTeststapel(new Zahlenkarte(SCHWARZ,Wert.PLUSVIER), new Zahlenkarte(Farbe.BLAU,Wert.RICHTUNGSWECHSEL));  //--> Wenn mit Teststapel gespielt wird
        System.out.println("Karten werden ausgeteilt");     //Karten austeilen -->7 Karten pro Spieler
        for (Spieler spieler : alleSpieler) {
            while (spieler.spielerHand.size() < 2) { //todo 2 statt 7

                spieler.spielerHand.add(kartenstapel.abheben());
            }
            System.out.println(spieler.getName() + " hat " + spieler.spielerHand.size() + " Handkarten.");
        }
        System.out.println();
        ersteKarteAblegen();
    }


    public void ersteKarteAblegen() {
        kartenstapel.karteAblegen(kartenstapel.abheben());
        System.out.println("Die erste Karte ist: ");
        System.out.println(kartenstapel.obersteKarte());
        //Test ob +4 Aufliegt, wenn ja Karte zurück, mischen und neuer Aufruf der Methode
        while (kartenstapel.obersteKarte().getWert() == Wert.PLUSVIER) {
            System.out.println("Es liegt eine +4 auf, nochmal mischen, eine neue Karte wird aufgelegt");
            kartenstapel.karteAblegen(kartenstapel.abheben());
            System.out.println("Die erste Karte ist: ");
            System.out.println(kartenstapel.obersteKarte());

        }
    }

    //Fragt aktuellen Spieler ob er vor dem Ablegen der Karte seine eigene Hand auf der Konsole sehen möchte
    public void abfrageKartenhandZeigen() {
        //diese Methode prüft zunächst ob der aktuelle Spieler ein Bot ist oder ein echter Spieler,
        //wenn echter Spieler, wird dieser gefragt, ob er seine Hand einsehen möchte
        //die Abfrage wird über Konsole mit y oder n getätigt, bei falscher Eingabe gibt es eine Exception
        if (aktuellerSpieler instanceof EchteSpieler) {
            System.out.println("Möchten Sie Ihre Hand angezeigt bekommen?");
            System.out.println("Bitte Y (YES) oder N (No) eingeben");
            String c;
            while (input.hasNext()) {
                c = input.nextLine();
                if (c.toLowerCase().equals("y")) {
                    aktuellerSpieler.printSpielerHand();
                    return;
                } else if (c.equalsIgnoreCase("n")) {
                    return;
                } else {
                    System.out.println("Falsche Eingabe!");
                }
            }
        }
    }

    // funktioniert nicht:
//            System.out.println("Möchten Sie Ihre Hand angezeigt bekommen?");
//            System.out.println("Bitte Y (YES) oder N (No) eingeben");
//            try {
//                Scanner scanner = new Scanner(System.in);
//                String c = scanner.nextLine();
//                if (c.equalsIgnoreCase("y")) {
//                    aktuellerSpieler.printSpielerHand();
//                    return;
//                }
//                if (c.equalsIgnoreCase("n")) {
//                    return;
//                }
//                while (!c.equalsIgnoreCase("y") || !c.equalsIgnoreCase("n")) {
//                    System.out.println("Falsche Eingabe!");
//                    throw new FalscheEingabeException("Falsche Eingabe");
//                }
//            } catch (FalscheEingabeException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void printSpielerHand() {
        int index = 0;
        for (Karte k : aktuellerSpieler.spielerHand) {
            System.out.println("  " + (index++) + k);
        }
    }

    public void WerIstDranUndWelcheKarte() {    //todo: wird zu wer ist dran
        System.out.println();
        System.out.println("-----");
        System.out.println(aktuellerSpieler.getName() + "  ist an der Reihe!");
        abfrageKartenhandZeigen();      //todo: die Methoden direkt in der App aufrufen
        kartenstapel.AusgabeObersteKarteAblagestapel();
        // Nur zum TESTEN
        // aktuellerSpieler.printSpielerHand();
    }

    private boolean isValid(String s) {
        if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n")) {
            System.out.println("Falsche Eingabe!)");
            return false;
        } else return true;
    }

    public void neueKarteHeben() {
        String c;
        output.println("Möchten Sie eine neue Karte abheben? Bitte Y (YES) oder N (NO) eingeben");
        c = input.nextLine();
        if (c.toLowerCase().equals("y")) {
            Karte neu = kartenstapel.abheben();
            System.out.println(neu.toString());
        } else if (c.equalsIgnoreCase("n")) {
                System.out.println("Bitte legen Sie eine passende Karte ab");
                abfrageKartenhandZeigen();
                karteAblegen();
        } else {
            output.println("Falsche Eingabe");
        }
    }


    public void falscheKarte() {
        boolean quit = false;
        String c;
        System.out.println("Falsche Karte gelegt. Bitte legen Sie eine passende Karte ab");
        System.out.println("Es liegt die " + kartenstapel.obersteKarte() + " oben auf!");
        System.out.println("Möchten Sie eine neue Karte abheben? Bitte Y (YES) oder N (NO) eingeben");

          while(input.hasNext()) {
            c = input.nextLine();
            if (c.toLowerCase().equals("y")) {
                Karte neu = kartenstapel.abheben();
                System.out.println(neu.toString());
                while(input.hasNext()) {
                    System.out.println("Möchten Sie die neue Karte spielen? Bitte Y (YES) oder N (NO) eingeben");
                    c = input.nextLine();
                    if (c.toLowerCase().equals("y")) {
                        if (passendeKarte(neu, kartenstapel.obersteKarte())) {
                            System.out.println("Spielzug korrekt. Diese Karte wurde abgelegt:");
                            kartenstapel.karteAblegen(neu);
                            break;
                        }
                    } else if (c.toLowerCase().equals("n")) {
                        System.out.println("Sie müssen die gespielte Karte behalten!");
                        aktuellerSpieler.spielerHand.add(neu);
                        break;
                    } else {
                        System.out.println("Falsche Eingabe!");
                    }
                } break;
            } else if (c.equalsIgnoreCase("n")) {
                System.out.println("Bitte legen Sie eine passende Karte ab");
                abfrageKartenhandZeigen();
                karteAblegen();
                break;
            } else {
                System.out.println("Falsche Eingabe!");
            }
        }

    }



//        do {
//            c = input.nextLine();
//            if (c.toLowerCase().equals("y")) {
//                Karte neu = kartenstapel.abheben();
//                System.out.println(neu.toString());
//                do {
//                    System.out.println("Möchten Sie die neue Karte spielen? Bitte Y (YES) oder N (NO) eingeben");
//                    c = input.nextLine();
//                    if (c.toLowerCase().equals("y")) {
//                        if (passendeKarte(neu, kartenstapel.obersteKarte())) {
//                            System.out.println("Spielzug korrekt. Diese Karte wurde abgelegt:");
//                            kartenstapel.karteAblegen(neu);
//                            break;
//                        }
//                    } else if (c.toLowerCase().equals("n")) {
//                        System.out.println("Sie müssen die gespielte Karte behalten!");
//                        aktuellerSpieler.spielerHand.add(neu);
//                        break;
//                    } else {
//                        System.out.println("Falsche Eingabe!");
//                    }
//                } while (true);
//                break;
//            } else if (c.equalsIgnoreCase("n")) {
//                System.out.println("Bitte legen Sie eine passende Karte ab");
//                abfrageKartenhandZeigen();
//                karteAblegen();
//                break;
//            } else {
//                System.out.println("Falsche Eingabe!");
//            }
//        } while (true);
//
//    }


    public void karteAblegen() {
        //per Eingabe Karte spielen
        //check if chosen card matches one available in the Kartenhand-array
        if (aktuellerSpieler instanceof EchteSpieler) {
            if (input.hasNextInt()) {
                int position = input.nextInt();
                Karte handKarte = aktuellerSpieler.spielerHand.get(position);
                if (!passendeKarte(handKarte, kartenstapel.obersteKarte())) {
                    falscheKarte();
                } else {
                    System.out.println("Spielzug korrekt. Diese Karte wurde abgelegt:");
                    System.out.println(aktuellerSpieler.spielerHand.get(position));
                    kartenstapel.karteAblegen(aktuellerSpieler.spielerHand.get(position));
                    aktuellerSpieler.spielerHand.remove(position);
                }
            } else {
                System.out.println("Bitte geben Sie die entsprechende Zahl für die gewünschte Karte ein");
            }

        } else {
            boolean karteGespielt = false;
            int i = 0;
            System.out.println(aktuellerSpieler.getSpielerHand());
            while (i < aktuellerSpieler.getSpielerHand().size()) {
                Karte karte = aktuellerSpieler.spielerHand.get(i);
                if (kartenstapel.obersteKarte().getFarbe() == (SCHWARZ)
                        || karte.getFarbe() == (SCHWARZ)
                        || kartenstapel.obersteKarte().getFarbe() == (karte.getFarbe())
                        || kartenstapel.obersteKarte().getWert() == (karte.getWert())) {
                    kartenstapel.karteAblegen(karte);
                    aktuellerSpieler.spielerHand.remove(aktuellerSpieler.spielerHand.get(i));
                    System.out.println("Karte abgelegt: " + kartenstapel.obersteKarte());
                    if(aktuellerSpieler.spielerHand.size() < 2){
                        botUno();
                    }
                    i = aktuellerSpieler.getSpielerHand().size() + 1;
                    karteGespielt = true;
//                        else if(kartenstapel.obersteKarte().getFarbe().equals(SCHWARZ)){
//                            System.out.println("Bitte Farbe und falls + 4 heben, bitte Eingabe mit 4 beginnen \n" +
//                                    "(Beispiel: 4Blau)");
//                            if(input.nextLine().equals("4Blau")
                } else {
//                    System.out.println("Karte nicht gefunden");
//                    System.out.println(kartenstapel.obersteKarte() + " " + aktuellerSpieler.spielerHand.get(i));
                    i++;


                }
            }
            if (!karteGespielt) {
//                System.out.println(kartenstapel.obersteKarte());
//                System.out.println(aktuellerSpieler.getSpielerHand());
                System.out.println("Keine passende Karte - " + aktuellerSpieler.getName() + " muss abheben");
                Karte neu = kartenstapel.abheben();
//                System.out.println(neu);
                if (neu.getFarbe().equals(SCHWARZ) || neu.getFarbe().equals(kartenstapel.obersteKarte().getFarbe()) ||
                        neu.getWert() == (kartenstapel.obersteKarte().getWert())) {
                    kartenstapel.karteAblegen(neu);
                    System.out.println(neu + " wurde soeben abgelegt!");
                    if(aktuellerSpieler.spielerHand.size() == 2){
                        botUno();
                    }

                } else {
                    aktuellerSpieler.spielerHand.add(neu);
                    System.out.println("Spieler bekommt neue karte: " + neu);

                }
            }
        }
    }

    public void botUno(){
        output.println("**********UNO!**********");
        uno = true;
    }


    private boolean passendeKarte(Karte handKarte, Karte ablageStapel) {
        boolean ausgabe;
        if (ablageStapel.getFarbe().equals(SCHWARZ)) {
            ausgabe = true;
        } else if (handKarte.getFarbe().equals(ablageStapel.getFarbe()) || handKarte.getWert() == ablageStapel.getWert()
                || handKarte.getFarbe().equals(SCHWARZ)) {
            ausgabe = true;
        } else ausgabe = false;

        return ausgabe;
        // TODO: 06.07.2020 aktionskarten ?
    }

    public Spieler spielerWechsel() {
        int i = 0;

        // Spielerwechsel nach Beendigung des Spielzuges
        //Richtungswechsel wenn auf Stapel "Richtungswechsel" liegt
        //todo: Aussetzenkarte ist Spieler überspringen
        if (!aktuellerSpieler.getSpielerHand().isEmpty()) {

            if (kartenstapel.obersteKarte().getWert().equals(Wert.AUSSETZEN)) {
                System.out.println("Spieler nach " + aktuellerSpieler.getName() + " muss aussetzen!");
                i = 1;
            }

            if (kartenstapel.obersteKarte().getWert().equals(Wert.RICHTUNGSWECHSEL)) {
                spielrichtung = !spielrichtung;
            }
            if (spielrichtung) {
                switch (alleSpieler.indexOf(aktuellerSpieler)) {
                    case 0:
                        aktuellerSpieler = alleSpieler.get(1 + i);
                        break;
                    case 1:
                        aktuellerSpieler = alleSpieler.get(2 + i);
                        break;
                    case 2:
                        aktuellerSpieler = alleSpieler.get(3 - (3 * i));
                        break;
                    case 3:
                        aktuellerSpieler = alleSpieler.get(0 + i);
                        break;
                }
            }
            if (!spielrichtung) {
                switch (alleSpieler.indexOf(aktuellerSpieler)) {
                    case 0:
                        aktuellerSpieler = alleSpieler.get(3 - i);
                        break;
                    case 1:
                        aktuellerSpieler = alleSpieler.get(0 + (3 * i));
                        break;
                    case 2:
                        aktuellerSpieler = alleSpieler.get(1 - i);
                        break;
                    case 3:
                        aktuellerSpieler = alleSpieler.get(2 - i);
                        break;
                }

            }
        }
        i = 0;
        return aktuellerSpieler;
    }

//    public void endeSpielzug(){
//            if((kartenstapel.obersteKarte().getFarbe() == (SCHWARZ)
//        System.out.println("Ist Ihr Spielzug beendet?");
//        try {
//            String s = input.nextLine();
//           if(s.equalsIgnoreCase("y")) {
//                return;
//            }
//            if (s.equalsIgnoreCase("n")) {
//                System.out.println("Bitte \"Uno\" oder \"Farbe + (blau,gelb,grün,rot)\" eingeben:");
//                String next = input.nextLine();
//                try {
//                    if(next.equalsIgnoreCase("UNO")) {
//                        aktuellerSpieler.setUno(true);
//                    } else if(next.startsWith("Farbe")){
//                        kartenstapel.se
//                    }
//            while (!input.equalsIgnoreCase("y") || !input.equalsIgnoreCase("n")) {
//                System.out.println("Falsche Eingabe!");
//                throw new FalscheEingabeException("Falsche Eingabe");
//            }
//        } catch (FalscheEingabeException e) {
//            abfrageKartenhandZeigen();
//            //e.printStackTrace();
//        }
//    }
//}
//    }

    public int getPunkteVonAllenSpielern() {
        int punkteAlleSpieler = 0;
        for (Spieler s : alleSpieler) {
            punkteAlleSpieler += s.getPunkteVonSpielerHand();
        }
        return punkteAlleSpieler;
    }

    @Override
    public String toString() {
        return "SpielerManager{" +
                "alleSpieler=" + alleSpieler +
                '}';
    }

}
