package edu.ezip.ing1.pds.business.dto.entity;

public class Entity {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String description;

    public Entity(final String name, final String email, final String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    public Entity(final String name, final String email, final String phone, String description) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.description = description;
    }

    public Entity() {

    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }



    public String toString() {
        return "Entity [ id="+ id +", name=" + name + ", mail=" + email + ", phone=" + phone+ " description="+ description+ "]";
    }



}
