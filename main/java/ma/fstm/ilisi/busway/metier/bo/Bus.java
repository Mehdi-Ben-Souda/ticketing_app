package ma.fstm.ilisi.busway.metier.bo;
import java.util.*;

/**
 * 
 */
public class Bus {

    /**
     * Default constructor
     */
    public Bus() {
    }

    public Bus(int capacite, String matricule) {
        this.capacite = capacite;
        this.matricule = matricule;
    }

    private int capacite;

    private String matricule;

    public int getNbPlaces() {
        return capacite;
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
}