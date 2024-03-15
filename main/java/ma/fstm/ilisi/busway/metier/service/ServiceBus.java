package ma.fstm.ilisi.busway.metier.service;

import ma.fstm.ilisi.busway.dao.DAOBus;
import ma.fstm.ilisi.busway.metier.bo.Bus;

public class ServiceBus {

    public Bus ajouterBus(String matricule,int capacite) {
        Bus bus=new Bus(capacite,matricule);
        new DAOBus().ajouterBus(bus);
        return  bus;
    }
}
