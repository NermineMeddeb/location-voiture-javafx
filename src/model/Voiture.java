package model;

public class Voiture {
	private int id;
	private String marque;
	private String modele;
	private String type_carburant;
	private boolean disponibilite;

	
	
	public Voiture(String marque, String modele, String type_carburant,
			boolean disponibilite) {
		this.marque = marque;
		this.modele = modele;
		this.type_carburant = type_carburant;
		this.disponibilite = disponibilite;
	}
	public Voiture(int id,String marque, String modele , String type_carburant, boolean disponibilite) {
		this.id = id;
		this.marque = marque;
		this.modele = modele;
		this.type_carburant = type_carburant;
		this.disponibilite = disponibilite;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getMarque() {
		return marque;
	}




	public void setMarque(String marque) {
		this.marque = marque;
	}




	public String getModele() {
		return modele;
	}




	public void setModele(String modele) {
		this.modele = modele;
	}




	public String getType_carburant() {
		return type_carburant;
	}




	public void setType_carburant(String type_carburant) {
		this.type_carburant = type_carburant;
	}




	public boolean isDisponibilite() {
		return disponibilite;
	}




	public void setDisponibilite(boolean disponibilite) {
		this.disponibilite = disponibilite;
	}




	@Override
	public String toString() {
		return "Vehicule ...";
	}
	
}