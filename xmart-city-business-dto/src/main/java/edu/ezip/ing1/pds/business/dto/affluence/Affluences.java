package edu.ezip.ing1.pds.business.dto.affluence;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Affluences{

	private ArrayList<Affluence> affluences = new ArrayList<>();

    public ArrayList<Affluence> getAffluences() {
        return affluences;
    }

    public void setAffluences(ArrayList<Affluence> affluences) {
        this.affluences = affluences;
    }

    public final Affluences add(final Affluence affluence) {
        affluences.add(affluence);
        return this;
    }

    @Override
    public String toString() {
        return  " Affluences : " + affluences;
    }
	
}

