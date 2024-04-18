import java.util.ArrayList;

public class Elève {
	
	private String nom_élève;
	private String prénom_élève;
	private String id_élève;
	private ArrayList<Matière> liste_matières;
	
	public Elève(String nom_élève, String prénom_élève, String id_élève, ArrayList<Matière> liste_matières) {
		super();
		this.nom_élève = nom_élève;
		this.prénom_élève = prénom_élève;
		this.id_élève = id_élève;
		this.liste_matières = liste_matières;
	}
	
	public Double CalculMoyenne() {
		double somme = 0;
		double nb_matières = 0;
		for (Matière matière : liste_matières) {
			somme += matière.CalculMoyenne();
			nb_matières +=1;
		}
		return somme/nb_matières;
	}
	
	public void ajoutNote(Double note1,Double note2,String mat) {
		for (Matière matière : liste_matières) {
			if (mat.equals(matière.getNom_matière())) {
				matière.setNote_orale(note1);
				matière.setNote_écrite(note2);
			}
			else {
				Matière nouvelle_matière = new Matière(mat,note1,note2);
				liste_matières.add(nouvelle_matière);
			}
		}
	}
	
	public String Admission() {
		double moyenne = CalculMoyenne();
		if (moyenne >= 10) {return "Admission validée";} //si moyenne >= 10
			else {return "Admission refusée";} //si moyenne < 10
	}
	
	
	public String Affiche() {
		String texte = "";
		System.out.println(prénom_élève);
		texte += prénom_élève + " \n";
		System.out.println(nom_élève);
		texte += nom_élève + " \n";
		//try {
		for (Matière matière : liste_matières) {
			texte += matière.getNom_matière() + ": \n";
			System.out.println(matière + " : ");
			texte += " Note orale : " + String.valueOf(matière.getNote_orale())+"\n";
			System.out.println("Note orale : "+ matière.getNote_orale());
			texte += " Note écrite : " + String.valueOf(matière.getNote_écrite())+"\n";
			System.out.println("Note écrite : "+ matière.getNote_écrite());
			System.out.println();}
		return texte;}
		//catch (java.lang.NullPointerException erreur_null) {
			//return texte;
		


	//GETTERS 
	
	public String getNom_élève() {
		return nom_élève;
	}

	public String getPrénom_élève() {
		return prénom_élève;
	}

	public String getId_élève() {
		return id_élève;
	}

	public ArrayList<Matière> getListe_matières() {
		return liste_matières;
	}

	// SETTERS 
	
	public void setNom_élève(String nom_élève) {
		this.nom_élève = nom_élève;
	}

	public void setPrénom_élève(String prénom_élève) {
		this.prénom_élève = prénom_élève;
	}

	public void setId_élève(String id_élève) {
		this.id_élève = id_élève;
	}

	public void setListe_matières(ArrayList<Matière> liste_matières) {
		this.liste_matières = liste_matières;
	}
	
}