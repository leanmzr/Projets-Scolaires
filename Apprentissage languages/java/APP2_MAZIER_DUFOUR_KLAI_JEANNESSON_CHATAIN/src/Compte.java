import java.util.ArrayList;

public class Compte {
		
	//Définition des paramètres de classe
	
	private int numero;
	private float sold;
	private Personne client ;
	private int NbOperation;
	private float ValDecouvertMax;
	
	private float ValdepotMax;
	
	
	// Constructeur pour la classe Compte
	public Compte(int numero, float sold, Personne client, int nbOperation, float valDecouvertMax, 
			float valdepotMax) {
		super();
		this.numero = numero;
		this.sold = sold;
		this.client = client;
		NbOperation = nbOperation;
		ValDecouvertMax = valDecouvertMax;
		
		ValdepotMax = valdepotMax;
	}
	
	/**
	* Permet d'afficher toutes les informations du compte
	*/
	public void printInfo() {
		System.out.println("numero compte : "+this.numero+" \nsold : "+this.sold+"\nclient : "+this.client.getNom()+"\nNombre d'opérations à ce jour :"+NbOperation+"\nValeur de decouvert maximum :"+ValDecouvertMax+"\n valeur de depot maximum :"+ValdepotMax);
	}
	
	
	/**
	* Permet d'afficher certaines informations du compte
	*/
	public void printInfo2() {
		System.out.println("\nclient : "+this.client.getNom()+" \nsold : "+this.sold+"\nclient : "+"\nNombre d'opérations à ce jour :"+NbOperation);
	}
	
	
	/** Renvoie le client correspondant au compte
	 * @return la variable client
	 */	
	public Personne getClient() {
		return this.client;
	}	
	
	
	/**Vérification du dépassement de plafond après opération débitaire
	 * @param sous est la valeur débitée au client
	 * @param choix est une variable qui permettra l'affichage automatique du solde du compte après 
	 * opération
	 */
	public void supSold(int sous, int choix) {
		if ( this.ValDecouvertMax > this.sold - sous ) {
	    	System.out.println("Vous avez depassé votre plafond ");
	    }else {
	    	this.sold -=sous;
			NbOperation +=1;
	    }
		if (choix ==1) {
			System.out.println("votre solde est desormais a : "+this.sold);
			}
	}
	
	
	/**Vérification du dépassement de plafond après dépôt
	 * @param sous est la valeur déposée par le client
	 * @param choix est une variable qui permettra l'affichage automatique du solde du compte après 
	 * opération
	 */	
	public void addSold(int sous,int choix) {
		if(this.ValdepotMax >  sous) {
			this.sold +=sous;
			NbOperation +=1;
			}
		else {
			System.out.println("La valeur de depot est depassé");
			}
		if (choix ==1) {
		System.out.println("votre solde est desormais a : "+this.sold);
		}
	}
	
	
	/**Renvoie le numéro du compte
	 * @return la variable numero
	 */
	public int getNum() {
		return this.numero;
	}
	
	
	/**Renvoie le solde du compte
	 * @return la variable sold
	 */
	public float getSold() {
		return this.sold;
	}	
	

}

