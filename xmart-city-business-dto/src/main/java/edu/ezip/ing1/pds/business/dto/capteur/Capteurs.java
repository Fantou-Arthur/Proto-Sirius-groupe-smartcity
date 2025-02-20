package edu.ezip.ing1.pds.business.dto.capteur;


import edu.ezip.ing1.pds.business.dto.capteur.Capteur;

import java.util.LinkedHashSet;
import java.util.Set;

public class Capteurs {
    private Set<Capteur> capteurs = new LinkedHashSet<Capteur>();

    public Set<Capteur> getCapteurs() {
        return capteurs;
    }

    public void setStudents(Set<Capteur> capteurs) {
        this.capteurs = capteurs;
    }

    public final edu.ezip.ing1.pds.business.dto.capteur.Capteurs add (final Capteur capteur) {
        capteurs.add(capteur);
        return this;
    }

    @Override
    public String toString() {
        return "Students{" +
                "students=" + capteurs +
                '}';
    }
}