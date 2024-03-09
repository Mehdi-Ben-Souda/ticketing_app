package ma.fstm.ilisi.busway.metier.bo;
import java.util.*;

/**
 * 
 */
public class Conducteur {

    /**
     * Default constructor
     */
    private String matricule;
    private String nom;
    private String prenom;
    private String cin;
    private Date dateNaissance;

    public Conducteur(String matricule, String nom, String prenom, String cin, Date dateNaissance) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.dateNaissance = dateNaissance;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }
}