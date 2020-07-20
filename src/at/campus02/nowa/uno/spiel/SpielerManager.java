package at.campus02.nowa.uno.spiel;

import at.campus02.nowa.uno.*;

import at.campus02.nowa.uno.karte.*;

import at.campus02.nowa.uno.kartenstapel.TeststapelWunschkarte;
import at.campus02.nowa.uno.spieler.BotSpieler;
import at.campus02.nowa.uno.spieler.EchteSpieler;
import at.campus02.nowa.uno.spieler.Spieler;

import java.util.ArrayList;
import java.util.Collections;

import static at.campus02.nowa.uno.karte.Farbe.SCHWARZ;

import java.util.Scanner;

public class SpielerManager {
        Scanner input = new Scanner(System.in);
        //  Spieler in  Liste
        protected ArrayList<Spieler> alleSpieler;
        Kartenmanager kartenstapel;
//        TeststapelWunschkarte kartenstapel;  //--> zum Testen mit speziellen Karten


        Spieler aktuellerSpieler = null;
        boolean spielrichtung = true;
        boolean aussetzten = false;

        //private PrintStream output;
        //private final Scanner input;


        public SpielerManager() {   //todo: Ablagestapel, Verteilstapel, Scanner, alleSpieler-Array werden dem SpielerManager als Parameter übergeben.
            // todo: So können diese von App erstellt werden un Spielermanager nutzt dann die gleichen Objekte
           this.kartenstapel = new Kartenmanager();
//            this.kartenstapel = new TeststapelWunschkarte();
            this.alleSpieler = new ArrayList<>();

            //this.input = input;
            //this.output = output;
        }

