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

}