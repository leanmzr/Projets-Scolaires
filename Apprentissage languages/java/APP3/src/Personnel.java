import java.util.ArrayList;
import java.text.ParseException;

public class Personnel {

	private ArrayList<Employé> employés ; 

public void Création_employé(Employé nouveau) {
	nouveau.affiche_info();
	employés.add(nouveau);
}

public void Modification_employé(Employé modifié, int index) {
	employés.remove(index);
	employés.add(modifié);
}
	
public void Vérification_PrimeAncienneté(int index) throws ParseException {
	if (employés.get(index).Prime_Ancienneté()==0) {
		System.out.println("Employé non-éligible à la prime d'ancienneté");}
	else {
		System.out.println("Employé éligible à la prime d'ancienneté");
	}
}
public float Moyenne_salaire() {
	int n = 0;
	float s = 0;
	for (Employé employé : employés) {
		s += employé.CalculSalaire() ;
		n +=1 ;
	}
	return (s/n);
}

public void afficherEmployé(int index) {
	employés.get(index).affiche_info();
}

public void Suppression_Employé(int index) {
	employés.remove(index);
}

public static float Recherche_employé(ArrayList<Employé> employés, String name ) {
	float var=0;
	for(Employé employe : employés) {
		var+=1;
		if (employe.GetNom().equals(name)) {
			return var;}
	}
	return -1;
		
}
public ArrayList<Employé> getEmployés() {
	return employés;
}

public void setEmployés(ArrayList<Employé> employés) {
	this.employés = employés;
}


}
	
	