package at.campus02.nowa.uno.spieler;

import at.campus02.nowa.uno.karte.Karte;

import java.util.ArrayList;


public class BotSpieler extends Spieler {


    public BotSpieler(String name) {
        super(name);
    }


    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public ArrayList<Karte> getSpielerHand() {
        return super.getSpielerHand();
    }

    @Override
    public void spielen() {

    }

    @Override

    public void printSpielerHand() {
        super.printSpielerHand();
    }


    public int getPunkteVonSpielerHand() {
        return super.getPunkteVonSpielerHand();

    }

    //Methoden:
    //im Kontruktor wir botNr mit erstellt;

    //Botname muss automatisch erstellt werden (botNr + String) und es brauch einen getter für den Botnamen

    //erstelleBots(){} --> holt mit getAnzahlEchteSpieler() die Anzahl der Spieler und generiert dann die nötige Anzahl Bots um 4 Spieler zu haben (hier würde sich Sitch anbieten)

}

