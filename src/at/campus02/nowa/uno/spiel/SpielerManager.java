package at.campus02.nowa.uno.spiel;

import at.campus02.nowa.uno.karte.*;

import at.campus02.nowa.uno.kartenstapel.TeststapelWunschkarte;
import at.campus02.nowa.uno.spieler.BotSpieler;
import at.campus02.nowa.uno.spieler.EchteSpieler;
import at.campus02.nowa.uno.spieler.Spieler;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;

import java.util.InputMismatchException;
import java.util.Scanner;

import static at.campus02.nowa.uno.karte.Farbe.*;

public class SpielerManager {
    private final Scanner input;
    private final PrintStream output;
    //  Spieler in  Liste
    protected ArrayList<Spieler> alleSpieler;

    Kartenmanager kartenstapel;
//        TeststapelWunschkarte kartenstapel;  //--> zum Testen mit speziellen Karten

    Spieler aktuellerSpieler = null;
    boolean spielrichtung = true;
    Karte farbWahl = new Zahlenkarte(BLAU, Wert.PLUSVIER);
    boolean letzte = false;
    boolean keineWeitereAblage = false;            //--> soll verhindern dass nach neu gehobener Karte nocheinmal abgelegt werden muss
    boolean quit = false;
    boolean out = false;
    boolean naechsterSpieler = false;
    boolean abgehoben = false;
    boolean ersteRunde = true;
    boolean aushelfen = false;

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
        //die Abfrage wird über Konsole mit y oder n getätigt, bei falscher Eingabe muss nochmal eingegeben werden
        if (aktuellerSpieler instanceof EchteSpieler) {
            System.out.println("Möchten Sie Ihre Hand angezeigt bekommen?");
            System.out.println("Bitte Y (YES) oder N (No) eingeben");
            String c;
            while (input.hasNext()) {
                c = input.nextLine();
                if (c.toLowerCase().equals("y")) {
                    aktuellerSpieler.printSpielerHand();
                    break;
                } else if (c.equalsIgnoreCase("n")) {
                    break;
                } else {
                    System.out.println("Falsche Eingabe!");
                }
            }
        }
    }

    public void plusZweiVier(){
        if (kartenstapel.obersteKarte().getWert().equals(Wert.PLUSZWEI)) {
            aktuellerSpieler.spielerHand.add(kartenstapel.abheben());
            aktuellerSpieler.spielerHand.add(kartenstapel.abheben());
            abgehoben = true;
            output.println(aktuellerSpieler.getName() + " hat soeben 2 Karten aufgenommen");
        } else if (kartenstapel.obersteKarte().getWert().equals(Wert.PLUSVIER)) {
            abgehoben = true;
            aktuellerSpieler.spielerHand.add(kartenstapel.abheben());
            aktuellerSpieler.spielerHand.add(kartenstapel.abheben());
            aktuellerSpieler.spielerHand.add(kartenstapel.abheben());
            aktuellerSpieler.spielerHand.add(kartenstapel.abheben());
            output.println(aktuellerSpieler.getName() + " hat soeben 4 Karten aufgenommen");
        } return;

    }

    public void ausgabeAktuellerSpieler() {    //todo: wird zu wer ist dran
        output.println();
        output.println("-----");
        output.println(aktuellerSpieler.getName() + "  ist an der Reihe!");
        if(!abgehoben && !ersteRunde){
            plusZweiVier();
        }
        kartenstapel.genugKartenAmStapel();
        kartenstapel.ausgabeObersteKarteAblagestapel();
        //todo: die Methoden direkt in der App aufrufen

    }

    public void kreativeLoesungUmInputZuLöschen(){
        if(input.hasNext())
            return;
        else return;
    }

    public void neueKarteHeben() {
        if (aktuellerSpieler instanceof EchteSpieler) {
            String c;
            output.println("Möchten Sie eine neue Karte abheben? Bitte Y (YES) oder N (NO) eingeben");
            if(aushelfen){
                kreativeLoesungUmInputZuLöschen();
            }
            while (!keineWeitereAblage || !quit) {
                c = input.nextLine();
                if (c.toLowerCase().equals("y")) {
                    Karte neu = kartenstapel.abheben();
                    System.out.println(neu.toString());
                    System.out.println("Möchten Sie die neue Karte spielen? Bitte Y (YES) oder N (NO) eingeben");
                    while (!quit) {
                        c = input.nextLine();
                        if (c.toLowerCase().equals("y")) {
                            if (!passendeKarte(neu, kartenstapel.obersteKarte())) {
                                keineWeitereAblage = true;
                                aktuellerSpieler.spielerHand.add(neu);
                                falscheKarte();
                                break;
                            } else {
                                output.println("Spielzug korrekt. Diese Karte wurde abgelegt:");
                                // sollte aussetzen/richtungswechsel beeinflussen
                                naechsterSpieler = false;
                                keineWeitereAblage = true;
                                quit = true;
                                abgehoben = false;
                                // Farbwahl Karte vom Stapel entfernen
                                kartenstapel.farbwahlKarteWeg();
                                output.println(neu.toString());
                                kartenstapel.karteAblegen(neu);
                                output.println("Möchten Sie den Spielzug beenden?  Bitte Y (YES) oder N (NO) eingeben");
                                return;
                            }
                        } else if (c.toLowerCase().equals("n")) {
                            System.out.println("Sie möchten die gehobene Karte behalten!");
                            aktuellerSpieler.spielerHand.add(neu);
                            keineWeitereAblage = true;
                            quit = true;
                            output.println("Möchten Sie den Spielzug beenden?  Bitte Y (YES) oder N (NO) eingeben");
                            return;
                        } else {
                            output.println("Bitte Y (YES) oder N (NO) eingeben");
                        } return;
                    }
                } else if (c.equalsIgnoreCase("n")) {
                    kartenstapel.ausgabeObersteKarteAblagestapel();
                    karteAblegen();
                    break;
                } else {
                    output.println("Bitte Y (YES) oder N (NO) eingeben");
                }
            }
        }
    }


    public void falscheKarte() {
        if (keineWeitereAblage) {
            quit = true;
            output.println("Diese Karte passt nicht auf die Aufliegende, Sie müssen die Karte behalten.");
            output.println("Möchten Sie den Spielzug beenden?  Bitte Y (YES) oder N (NO) eingeben");
            return;
        } else {
            output.println("Falsche Karte gelegt. Bitte legen Sie eine passende Karte ab");
            kartenstapel.ausgabeObersteKarteAblagestapel();
            kreativeLoesungUmInputZuLöschen();
            aushelfen = true;
            neueKarteHeben();
            return;
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
//                 } while  --> pölzl
//                } while ((c.toLowerCase().equals("y") && (c.toLowerCase().equals("n"));
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

        // wenn neue Karte gehoben wurde, soll diese Methode abgebrochen werden

        if (aktuellerSpieler instanceof EchteSpieler) {
            // Falls neue karte gehoben wird und in diese Methode gesprungen --> kartegehoben = false damit sie nicht aufgerufen wird
            if (!keineWeitereAblage) {
                try {
                    kreativeLoesungUmInputZuLöschen();
                    while (input.hasNext() && !out) {
                        kreativeLoesungUmInputZuLöschen();
                        int position = input.nextInt();
                        if (position > aktuellerSpieler.spielerHand.size()) {
                            int anzahlKarten = aktuellerSpieler.spielerHand.size() - 1;
                            output.println("Bitte eine Zahl bis höchstens " + anzahlKarten + " eingeben!");
                            input.next();
                        } else {
                            Karte handKarte = aktuellerSpieler.spielerHand.get(position);
                            if (!passendeKarte(handKarte, kartenstapel.obersteKarte())) {
                                falscheKarte();
                                return;
                            } else {
                                System.out.println("Spielzug korrekt. Diese Karte wurde abgelegt:");
                                // sollte aussetzen/richtungswechsel kontrollieren
                                abgehoben = false;
                                naechsterSpieler = false;
                                // Farbwahl Karte vom Stapel entfernen
                                kartenstapel.farbwahlKarteWeg();
                                System.out.println(aktuellerSpieler.spielerHand.get(position));
                                kartenstapel.karteAblegen(aktuellerSpieler.spielerHand.get(position));
                                aktuellerSpieler.spielerHand.remove(position);
                                // raus aus der schleife
                                out = true;
                                output.println("Möchten Sie den Spielzug beenden? Bitte Y (YES) oder N (NO) eingeben");
                                return;
                            }
                        }
                    }
                } catch (InputMismatchException e) {
                    int anzahlKarten = aktuellerSpieler.spielerHand.size() - 1;
                    kreativeLoesungUmInputZuLöschen();
                    output.println("Bitte eine Zahl bis höchstens " + anzahlKarten + " eingeben!" +
                            "--> ELSE IF IM ablegen");
                    input.next();
                }


            }


//
//
//        if (aktuellerSpieler instanceof EchteSpieler) {
//            // Falls neue karte gehoben wird und in diese Methode gesprungen --> kartegehoben = false damit sie nicht aufgerufen wird
//            if (!keineWeitereAblage) {
//                try {
//                    kreativeLoesungUmInputZuLöschen();
//                    while (input.hasNext() && !out) {
//                        kreativeLoesungUmInputZuLöschen();
//                        int position = input.nextInt();
//                        if (position > aktuellerSpieler.spielerHand.size()) {
//                            int anzahlKarten = aktuellerSpieler.spielerHand.size() - 1;
//                            output.println("Bitte eine Zahl bis höchstens " + anzahlKarten + " eingeben!");
//                            input.next();
//                        } else {
//                            Karte handKarte = aktuellerSpieler.spielerHand.get(position);
//                            if (!passendeKarte(handKarte, kartenstapel.obersteKarte())) {
//                                falscheKarte();
//                            } else {
//                                System.out.println("Spielzug korrekt. Diese Karte wurde abgelegt:");
//                                // sollte aussetzen/richtungswechsel kontrollieren
//                                abgehoben = false;
//                                naechsterSpieler = false;
//                                System.out.println(aktuellerSpieler.spielerHand.get(position));
//                                kartenstapel.karteAblegen(aktuellerSpieler.spielerHand.get(position));
//                                aktuellerSpieler.spielerHand.remove(position);
//                                // raus aus der schleife
//                                out = true;
//                                output.println("Möchten Sie den Spielzug beenden? Bitte Y (YES) oder N (NO) eingeben");
//                                return;
//                            }
//                        }
//                    }
//                } catch (InputMismatchException e) {
//                    int anzahlKarten = aktuellerSpieler.spielerHand.size() - 1;
//                    kreativeLoesungUmInputZuLöschen();
//                    output.println("Bitte eine Zahl bis höchstens " + anzahlKarten + " eingeben!" +
//                            "--> ELSE IF IM ablegen");
//                }
//
//
//            }


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
                    if (aktuellerSpieler.spielerHand.size() == 1) {
                        botUno();
                    }
                    i = aktuellerSpieler.getSpielerHand().size() + 1;

                    karteGespielt = true;
//                        else if(kartenstapel.obersteKarte().getFarbe().equals(SCHWARZ)){
//                            System.out.println("Bitte Farbe und falls + 4 heben, bitte Eingabe mit 4 beginnen \n" +
//                                    "(Beispiel: 4Blau)");
//                            if(input.nextLine().equals("4Blau")
                } else {

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
                    if (aktuellerSpieler.spielerHand.size() == 1) {
                        botUno();
                    }

                } else {
                    aktuellerSpieler.spielerHand.add(neu);
                    System.out.println("Spieler bekommt neue karte: " + neu);

                }
            }
        }
        keineKarten();
    }


    // Beendet rundenloop
    public void keineKarten() {
        if (aktuellerSpieler.getSpielerHand().isEmpty()) {
            letzte = true;
        }
    }

    public void botUno() {
        output.println("**********UNO!**********");
    }

    public boolean unoGerufen() {
        if (aktuellerSpieler.getSpielerHand().size() == 1) {
            return false;
        } else return true;
    }

    public void farbEingabe(Scanner input) {
        output.println("Bitte geben Sie \"Uno\" oder eine Farbwahl ein!");
        while (input.hasNext()) {
            String s = input.nextLine();
            if (s.equalsIgnoreCase("UNO")) {
                output.println(aktuellerSpieler.getName() + " hat \"Uno\" gerufen!");
                break;
            } else if (kartenstapel.obersteKarte().getFarbe().equals(SCHWARZ)){
                if (s.equalsIgnoreCase("BLAU")) {
                    farbWahl.setFarbe(BLAU);
                    kartenstapel.karteAblegen(farbWahl);
                    output.println("Sie haben sich die Farbe Blau gewünscht!");
                    break;
                } else if (s.equalsIgnoreCase("ROT")) {

                    farbWahl.setFarbe(ROT);
                    kartenstapel.karteAblegen(farbWahl);
                    output.println("Sie haben sich die Farbe Rot gewünscht!");
                    break;
                } else if (s.equalsIgnoreCase("GRUEN")) {

                    farbWahl.setFarbe(GRUEN);
                    kartenstapel.karteAblegen(farbWahl);
                    output.println("Sie haben sich die Farbe Grün gewünscht!");
                    break;
                } else if (s.equalsIgnoreCase("GELB")) {

                    farbWahl.setFarbe(GELB);
                    kartenstapel.karteAblegen(farbWahl);
                    output.println("Sie haben sich die Farbe Gelb gewünscht!");
                    break;
                } else {
                    falscheEingabe();
                    break;
                }
            }

        }

    }

    public void falscheEingabe() {
        output.println("Haben Sie falsch eingegeben und möchten den Spielzug beenden? Bitte Y (YES) eingeben: ");
        String s = input.nextLine();
        if (s.equalsIgnoreCase("y")) {
            output.println("Möchten Sie den Spielzug beenden?  Bitte Y (YES) oder N (NO) eingeben");
            spielzugBeendet();
        }
        return;
    }

    public void spielzugBeendet() {
        if (aktuellerSpieler instanceof EchteSpieler) {
            keineWeitereAblage = false;
            aushelfen = false;
            quit = false;
            out = false;
//            output.println("Möchten Sie den Spielzug beenden?  Bitte Y (YES) oder N (NO) eingeben");
            while (input.hasNext()) {
                String s = input.nextLine();
                if (s.equalsIgnoreCase("y")) {
                    if (!unoGerufen()) {
                        output.println(aktuellerSpieler.getName() + " hat vergessen \"Uno \" zu rufen, Sie bekommen eine weitere Karte!");
                        aktuellerSpieler.spielerHand.add(kartenstapel.abheben());
                    }
                    break;
                } else if (s.equalsIgnoreCase("n")) {
                    farbEingabe(input);
                    break;

                }
            }
        }
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
        ersteRunde = false;

        // Spielerwechsel nach Beendigung des Spielzuges
        //Richtungswechsel wenn auf Stapel "Richtungswechsel" liegt
        //todo: Aussetzenkarte ist Spieler überspringen

        // aussetzen oder richtungswechsel liegt oben auf - wenn true, wird das übersprungen
        if(!naechsterSpieler){
            if (kartenstapel.obersteKarte().getWert().equals(Wert.AUSSETZEN)) {
                System.out.println("Spieler nach " + aktuellerSpieler.getName() + " muss aussetzen!");
                i = 1;
                naechsterSpieler = true;
            }
            if (kartenstapel.obersteKarte().getWert().equals(Wert.RICHTUNGSWECHSEL)) {
                spielrichtung = !spielrichtung;
                naechsterSpieler = true;
            }
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

        i = 0;
        return aktuellerSpieler;
    }



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
