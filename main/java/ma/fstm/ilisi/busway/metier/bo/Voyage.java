package ma.fstm.ilisi.busway.metier.bo;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Voyage {
    private int idVoyage;
    private LocalTime heureDepart;
    private LocalTime heureArrivée;
    private float prix;
    private String numeroLigne;
    private int statut;
    private Bus bus;
    private Station depart;
    private Station arrivée;
    private ArrayList<Arret> arrets;
    private ArrayList<Reservation> reservations;

    private static int cmp = 0;

    public Voyage(int idVoyage, String heureDepart,String heureArrivée, float prix, String numeroLigne, Station depart, Station arrivée, Bus bus) throws DateTimeParseException {
        this.idVoyage = cmp++;
        this.prix = prix;
        this.numeroLigne = numeroLigne;
        this.bus = bus;
        this.depart = depart;
        this.arrivée = arrivée;
        this.arrets = new ArrayList<Arret>();
        this.heureDepart = LocalTime.parse(heureDepart);
        this.heureArrivée = LocalTime.parse(heureArrivée);
        this.reservations = new ArrayList<Reservation>();
        this.statut=0;
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

        //return bus.getCapacite() > (reservations.size()-cpt) ;
        return true;
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