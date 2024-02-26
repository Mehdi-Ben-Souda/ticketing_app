package ma.fstm.ilisi.busway.metier.bo;
import java.time.LocalTime;
import java.util.*;

/**
 * 
 */
public class Arrivée{

    private LocalTime heureArrivée;
    private Station station;
    private Voyage voyage;
    public Arrivée(String heureArrivée,Station station,Voyage voyage) {
        this.heureArrivée = LocalTime.parse(heureArrivée);
        this.station = station;
        this.voyage = voyage;
    }

    public Arrivée(LocalTime heureArrivée,Station station) {
        this.heureArrivée = heureArrivée;
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