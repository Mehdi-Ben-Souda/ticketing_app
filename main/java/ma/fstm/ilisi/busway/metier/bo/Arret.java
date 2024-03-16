package ma.fstm.ilisi.busway.metier.bo;
import java.util.*;
import java.time.*;

/**
 * 
 */
public class Arret{
    private LocalTime heureArret;
    private Station station;
    private int nbDescendu;
    private boolean traversed;
    private Voyage voyage;
    public Arret(String heureArret,Station station,Voyage voyage) {
        this.heureArret = LocalTime.parse(heureArret);
        this.station = station;
        this.voyage = voyage;
        this.nbDescendu = 0;
        traversed = false;
    }
    public Arret(String heureArret,Station station) {
        this.heureArret = LocalTime.parse(heureArret);
        this.station = station;
        this.nbDescendu = 0;
        traversed = false;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    public Station getStation() {
        return station;
    }

    @Override
    public String toString() {
        return "Arret{" +
                "heureArret=" + heureArret +
                ", station=" + station +
                '}';
    }

    public int getNbDescendu() {
        return nbDescendu;
    }

    public void setNbDescendu(int nbDescendu) {
        this.nbDescendu = nbDescendu;
    }

    public boolean isTraversed() {
        return traversed;
    }

    public void setTraversed(boolean traversed) {
        this.traversed = traversed;
    }
}