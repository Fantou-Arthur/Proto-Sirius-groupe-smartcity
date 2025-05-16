package edu.ezip.ing1.pds.business.server.queries;

public enum Queries {

    INSERT_AFFLUENCE("INSERT INTO Affluencess (id_place, peopleCount, exitCount, entryCount) VALUES (?, ?, ?, ?)"),
    EDIT_AFFLUENCE("UPDATE Affluences SET peopleCount = ? , exitCount = ? , entryCount = ? WHERE id = ? AND id_place = ?"),
    DELETE_AFFLUENCE("DELETE FROM Affluences WHERE id = ? AND id_place = ?"),
    SELECT_ALL_AFFLUENCES("SELECT Affluences.id, Affluences.peopleCount, Affluences.exitCount, Affluences.entryCount, Affluences.id_place, Affluences.peakStatus, Affluences.recordAt, Affluences.density FROM Affluences"),
    GET_TREE_VIEW("SELECT a.country, a.city, a.streetName, a.postalCode, a.city, a.id, p.name, p.type, p.description, p.maxCapacity, p.peakHours, p.id, s.name, s.model, s.isActive, s.id, p.id FROM Address AS a, Places AS p, sensor AS s WHERE a.id=p.id AND p.id=s.id_lieu ORDER BY p.name"),
    
    INSERT_PLACE("INSERT INTO Places (name, address, maxCapacity) VALUES (?, ?, ?)"),
    SELECT_ALL_PLACES("SELECT id, name, address, maxCapacity FROM Places"),
    UPDATE_PLACE("UPDATE Places SET name = ?, address = ?, maxCapacity = ? WHERE id = ?"),
    DELETE_PLACE("DELETE FROM Places WHERE id = ?"),

    INSERT_CAPTEUR("INSERT INTO sensor (id, name, isActive, id_lieu) VALUES (?, ?, ?, ?)"),
    DELETE_CAPTEUR("DELETE FROM sensor WHERE id = ?"),
    EDIT_CAPTEUR("UPDATE sensor SET name = ?, isActive = ?,id_lieu = ? WHERE id = ?"),
    SELECT_ALL_CAPTEURS("SELECT id, name, isActive, id_lieu FROM sensor");

    private  String query;

    private Queries(final String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

