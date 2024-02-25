package ma.fstm.ilisi.busway.metier.bo;
import java.util.*;

public class Voyage {

    private Date dateVoyage;
    private float prix;
    private String numeroLigne;
    private Depart depart;
    private Arrivée arrivée;
    private ArrayList<Arret> arrets;
    public Voyage(Date dateVoyage, float prix, String numeroLigne, Depart depart, Arrivée arrivée, ArrayList<Arret> arrets) {
        this.dateVoyage = dateVoyage;
        this.prix = prix;
        this.numeroLigne = numeroLigne;
        this.depart = depart;
        this.arrivée = arrivée;
        this.arrets = arrets;
    }



}