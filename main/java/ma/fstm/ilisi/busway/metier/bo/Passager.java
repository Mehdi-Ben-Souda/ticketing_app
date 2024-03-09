package ma.fstm.ilisi.busway.metier.bo;
import java.util.*;

/**
 * 
 */
public class Passager extends Personne {

    /**
     * Default constructor
     */
    public Passager() {
    }
    public Passager(String nom, String prenom, String cin, Date dateNaissance) {
        super(nom, prenom, cin, dateNaissance);
    }

}