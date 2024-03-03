package ma.fstm.ilisi.busway.metier.bo;
import java.util.*;

public class Voyage {
    private int idVoyage;
    private Date dateVoyage;
    private float prix;
    private String numeroLigne;
    private Bus bus;
    private Depart depart;
    private Arrivée arrivée;
    private ArrayList<Arret> arrets;
    private ArrayList<Reservation> reservations;
    public Voyage(int idVoyage, Date dateVoyage, float prix, String numeroLigne, Depart depart, Arrivée arrivée, ArrayList<Arret> arrets, Bus bus) {
        this.idVoyage = idVoyage;
        this.dateVoyage = dateVoyage;
        this.prix = prix;
        this.numeroLigne = numeroLigne;
        this.bus = bus;
        this.depart = depart;
        this.arrivée = arrivée;
        this.arrets = arrets;
        reservations = new ArrayList<Reservation>();
    }

    public void ajouterReservation(Reservation reservation)
    {
        reservations.add(reservation);
    }

    public int getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(int idVoyage) {
        this.idVoyage = idVoyage;
    }

    public Date getDateVoyage() {
        return dateVoyage;
    }

    public void setDateVoyage(Date dateVoyage) {
        this.dateVoyage = dateVoyage;
    }

    public ArrayList<Arret> getArrets() {
        return arrets;
    }

    public Depart getDepart() {
        return depart;
    }

    public Arrivée getArrivée() {
        return arrivée;
    }

    public boolean passeParStation(Station station) {
        // Vérifie si la station de départ correspond
        if (depart.getStation().equals(station)) {
            return true;
        }

        // Vérifie si la station d'arrivée correspond
        if (arrivée.getStation().equals(station)) {
            return true;
        }
        // Vérifie si une des stations d'arrêt correspond
        for (Arret arret : arrets) {
            if (arret.getStation().equals(station)) {
                return true;
            }
        }
        // Aucune correspondance trouvée, le voyage ne passe pas par la station
        return false;
    }


    public Bus getBus() {
        return bus;
    }


    @Override
    public String toString() {
        return "Voyage{" +
                "idVoyage=" + idVoyage +
                ", dateVoyage=" + dateVoyage +
                ", prix=" + prix +
                ", numeroLigne='" + numeroLigne +
                ", bus=" + bus +
                '}';
    }

    public boolean verifierDisponibilite(Station stationD, Station stationA)
    {
        int cpt=0 ;
        int indexStationDepartReservation = -1;
        int indexStationArriveeReservation = -1;

        int indexStationArrivee=getindexStation(stationA);
        int indexStationDepart=getindexStation(stationD);
        if(indexStationDepart>=indexStationArrivee)
        {
            return false;
        }

        for (Reservation reservation : reservations) {

            indexStationArriveeReservation=getindexStation(reservation.getStationArrivee());

            indexStationDepartReservation=getindexStation(reservation.getStationDepart());


                //Si la station de départ de la réservation est avant la station de départ du segment desiré et
                // la station d'arrivée de la réservation est après la station de départ du segment désiré

            if(indexStationArriveeReservation<=indexStationDepart)
            {
                cpt++;
                continue;
            }
            if(indexStationDepartReservation>=indexStationArrivee) {
                cpt++;
                continue;
            }
        }
        return bus.getCapacite() > (reservations.size()-cpt) ;
    }

    public int getindexStation(Station station)
    {
        if(station.equals(depart.getStation()))
        {
            return -1;
        }
        else
        {
            if(station.equals(arrivée.getStation()))
            {
                return arrets.size()+1;
            }

            return arrets.indexOf(station);
        }
    }

}