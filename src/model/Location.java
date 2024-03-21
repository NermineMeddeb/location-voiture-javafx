package model;

import java.sql.Date;

public class  Location{
	private int idlocation ;
	private int idvehicule;
	private int idclient ;
	private Date datedebut;
	private Date datefin;
	private int montant ;
	private boolean statut ;
	
	public int getIdlocation() {
		return idlocation;
	}

	public void setIdlocation(int idlocation) {
		this.idlocation = idlocation;
	}

	public int getIdvehicule() {
		return idvehicule;
	}

	public void setIdvehicule(int idvehicule) {
		this.idvehicule = idvehicule;
	}

	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

	public Date getDatedebut() {
		return datedebut;
	}

	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}

	public Date getDatefin() {
		return datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public boolean isStatut() {
		return statut;
	}

	public void setStatut(boolean statut) {
		this.statut = statut;
	}

	public Location(Date datedebut, Date datefin, int montant, boolean statut) {
		super();
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.montant = montant;
		this.statut = statut;
	}
	public Location(int montant) {
		this.montant = montant;
	}
}
	
	