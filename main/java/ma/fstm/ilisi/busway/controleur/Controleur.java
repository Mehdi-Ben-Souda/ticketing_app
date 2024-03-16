/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.fstm.ilisi.busway.controleur;

import ma.fstm.ilisi.busway.dao.DAOStation;
import ma.fstm.ilisi.busway.metier.bo.*;
import ma.fstm.ilisi.busway.metier.bo.Catalogues.CatalogueBus;
import ma.fstm.ilisi.busway.metier.bo.Catalogues.CatalogueConducteur;
import ma.fstm.ilisi.busway.metier.bo.Catalogues.CatalogueStation;
import ma.fstm.ilisi.busway.metier.bo.Catalogues.CatalogueVoyage;
import ma.fstm.ilisi.busway.metier.exceptions.BusDejaExiste;
import ma.fstm.ilisi.busway.metier.exceptions.ConducteurDejaExiste;
import ma.fstm.ilisi.busway.metier.exceptions.StationIntrouvable;
import ma.fstm.ilisi.busway.metier.service.ServiceBus;
import ma.fstm.ilisi.busway.metier.service.ServiceReservation;
import ma.fstm.ilisi.busway.metier.service.ServiceVoyage;

import java.time.format.DateTimeParseException;
import java.util.*;

/**
 *
 * @author Jebbanema
 */
public class Controleur {
    
    public CatalogueBus catalogueBus;
    public CatalogueStation catalogueStation;
    public CatalogueVoyage catalogueVoyage;
    private CatalogueConducteur catalogueConducteur;
    public  Controleur(){
        this.catalogueBus = new CatalogueBus();
        this.catalogueStation = new CatalogueStation();
        this.catalogueVoyage = new CatalogueVoyage();
        this.catalogueConducteur = new CatalogueConducteur();
        catalogueBus.retrieveTousLesBus();
        catalogueStation.retreiveTousLesStations();
        catalogueVoyage.retreiveTousLesVoyages();
    }
    public Controleur(CatalogueBus catalogueBus, CatalogueStation catalogueStation, CatalogueVoyage catalogueVoyage, CatalogueConducteur catalogueConducteur) {
        this.catalogueBus = catalogueBus;
        this.catalogueStation = catalogueStation;
        this.catalogueVoyage = catalogueVoyage;
        this.catalogueConducteur = catalogueConducteur;
    }


    public void reserver(String idStationD,Passager passager,int IdVoyage)
    {
        try {
            Station stationD=catalogueStation.chercherStationByNom(idStationD);
            Voyage voyage=catalogueVoyage.chercherVoyageByID(IdVoyage);

            new ServiceReservation().reserver(stationD,passager,voyage);
        }
        catch (StationIntrouvable e) {
            e.printStackTrace();
            }

    }
    public void ligneParStation(String nomStationD){
        try {
            System.out.println("Les lignes disponibles pour la station "+nomStationD+" sont :");
            //recuperer la stations de depart
            Station SD = catalogueStation.chercherStationByNom(nomStationD);

            List<String> lignes = new ArrayList<String>();
            for(Voyage v: catalogueVoyage.voyagesByStation(SD)){// Mzyana
                if(lignes.indexOf(v.getNumeroLigne())==-1){
                    lignes.add(v.getNumeroLigne());
                    System.out.println(v.getNumeroLigne());
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void LesBusDisponibles(String nomStationD, String nligne){
        try {
            System.out.println("Les bus disponibles pour la station "+nomStationD+" sont :");
            //recuperer la stations de depart
            Station SD = catalogueStation.chercherStationByNom(nomStationD);
            System.out.println(SD);
            //recuperer les voyages
            List<Voyage> Voyages = catalogueVoyage.voyagesByStation(SD,nligne);// Mzyana
            List<Voyage> vDisponibles = new ArrayList<Voyage>();
            for(Voyage v: Voyages){
                System.out.println((v));
                if(v.verifierDisponibilite(SD)){// Verifier disponibilité est a changer
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


    public Voyage ajouterVoyage(String matriculeBus, String matriculeConducteur,String heureDepart, String heureArrivee,
                              String nomStationDepart , String nomStationArrivee , ArrayList<Arret> Arrets, float prix, String numeroLigne)
    {
        try {
            Bus bus=catalogueBus.chercherBusByMatricule(matriculeBus);
            Conducteur conducteur=catalogueConducteur.chercherConducteurByMatricule(matriculeConducteur);
            Station stationDepart=catalogueStation.chercherStationByNom(nomStationDepart);
            Station stationArrivee=catalogueStation.chercherStationByNom(nomStationArrivee);

            Voyage nvVoyage = new ServiceVoyage().ajouterVoyage(bus,stationDepart,Arrets,stationArrivee,heureDepart,heureArrivee,prix,numeroLigne);
            catalogueVoyage.ajouterVoyage(nvVoyage);
            return nvVoyage;
        }
        catch (DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }
        catch (StationIntrouvable e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public void ajouterBus(String matricule,int capacite)
    {
        try {
            catalogueBus.ajouterBus(new Bus(capacite,matricule));
            new ServiceBus().ajouterBus(matricule,capacite);
        }
        catch (BusDejaExiste e){
            e.printStackTrace();
        }

    }

    public void ajouterStation(String nom, String adresse, float latitude, float longitude)
    {
        Station station=new Station(nom,adresse,latitude,longitude);
        catalogueStation.ajouterStation(station);
        new DAOStation().ajouterStation(station);
    }

    public void ajouterConducteur(String matricule, String nom, String prenom, String cin, String dateNaissance)
    {
        try {
            catalogueConducteur.ajouterConducteur(new Conducteur(matricule,nom,prenom,cin,new Date(dateNaissance)
            ));
        }
        catch (ConducteurDejaExiste e) {
            e.printStackTrace();

        }
    }




    public void supprimerBus(String matricule)
    {
        catalogueBus.supprimerBus(matricule);
    }

    public void afficherReservation(int voyageId) {
        try {
            Voyage voyage = catalogueVoyage.chercherVoyageByID(voyageId);
            System.out.println("Les reservations pour le voyage " + voyageId + " sont :");
            for (Reservation r : voyage.getReservations()) {
                System.out.println(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
