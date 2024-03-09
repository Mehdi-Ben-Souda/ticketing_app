package ma.fstm.ilisi.busway;

import ma.fstm.ilisi.busway.controleur.Controleur;
import ma.fstm.ilisi.busway.metier.bo.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;


public class Main {
    public static void main(String[] args) {
        CatalogueBus catalogueBus = new CatalogueBus();
        catalogueBus.ajouterBus(new Bus(4, "1234"));
        catalogueBus.ajouterBus(new Bus(20, "5678"));
        catalogueBus.ajouterBus(new Bus(25, "91011"));
        catalogueBus.ajouterBus(new Bus(50, "121314"));

        CatalogueStation catalogueStation = new CatalogueStation();
        catalogueStation.ajouterStation(new Station("Station1", "Adresse1"));
        catalogueStation.ajouterStation(new Station("Station2", "Adresse2"));
        catalogueStation.ajouterStation(new Station("Station3", "Adresse3"));
        catalogueStation.ajouterStation(new Station("Station4", "Adresse4"));
        catalogueStation.ajouterStation(new Station("Station5", "Adresse5"));
        catalogueStation.ajouterStation(new Station("Station6", "Adresse6"));
        catalogueStation.ajouterStation(new Station("Station7", "Adresse7"));
        catalogueStation.ajouterStation(new Station("Station8", "Adresse8"));
        catalogueStation.ajouterStation(new Station("Station9", "Adresse9"));
        catalogueStation.ajouterStation(new Station("Station10", "Adresse10"));
        catalogueStation.ajouterStation(new Station("Station11", "Adresse11"));
        catalogueStation.ajouterStation(new Station("Station12", "Adresse12"));
        catalogueStation.ajouterStation(new Station("Station13", "Adresse13"));
        catalogueStation.ajouterStation(new Station("Station14", "Adresse14"));
        catalogueStation.ajouterStation(new Station("Station15", "Adresse15"));


        Station station1=catalogueStation.chercherStationByNom("Station1");
        Station station2=catalogueStation.chercherStationByNom("Station2");
        Station station7=catalogueStation.chercherStationByNom("Station7");
        Station station4=catalogueStation.chercherStationByNom("Station4");
        Station station15=catalogueStation.chercherStationByNom("Station15");
        Station station10=catalogueStation.chercherStationByNom("Station10");
        Station station11=catalogueStation.chercherStationByNom("Station11");
        Station station12=catalogueStation.chercherStationByNom("Station12");
        Station station13=catalogueStation.chercherStationByNom("Station13");


        Voyage voyage1 = new Voyage(1,"12:00","12:50",8, "32D", station1, station10,catalogueBus.chercherBusByMatricule("1234"));

        ArrayList<Arret> lesArrets = new ArrayList<Arret>();
        lesArrets.add(new Arret("12:10", station2,voyage1));
        lesArrets.add(new Arret("12:20", station7,voyage1));
        lesArrets.add(new Arret("12:30", station4,voyage1));
        lesArrets.add(new Arret("12:40",station15,voyage1));

        voyage1.setArrets(lesArrets);
        for(Arret a:lesArrets)
        {
            a.getStation().ajouterArret(a);
        }

        System.out.println(lesArrets);



        Voyage voyage2=new Voyage(2,"14:00","14:50",8,"800",station13,station15,catalogueBus.chercherBusByMatricule("5678"));

        ArrayList<Arret> lesArrets2 = new ArrayList<Arret>();
        lesArrets2.add(new Arret("14:10",station4,voyage2));
        lesArrets2.add(new Arret("14:20",station1,voyage2));
        lesArrets2.add(new Arret("14:30",station2,voyage2));
        lesArrets2.add(new Arret("14:40",station7,voyage2));
        voyage2.setArrets(lesArrets2);

        for(Arret a:lesArrets2)
        {
            a.getStation().ajouterArret(a);
        }

        CatalogueVoyage catalogueVoyage = new CatalogueVoyage();

        catalogueVoyage.ajouterVoyage(voyage1);
        catalogueVoyage.ajouterVoyage(voyage2);

        /*
        Reservation reservation1 = new Reservation(station1, station7, LocalTime.now());
        Reservation reservation2 = new Reservation(station1, station7, LocalTime.now());
        Reservation reservation3 = new Reservation(station1, station7, LocalTime.now());

        Reservation reservation4 = new Reservation(station1, station7, LocalTime.now());
        */

        /*voyage1.ajouterReservation(reservation1);
        voyage1.ajouterReservation(reservation2);
        voyage1.ajouterReservation(reservation3);

        voyage2.ajouterReservation(reservation4);*/

        //System.out.println(voyage1.verifierDisponibilite(station7, station1));

        Controleur c = new Controleur(catalogueBus,catalogueStation,catalogueVoyage);
        c.reserver("Station1","Station7",new Passager("Jebbanema","Mohammed","Bh1234",new Date()),1);
        c.reserver("Station1","Station7",new Passager("Ben Souda","EL MHEDI SALAH","Bh1234",new Date()),1);
        c.LesBusDisponibles("Station1","Station7");

    }
}