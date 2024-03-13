package ma.fstm.ilisi.busway.dao;

import ma.fstm.ilisi.busway.metier.bo.Conducteur;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.Values;

import static org.neo4j.driver.Values.parameters;


public class DAOConducteur {
    public void ajouterConducteur(Conducteur conducteur) {
        try (Transaction tx = Connexion.getSession().beginTransaction()) {
            tx.run("CREATE (a:CONDUCTEUR {cin: $cin, nom: $nom, prenom: $prenom, dateNaissance: $dateNaissance}) ",parameters("cin", conducteur.getCin(), "nom", conducteur.getNom(), "prenom", conducteur.getPrenom(), "dateNaissance", conducteur.getDateNaissance().toString()));
            tx.commit();
            tx.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
