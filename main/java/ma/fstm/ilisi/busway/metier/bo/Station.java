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
        this.lesReservations=new ArrayList<Reservation>();
        this.voyage_arrets = new ArrayList<Arret>();
        this.voyage_arrivee = new ArrayList<Voyage>();
        this.voyage_departs = new ArrayList<Voyage>();
    }

    public Station(String nomStation, String adresse, float latitude, float longitude) {
        this.nomStation = nomStation;
        this.adresse = adresse;
        this.latitude = latitude;
        this.longitude = longitude;
        this.voyage_arrets = new ArrayList<Arret>();
        this.voyage_arrivee = new ArrayList<Voyage>();
        this.voyage_departs = new ArrayList<Voyage>();
    }
    private String nomStation;
    private String adresse;
    private float latitude;
    private float longitude;
    private ArrayList<Arret> voyage_arrets;

    private ArrayList<Voyage> voyage_arrivee;

    private ArrayList<Voyage> voyage_departs;

    private ArrayList<Reservation> lesReservations;

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

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
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

    public void ajouterVoyageDepart(Voyage voyage) {
        this.voyage_departs.add(voyage);
    }
    public void ajouterVoyageArrivee(Voyage voyage) {
        this.voyage_arrivee.add(voyage);
    }

    public void ajouterReservation(Reservation reservation) {
    }
}