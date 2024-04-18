import java.util.ArrayList;
import java.util.Date;


public class Vente extends Employé {


	private double CA;

	public Vente(String nom, String prénom, String numéro, Date date_de_naissance, Date date_embauche, double cA) {
		super(nom, prénom, numéro, date_de_naissance, date_embauche);
		CA = cA;
	}

	
    public double CalculSalaire() {
        double CA = this.CA;
        double salaire = (CA*0.25)+400;
        if (Vente.Prime_Ancienneté() == 0)
        { return salaire+0.02*salaire;}
        else {
        return salaire+0.07*salaire;}
        }
	
//GETTERS
	public double getCA() {
		return CA;
	}
	
	public void affiche_info() {
		System.out.println("Nom : "+GetNom()+"\nPrénom : "+GetPrenom()+"\nNuméro de téléphone : "+GetNum()+"\nDate de naissance : "+GetDateNaissance()+"\nEmployé à la vente depuis : "+GetDateEmbauche()+"\n Chiffres d'affaires : "+CA+"\n Salaire : "+ this.CalculSalaire()+"\n");
	}
//SETTERS

	public void setCA(double cA) {
		CA = cA;
	}


}