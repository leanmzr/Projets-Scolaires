package data;
import java.util.ArrayList;

public class Client {
	
	private String numeroSS;
	private String nom;
	private String prenom;
	private String adresse;
	private String numeroTelephone;
	private ArrayList<Achat> AchatsClient = new ArrayList<Achat>(); 
	
	public Client(String numeroSS, String nom, String prenom, String adresse, String numeroTelephone) {
		super();
		this.numeroSS = numeroSS;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numeroTelephone = numeroTelephone;
	}
	
	
	
	public String getNumeroSS() {
		return numeroSS;
	}




	public void setNumeroSS(String numeroSS) {
		this.numeroSS = numeroSS;
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




	public String getAdresse() {
		return adresse;
	}




	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}




	public String getNumeroTelephone() {
		return numeroTelephone;
	}




	public void setNumeroTelephone(String numeroTelephone) {
		this.numeroTelephone = numeroTelephone;
	}

	public void achete(Medicament med , int quantite ) {
		AchatsClient.add(new Achat(med,quantite));
	}

	public ArrayList<Achat> getAchat() {
		return AchatsClient ;
		
	}
	public Achat getachatInd(int indiceligne) {
		
		return AchatsClient.get(indiceligne);
		
	}
	
	
	
	

}
