package edu.ezip.ing1.pds.business.dto.affluence;

public class Affluence{
    private int id;
    private double density;
    private boolean peak;
	private int idPlace;
	private int nbrPers;
	private int nbrDepart;
	private int nbrArriver;

	public Affluence() {}

    public Affluence(final int idPlace, final boolean peak, final double density, final int nbrPers, final int nbrDepart, final int nbrArriver) {
        this.idPlace = idPlace;
        this.nbrPers = nbrPers;
        this.peak = peak;
        this.density = density;
        this.nbrDepart = nbrDepart;
        this.nbrArriver = nbrArriver;
    }
    public Affluence(final int id, final int idPlace, final boolean peak, final double density, final int nbrPers, final int nbrDepart, final int nbrArriver) {
        this.id = id;
        this.idPlace = idPlace;
        this.peak = peak;
        this.density = density;
        this.nbrPers = nbrPers;
        this.nbrDepart = nbrDepart;
        this.nbrArriver = nbrArriver;
    }

    public void setPeak(boolean peak){
        this.peak = peak;
    }

    public boolean getPeak(){
        return this.peak;
    }

    public double getDensity(){
        return this.density;
    }

    public void setDensity(double density){
        this.density = density;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getIdPlace() {
        return idPlace;
    }
    public void setIdPlace(int idPlace) {
        this.idPlace = idPlace;
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
        return "Affluence [id="+id+", idPlace=" + idPlace + ", nbrPers=" + nbrPers + ", nbrDepart=" + nbrDepart + ", nbrArriver=" + nbrArriver+"]";
    }


}


