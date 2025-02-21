package edu.ezip.ing1.pds.business.server.queries;

public enum Queries {
    INSERT_PLACE("INSERT INTO Places (name, address, maxCapacity) VALUES (?, ?, ?)"),
    SELECT_ALL_PLACES("SELECT name, address, maxCapacity FROM Places"),
    INSERT_CAPTEUR("INSERT INTO sensor (id, name, isActive, id_lieu) VALUES (?, ?, ?, ?)"),
    SELECT_ALL_CAPTEURS("SELECT id, name, status, id_lieu FROM sensor");

    private  String query;

    private Queries(final String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

