package ma.fstm.ilisi.busway.metier.exceptions;

public class LigneIntrouvable extends Exception{

    @Override
    public String toString() {
        return "La station est introuvable !!! ";
    }
}
