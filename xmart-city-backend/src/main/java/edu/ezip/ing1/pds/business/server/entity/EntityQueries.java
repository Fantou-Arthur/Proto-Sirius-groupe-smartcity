package edu.ezip.ing1.pds.business.server.entity;

public enum EntityQueries {
    SELECT_ALL_ENTITY("SELECT id, name, email, phone, description FROM Entity");
    private  String query;

    private EntityQueries(final String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

