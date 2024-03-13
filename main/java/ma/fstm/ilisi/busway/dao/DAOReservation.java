package ma.fstm.ilisi.busway.dao;

import ma.fstm.ilisi.busway.metier.bo.Reservation;
import ma.fstm.ilisi.busway.metier.bo.Station;
import ma.fstm.ilisi.busway.metier.bo.Voyage;
import org.neo4j.driver.Result;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.internal.value.LocalTimeValue;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.neo4j.driver.Values.parameters;

public class DAOReservation {
    public void ajouterReservation(Reservation reservation) {
        try (Transaction tx = Connexion.getSession().beginTransaction()) {
            Result mat = tx.run(
                    "MATCH (stationD:STATION {nomStation: $nomStation}) " +
                            "CREATE (reservation:RESERVATION {heure_reservation: $heure_reservation, ligne: $ligne}) " +
                            "CREATE (reservation)-[:RESERVE {ligne: $ligne}]->(stationD)",
                    parameters(
                            "heure_reservation", reservation.getHeureReservation(),
                            "ligne", reservation.getVoyage().getNumeroLigne(),
                            "nomStation", reservation.getStationDepart().getNomStation()
                    )
            );
            tx.commit();
            tx.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Reservation> retreiveReservationByVoyage(Voyage voyage)
    {
        ArrayList<Reservation> list= new ArrayList<Reservation>();
        try (Transaction tx = Connexion.getSession().beginTransaction()) {
            Result mat = tx.run("MATCH (reservation:RESERVATION)-[:RESERVE {ligne: $ligne}]->(s:STATION) " +
                            "RETURN reservation.heure_reservation AS heure_reservation, s.nomStation AS nomStation" +
                            ", s.adresse AS adresse, s.latitude AS latitude, s.longitude AS longitude",
                    parameters("ligne", voyage.getNumeroLigne()));
            while (mat.hasNext()) {
                var record = mat.next();
                Station station=new Station(record.get("nomStation").asString(), record.get("adresse").asString(), record.get("latitude").asFloat(), record.get("longitude").asFloat());
                list.add(new Reservation(station,record.get("heure_reservation").asLocalTime(),voyage));
            }
            tx.commit();
            tx.close();
            return list;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null; // ou une autre valeur par défaut appropriée
        }
    }

}
