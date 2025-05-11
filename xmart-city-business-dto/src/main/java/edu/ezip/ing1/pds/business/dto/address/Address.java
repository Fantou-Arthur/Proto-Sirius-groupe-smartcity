package edu.ezip.ing1.pds.business.dto.address;

public class Address {
    private int id;
    private String name;
    private String streetName;
    private int streetNumber;
    private String postalCode;
    private String city;
    private String country;

    public Address(final String name, final String streetName, final int streetNumber, final String phone, final String city, final String country) {
        this.name = name;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = phone;
        this.city = city;
        this.country = country;
    }
    public Address(final String streetName, final int streetNumber, final String phone, final String city) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = phone;
        this.city = city;
    }

    public Address() {}

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
    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    public String getPostalCode(){
        return postalCode;
    }
    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }
    public int getStreetNumber() {
        return streetNumber;
    }
    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry(){
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String toString() {
        return "Address [ id="+ id + ", name=" + name +", streetNumber=" + streetNumber + ", streetName=" + streetName + ", PostalCode=" + postalCode + " city="+ city+ " country="+ country+ "]";
    }



}
