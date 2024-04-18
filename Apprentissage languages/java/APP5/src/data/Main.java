package data;
import GUI.Interface;

public class Main {
	public static Pharmacie pharma = new Pharmacie();
	public static void main(String[] args) {
		
		Client fred = new Client( "1111111111111", "fred", "fredou", " rue du pape", "callme");
		pharma.ajoutClient(fred);
		
		pharma.ajoutMed(new Medicament("Doliprane", "1234", "aie", 12));
		pharma.ajoutStocks("1234",13);
		// TODO Auto-generated method stub
	//Medicament dol = new Medicament("dol","doliprane","erhbf",32);
		//Medicament ibu = new Medicament("ibu","jhzefg","erhbf",32);
		//pharma.ajoutStocks( dol, 34);
		//pharma.ajoutStocks( ibu, 12);
		//pharma.ajoutStocks( dol, 34);
		
		
		//pharma.achatmed( "0230","doliprane",12) ;
		
	
		
		Interface interfac = new Interface(pharma);
		interfac.Menu();
	}
	

	
	
}
