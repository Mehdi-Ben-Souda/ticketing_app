/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.fstm.ilisi.busway.metier.bo;

import java.util.TreeMap;

/**
 *
 * @author Jebbanema
 */
public class CatalogueStation {
    
    public TreeMap<String, Station> collection =new TreeMap<String, Station>();
    
   public Station chercherStationByNom(String nom)
   {
       return collection.get(nom);
   }

    public void ajouterStation(Station station)
    {
        collection.put(station.getNomStation(), station);
    }


}
