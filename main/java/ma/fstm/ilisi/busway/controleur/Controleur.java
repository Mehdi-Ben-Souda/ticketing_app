/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.fstm.ilisi.busway.controleur;

import ma.fstm.ilisi.busway.metier.bo.*;
import ma.fstm.ilisi.busway.metier.service.ServiceReservation;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jebbanema
 */
public class Controleur {
    
    private CatalogueBus catalogueBus;
    private CatalogueStation catalogueStation;
    private CatalogueVoyage catalogueVoyage;

    
    public void reserver(String matricule,String idStationA,String idStationD,Passager passager)
    {
        Bus bus=catalogueBus.chercherBusByMatricule(matricule);
        Station stationA=catalogueStation.chercherStationByNom(idStationA);
        Station stationD=catalogueStation.chercherStationByNom(idStationD);
        //TO DO  : appeler la fonction isLimit
        new ServiceReservation().reserver(bus, stationA, stationD, passager);
    }
    public void AfficherBusDispo(String nomStationD, String nomStationA, Date DateV){
        //recuperer les stations de depart et d'arrive
        Station SD = catalogueStation.chercherStationByNom(nomStationD);
        Station SA = catalogueStation.chercherStationByNom(nomStationA);
        //recuperer les voyages
        List<Voyage> Voyages = catalogueVoyage.voyagesBySegment(DateV,SD,SA);
        List<Voyage> vDisponibles = new ArrayList<Voyage>();
        for(Voyage v: Voyages){
            if(v.verifierDisponibilite(SD, SA)){
                //POV:Le bus du voyage v est disponible
                vDisponibles.add(v);
                System.out.println((v));
            }
        }


    }

}
