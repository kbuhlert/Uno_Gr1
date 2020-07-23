package at.campus02.nowa.uno.spieler;

import at.campus02.nowa.uno.karte.Karte;

import java.util.ArrayList;


public class BotSpieler extends Spieler {

    /**
     * Klasse Botspieler managed die Bots, die selbständig die Spielzüge ausführen
     *
     * @param name
     */

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

    public void printSpielerHand() {
        super.printSpielerHand();
    }

    public int getPunkteVonSpielerHand() {
        return super.getPunkteVonSpielerHand();

    }
}

