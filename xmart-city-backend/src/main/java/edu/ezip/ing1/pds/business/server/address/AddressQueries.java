package edu.ezip.ing1.pds.business.server.address;

public enum AddressQueries {
    INSERT_ADDRESS("INSERT INTO Address (name, streetNumber, streetName, postalCode, city, country) VALUES (?, ?, ?, ?, ?)"),
    SELECT_ALL_ADDRESS("SELECT * FROM Address"),
    SELECT_ADDRESS_BY_NAME("SELECT * FROM Address WHERE name = ?"),
    UPDATE_ADDRESS("UPDATE Address SET name = ?, streetNumber = ?, streetName = ?, postalCode = ?, city = ?, country = ? WHERE id = ?"),
    DELETE_ADDRESS("DELETE FROM Address WHERE id = ?");
    private  String query;

    private AddressQueries(final String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

