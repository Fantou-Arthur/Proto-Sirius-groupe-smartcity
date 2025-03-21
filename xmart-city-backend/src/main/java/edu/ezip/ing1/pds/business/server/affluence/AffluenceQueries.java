package edu.ezip.ing1.pds.business.server.affluence;


public enum AffluenceQueries{
	INSERT_AFFLUENCE("INSERT INTO Affluence (id_1, NbrPers, NbrDepart, NbrArrive) VALUES (?, ?, ?, ?)"),
    EDIT_AFFLUENCE("UPDATE Affluence SET NbrPers = ? , NbrDepart = ? , NbrArrive = ? WHERE id = ? AND id_1 = ?"),
    DELETE_AFFLUENCE("DELETE FROM Affluence WHERE id = ? AND id_1 = ?"),
    SELECT_ALL_AFFLUENCES("SELECT Affluence.id, Affluence.NbrPers, Affluence.NbrDepart, Affluence.NbrArrive, Affluence.id_1 FROM Affluence"),
    GET_TREE_VIEW("SELECT a.country, a.city, a.streetName, a.id, p.name, p.id, s.name, s.id, p.id_place FROM AdresseTempAffluence AS a, PlaceTempAffluence AS p, SensorTempAffluence AS s WHERE a.id_place=p.id_place AND p.id_place=s.id_place ORDER BY p.name");
    
    private  String query;

    private AffluenceQueries(final String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

