package edu.ezip.ing1.pds.business.server.place;

public enum PlaceQueries {
    INSERT_PLACE("INSERT INTO Places (name, address, maxCapacity) VALUES (?, ?, ?)"),
    SELECT_ALL_PLACES("SELECT place.name, place.address, place.maxCapacity FROM Places");

    private  String query;

    private PlaceQueries(final String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

