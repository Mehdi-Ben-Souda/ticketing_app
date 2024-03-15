package ma.fstm.ilisi.busway.metier.service;

import ma.fstm.ilisi.busway.dao.DAOStation;
import ma.fstm.ilisi.busway.metier.bo.Station;

public class ServiceStation {
    public Station ajouterStation(String nom, String adresse, float latitude, float longitude) {
        Station station = new Station(nom, adresse, latitude, longitude);
        new DAOStation().ajouterStation(station);
        return station;
    }

}
