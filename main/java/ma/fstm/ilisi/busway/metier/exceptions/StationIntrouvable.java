package ma.fstm.ilisi.busway.metier.exceptions;

public class StationIntrouvable extends Exception{
    private String laStation;
    public StationIntrouvable(String laStation){
        this.laStation = laStation;
    }
    @Override
    public String toString() {
        return "La station "+laStation+" n'existe pas";
    }
}
