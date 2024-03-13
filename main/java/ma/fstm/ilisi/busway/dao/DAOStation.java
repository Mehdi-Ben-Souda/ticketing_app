package ma.fstm.ilisi.busway.dao;

import ma.fstm.ilisi.busway.metier.bo.Station;
import org.neo4j.driver.Result;
import org.neo4j.driver.Transaction;

import java.util.TreeMap;

import static org.neo4j.driver.Values.parameters;

public class DAOStation {

    public void ajouterStation(Station station) {
        try (Transaction tx = Connexion.getSession().beginTransaction()) {
            Result mat = tx.run("CREATE (c:STATION {nomStation: $nomStation, adresse: $adresse,latitude:$latitude , longitude:$longitude}) "
                    ,parameters("nomStation", station.getNomStation(), "adresse", station.getAdresse(),"latitude",station.getLatitude(),"longitude",station.getLongitude()));
            tx.commit();
            tx.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TreeMap<String, Station> retrieveAllStations() {
        try (Transaction tx = Connexion.getSession().beginTransaction()) {
            Result mat = tx.run("MATCH (s:STATION) RETURN s.nomStation AS nomStation, s.adresse AS adresse, s.latitude AS latitude, s.longitude AS longitude");
            TreeMap<String, Station> stationTreeMap = new TreeMap<>();
            while (mat.hasNext()) {
                var record = mat.next();
                stationTreeMap.put(record.get("nomStation").asString(), new Station(record.get("nomStation").asString(), record.get("adresse").asString(), record.get("latitude").asFloat(), record.get("longitude").asFloat()));
            }
            tx.commit();
            tx.close();
            return stationTreeMap;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null; // ou une autre valeur par défaut appropriée
        }
    }
}
