package edu.ezip.ing1.pds.business.server.user;

public enum UserQueries {
    INSERT_USER("INSERT INTO Users (username, password, email, entityId) VALUES (?, ?, ?, ?)"),
    SELECT_ALL_USER("SELECT * FROM Users WHERE username = ?"),
    SELECT_USER_BY_EMAIL("SELECT * FROM Users WHERE email = ?"),
    USER_UPDATE("UPDATE Users SET username = ?, password = ? WHERE id = ?"),
    USER_DELETE("DELETE FROM users WHERE username = ?"),
    LOGIN_USER("LOGIN_USER");

    private  String query;

    private UserQueries(final String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

