package data;

public class Medicament {
	
	
	private String libelle ;
	private String reference ;
	private String description ;
	private double prix ;
	

	public Medicament(String libelle, String reference, String description, double prix) {
		super();
		this.libelle = libelle;
		this.reference = reference;
		this.description = description;
		
		this.prix = prix;
	}
	

	
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public String getReference() {
		return reference;
	}





	public void setReference(String reference) {
		this.reference = reference;
	}





	public String getDescription() {
		return description;
	}





	public void setDescription(String description) {
		this.description = description;
	}





	public double getPrix() {
		return prix;
	}





	public void setPrix(double prix) {
		this.prix = prix;
	}





	
	

}
