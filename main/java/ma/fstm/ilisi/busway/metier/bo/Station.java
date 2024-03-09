package ma.fstm.ilisi.busway.metier.bo;
import java.util.*;

/**
 * 
 */
public class Station {

    /**
     * Default constructor
     */
    public Station() {
    }

    public Station(String nomStation, String adresse) {
        this.nomStation = nomStation;
        this.adresse = adresse;
        this.voyage_arrets = new ArrayList<Arret>();
    }
    private String nomStation;
    private String adresse;
    private ArrayList<Arret> voyage_arrets;

    private ArrayList<Voyage> voyage_arrivee;

    private ArrayList<Voyage> voyage_departs;

    public void setNomStation(String nomStation) {
        this.nomStation = nomStation;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public ArrayList<Voyage> getVoyage_arrivee() {
        return voyage_arrivee;
    }

    public void setVoyage_arrivee(ArrayList<Voyage> voyage_arrivee) {
        this.voyage_arrivee = voyage_arrivee;
    }

    public ArrayList<Voyage> getVoyage_departs() {
        return voyage_departs;
    }

    public void setVoyage_departs(ArrayList<Voyage> voyage_departs) {
        this.voyage_departs = voyage_departs;
    }

    public String getNomStation() {
		return nomStation;
	}

	public String getAdresse() {
		return adresse;
	}



    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Arret)
            return super.equals(((Arret)obj).getStation());
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Station{" +
                "nomStation='" + nomStation + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }

    public ArrayList<Arret> getVoyage_arrets() {
        return voyage_arrets;
    }

    public void setVoyage_arrets(ArrayList<Arret> voyage_arrets) {
        this.voyage_arrets = voyage_arrets;
    }

    public void ajouterArret(Arret arret)
    {
        voyage_arrets.add(arret);
    }
}