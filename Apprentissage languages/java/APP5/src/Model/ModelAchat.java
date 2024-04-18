package Model;
import data.*;
import javax.swing.table.AbstractTableModel;

public class ModelAchat extends AbstractTableModel{
	Client pharma;
	String[] title ={ "Reference medicament", "Nom medicament", "quantité", "prix" };
	public ModelAchat(Client pharma) {
		
		this.pharma = pharma;
		
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return pharma.getAchat().size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Achat client = pharma.getachatInd(rowIndex);
		switch(columnIndex) {
		case 0: return client.getMedoc().getReference();
		case 1: return client.getMedoc().getLibelle();
		case 2: return client.getQuantite();
		case 3: return client.getPrix();
	
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
