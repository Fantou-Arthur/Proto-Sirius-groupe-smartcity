package edu.ezip.ing1.pds.business.dto.capteur;


import java.util.Set;

public class Capteur {
    private String id;
    private String name;
    private String address;
    private int maxCapacity;


    public Capteur() {}

    public Capteur(final String id, final String name, final String address, final int maxCapacity) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.maxCapacity = maxCapacity;
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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getMaxCapacity() {
        return maxCapacity;
    }
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String toString() {
        return "Capteur [id=" + id + ", name=" + name + ", address=" + address + ", maxCapacity=" + maxCapacity+"]";
    }


}
