package ma.fstm.ilisi.busway.metier.service;

import ma.fstm.ilisi.busway.metier.bo.Catalogues.CatalogueStation;
import ma.fstm.ilisi.busway.metier.bo.Catalogues.CatalogueVoyage;
import ma.fstm.ilisi.busway.metier.bo.Station;
import ma.fstm.ilisi.busway.metier.exceptions.LigneIntrouvable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ServiceLocalisation {

    public CatalogueVoyage catalogueVoyage;

    public ServiceLocalisation(CatalogueVoyage cata)
    {
        catalogueVoyage=cata;
    }
    public List<Station> getNearestStations(float longitude , float latitude,String numeroLigne)
    {
        List<Station> lesStations=null;
        ArrayList<Double> lesDistances;
        try {
            lesDistances=new ArrayList<>();
            lesStations=catalogueVoyage.stationsByLigne(numeroLigne);
            for (Station laStation : lesStations)
            {
                double laDistance = Math.pow(longitude-laStation.getLongitude(),2) + Math.pow(latitude-laStation.getLatitude(),2);
                laDistance=Math.sqrt(laDistance);
                lesDistances.add(laDistance);
            }

           lesDistances.sort(new Comparator<Double>() {
                @Override
                public int compare(Double o1, Double o2) {
                    if(o1<o2)
                    {
                        return -1;
                    }
                    else if(o1>o2)
                    {
                        return 1;
                    }
                    return 0;
                }
            });
            System.out.println(lesDistances);

        }
        catch (LigneIntrouvable e )
        {
            e.printStackTrace();
        }
        return lesStations;
    }

}
