package edu.ezip.ing1.pds.business.dto.affluence;

//import java.util.*;
import edu.ezip.ing1.pds.business.dto.affluence.Pays;

import java.util.HashMap;

public class TreeData{
	
	public String name;
	public HashMap<String, Pays> pays = new HashMap<String, Pays>();

	public TreeData(String name){
		this.name = name;
	}

	public TreeData(String name, HashMap<String, Pays> pays){
		this.name = name;
		this.pays = pays;
	}

	public void addPays(Pays pays){
		if(!this.pays.containsKey(pays.getName())){
            this.pays.put(pays.getName(), pays);
        }
	}

	public Pays getPays(String name){
		return this.pays.get(name);
	}

	public void setPays(HashMap<String, Pays> pays){
		this.pays = pays;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}


	public HashMap<String, Pays> getAPays(){
		return this.pays;
	}

	public String toString(){
		return "TreeData [ name=" + this.name + ", pays=" + this.pays + "]";
	}
	
}