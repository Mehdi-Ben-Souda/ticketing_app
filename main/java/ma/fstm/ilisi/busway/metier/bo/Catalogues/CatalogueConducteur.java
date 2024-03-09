package ma.fstm.ilisi.busway.metier.bo.Catalogues;

import ma.fstm.ilisi.busway.metier.bo.Conducteur;
import ma.fstm.ilisi.busway.metier.exceptions.ConducteurDejaExiste;

import java.util.HashMap;

public class CatalogueConducteur {

    public HashMap<String, Conducteur> collection = new HashMap<String,Conducteur>();

    public void ajouterConducteur(Conducteur conducteur) throws ConducteurDejaExiste
    {
        if(collection.get(conducteur.getMatricule())!=null)
            throw new ConducteurDejaExiste();

        collection.put(conducteur.getMatricule(), conducteur);
    }
    public Conducteur chercherConducteurByMatricule(String matricule)
    {
        return collection.get(matricule);
    }



}
