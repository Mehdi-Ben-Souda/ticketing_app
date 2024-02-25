package ma.fstm.ilisi.busway.metier.bo;
import java.util.*;
import java.time.*;

/**
 * 
 */
public class Arret {
    private LocalTime heureArret;
    private Station station;
    private Voyage voyage;
    public Arret(String heureArret,Station station,Voyage voyage) {
        this.heureArret = LocalTime.parse(heureArret);
        this.station = station;
        this.voyage = voyage;
    }


}