package edu.ezip.ing1.pds.business.dto.address;


import java.util.ArrayList;

public class Addresses {
    private ArrayList<Address> entities = new ArrayList<>();

    public ArrayList<Address> getEntities() {
        return entities;
    }

    public void setAddress(ArrayList<Address> entities) {
        this.entities = entities;
    }

    public final Addresses add (final Address address) {
        entities.add(address);
        return this;
    }

    @Override
    public String toString() {
        return  " Entities : " + entities;
    }
}
