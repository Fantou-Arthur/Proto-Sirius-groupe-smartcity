package edu.ezip.ing1.pds.business.dto.place;


import java.util.LinkedHashSet;
import java.util.Set;

public class Places {
    private Set<Place> places = new LinkedHashSet<Place>();

    public Set<Place> getPlaces() {
        return places;
    }

    public void setStudents(Set<Place> places) {
        this.places = places;
    }

    public final Places add (final Place place) {
        places.add(place);
        return this;
    }

    @Override
    public String toString() {
        return "Students{" +
                "students=" + places +
                '}';
    }
}
