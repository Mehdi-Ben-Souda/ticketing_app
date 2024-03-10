/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.fstm.ilisi.busway.controleur;

import ma.fstm.ilisi.busway.metier.bo.*;
import ma.fstm.ilisi.busway.metier.bo.Catalogues.CatalogueBus;
import ma.fstm.ilisi.busway.metier.bo.Catalogues.CatalogueConducteur;
import ma.fstm.ilisi.busway.metier.bo.Catalogues.CatalogueStation;
import ma.fstm.ilisi.busway.metier.bo.Catalogues.CatalogueVoyage;
import ma.fstm.ilisi.busway.metier.exceptions.BusDejaExiste;
import ma.fstm.ilisi.busway.metier.exceptions.ConducteurDejaExiste;
import ma.fstm.ilisi.busway.metier.exceptions.StationIntrouvable;
import ma.fstm.ilisi.busway.metier.service.ServiceReservation;
import ma.fstm.ilisi.busway.metier.service.ServiceVoyage;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 *
 * @author Jebbanema
 */
public class Controleur {
    
    private CatalogueBus catalogueBus;
    private CatalogueStation catalogueStation;
    private CatalogueVoyage catalogueVoyage;
    private CatalogueConducteur catalogueConducteur;

    public Controleur(CatalogueBus catalogueBus, CatalogueStation catalogueStation, CatalogueVoyage catalogueVoyage, CatalogueConducteur catalogueConducteur) {
        this.catalogueBus = catalogueBus;
        this.catalogueStation = catalogueStation;
        this.catalogueVoyage = catalogueVoyage;
        this.catalogueConducteur = catalogueConducteur;
    }


    public void reserver(String idStationD,String idStationA,Passager passager,int IdVoyage)
    {
        try {
            Station stationA=catalogueStation.chercherStationByNom(idStationA);
            Station stationD=catalogueStation.chercherStationByNom(idStationD);
            Voyage voyage=catalogueVoyage.chercherVoyageByID(IdVoyage);

            new ServiceReservation().reserver(stationD,stationA,passager,voyage);
        }
        catch (StationIntrouvable e) {
            e.printStackTrace();
            }

    }
    public void LesBusDisponibles(String nomStationD, String nomStationA){

        try {
            //recuperer les stations de depart et d'arrive
            Station SD = catalogueStation.chercherStationByNom(nomStationD);
            Station SA = catalogueStation.chercherStationByNom(nomStationA);
            //recuperer les voyages
            List<Voyage> Voyages = catalogueVoyage.voyagesBySegment(SD,SA);// Mzyana
            List<Voyage> vDisponibles = new ArrayList<Voyage>();
            for(Voyage v: Voyages){
                if(v.verifierDisponibilite(SD, SA)){// Verifier disponibilité est a changer
                    //POV:Le bus du voyage v est disponible
                    vDisponibles.add(v);
                    System.out.println((v));
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ajouterVoyage(String matriculeBus, String heureDepart,String heureArrivee,
                              String nomStationDepart , String nomStationArrivee , ArrayList<Arret> arrets,float prix,String numeroLigne)
    {
        try {
            Bus bus=catalogueBus.chercherBusByMatricule(matriculeBus);
            //Conducteur conducteur=catalogueConducteur.chercherConducteurByMatricule(matriculeConducteur);
            Station stationDepart=catalogueStation.chercherStationByNom(nomStationDepart);
            Station stationArrivee=catalogueStation.chercherStationByNom(nomStationArrivee);

            Voyage nvVoyage = new ServiceVoyage().ajouterVoyage(bus,stationDepart,arrets,stationArrivee,heureDepart,heureArrivee,prix,numeroLigne);
            catalogueVoyage.ajouterVoyage(nvVoyage);
        }
        catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        catch (StationIntrouvable e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public void ajouterBus(String matricule,int capacite)
    {
        try {
            catalogueBus.ajouterBus(new Bus(capacite,matricule));
        }
        catch (BusDejaExiste e){
            e.printStackTrace();
        }

    }

    public void ajouterStation(String nom, String adresse)
    {
        catalogueStation.ajouterStation(new Station(nom,adresse));
    }

    public void ajouterConducteur(String matricule, String nom, String prenom, String cin, Date dateNaissance)
    {
        try {
            catalogueConducteur.ajouterConducteur(new Conducteur(matricule,nom,prenom,cin,dateNaissance));
        }
        catch (ConducteurDejaExiste e) {
            e.printStackTrace();

        }
    }




    public void supprimerBus(String matricule)
    {
        catalogueBus.supprimerBus(matricule);
    }

}
