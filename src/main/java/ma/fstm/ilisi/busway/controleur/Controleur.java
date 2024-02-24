/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.fstm.ilisi.busway.controleur;

import ma.fstm.ilisi.busway.metier.bo.Bus;
import ma.fstm.ilisi.busway.metier.bo.CatalogueBus;
import ma.fstm.ilisi.busway.metier.bo.CatalogueStation;
import ma.fstm.ilisi.busway.metier.bo.Passager;
import ma.fstm.ilisi.busway.metier.bo.Station;
import ma.fstm.ilisi.busway.metier.service.ServiceReservation;

/**
 *
 * @author Jebbanema
 */
public class Controleur {
    
    private CatalogueBus catalogueBus;
    private CatalogueStation catalogueStation;
    
    public void reserver(String IdBus,String idStationA,String idStationD,Passager passager)
    {
        Bus bus=catalogueBus.chercherBusById(IdBus);
        Station stationA=catalogueStation.chercherStationById(idStationA);
        Station stationd=catalogueStation.chercherStationById(idStationD);
        new ServiceReservation().reserver(bus, stationA, stationd, passager);
    }
}
