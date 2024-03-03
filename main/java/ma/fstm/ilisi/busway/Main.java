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



        Depart depart1 = new Depart(LocalTime.parse("12:00"), station1);
        Arrivée arrivée1 = new Arrivée(LocalTime.parse("12:50"), station10);

        ArrayList<Arret> lesArrets = new ArrayList<Arret>();
        lesArrets.add(new Arret(LocalTime.parse("12:10"), station2));
        lesArrets.add(new Arret(LocalTime.parse("12:20"), station7));
        lesArrets.add(new Arret(LocalTime.parse("12:30"), station4));
        lesArrets.add(new Arret(LocalTime.parse("12:40"), station15));

        System.out.println(lesArrets);
        Voyage voyage1 = new Voyage(1, new Date("12/12/2020"), 8, "32D", depart1, arrivée1,
                lesArrets, catalogueBus.chercherBusByMatricule("1234"));


        Depart depart2=new Depart(LocalTime.parse("14:00"),station15);
        Arrivée arrivée2=new Arrivée(LocalTime.parse("14:50"),station13);
        ArrayList<Arret> lesArrets2 = new ArrayList<Arret>();
        lesArrets2.add(new Arret(LocalTime.parse("14:10"),station4));
        lesArrets2.add(new Arret(LocalTime.parse("14:20"),station1));
        lesArrets2.add(new Arret(LocalTime.parse("14:30"),station2));
        lesArrets2.add(new Arret(LocalTime.parse("14:40"),station7));



        Voyage voyage2=new Voyage(2,new Date("12/12/2020"),8,"800",depart2,arrivée2,
                lesArrets2,catalogueBus.chercherBusByMatricule("5678"));




        depart1.setVoyage(voyage1);
        arrivée1.setVoyage(voyage1);
        for (Arret arret : lesArrets) {
            arret.setVoyage(voyage1);
        }
        depart2.setVoyage(voyage2);
        arrivée2.setVoyage(voyage2);
        for (Arret arret : lesArrets2) {
            arret.setVoyage(voyage2);
        }


        CatalogueVoyage catalogueVoyage = new CatalogueVoyage();

        catalogueVoyage.ajouterVoyage(voyage1);
        catalogueVoyage.ajouterVoyage(voyage2);

        Reservation reservation1 = new Reservation(station1, station7, LocalTime.now());
        Reservation reservation2 = new Reservation(station1, station7, LocalTime.now());
        Reservation reservation3 = new Reservation(station1, station7, LocalTime.now());

        Reservation reservation4 = new Reservation(station1, station7, LocalTime.now());


        voyage1.ajouterReservation(reservation1);
        voyage1.ajouterReservation(reservation2);
        voyage1.ajouterReservation(reservation3);

        voyage2.ajouterReservation(reservation4);

        //System.out.println(voyage1.verifierDisponibilite(station7, station1));

        Controleur c = new Controleur(catalogueBus,catalogueStation,catalogueVoyage);
        c.LesBusDisponibles("Station1","Station7",new Date("12/12/2020"));


    }
}