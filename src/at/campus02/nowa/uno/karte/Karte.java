package at.campus02.nowa.uno.karte;

import java.util.Objects;

public abstract class Karte {
    private Farbe farbe;
    private Wert wert;

    public Karte(Farbe farbe, Wert wert) {
        this.farbe = farbe;
        this.wert = wert;
    }

    public Farbe getFarbe() {
        return farbe;
    }

    public void setFarbe(Farbe farbe) {
        this.farbe = farbe;
    }

    public Wert getWert() {
        return wert;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFarbe(), getWert());
    }

    @Override
    public String toString() {
        return "{"
                + farbe +
                " || " + wert +
                '}';
    }

    public abstract int getPunkte();
}
