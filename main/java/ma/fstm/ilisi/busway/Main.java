package ma.fstm.ilisi.busway;

import ma.fstm.ilisi.busway.metier.bo.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;


public class Main {
    public static void main(String[] args) {
        CatalogueBus catalogueBus = new CatalogueBus();
        catalogueBus.ajouterBus(new Bus(3, "1234"));
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


        Depart depart1 = new Depart(LocalTime.parse("12:00"), station1);
        Arrivée arrivée1 = new Arrivée(LocalTime.parse("12:50"), station10);

        ArrayList<Arret> lesArrets = new ArrayList<Arret>();
        lesArrets.add(new Arret(LocalTime.parse("12:10"), station2));
        lesArrets.add(new Arret(LocalTime.parse("12:20"), station7));
        lesArrets.add(new Arret(LocalTime.parse("12:30"), station4));
        lesArrets.add(new Arret(LocalTime.parse("12:40"), station15));

        Voyage voyage1 = new Voyage(1, new Date("12/12/2020"), 8, "32D", depart1, arrivée1,
                lesArrets, catalogueBus.chercherBusByMatricule("1234"));

        depart1.setVoyage(voyage1);
        arrivée1.setVoyage(voyage1);
        for (Arret arret : lesArrets) {
            arret.setVoyage(voyage1);
        }

        CatalogueVoyage catalogueVoyage = new CatalogueVoyage();

        catalogueVoyage.ajouterVoyage(voyage1);

        Reservation reservation1 = new Reservation(station1, station7, LocalTime.now());
        Reservation reservation2 = new Reservation(station1, station7, LocalTime.now());
        Reservation reservation3 = new Reservation(station1, station7, LocalTime.now());


        voyage1.ajouterReservation(reservation1);
        voyage1.ajouterReservation(reservation2);
        voyage1.ajouterReservation(reservation3);

        System.out.println(voyage1.verifierDisponibilite(station2, station15));




    }
}
