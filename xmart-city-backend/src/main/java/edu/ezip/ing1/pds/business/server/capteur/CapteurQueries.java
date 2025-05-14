package edu.ezip.ing1.pds.business.server.capteur;

public enum CapteurQueries {
    INSERT_CAPTEUR("INSERT INTO sensor (id, name, isActive, id_lieu, description, manufacturer, model, status, id_affluence, installed, last_maintenance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"),
    DELETE_CAPTEUR("DELETE FROM sensor WHERE id = ?"),
    EDIT_CAPTEUR("UPDATE sensor SET name = ?, isActive = ?, id_lieu = ?, description = ?, manufacturer = ?, model = ?, status = ?, id_affluence = ?, installed = ?, last_maintenance = ? WHERE id = ?"),
    SELECT_ALL_CAPTEURS("SELECT id, name, isActive, id_lieu, description, manufacturer, model, status, id_affluence, installed, last_maintenance FROM sensor");


    private  String query;

    private CapteurQueries(final String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
