import java.util.ArrayList;
import java.util.Date;

public class Production extends Employé {
	
	private int UnitProd;

	public Production(String nom, String prénom, String numéro, Date date_de_naissance, Date date_embauche,
			int unitProd) {
		super(nom, prénom, numéro, date_de_naissance, date_embauche);
		UnitProd = unitProd;
	}
	@Override
	public double CalculSalaire() {
	    double salaire = UnitProd*4;
	    if (Vente.Prime_Ancienneté() == 0)
        { return salaire+0.02*salaire;}
        else {
        return salaire+0.07*salaire;}
        }
	
	

	
//GETTERS
	
	
	public int getUnitProd() {
		return UnitProd;
	}

	public void affiche_info() {
		System.out.println("Nom : "+GetNom()+"\nPrénom : "+GetPrenom()+"\nNuméro de téléphone : "+GetNum()+"\nDate de naissance : "+GetDateNaissance()+"\nEmployé à la production depuis : "+GetDateEmbauche()+"\n Unités produites : "+UnitProd+"\n Salaire : "+ this.CalculSalaire()+"\n");
	}
	
//SETTERS
	public void setUnitProd(int unitProd) {
		UnitProd = unitProd;
	}

}