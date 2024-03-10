package ma.fstm.ilisi.busway.metier.bo.Catalogues;

import ma.fstm.ilisi.busway.metier.bo.Station;
import ma.fstm.ilisi.busway.metier.bo.Voyage;

import java.util.*;

public class CatalogueVoyage {
    public TreeMap<Integer, Voyage> collection =new TreeMap<Integer, Voyage>();

    public Voyage chercherVoyageByID(int ID)
    {
        return collection.get(ID);
    }

    public void ajouterVoyage(Voyage V)
    {
        collection.put(V.getIdVoyage(), V);
    }

    public List<Voyage> voyagesByStation(Station SD,String nLigne) {
        List<Voyage> voyagesByStation = new ArrayList<Voyage>();
        for (Map.Entry<Integer, Voyage> entry : collection.entrySet()) {
            Voyage voyage = entry.getValue();
                if (voyage.passeParStation(SD) && voyage.getNumeroLigne().equals(nLigne)) {
                        voyagesByStation.add(voyage);
                }
        }
        return voyagesByStation;
    }

}
