package data;

public class Achat {
	
	private Medicament medoc ;
	private int quantite ;
	private double prix;
	
	public Achat(Medicament medoc, int quantite) {
		super();
		this.medoc = medoc;
		this.quantite = quantite;
		this.prix = medoc.getPrix()*quantite;
	}

	public Medicament getMedoc() {
		return medoc;
	}

	
	public void setMedoc(Medicament medoc) {
		this.medoc = medoc;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}
	
}
