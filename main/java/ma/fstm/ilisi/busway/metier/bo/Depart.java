package ma.fstm.ilisi.busway.metier.bo;
import java.time.LocalTime;
import java.util.*;

/**
 * 
 */
public class Depart{

    private LocalTime heureDepart;
    private Station station;
    private Voyage voyage;
    public Depart(String heureDepart,Station station,Voyage voyage) {
        this.heureDepart = LocalTime.parse(heureDepart);
        this.station = station;
        this.voyage = voyage;
    }

    public Depart(LocalTime heureDepart,Station station) {
        this.heureDepart = heureDepart;
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