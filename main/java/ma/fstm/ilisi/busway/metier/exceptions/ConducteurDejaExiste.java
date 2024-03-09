package ma.fstm.ilisi.busway.metier.exceptions;

public class ConducteurDejaExiste extends Exception{

    @Override
    public String toString() {
        super.toString();
        return "Il existe déja un Conducteur sous le meme matricule";
    }
}
