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

    public Controleur(CatalogueBus catalogueBus, CatalogueStation catalogueStation, CatalogueVoyage catalogueVoyage) {
        this.catalogueBus = catalogueBus;
        this.catalogueStation = catalogueStation;
        this.catalogueVoyage = catalogueVoyage;
    }


    public void reserver(String idStationD,String idStationA,Passager passager,int IdVoyage)
    {
        Station stationA=catalogueStation.chercherStationByNom(idStationA);
        Station stationD=catalogueStation.chercherStationByNom(idStationD);
        Voyage voyage=catalogueVoyage.chercherVoyageByID(IdVoyage);

        new ServiceReservation().reserver(stationD,stationA,passager,voyage);



    }
    public void LesBusDisponibles(String nomStationD, String nomStationA, Date DateV){
        //recuperer les stations de depart et d'arrive
        Station SD = catalogueStation.chercherStationByNom(nomStationD);
        Station SA = catalogueStation.chercherStationByNom(nomStationA);
        //recuperer les voyages
        List<Voyage> Voyages = catalogueVoyage.voyagesBySegment(DateV,SA,SD);
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
