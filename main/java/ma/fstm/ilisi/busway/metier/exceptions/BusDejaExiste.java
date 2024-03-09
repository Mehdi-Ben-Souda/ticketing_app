package ma.fstm.ilisi.busway.metier.exceptions;

public class BusDejaExiste extends Exception{
    @Override
    public String toString() {
        return "Il existe déja un Bus sous le meme matricule";
    }
}
