package at.campus02.nowa.uno;

public abstract class Karte {
    private Farbe farbe;
    private Kartenwert wert;

    public Karte(Farbe farbe, Kartenwert wert) {
        this.farbe = farbe;
        this.wert = wert;
    }

    public Farbe getFarbe() {
        return farbe;
    }

    public void setFarbe(Farbe farbe) {
        this.farbe = farbe;
    }

    public Kartenwert getWert() {
        return wert;
    }

    public void setWert(Kartenwert wert) {
        this.wert = wert;
    }

    @Override
    public String toString() {
        return "Karte{" +
                "farbe=" + farbe +
                ", wert=" + wert +
                '}';
    }
}
