package edu.ezip.ing1.pds.business.dto.entity;


import edu.ezip.ing1.pds.business.dto.place.Place;

import java.util.ArrayList;

public class Entities {
    private ArrayList<Entity> entities = new ArrayList<>();

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setPlaces(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public final Entities add (final Entity entity) {
        entities.add(entity);
        return this;
    }

    @Override
    public String toString() {
        return  " Entities : " + entities;
    }
}
