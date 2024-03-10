package ma.fstm.ilisi.busway.dao;

import ma.fstm.ilisi.busway.metier.bo.Bus;
import org.neo4j.driver.Result;
import org.neo4j.driver.Transaction;

import java.util.TreeMap;

import static org.neo4j.driver.Values.parameters;

public class DAOBus {
    public void ajouterBus(Bus bus) {
        try (Transaction tx = Connexion.getSession().beginTransaction()) {
            Result mat = tx.run("CREATE (a:BUS {matricule: $matricule, capacite: $capacite}) ",parameters("matricule", bus.getMatricule(), "capacite", bus.getCapacite()));
            tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    public TreeMap<String, Bus> retrieveAllBuses() {
        try (Transaction tx = Connexion.getSession().beginTransaction()) {
            Result mat = tx.run("MATCH (b:BUS) RETURN b.matricule AS matricule, b.capacite AS capacite");
            TreeMap<String, Bus> busTreeMap = new TreeMap<>();
            while (mat.hasNext()) {
                var record = mat.next();
                busTreeMap.put(record.get("matricule").asString(), new Bus( record.get("capacite").asInt(),record.get("matricule").asString()));
            }
            tx.commit();
            return busTreeMap;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null; // ou une autre valeur par défaut appropriée
        }
    }
}
