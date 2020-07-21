package at.campus02.nowa.uno.karte;

public class Zahlenkarte extends Karte{
    private int punkte;

    public Zahlenkarte(Farbe farbe, Wert wert) {
        super(farbe, wert);
        this.punkte = getPunkte();
    }



    public int getPunkte() {
        if (getWert().equals(Wert.PLUSZWEI)){
            punkte += 20;
        }
        if (getWert().equals(Wert.RICHTUNGSWECHSEL)){
            punkte += 20;
        }
        if (getWert().equals(Wert.AUSSETZEN)){
            punkte += 20;
        }
        if (getWert().equals(Wert.FARBWAHL)){
            punkte += 50;
        }
        if (getWert().equals(Wert.PLUSVIER)){
            punkte += 50;
        }
        if (getWert().equals(Wert.NULL)){
            punkte += 0;
        }
        if (getWert().equals(Wert.EINS)){
            punkte += 1;
        }
        if (getWert().equals(Wert.ZWEI)){
            punkte += 2;
        }
        if (getWert().equals(Wert.DREI)){
            punkte += 3;
        }
        if (getWert().equals(Wert.VIER)){
            punkte += 4;
        }
        if (getWert().equals(Wert.FUENF)){
            punkte += 5;
        }
        if (getWert().equals(Wert.SECHS)){
            punkte += 6;
        }
        if (getWert().equals(Wert.SIEBEN)){
            punkte += 7;
        }
        if (getWert().equals(Wert.ACHT)){
            punkte += 8;
        }
        if (getWert().equals(Wert.NEUN)){
            punkte += 9;
        }
        return punkte;
    }
}

//Zahlenkarten sind alle Zahlen mit Wert 0-9 und Farbe rot, gelb, blau, grün

