package ma.fstm.ilisi.busway.dao;

import ma.fstm.ilisi.busway.metier.bo.*;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.types.Node;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static org.neo4j.driver.Values.parameters;

public class DAOVoyage {
    public int ajouterVoyage(Voyage voyage) {
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
            requette += " CREATE (bus)-[idligne:ASSOCIE_LIGNE{ligne:'" + voyage.getNumeroLigne() + "',heureDepart:'" + voyage.getHeureDepart() + "'}]->(stationD) RETURN id(idligne) AS id";
            Result result = tx.run(requette);
            int idVoyage = result.single().get("id").asInt();
            tx.commit();
            return idVoyage;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }
    public int getDescendu_now(Voyage voyage) {
        try (Transaction tx = Connexion.getSession().beginTransaction()) {
            Result mat = tx.run("MATCH (bus:BUS {matricule: $matricule})-[r:BUS_EST_PASSE_PAR {ligne: $ligne}]->(station:STATION) " +
                            "RETURN sum(r.nbr_descendu) AS nbr ",
                    parameters("matricule", voyage.getBus().getMatricule(), "ligne", voyage.getNumeroLigne()));

            int sumNbr = mat.single().get("nbr").asInt();
            tx.commit();
            tx.close();
            return sumNbr;
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // ou une autre valeur par défaut appropriée
        }

    }

    public TreeMap<Integer, Voyage> retrieveAllVoyages() {
        try (Session session = Connexion.getSession()) {
            TreeMap<Integer, Voyage> voyageMap = new TreeMap<>();

            try (Transaction tx = session.beginTransaction()) {
                Result mat = tx.run("MATCH (bus:BUS)-[r:ASSOCIE_LIGNE]->(stationD:STATION) " +
                        "RETURN bus.matricule AS matricule, bus.capacite as capacite, r.ligne AS ligne, " +
                        "r.heureDepart AS heureDepart, ID(r) as idVoyage," +
                        "stationD");

                while (mat.hasNext()) {
                    Record record = mat.next();
                    Node stationDNode = record.get("stationD").asNode();

                    Bus bus = new Bus(record.get("capacite").asInt(), record.get("matricule").asString());
                    Station stationD = new Station(
                            stationDNode.get("nomStation").asString(),
                            stationDNode.get("adresse").asString(),
                            stationDNode.get("latitude").asFloat(),
                            stationDNode.get("longitude").asFloat()
                    );

                    // Fetching related stations
                    Result rst = tx.run("MATCH (station1:STATION)-[r:ARRIVEE_LIGNE]->(stationA:STATION)" +
                                    " WHERE r.ligne = $ligne " +
                                    "RETURN station1, stationA",
                            parameters("ligne", record.get("ligne").asString()));
                    Station stationavA=null;
                    Station stationA=null;
                    while (rst.hasNext()) {
                        Record record3 = rst.next();
                        Node station1Node = record3.get("station1").asNode();
                        Node stationANode = record3.get("stationA").asNode();

                        stationavA= new Station(
                                station1Node.get("nomStation").asString(),
                                station1Node.get("adresse").asString(),
                                station1Node.get("latitude").asFloat(),
                                station1Node.get("longitude").asFloat()
                        );

                        stationA= new Station(
                                stationANode.get("nomStation").asString(),
                                stationANode.get("adresse").asString(),
                                stationANode.get("latitude").asFloat(),
                                stationANode.get("longitude").asFloat()
                        );

                        // Process stations
                    }

                    rst = tx.run("MATCH(:STATION)-[r:DEPART_LIGNE{ligne:'"+record.get("ligne").asString()+"'}]->(:STATION) return r.prix as prix");
                    float prix =Float.parseFloat(rst.single().get("prix").asString());
                    Voyage voyage = new Voyage(record.get("idVoyage").asInt(), record.get("heureDepart").asString(), record.get("heureDepart").asString(),
                            prix, record.get("ligne").asString(), stationD, null, stationA, bus);

                    // Fetching related stops
                    Result rst2 = tx.run("MATCH (station1:STATION)-[r:PASSAGE_LIGNE]->(station2:STATION) " +
                                    "WHERE r.ligne = $ligne " +
                                    "RETURN station1",
                            parameters("ligne", record.get("ligne").asString()));

                    ArrayList<Arret> listArrets=new ArrayList<Arret>();
                    while (rst2.hasNext()) {
                        Record record2 = rst2.next();
                        Node station1Node = record2.get("station1").asNode();

                        Station station = new Station(
                                station1Node.get("nomStation").asString(),
                                station1Node.get("adresse").asString(),
                                station1Node.get("latitude").asFloat(),
                                station1Node.get("longitude").asFloat()
                        );
                        listArrets.add(new Arret("00:00",station,voyage));
                    }
                    listArrets.add(new Arret("00:00",stationavA,voyage));
                    // Fetching related reservations
                    Result result = tx.run("MATCH (reservation:RESERVATION)-[:RESERVE {ligne: $ligne}]->(s:STATION) " +
                                    "RETURN reservation, s",
                            parameters("ligne", record.get("ligne").asString()));
                    ArrayList<Reservation> reservations=new ArrayList<Reservation>();
                    while (result.hasNext()) {
                        Record rcrd = result.next();
                        Node reservationNode = rcrd.get("reservation").asNode();
                        Node stationNode = rcrd.get("s").asNode();
                        Station stationdepart = new Station(
                                stationNode.get("nomStation").asString(),
                                stationNode.get("adresse").asString(),
                                stationNode.get("latitude").asFloat(),
                                stationNode.get("longitude").asFloat()
                        );
                        Reservation reservation = new Reservation(stationdepart, LocalTime.now(),voyage);
                        reservations.add(reservation);
                    }
                    voyage.setArrets(listArrets);
                    voyage.setReservations(reservations);
                    voyageMap.put(voyage.getIdVoyage(),voyage);
                }

                tx.commit();
                Connexion.closeSession();
                return voyageMap;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }


    }
}
