/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.fstm.ilisi.busway.metier.bo.Catalogues;

import ma.fstm.ilisi.busway.dao.DAOStation;
import ma.fstm.ilisi.busway.metier.bo.Station;
import ma.fstm.ilisi.busway.metier.exceptions.StationIntrouvable;

import java.util.TreeMap;

/**
 *
 * @author Jebbanema
 */
public class CatalogueStation {
    
    public TreeMap<String, Station> collection =new TreeMap<String, Station>();
    
   public Station chercherStationByNom(String nom) throws StationIntrouvable
   {
       if (collection.get(nom) == null)
           throw new StationIntrouvable(nom);
     return collection.get(nom);
   }


    public void ajouterStation(Station station)
    {
        collection.put(station.getNomStation(), station);
    }

    public void  retreiveTousLesStations()
    {
        collection=new DAOStation().retrieveAllStations();
    }
}
