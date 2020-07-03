package at.campus02.nowa.uno;

import java.util.ArrayList;
import java.util.Collections;

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

    // zufälligen Startspieler festlegen:
    public void startSpieler () {
        System.out.print("Es beginnt: ");
        Collections.shuffle(alleSpieler);

        System.out.println(alleSpieler.get(0).getName());
        printAlleSpielerNamen();
    }

        /*
        int random = 0;
        do {
            random= (int) (Math.random() * 10);
        } while (random>3);
        System.out.println(random);
        System.out.println(alleSpieler.get(random).getName());
        }


         */

    @Override
    public String toString() {
        return "SpielerManager{" +
                "alleSpieler=" + alleSpieler +
                '}';
    }
}
