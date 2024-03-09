import ma.fstm.ilisi.busway.controleur.Controleur;
import ma.fstm.ilisi.busway.metier.bo.*;

public class Menu {

    Controleur controleur;
    CatalogueBus catalogueBus = new CatalogueBus();
    CatalogueStation catalogueStation = new CatalogueStation();
    CatalogueVoyage catalogueVoyage = new CatalogueVoyage();

    Menu(){
        Bus bus1 = new Bus(50,"1234");
        Bus bus2 = new Bus(50,"1235");
        Bus bus3 = new Bus(50,"1236");
        Bus bus4 = new Bus(50,"1237");
        Bus bus5 = new Bus(50,"1238");
        Bus bus6 = new Bus(50,"1239");
        Bus bus7 = new Bus(50,"1240");
        Bus bus8 = new Bus(50,"1241");
        Bus bus9 = new Bus(50,"1242");
        Bus bus10 = new Bus(50,"1243");
        catalogueBus.ajouterBus(bus1);
        catalogueBus.ajouterBus(bus2);
        catalogueBus.ajouterBus(bus3);
        catalogueBus.ajouterBus(bus4);
        catalogueBus.ajouterBus(bus5);
        catalogueBus.ajouterBus(bus6);
        catalogueBus.ajouterBus(bus7);
        catalogueBus.ajouterBus(bus8);
        catalogueBus.ajouterBus(bus9);
        catalogueBus.ajouterBus(bus10);

        Station station1 = new Station("Station1", "Adresse1");
        Station station2 = new Station("Station2", "Adresse2");
        Station station3 = new Station("Station3", "Adresse3");
        Station station4 = new Station("Station4", "Adresse4");
        Station station5 = new Station("Station5", "Adresse5");
        Station station6 = new Station("Station6", "Adresse6");
        Station station7 = new Station("Station7", "Adresse7");
        Station station8 = new Station("Station8", "Adresse8");
        Station station9 = new Station("Station9", "Adresse9");
        Station station10 = new Station("Station10", "Adresse10");
        Station station11 = new Station("Station11", "Adresse11");
        Station station12 = new Station("Station12", "Adresse12");
        Station station13 = new Station("Station13", "Adresse13");
        Station station14 = new Station("Station14", "Adresse14");
        Station station15 = new Station("Station15", "Adresse15");

        catalogueStation.ajouterStation(station1);
        catalogueStation.ajouterStation(station2);
        catalogueStation.ajouterStation(station3);
        catalogueStation.ajouterStation(station4);
        catalogueStation.ajouterStation(station5);
        catalogueStation.ajouterStation(station6);
        catalogueStation.ajouterStation(station7);
        catalogueStation.ajouterStation(station8);
        catalogueStation.ajouterStation(station9);
        catalogueStation.ajouterStation(station10);
        catalogueStation.ajouterStation(station11);
        catalogueStation.ajouterStation(station12);
        catalogueStation.ajouterStation(station13);
        catalogueStation.ajouterStation(station14);
        catalogueStation.ajouterStation(station15);

        Voyage voyage1 = new Voyage(1,"12:00","12:50",8, "32D", station1, station10,catalogueBus.chercherBusByMatricule("1234"));
        Voyage voyage2 = new Voyage(2,"14:00","14:50",8,"800",station13,station15,catalogueBus.chercherBusByMatricule("5678"));



        controleur = new Controleur(catalogueBus, catalogueStation, catalogueVoyage);


    }


    public static void main(String[] args) {


    }
}
