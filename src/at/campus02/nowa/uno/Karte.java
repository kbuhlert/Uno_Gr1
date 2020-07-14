package at.campus02.nowa.uno;

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

    public void setWert(Wert wert) {
        this.wert = wert;
    }

    //EqualsMethode wird für Vergleich der Karten benötigt


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Karte)) return false;
        Karte karte = (Karte) o;
        return getFarbe() == karte.getFarbe() ||
                getWert() == karte.getWert();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFarbe(), getWert());
    }

    @Override
    public String toString() {
        return "Karte{" +
                "farbe=" + farbe +
                ", wert=" + wert +
                '}';
    }
}
