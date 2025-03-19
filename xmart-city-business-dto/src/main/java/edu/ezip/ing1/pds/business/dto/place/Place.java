package edu.ezip.ing1.pds.business.dto.place;

import java.util.HashMap;
import java.util.Map;

public class Place {
    private String name;
    private String address;
    private int maxCapacity;


    public Place() {}

    public Place(final String name, final String address, final int maxCapacity) {
        this.name = name;
        this.address = address;
        this.maxCapacity = maxCapacity;
    }

    // Getters and Setters
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
        return "Place [ name=" + name + ", address=" + address + ", maxCapacity=" + maxCapacity+"]";
    }


}
