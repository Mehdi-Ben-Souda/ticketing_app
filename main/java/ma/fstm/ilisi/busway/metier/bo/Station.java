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
        this.arrets = new ArrayList<Arret>();
    }
    private String nomStation;
    private String adresse;
    private ArrayList<Arret> arrets;

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

    public ArrayList<Arret> getArrets() {
        return arrets;
    }

    public void setArrets(ArrayList<Arret> arrets) {
        this.arrets = arrets;
    }

    public void ajouterArret(Arret arret)
    {
        arrets.add(arret);
    }
}