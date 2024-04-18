import java.util.ArrayList;
import java.util.Scanner ;

public class Personne {
		
		//Définition des paramètres de classe
	
		private String nom;
		private String prenom;
		static int nombreClient;
		private int numClient;
		
		//Constructeur 1 pour la classe Personne avec incrémentation
		public Personne(String nom, String prenom) {
			this.nom = nom;
			this.prenom = prenom;
			this.nombreClient = nombreClient + 1; 
			this.numClient = nombreClient;
			
		}
		//Constructeur 2 pour la classe Personne
		public Personne(String nom, String prenom, int numeroClient) {
			super();
			this.nom = nom;
			this.prenom = prenom;
			this.numClient = numClient;
		
			
		}
		/** Avoir le nom d'un client
		 * @return la variable nom
		 */
		public String getNom() {
			return this.nom;
		}
		/** Avoir le numéro d'un client
		 * @return la variable numClient
		 */
		public int getNum() {
			return this.numClient;
		}

		
		
		
}