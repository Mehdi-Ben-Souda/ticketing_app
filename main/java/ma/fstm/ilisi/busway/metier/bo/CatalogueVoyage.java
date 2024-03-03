package ma.fstm.ilisi.busway.metier.bo;

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

    public List<Voyage> voyagesBySegment(Date date,Station SD,Station SA) {
        List<Voyage> voyagesBySegment = new ArrayList<Voyage>();
        for (Map.Entry<Integer, Voyage> entry : collection.entrySet()) {
            Voyage voyage = entry.getValue();
            if (voyage.getDateVoyage().equals(date)) {
                if (voyage.passeParStation(SD) && voyage.passeParStation(SA)) {
                    if(voyage.getindexStation(SD) < voyage.getindexStation(SA))
                        voyagesBySegment.add(voyage);
                }
            }
        }
        return voyagesBySegment;
    }

}
