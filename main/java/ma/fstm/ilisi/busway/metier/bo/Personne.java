package ma.fstm.ilisi.busway.metier.bo;
import java.util.*;

/**
 * 
 */
public class Personne {

    private String nom;
    private String prenom;
    private String cin;
    private Date dateNaissance;


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


}