package edu.ezip.ing1.pds.business.dto.capteur;


import java.sql.Date;

public class Capteur {
    private int id;
    private String name;
    private boolean state;
    private int id_lieu;

    private String description;
    private String manufacturer;
    private String model;

    private String status;
    private int id_affluence;

    private String installed;
    private String last_maintenance;




    public Capteur() {}

    public Capteur(final int id, final String name, final boolean state, final int id_lieu, final String description, final String manufacturer, final String model, final String status, final int id_affluence, final String installed, final String last_maintenance) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.id_lieu = id_lieu;
        this.description = description;
        this.manufacturer = manufacturer;
        this.model = model;
        this.status = status;
        this.id_affluence = id_affluence;
        this.installed = installed;
        this.last_maintenance = last_maintenance;

    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
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
    public int getId_lieu() {
        return id_lieu;
    }
    public void setId_lieu(int id_lieu) {
        this.id_lieu = id_lieu;
    }

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public String getModel() {return model;}
    public void setModel(String model) {this.model = model;}

    public String getInstalled() {return installed;}
    public void setInstalled(String installed) {this.installed = installed;}

    public String getLastMaintenance() {return last_maintenance;}
    public void setLastMaintenance(String lastMaintenance) {this.last_maintenance = lastMaintenance;}

    public String getManufacturer() {return manufacturer;}
    public void setManufacturer(String manufacturer) {this.manufacturer = manufacturer;}

    public int getId_affluence() {return id_affluence;}
    public void setId_affluence(int id_affluence) {this.id_affluence = id_affluence;}


    public String toString() {
        return "sensor [id=" + id + ", name=" + name + ", state=" + state + ", id_lieu=" + id_lieu+ ", description="+ description+ ", manufacturer=" + manufacturer + ",model =" + model +", status=" + status + ", id_affluence=" + id_affluence + ", installed"+ installed + ", last_maintenance"+ last_maintenance + "]";
    }


}
