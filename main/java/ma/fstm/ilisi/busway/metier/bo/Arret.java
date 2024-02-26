package ma.fstm.ilisi.busway.metier.bo;
import java.util.*;
import java.time.*;

/**
 * 
 */
public class Arret{
    private LocalTime heureArret;
    private Station station;
    private Voyage voyage;
    public Arret(String heureArret,Station station,Voyage voyage) {
        this.heureArret = LocalTime.parse(heureArret);
        this.station = station;
        this.voyage = voyage;
    }

    public Arret(LocalTime heureArret,Station station) {
        this.heureArret = heureArret;
        this.station = station;
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
}