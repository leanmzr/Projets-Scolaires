import java.text.ParseException;
import java.util.Date;

public class Interface_gestion_personnel {
	
	public static void main(String[] args) throws ParseException {
		
		Employé n1 = new Production("Vincent","Cassel","0651403089",new Date(),new Date(),360);
		Employé n2 = new Vente("Pierre","Lapin","0673631912",new Date(),new Date(),5600);
		Employé n1modif = new Production("Vincent","Roux-Cassel","0651403089",new Date(),new Date(),360);
		
		Personnel effectif = new Personnel();
		
		effectif.Création_employé(n1);
		effectif.Création_employé(n2);
		
		effectif.Modification_employé(n1modif, 0);
		
		effectif.afficherEmployé(1);
		
		effectif.Suppression_Employé(0);
		
		effectif.Moyenne_salaire();
		
		effectif.Vérification_PrimeAncienneté(0);
	}
	
}