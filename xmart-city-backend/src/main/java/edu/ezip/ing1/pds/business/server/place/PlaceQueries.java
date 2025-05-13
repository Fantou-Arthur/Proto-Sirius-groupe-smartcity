package edu.ezip.ing1.pds.business.server.place;

public enum PlaceQueries {
    INSERT_PLACE("INSERT INTO Places (name, address, maxCapacity) VALUES (?, ?, ?)"),
    UPDATE_PLACE("UPDATE Places SET name = ?, address = ?, maxCapacity = ? WHERE id = ?"),
    SELECT_ID_NAME_PLACES("SELECT id, name FROM Places"),
    SELECT_ALL_PLACES("SELECT id, name, address, maxCapacity FROM Places"),
    DELETE_PLACE("DELETE FROM Places WHERE id = ?");

    private  String query;

    private PlaceQueries(final String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

