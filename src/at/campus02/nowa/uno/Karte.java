package at.campus02.nowa.uno;

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

    @Override
    public String toString() {
        return "Karte{" +
                "farbe=" + farbe +
                ", wert=" + wert +
                '}';
    }
}
