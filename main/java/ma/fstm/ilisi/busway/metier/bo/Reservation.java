package ma.fstm.ilisi.busway.metier.bo;
import java.time.LocalTime;
import java.util.*;

/**
 * 
 */
public class Reservation {

    private Station stationDepart;
    private Station stationArrivee;
    private LocalTime heureReservation;

    public Reservation() {
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