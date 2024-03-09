package ma.fstm.ilisi.busway.metier.bo;
import java.util.*;

/**
 * 
 */
public class Personne {

    /**
     * Default constructor
     */
    public Personne() {
    }

    public Personne(String nom, String prenom, String cin, Date dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.dateNaissance = dateNaissance;
    }
    /**
     * 
     */
    private String nom;

    /**
     * 
     */
    private String prenom;

    /**
     * 
     */
    private String cin;

    /**
     * 
     */
    private Date dateNaissance;

}