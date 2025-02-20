package edu.ezip.ing1.pds.business.server.capteur;

public enum CapteurQueries {
    INSERT_CAPTEUR("INSERT INTO Capteurs (name, address, maxCapacity) VALUES (?, ?, ?)"),
    SELECT_ALL_CAPTEURS("SELECT capteur.name, capteur.address, capteur.maxCapacity FROM Capteurs");

    private  String query;

    private CapteurQueries(final String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
