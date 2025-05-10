package edu.ezip.ing1.pds.business.dto.place;

import java.sql.Time;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Place {
    private int id;
    private String name;
    private Type type;
    private int maxCapacity;
    private int id_address;
    private int id_entity;
    private double longitude;
    private double latitude;
    private Time peakHour;
    private String description;

    public Place() {}

    public Place(final String name, final int address, final int entity,final int maxCapacity) {
        this.name = name;
        this.id_address = address;
        this.id_entity = entity;
        this.maxCapacity = maxCapacity;
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
    public int getAddress() {
        return id_address;
    }
    public void setAddress(int address) {
        this.id_address = address;
    }
    public int getMaxCapacity() {
        return maxCapacity;
    }
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    public double getLatitude(){ return this.latitude; }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude(){ return this.longitude; }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public Time getPeakHour() {
        return peakHour;
    }
    public void setPeakHour(Time peakHour) {
        this.peakHour = peakHour;
    }
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setId_address(int id_address) {
        this.id_address = id_address;
    }
    public int getId_address() {
        return id_address;
    }
    public int getId_entity() {
        return id_entity;
    }
    public void setId_entity(int id_entity) {
        this.id_entity = id_entity;
    }

    public String toString() {
        return "Place [ id="+ id +", name=" + name + ", address=" + id_address + ", maxCapacity=" + maxCapacity+ ", type=" + type + ", latitude=" + latitude + ", longitude=" + longitude + ", peakHour=" + peakHour + ", description=" + description+ "]";
    }


}
