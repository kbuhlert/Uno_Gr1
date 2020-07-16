package at.campus02.nowa.uno.spieler;

import at.campus02.nowa.uno.kartenstapel.Kartenstapel;
import at.campus02.nowa.uno.karte.Karte;

import java.util.ArrayList;

public class BotSpieler extends Spieler{


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
    public void karteSpielen(Karte karte) {
        for(Karte k : spielerHand){
            if(k.getWert().equals(karte.getWert()) || k.getFarbe().equals(karte.getFarbe())){

            }
        }

        if(aktuellerSpieler.spielerHand.contains(ablagestapel.obersteKarte())){
//                System.out.println("Karte gefunden!");

        for (int i = 0; i < aktuellerSpieler.spielerHand.size(); i++){
            k = aktuellerSpieler.spielerHand.get(i);
            System.out.println(k);
            if (k.equals(ablagestapel.obersteKarte())) {
                ablagestapel.add(k);
                System.out.println(k + " wird ausgespielt");
                System.out.println(k + " wurde soeben abgelegt!");
                aktuellerSpieler.spielerHand.remove(k);
                System.out.println(aktuellerSpieler.spielerHand);
                break; }

        }

    } else {
        System.out.println(aktuellerSpieler.getSpielerHand());
        System.out.println("Keine passende Karte - " + aktuellerSpieler.getName() + " muss abheben");
        Karte neu = verteilstapel.abheben();
        if(passendeKarte(neu, ablagestapel.obersteKarte())){
            ablagestapel.add(neu);
            System.out.println(neu + " wird ausgespielt");
            System.out.println(neu + " wurde soeben abgelegt!");
        } else {
            aktuellerSpieler.spielerHand.add(neu);
            System.out.println("Spieler bekommt neue karte: " + neu);}

    }

    //Methoden:
    //im Kontruktor wir botNr mit erstellt;

    //Botname muss automatisch erstellt werden (botNr + String) und es brauch einen getter für den Botnamen

    //erstelleBots(){} --> holt mit getAnzahlEchteSpieler() die Anzahl der Spieler und generiert dann die nötige Anzahl Bots um 4 Spieler zu haben (hier würde sich Sitch anbieten)

}
