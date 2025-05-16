package edu.ezip.ing1.pds.business.dto.place;

public class IdNamePlace {
    private final int id;
    private final String name;

    public IdNamePlace(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Place [ id="+ id +", name=" + name + "]";
    }
}
