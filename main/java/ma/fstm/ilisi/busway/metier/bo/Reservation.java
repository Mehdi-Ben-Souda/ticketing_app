package ma.fstm.ilisi.busway.metier.bo;
import java.time.LocalTime;
import java.util.*;

/**
 * 
 */
public class Reservation {

    private LocalTime heureReservation;
    private Station stationDepart;
    // il faut ajouter le passaaaggeeeeeeer

    public Reservation() {
    }

    public Reservation(Station stationDepart, LocalTime heureReservation) {
        this.stationDepart = stationDepart;
        this.heureReservation = heureReservation;
    }

    public Station getStationDepart() {
        return stationDepart;
    }



    public LocalTime getHeureReservation() {
        return heureReservation;
    }

    public String toString()
    {
        return "Reservation{" +
                "heureReservation=" + heureReservation +
                ", stationDepart=" + stationDepart +
                '}';
    }
}