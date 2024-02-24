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
public class CatalogueBus {
    
    TreeMap<String, Bus> collection =new TreeMap<String, Bus>();

   public Bus chercherBusById(String IdBus)
   {
       return collection.get(IdBus);
   }
}