    public void spielerZuweisen() {
        Spieler spieler;

        int anzBot = 1;
//        input = new Scanner(System.in);
        while(alleSpieler.size() < 4) {
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
  //      kartenstapel.neuerTeststapel(new Zahlenkarte(Farbe.BLAU,Wert.ACHT), new Zahlenkarte(Farbe.BLAU,Wert.RICHTUNGSWECHSEL));  //--> Wenn mit Teststapel gespielt wird
//        kartenstapel.neuerTeststapel(new Zahlenkarte(SCHWARZ,Wert.PLUSVIER), new Zahlenkarte(Farbe.BLAU,Wert.RICHTUNGSWECHSEL));  //--> Wenn mit Teststapel gespielt wird
        System.out.println("Karten werden ausgeteilt");     //Karten austeilen -->7 Karten pro Spieler
        for (Spieler spieler : alleSpieler) {
            while (spieler.spielerHand.size() < 7) {
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
        while (kartenstapel.obersteKarte().getFarbe() == Farbe.SCHWARZ && kartenstapel.obersteKarte().getWert() == Wert.PLUSVIER) {
            System.out.println("Es liegt eine +4 auf, nochmal mischen, eine neue Karte wird aufgelegt");
            kartenstapel.karteAblegen(kartenstapel.obersteKarte());
            System.out.println(kartenstapel.obersteKarte());
        }
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
        kartenstapel.AusgabeObersteKarteAblagestapel();
    }



    public void karteAblegen() {
        //per Eingabe Karte spielen
        //check if chosen card matches one available in the Kartenhand-array

        if (aktuellerSpieler instanceof EchteSpieler) {

                int position = input.nextInt();
            Karte handKarte = aktuellerSpieler.spielerHand.get(position);

            if (!passendeKarte(handKarte, kartenstapel.obersteKarte())) {
                System.out.println("Falsche Karte gelegt. Bitte legen Sie eine passende Karte ab");
                System.out.println("Es liegt die " + kartenstapel.obersteKarte() + " oben auf!");
                System.out.println("Möchten Sie eine neue Karte abheben? Bitte Y (YES) oder N (NO) eingeben");
                if(input.nextLine().toLowerCase().equals("y")){
                    Karte karte = kartenstapel.abheben();
                    System.out.println(karte);
                    System.out.println("Möchten Sie die neue Karte spielen? Bitte Y (YES) oder N (NO) eingeben");
                    if(input.nextLine().toLowerCase().equals("y")){
                        karteAblegen();

                    } else if(input.nextLine().toLowerCase().equals("n")){
                        aktuellerSpieler.spielerHand.add(karte);
                    }
                } else if (input.nextLine().toLowerCase().equals("n")){
                    System.out.println("Bitte legen Sie eine passende Karte ab");
                    karteAblegen();
                }
                // TODO: 19.07.2020 abheben oä implementieren! falls keine passende karte
            } else {
                System.out.println("Spielzug korrekt. Diese Karte wurde abgelegt:");
                System.out.println(aktuellerSpieler.spielerHand.get(position));
                kartenstapel.karteAblegen(aktuellerSpieler.spielerHand.get(position));
                aktuellerSpieler.spielerHand.remove(position);
            }

        } else {
            boolean karteGespielt = false;
            int i = 0;
            while (i < aktuellerSpieler.getSpielerHand().size()) {
                Karte karte = aktuellerSpieler.spielerHand.get(i);
                if (kartenstapel.obersteKarte().getFarbe() == (SCHWARZ)
                        || karte.getFarbe() == (SCHWARZ)
                        || kartenstapel.obersteKarte().getFarbe() == (karte.getFarbe())
                        || kartenstapel.obersteKarte().getWert() == (karte.getWert())) {
                    kartenstapel.karteAblegen(karte);
                    aktuellerSpieler.spielerHand.remove(aktuellerSpieler.spielerHand.get(i));
                    System.out.println("Karte abgelegt: " + kartenstapel.obersteKarte());
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
                System.out.println(kartenstapel.obersteKarte());
                System.out.println(aktuellerSpieler.getSpielerHand());
                System.out.println("Keine passende Karte - " + aktuellerSpieler.getName() + " muss abheben");
                Karte neu = kartenstapel.abheben();
                System.out.println(neu);
                if (neu.getFarbe().equals(SCHWARZ) || neu.getFarbe().equals(kartenstapel.obersteKarte().getFarbe()) ||
                        neu.getWert() == (kartenstapel.obersteKarte().getWert())) {
                    kartenstapel.karteAblegen(neu);
                    System.out.println(neu + " wurde soeben abgelegt!");

                } else {
                    aktuellerSpieler.spielerHand.add(neu);
                    System.out.println("Spieler bekommt neue karte: " + neu);
                }
            }
        } spielerWechsel();
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
        if (kartenstapel.obersteKarte().getWert().equals(Wert.AUSSETZEN)){
                 i = 1;}

        if (kartenstapel.obersteKarte().getWert().equals(Wert.RICHTUNGSWECHSEL)) {
            spielrichtung = !spielrichtung;
        }
        if (spielrichtung) {
            switch (alleSpieler.indexOf(aktuellerSpieler)) {
                case 0:
                    aktuellerSpieler = alleSpieler.get(1+i);
                    break;
                case 1:
                    aktuellerSpieler = alleSpieler.get(2+i);
                    break;
                case 2:
                    aktuellerSpieler = alleSpieler.get(3-(3*i));
                    break;
                case 3:
                    aktuellerSpieler = alleSpieler.get(0+i);
                    break;
            }
        }
        if (!spielrichtung) {
            switch (alleSpieler.indexOf(aktuellerSpieler)) {
                case 0:
                    aktuellerSpieler = alleSpieler.get(3-i);
                    break;
                case 1:
                    aktuellerSpieler = alleSpieler.get(0+(3*i));
                    break;
                case 2:
                    aktuellerSpieler = alleSpieler.get(1-i);
                    break;
                case 3:
                    aktuellerSpieler = alleSpieler.get(2-i);
                    break;
            }
        }
        i = 0;
        return aktuellerSpieler;
    }


    @Override
    public String toString() {
        return "SpielerManager{" +
                "alleSpieler=" + alleSpieler +
                '}';
    }

}
