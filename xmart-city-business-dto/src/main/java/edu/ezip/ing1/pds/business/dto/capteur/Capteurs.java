package edu.ezip.ing1.pds.business.dto.capteur;


import edu.ezip.ing1.pds.business.dto.capteur.Capteur;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Capteurs {
    private ArrayList<Capteur> capteurs = new ArrayList<Capteur>();

    public ArrayList<Capteur> getCapteurs() {
        return capteurs;
    }

    public void setCapteurs(ArrayList<Capteur> capteurs) {
        this.capteurs = capteurs;
    }

    public final edu.ezip.ing1.pds.business.dto.capteur.Capteurs add (final Capteur capteur) {
        capteurs.add(capteur);
        return this;
    }

    @Override
    public String toString() {
        return "Capteurs{" +
                "capteurs=" + capteurs +
                '}';
    }
}