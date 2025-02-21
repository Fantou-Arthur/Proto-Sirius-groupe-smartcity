package edu.ezip.ing1.pds.business.server.affluence;


public enum AffluenceQueries{
	INSERT_AFFLUENCE("INSERT INTO Affluences (id_1, NbrPers, NbrDepart, NbrArrive) VALUES (?, ?, ?, ?)"),
    EDIT_AFFLUENCE("UPDATE Affluences SET NbrPers= ? , NbrDepart= ? , NbrArrive= ? WHERE id= ? AND id_1= ?"),
    DELETE_AFFLUENCE("DELETE FROM Affluences WHERE id= ? AND id_1= ?"),
    SELECT_ALL_AFFLUENCES("SELECT Affluences.id, Affluences.id_1, Affluences.NbrPers, Affluences.NbrDepart, Affluences.NbrArrive, Affluences.NbrDepart FROM Affluences");

    private  String query;

    private AffluenceQueries(final String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

