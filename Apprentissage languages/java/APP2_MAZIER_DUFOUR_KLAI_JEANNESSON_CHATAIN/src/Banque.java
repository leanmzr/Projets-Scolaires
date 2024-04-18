import java.util.ArrayList;
import java.util.Scanner;

//classe main
public class Banque {
		public static void main(String[] args) {
			
			ArrayList<Personne> clients = new ArrayList<>();
			ArrayList<Compte> comptes = new ArrayList<>();
			menu(comptes,clients);

		}
		/** Créer un client et l'ajouter à notre base de données
		 * @param clients est notre liste de clients
		 * @param comptes est notre liste de comptes
		 */
		public static void CreationClient(ArrayList<Personne> clients,ArrayList<Compte> comptes) {
			Scanner sc = new Scanner(System.in);
				System.out.println("Vous etes sur le point de devenir client, quel est votre nom ?");
				String Nom = sc.next();
				System.out.println(" Prenom ?");
				String Prenom = sc.next();
				
				/*Sachant qu'on incrémente à la source de création du client, la ligne ci-dessous a été 
				passée en commentaire */
				//int Num = clients.size()+1;
				
				Personne client = new Personne(Nom, Prenom);
				clients.add(client);
				int Num = client.getNum();
				
				System.out.println("votre compte n: "+Num+ " a bien été crée au nom de "+Nom+" "+Prenom);
				
				System.out.println("quelle valeur de decouvert maximum voulez vous autoriser ?");
				int valDecMax = -(sc.nextInt());
				
				System.out.println("quelle valeur de depot maximum voulez vous autoriser ?");
				int valDepMax = sc.nextInt();
				
				Compte celuila = new Compte(Num,0,client,0,valDecMax,valDepMax);
				comptes.add(celuila);
				celuila.printInfo();

					}
				
				

				
				
		
		/** Modification positive du solde d'un compte
		 * @param compte est le compte correspondant au solde à modifier
		 */
		public static void modifDeSoldplus(Compte compte) {
			Scanner sc = new Scanner(System.in);
				System.out.println("Combien voulez vous ajouter a votre sold ?");
			    int soussold = sc.nextInt();
			    compte.addSold(soussold, 1);
		}
		
		/** Modification négative du solde d'un compte
		 * @param compte est le compte correspondant au solde à modifier
		 */
		public static void modifDeSoldmoins(Compte compte) {
			Scanner sc = new Scanner(System.in);
				System.out.println("Combien voulez vous debiter a votre sold ?");
			    int soussold = sc.nextInt();
			    compte.supSold(soussold , 1);
				}
		/** Virement entre deux comptes
		 * @param comptes est la liste de comptes dans laquelle on ira chercher les comptes concernés
		 * par le virement
		 * @param clients est la liste des clients  à laquelle on accède afin de mettre une identité 
		 * sur un numéro de compte
		 */
		public static void faireVirement(ArrayList<Compte> comptes, ArrayList<Personne> clients) {
			
			Compte compte1 =null;
			Compte compte2 =null;
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Entrez votre numero client : ");
			int numclient1 = sc.nextInt();
			
			 if (comptes.size() >= numclient1) {
				 
				 compte1 = comptes.get(numclient1-1);
				 System.out.println("Bienvenue M/Mme "+compte1.getClient().getNom());
				 System.out.println("Entrez le numero de compte de la personne a qui vous voulez faire un virement : ");
				 int numclient2 = sc.nextInt();
				 
				 if (comptes.size() >= numclient2) {
					 
					 compte2 = comptes.get(numclient2-1);
					 System.out.println("Combien voulez vous virez a M/Mme "+compte2.getClient().getNom()+" ?");
					 int argentAvirer = sc.nextInt();
					 compte1.supSold(argentAvirer,2);
		    		 compte2.addSold(argentAvirer,2);
		    		 System.out.println("votre sold est desormais a : "+compte1.getSold());
					
				 	}
				 else{
					 System.out.println("aucun compte client n'est enregisté a ce numero");
					 
				 	}
				 }
			 else {
				 System.out.println("aucun compte client n'est enregisté a votre nom");
			 }
			
			}
			
		/** Interface d'actions avec l'utilisateur comprenant 6 possibilités
		 * @param comptes est la liste de comptes utile à toutes les fonctions
		 * @param clients est la liste des clients utile pour la création de clients et les virements
		 */
		public static void menu(ArrayList<Compte> comptes, ArrayList<Personne> clients) {
			int var = 0;
			while (var==0) {
				Scanner sc = new Scanner(System.in);
					System.out.println("\n 1 : creation d'un compte \n 2 : crediter un compte \n 3 : debiter \n 4 : faire un virement \n 5 : afficher les information du compte \n 6 : quitter l'interface");
					System.out.println("Quelle opperation voulez vous faire?");
				    int opp = sc.nextInt();
				    if (opp == 1) {
				    	CreationClient(clients,comptes);
		
				    }
			
				    if (opp == 2) {
				    	System.out.println("Quel est votre numero de compte ? ");
				    	int ncompte1 = sc.nextInt();
					    	 if (comptes.size() >= ncompte1) {
					    		 modifDeSoldplus(comptes.get(ncompte1-1));
					    	 }
					    	 else {
					    		 System.out.println("aucun compte client n'est enregisté a votre nom");
					    	 }
				    	 }
				    
				    
				    if (opp == 3) {
				    	System.out.println("Quel est votre numero de compte ? ");
				    	int ncompte1 = sc.nextInt();
					    	 if (comptes.size() >= ncompte1) {
					    		 modifDeSoldmoins(comptes.get(ncompte1-1));
					    	 }
					    	 else {
					    		 System.out.println("aucun compte client n'est enregisté a votre nom");
					    	 }
				    	 }
				    
				    
				    if (opp == 4) {
				    	faireVirement(comptes,clients);
					}
				    
				    
				    if (opp == 5) {
				    	System.out.println("Quel est votre numero de compte ? ");
				    	int ncompte1 = sc.nextInt();
					    	 if (comptes.size() >= ncompte1) {
					    		 comptes.get(ncompte1-1).printInfo2();
					    	 }
					    	 else {
					    		 System.out.println("aucun compte client n'est enregisté a votre nom");
					    	 }
				    	 }

				    if (opp == 6) {
			    		var = 1;
			    	}
		}
		}
}
		

