package edu.ezip.ing1.pds.business.dto.capteur;


import java.util.Set;

public class Capteur {
    private String id;
    private String name;
    private boolean state;
    private String id_lieu;


    public Capteur() {}

    public Capteur(final String id, final String name, final boolean state, final String id_lieu) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.id_lieu = id_lieu;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean getState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }
    public String getId_lieu() {
        return id_lieu;
    }
    public void setId_lieu(String id_lieu) {
        this.id_lieu = id_lieu;
    }

    public String toString() {
        return "Capteur [id=" + id + ", name=" + name + ", state=" + state + ", id_lieu=" + id_lieu+"]";
    }


}
