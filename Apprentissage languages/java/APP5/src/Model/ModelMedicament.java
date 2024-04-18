package Model;
import data.*;
import javax.swing.table.AbstractTableModel;

public class ModelMedicament extends AbstractTableModel{
	Pharmacie pharma;
	String[] title ={ "Reference", "Libellé", "Description", "Prix" ,"Quantité"};
	
	public ModelMedicament(Pharmacie pharma) {
		
		this.pharma = pharma;
		
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return pharma.getStocks().size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Medicament med = pharma.getmedicamentInd(rowIndex);
		switch(columnIndex) {
		case 0: return med.getReference();
		case 1: return med.getLibelle();
		case 2: return med.getDescription();
		case 3: return med.getPrix();
		case 4: return pharma.trouveStock(med).getNombre();
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

