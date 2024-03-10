/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.fstm.ilisi.busway.metier.service;

import ma.fstm.ilisi.busway.metier.bo.*;

import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author Jebbanema
 */
public class ServiceReservation
{
    public void reserver(Station stationD, Passager passager, Voyage voyage)
    {
        //verifier la disponibilite du bus pour le voyage entre les stations stationD et stationA
        if(voyage.verifierDisponibilite(stationD))
        {
            Reservation reservation=new Reservation(stationD,LocalTime.now());
            voyage.ajouterReservation(reservation);
            stationD.ajouterReservation(reservation);
            //to do :appel a DAOReservation
        }
        //to do : si non lancer une exception
    }
}
