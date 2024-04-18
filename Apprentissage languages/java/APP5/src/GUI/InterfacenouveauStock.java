package GUI;
import data.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfacenouveauStock {
	Pharmacie pharma;
	 JOptionPane op1;
	 
	public InterfacenouveauStock(Pharmacie pharma) {
		super();
		this.pharma = pharma;
	}

	public void start() {
	
		JFrame frameNVStock = new JFrame("Nouveau stock");
		frameNVStock.setSize(400,400);
		frameNVStock.setResizable(false);
		
		JPanel JPNStock = new JPanel(new GridLayout(3,1));
		
			JPanel Pmed = new JPanel();
				
				JLabel LrefMed = new JLabel("reference du medicament : ");
				LrefMed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				Pmed.add(LrefMed);
				

		        String[] clientlist = new String[pharma.getStocks().size()] ;
		        int i =0;
		        for (Stock stock : pharma.getStocks()) {
		        	
		        	clientlist[i] = stock.getMedoc().getReference();
		        	System.out.println(clientlist[i]);
		        	i++;
		        }

		        JComboBox<String> bookList = new JComboBox<>(clientlist);

		        //add to the parent container (e.g. a JFrame):
		        Pmed.add(bookList);
			
		        //get the selected item:
	        
				
			
			
				JPNStock.add(Pmed);
			
			JPanel PQuantite = new JPanel();
				
				JLabel Lquantite = new JLabel("quantité : ");
				Lquantite.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				PQuantite.add(Lquantite);
				JTextField  quantiteField = new JTextField ("");
				quantiteField.setColumns(10);
				PQuantite.add(quantiteField);
			
				JPNStock.add(PQuantite);
			
				
		
		JPanel retVP = new JPanel();	
		JButton validerCreationClient = new JButton("Valider");
		retVP.add(validerCreationClient);
		
		
		ActionListener actionBoutonValider = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent boutoncliqué) {
			
				String referenceM = (String) bookList.getSelectedItem();
				int quantiefloatM = Integer.valueOf(quantiteField.getText().toString());
			
				
				if (pharma.verifMed(referenceM)==0) {
					op1.showMessageDialog(null, "Le medicament n'existe pas dans la pharmacie","erreur", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					pharma.ajoutStocks(referenceM,quantiefloatM);
					op1.showMessageDialog(null, "Le stock a bien été ajouté", "validation", JOptionPane.INFORMATION_MESSAGE);
					frameNVStock.dispose();
				
			}}
		};
		validerCreationClient.addActionListener(actionBoutonValider);
    
		
		JPNStock.add(retVP);
		
        
		frameNVStock.getContentPane().add(JPNStock);
		JPNStock.setVisible(true);
		frameNVStock.setVisible(true);
	}
	
}
	