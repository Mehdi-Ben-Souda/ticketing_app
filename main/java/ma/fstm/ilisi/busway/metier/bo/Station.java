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
    }

    /**
     * 
     */
    private String nomStation;

    /**
     * 
     */
    private String adresse;

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
}