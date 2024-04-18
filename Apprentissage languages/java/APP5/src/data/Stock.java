package data;
public class Stock {
	private Medicament medoc;
	private int nombre ;
	
	public Stock(Medicament medoc, int nombre) {
		super();
		this.medoc = medoc;
		this.nombre = nombre;
	}
	

	public Medicament getMedoc() {
		return medoc;
	}

	public void setMedoc(Medicament medoc) {
		this.medoc = medoc;
	}

	public int getNombre() {
		return nombre;
	}

	public void setStock(int nombre) {
		this.nombre = nombre;
	}
	
	
}