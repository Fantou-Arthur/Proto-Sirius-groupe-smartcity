package edu.ezip.ing1.pds.business.dto.affluence;

import java.util.ArrayList;
import edu.ezip.ing1.pds.business.dto.affluence.Sensor;

public class SensorInfos{
	
	private ArrayList<String> path = new ArrayList<String>();
	private Sensor sensor;

	public SensorInfos(){}

	public SensorInfos(ArrayList<String> path, Sensor sensor){
		this.path = path;
		this.sensor = sensor;
	}

	public void setPath(ArrayList<String> path){
		this.path = path;
	}

	public ArrayList<String> getPath(){
		return this.path;
	}

	public void setSensor(Sensor sensor){
		this.sensor = sensor;
	}

	public Sensor getSensor(){
		return this.sensor;
	}

	public String toString(){
		return "SensorInfos [ path=" + this.path + ", sensor=" + this.sensor + "]";
	}


}

