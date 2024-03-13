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

    private Voyage voyage;
    private Passager passager;

    public Reservation() {
    }

    public void setHeureReservation(LocalTime heureReservation) {
        this.heureReservation = heureReservation;
    }

    public void setStationDepart(Station stationDepart) {
        this.stationDepart = stationDepart;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    public Passager getPassager() {
        return passager;
    }

    public void setPassager(Passager passager) {
        this.passager = passager;
    }

    public Reservation(Station stationDepart, LocalTime heureReservation, Voyage voyage) {
        this.stationDepart = stationDepart;
        this.heureReservation = heureReservation;
        this.voyage=voyage;
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