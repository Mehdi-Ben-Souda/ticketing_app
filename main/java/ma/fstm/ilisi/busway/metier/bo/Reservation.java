package ma.fstm.ilisi.busway.metier.bo;
import java.time.LocalTime;
import java.util.*;

/**
 * 
 */
public class Reservation {

    private LocalTime heureReservation;
    private Station stationDepart;
    private Station stationArrivee;


    public Reservation() {
    }

    public Reservation(Station stationDepart, Station stationArrivee, LocalTime heureReservation) {
        this.stationDepart = stationDepart;
        this.stationArrivee = stationArrivee;
        this.heureReservation = heureReservation;
    }

    public Station getStationDepart() {
        return stationDepart;
    }

    public Station getStationArrivee() {
        return stationArrivee;
    }

    public LocalTime getHeureReservation() {
        return heureReservation;
    }


}