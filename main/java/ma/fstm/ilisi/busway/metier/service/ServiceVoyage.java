package ma.fstm.ilisi.busway.metier.service;

import ma.fstm.ilisi.busway.metier.bo.Arret;
import ma.fstm.ilisi.busway.metier.bo.Arrivée;
import ma.fstm.ilisi.busway.metier.bo.Bus;
import ma.fstm.ilisi.busway.metier.bo.Depart;

import java.util.ArrayList;
import java.util.Date;

public class ServiceVoyage {

    public void ajouterVoyage(Bus bus , Date date,
                              Depart depart, Arrivée arrivée, ArrayList<Arret> arrets)
    {
        //verifier si le bus a un voyage avec la meme date et presque les meme heures

    }
}
