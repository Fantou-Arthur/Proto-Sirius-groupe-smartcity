package edu.ezip.ing1.pds.business.dto.affluence;

public class Sensor{
		
	public int id;
    public String name;
    public boolean state;
    public String model;
    public int id_lieu;

    public Sensor(){}

    public Sensor(int id, String name, boolean state, String model, int id_lieu){
    	this.id = id;
    	this.name = name;
    	this.state = state;
    	this.model = model;
    	this.id_lieu = id_lieu;
    }

    public void setId(int id){
    	this.id = id;
    }

    public int getId(){
    	return this.id;
    }

    public void setName(String name){
    	this.name = name;
    }

    public String getName(){
    	return this.name;
    }

    public void setState(boolean state){
    	this.state = state;
    }

    public boolean getState(){
    	return this.state;
    }

    public void setModel(String model){
    	this.model = model;
    }

    public String getModel(){
    	return this.model;
    }

    public void setIdLieu(int id_lieu){
    	this.id_lieu = id_lieu;
    }

    public int getIdLieu(){
    	return this.id_lieu;
    }

    public String toString(){
    	return "Sensor [ id=" + this.id + ", name=" + this.name + " , state=" + this.state + " , model=" + this.model + " , id_lieu=" + this.id_lieu + "]";
    }

}




