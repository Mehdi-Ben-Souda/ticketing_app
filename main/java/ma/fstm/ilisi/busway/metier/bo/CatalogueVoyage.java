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

    public List<Voyage> voyagesBySegment(Station SD,Station SA) {
        List<Voyage> voyagesBySegment = new ArrayList<Voyage>();
        for (Map.Entry<Integer, Voyage> entry : collection.entrySet()) {
            Voyage voyage = entry.getValue();
                if (voyage.passeParStation(SD) && voyage.passeParStation(SA)) {
                    if(voyage.getindexStation(SD) < voyage.getindexStation(SA))
                        voyagesBySegment.add(voyage);
                }
        }
        return voyagesBySegment;
    }

}
