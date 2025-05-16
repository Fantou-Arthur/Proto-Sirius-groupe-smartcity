package edu.ezip.ing1.pds.business.dto.affluence;

import java.util.HashMap;
import java.util.Set;
import edu.ezip.ing1.pds.business.dto.affluence.SensorInfos;

public class TreeViewData{
	private HashMap<String, SensorInfos> data = new HashMap<String, SensorInfos>();

	public TreeViewData(){}

	public TreeViewData(HashMap<String, SensorInfos> Data){
		this.data = data;
	}

	public void addSensor(String name, SensorInfos sensorData){
		this.data.put(name, sensorData);
	}

	public SensorInfos getSensorData(String name){
		return this.data.get(name);
	}

	public Set<String> keysSet(){
		return this.data.keySet();
	}

	public void setData(HashMap<String, SensorInfos> data){
		this.data = data;
	}

	public HashMap<String, SensorInfos> getData(){
		return this.data;
	}
}



