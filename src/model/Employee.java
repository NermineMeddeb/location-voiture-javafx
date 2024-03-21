package model;


public class Employee {
    private int idemployee;
    private String nom;
    private String prenom;
    private String poste;
    private int salaire;
    private String email;
    private String type;
    private String pwd;
    private String telephone;

    public Employee(String nom, String prenom, String poste, int salaire, String email, String telephone,String type,String pwd) {
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.salaire = salaire;
        this.email = email;
        this.telephone = telephone;
        this.type = type;
        this.pwd = pwd;
    }
    public Employee(int id ,String nom, String prenom, String poste, int salaire, String email, String telephone,String type,String pwd) {
        this.idemployee = id;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.salaire = salaire;
        this.email = email;
        this.telephone = telephone;
        this.type = type;
        this.pwd = pwd;
    }


    // Getters and Setters
    public int getIdemployee() {
        return idemployee;
    }

    public void setIdemployee(int idemployee) {
        this.idemployee = idemployee;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
    
}
