public class Matière {
	
	private String nom_matière;
	private Double note_orale;
	private Double note_écrite;
	
	public Matière(String nom_matière, Double note_orale, Double note_écrite) {
		super();
		this.nom_matière = nom_matière;
		this.note_orale = note_orale;
		this.note_écrite = note_écrite;
	}
	
	public Double CalculMoyenne() {
		double moyenne = 0;
		moyenne += note_orale*0.4 + note_écrite*0.6;
		return moyenne;
	}
	
	//GETTERS 
	
	public String getNom_matière() {
		return nom_matière;
	}

	public Double getNote_orale() {
		return note_orale;
	}

	public Double getNote_écrite() {
		return note_écrite;
	}

	//SETTERS
	
	public void setNom_matière(String nom_matière) {
		this.nom_matière = nom_matière;
	}
	
	public void setNote_orale(Double note_orale) {
		this.note_orale = note_orale;
	}

	public void setNote_écrite(Double note_écrite) {
		this.note_écrite = note_écrite;
	}
	
	
	
	
}