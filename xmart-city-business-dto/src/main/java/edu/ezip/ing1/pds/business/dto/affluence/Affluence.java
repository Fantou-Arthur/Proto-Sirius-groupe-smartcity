package edu.ezip.ing1.pds.business.dto.affluence;

public class Affluence{
    private int id;
	private int id_place;
	private int nbrPers;
	private int nbrDepart;
	private int nbrArriver;

	public Affluence() {}

    public Affluence(final int id_place, final int nbrPers, final int nbrDepart, final int nbrArriver) {
        this.id_place = id_place;
        this.nbrPers = nbrPers;
        this.nbrDepart = nbrDepart;
        this.nbrArriver = nbrArriver;
    }
    public Affluence(final int id, final int id_place, final int nbrPers, final int nbrDepart, final int nbrArriver) {
        this.id = id;
        this.id_place = id_place;
        this.nbrPers = nbrPers;
        this.nbrDepart = nbrDepart;
        this.nbrArriver = nbrArriver;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getIdPlace() {
        return id_place;
    }
    public void setIdPlace(int id_place) {
        this.id_place = id_place;
    }
    public int getNbrPers() {
        return nbrPers;
    }
    public void setNbrPers(int nbrPers) {
        this.nbrPers = nbrPers;
    }
    public int getNbrDepart() {
        return nbrDepart;
    }
    public void setNbrDepart(int nbrDepart) {
        this.nbrDepart = nbrDepart;
    }
    public int getNbrArriver() {
        return nbrArriver;
    }
    public void setNbrArriver(int nbrArriver) {
        this.nbrArriver = nbrArriver;
    }

    public String toString() {
        return "Affluence [id_place=" + id_place + ", nbrPers=" + nbrPers + ", nbrDepart=" + nbrDepart + ", nbrArriver=" + nbrArriver+"]";
    }


}


