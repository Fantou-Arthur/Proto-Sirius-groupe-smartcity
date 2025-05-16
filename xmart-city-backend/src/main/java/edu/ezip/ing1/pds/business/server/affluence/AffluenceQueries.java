package edu.ezip.ing1.pds.business.server.affluence;


public enum AffluenceQueries{
	INSERT_AFFLUENCE("INSERT INTO Affluences (id_place, density, peakStatus, peopleCount, exitCount, entryCount) VALUES (?, ?, ?, ?, ?, ?)"),
    EDIT_AFFLUENCE("UPDATE Affluences SET peopleCount = ? , exitCount = ? , entryCount = ?, density = ?, peakStatus = ? WHERE id_place = ? AND id = ?"),
    DELETE_AFFLUENCE("DELETE FROM Affluences WHERE id = ? AND id_place = ?"),
    SELECT_ALL_AFFLUENCES("SELECT Affluences.id, Affluences.peopleCount, Affluences.exitCount, Affluences.entryCount, Affluences.id_place, Affluences.peakStatus, Affluences.recordAt, Affluences.density FROM Affluences"),
    GET_TREE_VIEW("SELECT a.country, a.city, a.streetName, a.postalCode, a.city, a.id, p.name, p.type, p.description, p.maxCapacity, p.peakHours, p.id, s.name, s.model, s.isActive, s.id, p.id FROM Address AS a, Places AS p, sensor AS s WHERE a.id=p.id AND p.id=s.id_lieu ORDER BY p.name");
    private  String query;

    private AffluenceQueries(final String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

