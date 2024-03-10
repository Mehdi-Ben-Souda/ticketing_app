package ma.fstm.ilisi.busway.dao;

import ma.fstm.ilisi.busway.metier.bo.Arret;
import ma.fstm.ilisi.busway.metier.bo.Bus;
import ma.fstm.ilisi.busway.metier.bo.Station;
import ma.fstm.ilisi.busway.metier.bo.Voyage;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static org.neo4j.driver.Values.parameters;

public class DAOVoyage {
    public void ajouterVoyage(Voyage voyage) {
        try (Transaction tx = Connexion.getSession().beginTransaction()) {
            String requette;
            for (int i = 0; i < voyage.getArrets().size(); i++) {
                requette = " MATCH (station" + i + ":STATION {nomStation:'" + voyage.getArrets().get(i).getStation().getNomStation() + "'})";
                if (i == 0) {
                    requette += " MATCH (stationD:STATION {nomStation:'" + voyage.getDepart().getNomStation() + "'})";
                    requette += " WITH station" + i + ", stationD ";
                    requette += " CREATE (stationD)-[:DEPART_LIGNE{ligne:'" + voyage.getNumeroLigne() + "',prix:'" + voyage.getPrix() + "'}]->(station" + i + ")";
                } else if (i == voyage.getArrets().size() - 1) {
                    requette += " MATCH (stationA:STATION {nomStation:'" + voyage.getArrivée().getNomStation() + "'})";
                    requette += " MATCH (station" + (i - 1) + ":STATION {nomStation:'" + voyage.getArrets().get(i-1).getStation().getNomStation() + "'})";
                    requette += " WITH station" + (i - 1) + ",stationA,station" + i + " ";
                    requette += " CREATE (station" + (i - 1) + ")-[:PASSAGE_LIGNE{ligne:'" + voyage.getNumeroLigne() + "'}]->(station" + i + ")";
                    requette += " CREATE (station" + i + ")-[:ARRIVEE_LIGNE{ligne:'" + voyage.getNumeroLigne() + "'}]->(stationA)";
                } else {
                    requette += " MATCH (station" + (i - 1) + ":STATION {nomStation:'" + voyage.getArrets().get(i-1).getStation().getNomStation() + "'})";
                    requette += " WITH station" + (i - 1)+",station" + i + " ";
                    requette += " CREATE (station" + (i - 1) + ")-[:PASSAGE_LIGNE{ligne:'" + voyage.getNumeroLigne() + "'}]->(station" + i + ")";
                }
                tx.run(requette);
            }

            requette = " MATCH (stationD:STATION {nomStation:'" + voyage.getDepart().getNomStation() + "'})";
            requette += " MATCH (bus:BUS {matricule:'" + voyage.getBus().getMatricule() + "'})";
            requette += " WITH bus,stationD ";
            requette += " CREATE (bus)-[:ASSOCIE_LIGNE{ligne:'" + voyage.getNumeroLigne() + "',heureDepart:'" + voyage.getHeureDepart() + "'}]->(stationD)";

            tx.run(requette);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public int getDescendu_now(Voyage voyage) {
        try (Transaction tx = Connexion.getSession().beginTransaction()) {
            Result mat = tx.run("MATCH (bus:BUS {matricule: $matricule})-[r:BUS_EST_PASSE_PAR {ligne: $ligne}]->(station:STATION) " +
                            "RETURN sum(r.nbr_descendu) AS nbr ",
                    parameters("matricule", voyage.getBus().getMatricule(), "ligne", voyage.getNumeroLigne()));

            int sumNbr = mat.single().get("nbr").asInt();
            tx.commit();
            return sumNbr;
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // ou une autre valeur par défaut appropriée
        }

    }

    public Map<Integer, Voyage> retrieveAllVoyages() {
        try (Session session = Connexion.getSession()) {
            Map<Integer, Voyage> voyageMap = new TreeMap<>();

            try (Transaction tx = session.beginTransaction()) {
                Result mat = tx.run("MATCH (bus:BUS)-[r:ASSOCIE_LIGNE]->(stationD:STATION) " +
                        "RETURN bus.matricule AS matricule, bus.capacite as capacite, r.ligne AS ligne, r.heureDepart AS heureDepart, " +
                        "stationD.nomStation AS nomStationD, stationD.adresse AS adresse, stationD.latitude AS latitude, stationD.longitude AS longitude, ID(r) as idVoyage");

                while (mat.hasNext()) {
                    Record record = mat.next();
                    Bus bus = new Bus(record.get("capacite").asInt(), record.get("matricule").asString());
                    Station stationD = new Station(record.get("nomStationD").asString(), record.get("adresse").asString(),
                            record.get("latitude").asFloat(), record.get("longitude").asFloat());
                    ArrayList<Arret> arrets = new ArrayList<>();
                    Result rst = tx.run("MATCH (station1:STATION)-[r:ARRIVEE_LIGNE]->(stationA:STATION)" +
                            "RETURN r.ligne as ligne, stationA.nomStation AS nomStationA, stationA.adresse AS adresseA, stationA.latitude AS latitudeA, stationA.longitude AS longitudeA" +
                            " ,station1.nomStation AS nomStation, station1.adresse AS adresse, station1.latitude AS latitude, station1.longitude AS longitude");
                    Station stationA=null;
                    Station station1=null;
                    while (rst.hasNext()) {
                        Record record3 = rst.next();
                        if(record3.get("ligne").asString().equals(record.get("ligne").asString()))
                        {
                            station1 = new Station(record3.get("nomStation").asString(), record3.get("adresse").asString(),
                                    record3.get("latitude").asFloat(), record3.get("longitude").asFloat());
                            stationA = new Station(record3.get("nomStationA").asString(), record3.get("adresseA").asString(),
                                    record3.get("latitudeA").asFloat(), record3.get("longitudeA").asFloat());
                        }
                    }
                    rst = tx.run("MATCH(:STATION)-[r:DEPART_LIGNE{ligne:'"+record.get("ligne").asString()+"'}]->(:STATION) return r.prix as prix");
                    float prix =Float.parseFloat(rst.single().get("prix").asString());
                    Voyage voyage = new Voyage(record.get("idVoyage").asInt(), record.get("heureDepart").asString(), record.get("heureDepart").asString(),
                            prix, record.get("ligne").asString(), stationD, null, stationA, bus);

                    Result rst2 = tx.run("MATCH (station1:STATION)-[r:PASSAGE_LIGNE]->(station2:STATION) " +
                                    "WHERE r.ligne = $ligne " +
                                    "RETURN station1.nomStation AS nomStation, station1.adresse AS adresse, station1.latitude AS latitude, station1.longitude AS longitude",
                            parameters("ligne", record.get("ligne").asString()));

                    while (rst2.hasNext()) {
                        Record record2 = rst2.next();
                        Arret arret = new Arret("00:00", new Station(record2.get("nomStation").asString(), record2.get("adresse").asString(),
                                record2.get("latitude").asFloat(), record2.get("longitude").asFloat()), voyage);
                        arrets.add(arret);
                    }

                    Arret arret = new Arret("00:00", station1, voyage);
                    arrets.add(arret);

                    voyage.setArrets(arrets);
                    voyageMap.put(record.get("idVoyage").asInt(), voyage);
                }
                tx.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return voyageMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
