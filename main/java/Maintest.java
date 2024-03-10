import ma.fstm.ilisi.busway.dao.DAOBus;
import ma.fstm.ilisi.busway.dao.DAOConducteur;
import ma.fstm.ilisi.busway.dao.DAOStation;
import ma.fstm.ilisi.busway.dao.DAOVoyage;
import ma.fstm.ilisi.busway.metier.bo.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Maintest {
    public static void main(String[] args) {
        Bus bus=new Bus(25, "15535");
      // new DAOBus().ajouterBus(bus);
        //new DAOConducteur().ajouterConducteur(new Conducteur("1233","bensouda","mehdi", "L12233", new Date(2000, 11, 21)));
        Station station1 = new Station("Station1", "Adresse1", 33, 33);
        Station station2 = new Station("Station2", "Adresse2", 13, 73);
        Station station3 = new Station("Station3", "Adresse3", 23, 63);
        Station station4 = new Station("Station4", "Adresse4", 43, 53);
        Station station5 = new Station("Station5", "Adresse5", 53, 43);
        ArrayList< Arret> arrets = new ArrayList<Arret>();
        Voyage voyage=new Voyage(0,"12:00","14:00",8,"32D-A",station1,null,station5,bus);
        arrets.add(new Arret("12:00", station2,voyage));
        arrets.add(new Arret("12:30", station3,voyage));
        arrets.add(new Arret("13:00", station4,voyage));
        voyage.setArrets(arrets);
        //new DAOVoyage().ajouterVoyage(voyage);


        ArrayList< Arret> arrets2 = new ArrayList<Arret>();

        Voyage voyage2=new Voyage(0,"16:00","17:00",8,"32D-R",station5,null,station1,bus);
        arrets2.add(new Arret("12:00", station4,voyage));
        arrets2.add(new Arret("12:30", station3,voyage));
        arrets2.add(new Arret("13:00", station2,voyage));
        voyage2.setArrets(arrets2);
        //new DAOVoyage().ajouterVoyage(voyage2);
        //int v=new DAOVoyage().getDescendu_now(voyage);
        //System.out.println(v);
//        TreeMap<String,Station> allsta=new DAOStation().retrieveAllStations();
//        allsta.forEach((k,v)->{
//            System.out.println(v);
//        });
        Map<Integer,Voyage> map=new DAOVoyage().retrieveAllVoyages();
        for (Map.Entry<Integer, Voyage> entry : map.entrySet()) {
            Integer k = entry.getKey();
            Voyage v = entry.getValue();
            for (Arret a : v.getArrets()) {
                System.out.println(a);
            }
        }
    }
}
