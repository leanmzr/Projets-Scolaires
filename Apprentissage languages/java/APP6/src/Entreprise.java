import java.util.ArrayList;

public class Entreprise {
	String Nom ;
	ArrayList<VoitureH> listeDeVoiture;
	
	public Entreprise(String nom) {
		super();
		Nom = nom;
		this.listeDeVoiture = new ArrayList<VoitureH>();
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public ArrayList<VoitureH> getListeDeVoiture() {
		return listeDeVoiture;
	}

	public void setListeDeVoiture(ArrayList<VoitureH> listeDeVoiture) {
		this.listeDeVoiture = listeDeVoiture;
	}

	
}