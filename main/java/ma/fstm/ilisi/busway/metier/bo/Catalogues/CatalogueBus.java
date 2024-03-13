/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.fstm.ilisi.busway.metier.bo.Catalogues;

import ma.fstm.ilisi.busway.dao.DAOBus;
import ma.fstm.ilisi.busway.metier.bo.Bus;
import ma.fstm.ilisi.busway.metier.exceptions.BusDejaExiste;

import java.util.TreeMap;


/**
 *
 * @author Jebbanema
 */
public class CatalogueBus {
    public TreeMap<String, Bus> collection =new TreeMap<String, Bus>();

    public Bus chercherBusByMatricule(String matricule)
   {
       return collection.get(matricule);
   }

    public void ajouterBus(Bus bus) throws BusDejaExiste
    {
        if(collection.get(bus.getMatricule())!=null)
            throw new BusDejaExiste();

         collection.put(bus.getMatricule(), bus);
    }
    public void supprimerBus(String matricule)
    {
        collection.remove(matricule);
    }

    public void retrieveTousLesBus()
    {
        collection=new DAOBus().retrieveAllBuses();
    }
}
