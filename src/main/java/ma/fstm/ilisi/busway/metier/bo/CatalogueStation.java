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
    
    TreeMap<String, Station> collection =new TreeMap<String, Station>();
    
   public Station chercherStationById(String idStation)
   {
       return collection.get(idStation);
   }
}
