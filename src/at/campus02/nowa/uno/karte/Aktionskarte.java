package at.campus02.nowa.uno.karte;

public class Aktionskarte extends Karte{
    //Aktionskarten sind alle Karten die Aktion auslösen (abheben, richtungswechsel, aussetzen, farbwahl)

    //zusätzlich zur Farbe (abstrakte Klasse) hat Aktionskarte weitere Werte
    private Wert kartenwert;

    public Aktionskarte(Farbe farbe, Wert wert) {
        super(farbe, wert);
    }

    @Override
    public int getPunkte() {
        int punkte = 0;
        if (getWert().equals("PLUSZWEI")){
            punkte += 20;
        }
        if (getWert().equals("RICHTUNGSWECHSEL")){
            punkte += 20;
        }
        if (getWert().equals("AUSSETZEN")){
            punkte += 20;
        }
        if (getWert().equals("FARBWAHL")){
            punkte += 50;
        }
        if (getWert().equals("PLUSVIER")){
            punkte += 50;
        }
        return punkte;
    }

    //Methoden:
    //abheben();
    //richtungswechsel;
    //aussetzen();
    //farbeWaehlen();

}

/*
git config user.email „emailadresseMitDerManAufGithubRegistriertIst“

Git branch branchName

Git add .

Git commit -m „commitMessage“

Git push -u origin branchNameG
 */
