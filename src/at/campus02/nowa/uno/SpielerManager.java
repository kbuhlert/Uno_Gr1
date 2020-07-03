package at.campus02.nowa.uno;

import java.util.ArrayList;

public class SpielerManager {
    protected ArrayList<EchteSpieler> alleSpieler;

    public SpielerManager() {
        alleSpieler = new ArrayList<EchteSpieler>();
    }

    public void addEchteSpieler (EchteSpieler e) {
        alleSpieler.add(e);
    }

    public void printAlleSpielerNamen() {
        System.out.print("Im Spiel sind: ");
        for (EchteSpieler spieler : alleSpieler) {
            System.out.print(spieler.getName() + ", ");
        }
        System.out.println();
        System.out.println("May the odds be ever in your favour");
    }

    @Override
    public String toString() {
        return "SpielerManager{" +
                "alleSpieler=" + alleSpieler +
                '}';
    }
}
