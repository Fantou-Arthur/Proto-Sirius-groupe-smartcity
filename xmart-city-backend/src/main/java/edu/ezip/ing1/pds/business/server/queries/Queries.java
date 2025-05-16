package edu.ezip.ing1.pds.business.server.queries;

public enum Queries {


    INSERT_AFFLUENCE("INSERT INTO Affluences (id_place, density, peakStatus, peopleCount, exitCount, entryCount) VALUES (?, ?, ?, ?, ?, ?)"),
    EDIT_AFFLUENCE("UPDATE Affluences SET peopleCount = ? , exitCount = ? , entryCount = ?, density = ?, peakStatus = ? WHERE id_place = ? AND id = ?"),
    DELETE_AFFLUENCE("DELETE FROM Affluences WHERE id = ? AND id_place = ?"),
    SELECT_ALL_AFFLUENCES("SELECT Affluences.id, Affluences.peopleCount, Affluences.exitCount, Affluences.entryCount, Affluences.id_place, Affluences.peakStatus, Affluences.recordAt, Affluences.density FROM Affluences"),
    GET_TREE_VIEW("SELECT a.country, a.city, a.streetName, a.postalCode, a.city, a.id, p.name, p.type, p.description, p.maxCapacity, p.peakHours, p.id, s.name, s.model, s.isActive, s.id, p.id FROM Address AS a, Places AS p, sensor AS s WHERE a.id=p.id AND p.id=s.id_lieu ORDER BY p.name"),
    

    INSERT_PLACE("INSERT INTO Places (name, type, description, latitude, longitude, maxCapacity, peakHours, id_entity, id_address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"),
    UPDATE_PLACE("UPDATE Places SET name = ?, type = ?, description = ?, latitude = ?, longitude =?, maxCapacity = ?, peakHours = ?, id_address = ?  WHERE id = ?"),
    SELECT_ID_NAME_PLACES("SELECT id, name FROM Places"),
    SELECT_ALL_PLACES("SELECT * FROM Places where id_entity = ?"),

    DELETE_PLACE("DELETE FROM Places WHERE id = ?"),

    INSERT_CAPTEUR("INSERT INTO sensor (id, name, isActive, id_lieu) VALUES (?, ?, ?, ?)"),
    DELETE_CAPTEUR("DELETE FROM sensor WHERE id = ?"),
    EDIT_CAPTEUR("UPDATE sensor SET name = ?, isActive = ?,id_lieu = ? WHERE id = ?"),
    SELECT_ALL_CAPTEURS("SELECT id, name, isActive, id_lieu FROM sensor"),

    INSERT_USER("INSERT INTO Users (username, password, email) VALUES (?, ?, ?)"),
    LOGIN_USER("LOGIN_USER"),
    SELECT_ALL_ENTITY("SELECT_ALL_ENTITY"),


    INSERT_ADDRESS("INSERT INTO Address (name, streetNumber, streetName, postalCode, city, country) VALUES (?, ?, ?, ?, ?)"),
    SELECT_ALL_ADDRESS("SELECT * FROM Address"),
    SELECT_ADDRESS_BY_NAME("SELECT * FROM Address WHERE name = ?"),
    UPDATE_ADDRESS("UPDATE Address SET name = ?, streetNumber = ?, streetName = ?, postalCode = ?, city = ?, country = ? WHERE id = ?"),
    DELETE_ADDRESS("DELETE FROM Address WHERE id = ?");



    private  String query;

    private Queries(final String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

