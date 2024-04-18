import java.util.ArrayList;

public class Database {
	private ArrayList<Elève> liste_élèves;
	private ArrayList<String> liste_matières;
	
	public Database(ArrayList<Elève> liste_élèves, ArrayList<String> liste_matières) {
		super();
		this.liste_élèves = liste_élèves;
		this.liste_matières = liste_matières;
	}

	public void ajouter_élève(String nom, String prénom, String id_élève, ArrayList<Matière> liste_matière) {
		Elève nouveau = new Elève(nom, prénom,id_élève,liste_matière);
		liste_élèves.add(nouveau);}
	
	public void ajouter_matière(String matière) {
		liste_matières.add(matière);}
	
	public int chercher_élève(String id_élève) {
		int index = 0;
		for(Elève élève : liste_élèves) {
			if (élève.getId_élève().equals(id_élève)) {
				return index;}
			else {index ++;}}
		return -1;
			}
	
	public String taux_validation() {
		int v = 0;
		int n = 0;
		for(Elève élève : liste_élèves) {
			if (élève.Admission().equals("Admission validée")) {v ++;}
			else {n ++;}}
		return (v + " étudiants ont validé leur année. \n " + n + " étudiants n'ont pas valdié leur année.");

	}
	

	
	//GETTERS
	
	public ArrayList<Elève> getListe_élèves() {
		return liste_élèves;
	}

	public ArrayList<String> getListe_matières() {
		return liste_matières;
	}
	
	//SETTERS
	public void setListe_élèves(ArrayList<Elève> liste_élèves) {
		this.liste_élèves = liste_élèves;
	}

	public void setListe_matières(ArrayList<String> liste_matières) {
		this.liste_matières = liste_matières;
	}
	
}