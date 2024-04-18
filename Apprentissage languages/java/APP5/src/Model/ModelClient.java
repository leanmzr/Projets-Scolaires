package Model;
import data.*;
import javax.swing.table.AbstractTableModel;

public class ModelClient extends AbstractTableModel{
	Pharmacie pharma;
	String[] title ={ "Num securité sociale", "Nom", "Prenom", "adresse","Telephone" };
	public ModelClient(Pharmacie pharma) {
		
		this.pharma = pharma;
		
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return pharma.getClients().size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Client client = pharma.getclientInd(rowIndex);
		switch(columnIndex) {
		case 0: return client.getNumeroSS();
		case 1: return client.getNom();
		case 2: return client.getPrenom();
		case 3: return client.getAdresse();
		case 4: return client.getNumeroTelephone();
		default: return null;
		}
		
	}
	    
    

    @Override
    public String getColumnName(int columnIndex) {
        return title[columnIndex];
    }	

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public void setValueAt(Object val,int rowIndex, int columnIndex){
	}
	
	public Class<? extends Object>getColumnClass(int indiceCol){
		return getValueAt(0,indiceCol).getClass();
	}
	
}
