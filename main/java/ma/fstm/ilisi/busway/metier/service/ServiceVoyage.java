package ma.fstm.ilisi.busway.metier.service;


import ma.fstm.ilisi.busway.metier.bo.*;

import java.util.ArrayList;

public class ServiceVoyage {

    public Voyage ajouterVoyage(int idVoyage,Bus bus , Station depart, ArrayList<Arret> arrets, Station arrivée, String heureDepart,
                                String heureArrivée, float prix, String numeroLigne) throws Exception {
        Voyage voyage = new Voyage(idVoyage,heureDepart,heureArrivée,prix,numeroLigne,depart,arrets,arrivée,bus);

        bus.getVoyages().add(voyage);
        //ajouter le voyage dans la base de données
        return voyage;
    }

    public int getDescendu_now(Voyage voyage) {
        return -1;
    }

}
