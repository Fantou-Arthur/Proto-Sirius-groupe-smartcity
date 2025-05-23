package edu.ezip.ing1.pds.business.dto.user;


public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private int entityId;

    public User() {
    }
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public User(String username, String password, String email, int entityId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.entityId = entityId;
    }
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getEntityId(){
        return entityId;
    }
    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", email=" + email + '}';
    }




}
