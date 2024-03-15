package ma.fstm.ilisi.busway.metier.service;


import ma.fstm.ilisi.busway.dao.DAOVoyage;
import ma.fstm.ilisi.busway.metier.bo.*;

import java.util.ArrayList;

public class ServiceVoyage {

    public Voyage ajouterVoyage(Bus bus , Station depart, ArrayList<Arret> arrets, Station arrivée, String heureDepart,
                                String heureArrivée, float prix, String numeroLigne) throws Exception {
        Voyage voyage = new Voyage(heureDepart,heureArrivée,prix,numeroLigne,depart,arrets,arrivée,bus);
        int id=new DAOVoyage().ajouterVoyage(voyage);
        voyage.setIdVoyage(id);
        bus.getVoyages().add(voyage);
        return voyage;
    }

    public int getDescendu_now(Voyage voyage) {
        return -1;
    }

}
