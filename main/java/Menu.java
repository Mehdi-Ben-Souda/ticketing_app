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



        //adding conducteurs
        controleur.ajouterConducteur("1234","nom1","prenom1","cin1","2000/01/01");
        controleur.ajouterConducteur("1235","nom2","prenom2","cin2","2001/02/01");
        controleur.ajouterConducteur("1236","nom3","prenom3","cin3","2002/03/01");
        controleur.ajouterConducteur("1237","nom4","prenom4","cin4","2003/04/01");
        controleur.ajouterConducteur("1238","nom5","prenom5","cin5","2004/05/01");


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
                System.out.println("4. ajouter bus");
                System.out.println("5. ajouter station");
                System.out.println("6. ajouter voyage");
                System.out.println("0. Exit");
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        // Test reserver method
                        System.out.println("Enter station departure:");
                        String stationDepart = scanner.nextLine();
                        System.out.println("Enter voyage id:");
                        int voyage = scanner.nextInt();
                        scanner.nextLine();
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
                    case 0://exit
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                        break;
                    case 4:
                        System.out.println("Enter the matricule : ");
                        String str=scanner.nextLine();
                        System.out.println("capacite : ");
                        int capacite =scanner.nextInt();
                        scanner.nextLine();
                        menu.controleur.ajouterBus(str,capacite);
                        break;
                    case 5:
                        System.out.println("Enter the nom : ");
                        String nom=scanner.nextLine();
                        System.out.println("Enter the adresse : ");
                        String adresse=scanner.nextLine();
                        System.out.println("Enter the latitude : ");
                        float latitude=scanner.nextFloat();
                        scanner.nextLine();
                        System.out.println("Enter the longitude : ");
                        float longitude= scanner.nextFloat();
                        scanner.nextLine();
                        menu.controleur.ajouterStation(nom,adresse,latitude,longitude);
                        break;
                    case 6:
                        System.out.println("Enter the matricule bus : ");
                        String matriculeBus=scanner.nextLine();
                        System.out.println("Enter the matricule conducteur : ");
                        String matriculeConducteur=scanner.nextLine();
                        System.out.println("Enter the nom station depart : ");
                        String nomStationDepart=scanner.nextLine();
                        System.out.println("Enter the nom station arrivee : ");
                        String nomStationArrivee=scanner.nextLine();
                        System.out.println("enter the price");
                        float prix=scanner.nextFloat();
                        scanner.nextLine();
                        System.out.println("enter the line number");
                        String numeroLigne=scanner.nextLine();
                        System.out.println("enter the departure time");
                        String heureDepart=scanner.nextLine();
                        System.out.println("enter the arrivee time");
                        String heureArrivee=scanner.nextLine();
                        System.out.println("do you want to enter the arrets(yes/no)");
                        ArrayList<Arret> listArret=null;
                        if (scanner.nextLine().equals("yes")){
                            listArret=new ArrayList<Arret>();
                            while(true){
                                System.out.println("enter the station name");
                                String stationName=scanner.nextLine();
                                System.out.println("enter the time");
                                String time=scanner.nextLine();
                                listArret.add(new Arret(time,menu.controleur.catalogueStation.chercherStationByNom(stationName)));
                                System.out.println("do you want to enter another arret(no/yes)");
                                String rep=scanner.nextLine();
                                if(rep.equals("no")){
                                    break;
                                }
                            }
                        }

                        Voyage vg= menu.controleur.ajouterVoyage(matriculeBus,matriculeConducteur,
                                heureDepart,heureArrivee,nomStationDepart,nomStationArrivee,listArret,prix,numeroLigne);
                        vg.getArrets().forEach(arret ->arret.setVoyage(vg));
                        break;
                    default:
                        System.out.println("Invalid option. Please choose 1, 2 or 3.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
