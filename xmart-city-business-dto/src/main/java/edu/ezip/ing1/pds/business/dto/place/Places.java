package edu.ezip.ing1.pds.business.dto.place;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Places {
    private ArrayList<Place> places = new ArrayList<>();

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }

    public final Places add (final Place place) {
        places.add(place);
        return this;
    }

    @Override
    public String toString() {
        return  " Places : " + places;
    }
}
