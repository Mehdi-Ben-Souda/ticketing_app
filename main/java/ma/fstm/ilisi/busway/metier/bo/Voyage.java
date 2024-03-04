package ma.fstm.ilisi.busway.metier.bo;
import java.time.LocalTime;
import java.util.*;

public class Voyage {
    private int idVoyage;
    private LocalTime heureDepart;
    private LocalTime heureArrivée;
    private float prix;
    private String numeroLigne;
    private Bus bus;
    private Station depart;
    private Station arrivée;
    private ArrayList<Arret> arrets;
    private ArrayList<Reservation> reservations;

    public Voyage(int idVoyage, String heureDepart,String heureArrivée, float prix, String numeroLigne, Station depart, Station arrivée, Bus bus) {
        this.idVoyage = idVoyage;
        this.prix = prix;
        this.numeroLigne = numeroLigne;
        this.bus = bus;
        this.depart = depart;
        this.arrivée = arrivée;
        this.arrets = new ArrayList<Arret>();
        this.heureDepart = LocalTime.parse(heureDepart);
        this.heureArrivée = LocalTime.parse(heureArrivée);
        this.reservations = new ArrayList<Reservation>();
    }
    public int getIdVoyage() {
        return idVoyage;
    }
    public void setIdVoyage(int idVoyage) {
        this.idVoyage = idVoyage;
    }
    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getNumeroLigne() {
        return numeroLigne;
    }

    public void setNumeroLigne(String numeroLigne) {
        this.numeroLigne = numeroLigne;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }
    public Bus getBus() {
        return bus;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public LocalTime getHeureArrivée() {
        return heureArrivée;
    }

    public void setHeureArrivée(LocalTime heureArrivée) {
        this.heureArrivée = heureArrivée;
    }

    public void setDepart(Station depart) {
        this.depart = depart;
    }
    public Station getDepart() {
        return depart;
    }

    public void setArrivée(Station arrivée) {
        this.arrivée = arrivée;
    }

    public Station getArrivée() {
        return arrivée;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }

    public ArrayList<Arret> getArrets() {
        return arrets;
    }

    public void setArrets(ArrayList<Arret>arrets) {
        this.arrets = arrets;
    }

    public void ajouterReservation(Reservation reservation)
    {
        reservations.add(reservation);
    }
    public void ajouterArret(Arret arret)
    {
        arrets.add(arret);
    }

    @Override
    public String toString() {
        return "Voyage{" +
                "idVoyage=" + idVoyage +
                ", prix=" + prix +
                ", numeroLigne='" + numeroLigne +
                ", bus=" + bus +
                '}';
    }

    public boolean passeParStation(Station station) {
        // Vérifie si la station de départ correspond
        if (depart.equals(station)) {
            return true;
        }

        // Vérifie si la station d'arrivée correspond
        if (arrivée.equals(station)) {
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
        if(station.equals(depart))
        {
            return -1;
        }
        else
        {
            if(station.equals(arrivée))
            {
                return arrets.size()+1;
            }

            return arrets.indexOf(station);
        }
    }
}