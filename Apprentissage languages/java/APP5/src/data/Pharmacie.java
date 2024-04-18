package data;
import java.util.ArrayList;

public class Pharmacie {
	private  ArrayList<Client> Clients = new ArrayList<Client>();
	private  ArrayList<Stock> Stocks = new ArrayList<Stock>();
	
	public Pharmacie() {
		
		super();
	}
	
	

	public void ajoutClient( Client client) {
		Clients.add(client);
	}
	
	
	
	public void ajoutStocks( String refmedoc, int nombre) {
		
		Medicament medoc = trouveMed(refmedoc);
		for(Stock stock : Stocks) {
			if (stock.getMedoc().equals(medoc)) {
				stock.setStock(stock.getNombre()+nombre);
				
			}
		}
	}
	
	public ArrayList<Client> getClients() {
		return Clients;
		
	}
	
	public ArrayList<Stock> getStocks() {
		return Stocks;
		
	}
	
	
	
	public  void ajoutMed( Medicament medoc) {
		
		Stock nvstock = new Stock(medoc,0);
		Stocks.add(nvstock);
	}
	
	public int verifMed(String refmed) {
		int var=0;
		for(Stock stock : Stocks) 
			if (stock.getMedoc().getReference().equals(refmed)) {
				var +=1;
				
			}
	return var;
		
	}
	
	
	
	public  Medicament trouveMed( String refmed) {
		Medicament med = null;
		for(Stock stock : Stocks) 
			if (stock.getMedoc().getReference().equals(refmed)) {
				med=  stock.getMedoc();
				
			}
	return med;
		
	}
	public  Stock trouveStock( Medicament medic) {
		Stock med = null;
		for(Stock stock : Stocks) 
			if (stock.getMedoc().equals(medic)) {
				med=  stock;
				
			}
	return med;
		
	}
	
	public int verifClient(String numSS) {
		int var=0;
		for(Client client : Clients) 
			if (client.getNumeroSS().equals(numSS)) {
				var +=1;
				
			}
	return var;
		
	}
	
	
	
	public  Client trouveClient( String numSS) {
		Client cl = null;
		for(Client client : Clients) 
			if (client.getNumeroSS().equals(numSS)) {
				cl=client;
			}
	return cl;
		
	}
	public Medicament getmedicamentInd(int indiceligne) {
		
		return Stocks.get(indiceligne).getMedoc();
		
	}
	public Client getclientInd(int indiceligne) {
		
		return Clients.get(indiceligne);
		
	}
	
	
	
	}
