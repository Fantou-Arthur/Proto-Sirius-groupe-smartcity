package edu.ezip.ing1.pds.services;

public class UserSession {
    private static UserSession instance;
    private int entityId;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public void clearSession() {
        entityId = 0;
    }
}