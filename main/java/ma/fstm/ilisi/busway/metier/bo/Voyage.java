package ma.fstm.ilisi.busway.metier.bo;
import java.util.*;

public class Voyage {
    private int idVoyage;
    private Date dateVoyage;
    private float prix;
    private String numeroLigne;
    private Depart depart;
    private Arrivée arrivée;
    private ArrayList<Arret> arrets;
    public Voyage(int idVoyage, Date dateVoyage, float prix, String numeroLigne, Depart depart, Arrivée arrivée, ArrayList<Arret> arrets) {
        this.idVoyage = idVoyage;
        this.dateVoyage = dateVoyage;
        this.prix = prix;
        this.numeroLigne = numeroLigne;
        this.depart = depart;
        this.arrivée = arrivée;
        this.arrets = arrets;
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
}