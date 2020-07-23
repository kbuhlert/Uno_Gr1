package at.campus02.nowa.uno;

public class FalscheEingabeException extends Exception{

    /**Exception wird geworfen, wenn Spieler eine falsche Konsoleneingabe macht und kann dann eine Warnmessage ausgeben
     *
     * @param message
     */
    public FalscheEingabeException(String message) {
        super(message);
    }
}
