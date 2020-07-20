package at.campus02.nowa.uno.karte;

public class Zahlenkarte extends Karte{
    private int punkte;

    public Zahlenkarte(Farbe farbe, Wert wert) {
        super(farbe, wert);
        this.punkte = getPunkte();
    }

    public int getPunkte() {
        if (getWert().equals("NULL")){
            punkte += 0;
        }
        if (getWert().equals("EINS")){
            punkte += 1;
        }
        if (getWert().equals("ZWEI")){
            punkte += 2;
        }
        if (getWert().equals("DREI")){
            punkte += 3;
        }
        if (getWert().equals("VIER")){
            punkte += 4;
        }
        if (getWert().equals("FUENF")){
            punkte += 5;
        }
        if (getWert().equals("SECHS")){
            punkte += 6;
        }
        if (getWert().equals("SIEBEN")){
            punkte += 7;
        }
        if (getWert().equals("ACHT")){
            punkte += 8;
        }
        if (getWert().equals("NEUN")){
            punkte += 9;
        }
        return punkte;
    }
}

//Zahlenkarten sind alle Zahlen mit Wert 0-9 und Farbe rot, gelb, blau, grün

