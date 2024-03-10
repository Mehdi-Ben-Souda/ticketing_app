import ma.fstm.ilisi.busway.controleur.Controleur;
import ma.fstm.ilisi.busway.metier.bo.*;
import ma.fstm.ilisi.busway.metier.bo.Catalogues.*;
import ma.fstm.ilisi.busway.metier.exceptions.BusDejaExiste;
import ma.fstm.ilisi.busway.metier.exceptions.StationIntrouvable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Menu {

    Controleur controleur;
/*    CatalogueBus catalogueBus;
    CatalogueConducteur catalogueConducteur;
    CatalogueStation catalogueStation;
    CatalogueVoyage catalogueVoyage;*/

    Menu() throws BusDejaExiste, StationIntrouvable {
        controleur = new Controleur();
        controleur.ajouterBus("1234",50);
        controleur.ajouterBus("1235",2);
        controleur.ajouterBus("1236",50);
        controleur.ajouterBus("1237",50);
        controleur.ajouterBus("1238",50);


        //adding conducteurs
        controleur.ajouterConducteur("1234","nom1","prenom1","cin1","2000/01/01");
        controleur.ajouterConducteur("1235","nom2","prenom2","cin2","2001/02/01");
        controleur.ajouterConducteur("1236","nom3","prenom3","cin3","2002/03/01");
        controleur.ajouterConducteur("1237","nom4","prenom4","cin4","2003/04/01");
        controleur.ajouterConducteur("1238","nom5","prenom5","cin5","2004/05/01");

        //adding stations
        controleur.ajouterStation("Station1","Adresse1");
        controleur.ajouterStation("Station2","Adresse2");
        controleur.ajouterStation("Station3","Adresse3");
        controleur.ajouterStation("Station4","Adresse4");
        controleur.ajouterStation("Station5","Adresse5");
        controleur.ajouterStation("Station6","Adresse6");
        controleur.ajouterStation("Station7","Adresse7");
        controleur.ajouterStation("Station8","Adresse8");
        controleur.ajouterStation("Station9","Adresse9");
        controleur.ajouterStation("Station10","Adresse10");
        controleur.ajouterStation("Station11","Adresse11");
        controleur.ajouterStation("Station12","Adresse12");
        controleur.ajouterStation("Station13","Adresse13");
        controleur.ajouterStation("Station14","Adresse14");
        controleur.ajouterStation("Station15","Adresse15");

        //adding voyages

        controleur.ajouterVoyage(1,"1234","1234","12:00","13:00","Station1","Station15",null,8,"32D");
        controleur.ajouterVoyage(2,"1235","1235","14:00","14:50","Station5","Station12",null,6,"800");
        controleur.ajouterVoyage(3,"1236","1236","16:00","16:40","Station6","Station10",null,8,"900");
        controleur.ajouterVoyage(4,"1237","1237","18:00","18:40","Station3","Station15",null,5,"11");
        controleur.ajouterVoyage(5,"1238","1238","20:00","20:50","Station4","Station11",null,5,"67");
        controleur.ajouterVoyage(6,"1234","1234","10:00","11:00","Station15","Station1",null,8,"32D");
        controleur.ajouterVoyage(7,"1235","1235","07:00","07:50","Station12","Station5",null,6,"800");
        controleur.ajouterVoyage(8,"1236","1236","08:00","08:40","Station10","Station6",null,8,"900");
        controleur.ajouterVoyage(9,"1237","1237","09:00","09:40","Station15","Station3",null,5,"11");
        controleur.ajouterVoyage(10,"1238","1238","11:00","11:50","Station11","Station4",null,5,"67");

        //creation des 5 lignes
        ArrayList<Arret> aLigne32D = new ArrayList<Arret>();
        ArrayList<Arret> rLigne32D = new ArrayList<Arret>();
        ArrayList<Arret> aLigne800 = new ArrayList<Arret>();
        ArrayList<Arret> rLigne800 = new ArrayList<Arret>();
        ArrayList<Arret> aLigne900 = new ArrayList<Arret>();
        ArrayList<Arret> rLigne900 = new ArrayList<Arret>();
        ArrayList<Arret> aLigne11 = new ArrayList<Arret>();
        ArrayList<Arret> rLigne11 = new ArrayList<Arret>();
        ArrayList<Arret> aLigne67 = new ArrayList<Arret>();
        ArrayList<Arret> rLigne67 = new ArrayList<Arret>();

        aLigne32D.add(new Arret("12:10",controleur.catalogueStation.chercherStationByNom("Station2"),controleur.catalogueVoyage.chercherVoyageByID(1)));
        aLigne32D.add(new Arret("12:20",controleur.catalogueStation.chercherStationByNom("Station3"),controleur.catalogueVoyage.chercherVoyageByID(1)));
        aLigne32D.add(new Arret("12:30",controleur.catalogueStation.chercherStationByNom("Station4"),controleur.catalogueVoyage.chercherVoyageByID(1)));
        aLigne32D.add(new Arret("12:40",controleur.catalogueStation.chercherStationByNom("Station5"),controleur.catalogueVoyage.chercherVoyageByID(1)));
        aLigne32D.add(new Arret("12:50",controleur.catalogueStation.chercherStationByNom("Station10"),controleur.catalogueVoyage.chercherVoyageByID(1)));

        rLigne32D.add(new Arret("10:10",controleur.catalogueStation.chercherStationByNom("Station10"),controleur.catalogueVoyage.chercherVoyageByID(6)));
        rLigne32D.add(new Arret("10:20",controleur.catalogueStation.chercherStationByNom("Station5"),controleur.catalogueVoyage.chercherVoyageByID(6)));
        rLigne32D.add(new Arret("10:30",controleur.catalogueStation.chercherStationByNom("Station4"),controleur.catalogueVoyage.chercherVoyageByID(6)));
        rLigne32D.add(new Arret("10:40",controleur.catalogueStation.chercherStationByNom("Station3"),controleur.catalogueVoyage.chercherVoyageByID(6)));
        rLigne32D.add(new Arret("10:50",controleur.catalogueStation.chercherStationByNom("Station2"),controleur.catalogueVoyage.chercherVoyageByID(6)));

        aLigne800.add(new Arret("14:10",controleur.catalogueStation.chercherStationByNom("Station4"),controleur.catalogueVoyage.chercherVoyageByID(2)));
        aLigne800.add(new Arret("14:20",controleur.catalogueStation.chercherStationByNom("Station3"),controleur.catalogueVoyage.chercherVoyageByID(2)));
        aLigne800.add(new Arret("14:30",controleur.catalogueStation.chercherStationByNom("Station2"),controleur.catalogueVoyage.chercherVoyageByID(2)));
        aLigne800.add(new Arret("14:40",controleur.catalogueStation.chercherStationByNom("Station7"),controleur.catalogueVoyage.chercherVoyageByID(2)));

        rLigne800.add(new Arret("07:10",controleur.catalogueStation.chercherStationByNom("Station7"),controleur.catalogueVoyage.chercherVoyageByID(7)));
        rLigne800.add(new Arret("07:20",controleur.catalogueStation.chercherStationByNom("Station2"),controleur.catalogueVoyage.chercherVoyageByID(7)));
        rLigne800.add(new Arret("07:30",controleur.catalogueStation.chercherStationByNom("Station3"),controleur.catalogueVoyage.chercherVoyageByID(7)));
        rLigne800.add(new Arret("07:40",controleur.catalogueStation.chercherStationByNom("Station4"),controleur.catalogueVoyage.chercherVoyageByID(7)));

        aLigne900.add(new Arret("16:10",controleur.catalogueStation.chercherStationByNom("Station7"),controleur.catalogueVoyage.chercherVoyageByID(3)));
        aLigne900.add(new Arret("16:20",controleur.catalogueStation.chercherStationByNom("Station8"),controleur.catalogueVoyage.chercherVoyageByID(3)));
        aLigne900.add(new Arret("16:30",controleur.catalogueStation.chercherStationByNom("Station9"),controleur.catalogueVoyage.chercherVoyageByID(3)));

        rLigne900.add(new Arret("08:10",controleur.catalogueStation.chercherStationByNom("Station9"),controleur.catalogueVoyage.chercherVoyageByID(8)));
        rLigne900.add(new Arret("08:20",controleur.catalogueStation.chercherStationByNom("Station8"),controleur.catalogueVoyage.chercherVoyageByID(8)));
        rLigne900.add(new Arret("08:30",controleur.catalogueStation.chercherStationByNom("Station7"),controleur.catalogueVoyage.chercherVoyageByID(8)));

        aLigne11.add(new Arret("18:10",controleur.catalogueStation.chercherStationByNom("Station8"),controleur.catalogueVoyage.chercherVoyageByID(4)));
        aLigne11.add(new Arret("18:20",controleur.catalogueStation.chercherStationByNom("Station13"),controleur.catalogueVoyage.chercherVoyageByID(4)));
        aLigne11.add(new Arret("18:30",controleur.catalogueStation.chercherStationByNom("Station14"),controleur.catalogueVoyage.chercherVoyageByID(4)));

        rLigne11.add(new Arret("09:00",controleur.catalogueStation.chercherStationByNom("Station14"),controleur.catalogueVoyage.chercherVoyageByID(9)));
        rLigne11.add(new Arret("09:10",controleur.catalogueStation.chercherStationByNom("Station13"),controleur.catalogueVoyage.chercherVoyageByID(9)));
        rLigne11.add(new Arret("09:20",controleur.catalogueStation.chercherStationByNom("Station8"),controleur.catalogueVoyage.chercherVoyageByID(9)));

        aLigne67.add(new Arret("20:10",controleur.catalogueStation.chercherStationByNom("Station9"),controleur.catalogueVoyage.chercherVoyageByID(5)));
        aLigne67.add(new Arret("20:20",controleur.catalogueStation.chercherStationByNom("Station8"),controleur.catalogueVoyage.chercherVoyageByID(5)));
        aLigne67.add(new Arret("20:30",controleur.catalogueStation.chercherStationByNom("Station7"),controleur.catalogueVoyage.chercherVoyageByID(5)));
        aLigne67.add(new Arret("20:40",controleur.catalogueStation.chercherStationByNom("Station6"),controleur.catalogueVoyage.chercherVoyageByID(5)));

        rLigne67.add(new Arret("11:10",controleur.catalogueStation.chercherStationByNom("Station6"),controleur.catalogueVoyage.chercherVoyageByID(10)));
        rLigne67.add(new Arret("11:20",controleur.catalogueStation.chercherStationByNom("Station7"),controleur.catalogueVoyage.chercherVoyageByID(10)));
        rLigne67.add(new Arret("11:30",controleur.catalogueStation.chercherStationByNom("Station8"),controleur.catalogueVoyage.chercherVoyageByID(10)));
        rLigne67.add(new Arret("11:40",controleur.catalogueStation.chercherStationByNom("Station9"),controleur.catalogueVoyage.chercherVoyageByID(10)));

        //ajouter les arrets dans leurs voyages
        controleur.catalogueVoyage.chercherVoyageByID(1).setArrets(aLigne32D);
        controleur.catalogueVoyage.chercherVoyageByID(2).setArrets(aLigne800);
        controleur.catalogueVoyage.chercherVoyageByID(3).setArrets(aLigne900);
        controleur.catalogueVoyage.chercherVoyageByID(4).setArrets(aLigne11);
        controleur.catalogueVoyage.chercherVoyageByID(5).setArrets(aLigne67);
        controleur.catalogueVoyage.chercherVoyageByID(6).setArrets(rLigne32D);
        controleur.catalogueVoyage.chercherVoyageByID(7).setArrets(rLigne800);
        controleur.catalogueVoyage.chercherVoyageByID(8).setArrets(rLigne900);
        controleur.catalogueVoyage.chercherVoyageByID(9).setArrets(rLigne11);
        controleur.catalogueVoyage.chercherVoyageByID(10).setArrets(rLigne67);

        //ajouter les arrets dans les stations
        for(Arret a:aLigne32D)
        {
            a.getStation().ajouterArret(a);
        }
        for(Arret a:rLigne32D)
        {
            a.getStation().ajouterArret(a);
        }
        for(Arret a:aLigne800)
        {
            a.getStation().ajouterArret(a);
        }
        for(Arret a:rLigne800)
        {
            a.getStation().ajouterArret(a);
        }
        for(Arret a:aLigne900)
        {
            a.getStation().ajouterArret(a);
        }
        for(Arret a:rLigne900)
        {
            a.getStation().ajouterArret(a);
        }
        for(Arret a:aLigne11)
        {
            a.getStation().ajouterArret(a);
        }
        for(Arret a:rLigne11)
        {
            a.getStation().ajouterArret(a);
        }
        for (Arret a:aLigne67)
        {
            a.getStation().ajouterArret(a);
        }
        for (Arret a:rLigne67)
        {
            a.getStation().ajouterArret(a);
        }

        //ajouter les voyages dans les stations
        controleur.catalogueStation.chercherStationByNom("Station1").ajouterVoyageDepart(controleur.catalogueVoyage.chercherVoyageByID(1));
        controleur.catalogueStation.chercherStationByNom("Station5").ajouterVoyageDepart(controleur.catalogueVoyage.chercherVoyageByID(2));
        controleur.catalogueStation.chercherStationByNom("Station6").ajouterVoyageDepart(controleur.catalogueVoyage.chercherVoyageByID(3));
        controleur.catalogueStation.chercherStationByNom("Station3").ajouterVoyageDepart(controleur.catalogueVoyage.chercherVoyageByID(4));
        controleur.catalogueStation.chercherStationByNom("Station4").ajouterVoyageDepart(controleur.catalogueVoyage.chercherVoyageByID(5));
        controleur.catalogueStation.chercherStationByNom("Station15").ajouterVoyageDepart(controleur.catalogueVoyage.chercherVoyageByID(6));
        controleur.catalogueStation.chercherStationByNom("Station12").ajouterVoyageDepart(controleur.catalogueVoyage.chercherVoyageByID(7));
        controleur.catalogueStation.chercherStationByNom("Station10").ajouterVoyageDepart(controleur.catalogueVoyage.chercherVoyageByID(8));
        controleur.catalogueStation.chercherStationByNom("Station15").ajouterVoyageDepart(controleur.catalogueVoyage.chercherVoyageByID(9));
        controleur.catalogueStation.chercherStationByNom("Station11").ajouterVoyageDepart(controleur.catalogueVoyage.chercherVoyageByID(10));

        controleur.catalogueStation.chercherStationByNom("Station15").ajouterVoyageArrivee(controleur.catalogueVoyage.chercherVoyageByID(1));
        controleur.catalogueStation.chercherStationByNom("Station12").ajouterVoyageArrivee(controleur.catalogueVoyage.chercherVoyageByID(2));
        controleur.catalogueStation.chercherStationByNom("Station10").ajouterVoyageArrivee(controleur.catalogueVoyage.chercherVoyageByID(3));
        controleur.catalogueStation.chercherStationByNom("Station15").ajouterVoyageArrivee(controleur.catalogueVoyage.chercherVoyageByID(4));
        controleur.catalogueStation.chercherStationByNom("Station11").ajouterVoyageArrivee(controleur.catalogueVoyage.chercherVoyageByID(5));
        controleur.catalogueStation.chercherStationByNom("Station1").ajouterVoyageArrivee(controleur.catalogueVoyage.chercherVoyageByID(6));
        controleur.catalogueStation.chercherStationByNom("Station5").ajouterVoyageArrivee(controleur.catalogueVoyage.chercherVoyageByID(7));
        controleur.catalogueStation.chercherStationByNom("Station6").ajouterVoyageArrivee(controleur.catalogueVoyage.chercherVoyageByID(8));
        controleur.catalogueStation.chercherStationByNom("Station3").ajouterVoyageArrivee(controleur.catalogueVoyage.chercherVoyageByID(9));
        controleur.catalogueStation.chercherStationByNom("Station4").ajouterVoyageArrivee(controleur.catalogueVoyage.chercherVoyageByID(10));

    }


    public static void main(String[] args) {
        //menu pour tester la fonctionaliter de reserver et busdisponible
        try {
            Menu menu = new Menu();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. reserver");
                System.out.println("2. afficher busDisponible");
                System.out.println("3. afficher les reservations d'un voyage");
                System.out.println("4. Exit");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        // Test reserver method
                        System.out.println("Enter station departure:");
                        String stationDepart = scanner.next();
                        /*System.out.println("Enter passenger name:");
                        String name = scanner.next();
                        System.out.println("Enter passenger surname:");
                        String surname = scanner.next();
                        System.out.println("Enter passenger cin:");
                        String cin = scanner.next();
                        System.out.println("Enter passenger birth date (yyyy-MM-dd):");
                        String birthDate = scanner.next();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdf.parse(birthDate);*/
                        System.out.println("Enter voyage id:");
                        int voyage = scanner.nextInt();
                        menu.controleur.reserver(stationDepart, new Passager(),voyage);
                        break;
                    case 2:
                        // Test busDisponible method
                        System.out.println("Enter station departure:");
                        String stationDepartBus = scanner.next();
                        System.out.println("Enter line Number:");
                        String lineNumber = scanner.next();
                        menu.controleur.LesBusDisponibles(stationDepartBus, lineNumber);
                        break;
                    case 3://afficher les reservations d'un voyage
                        System.out.println("Enter voyage id:");
                        int voyageId = scanner.nextInt();
                        menu.controleur.afficherReservation(voyageId);
                        break;
                    case 4://exit
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please choose 1, 2 or 3.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
