package ma.fstm.ilisi.busway.metier.bo;
import java.time.LocalTime;
import java.util.*;

/**
 * 
 */
public class Bus {
    public ArrayList<Voyage> getVoyages() {
        return voyages;
    }

    public void setVoyages(ArrayList<Voyage> voyages) {
        this.voyages = voyages;
    }

    private int capacite;
    private String matricule;

    ArrayList<Voyage> voyages ;

    public Bus() {
    }

    public Bus(int capacite, String matricule) {
        this.capacite = capacite;
        this.matricule = matricule;
        this.voyages = new ArrayList<Voyage>();
    }
    public String getMatricule() {
        return matricule;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "capacite=" + capacite +
                ", matricule='" + matricule +
                '}';
    }
    public int getCapacite() {
        return capacite;
    }

    public boolean isBusy(LocalTime depart,LocalTime arrivee, Date date)
    {
        //To do : on a besoin d'ajouter la liste des voyage pour la class bus
        return true;
    }
}