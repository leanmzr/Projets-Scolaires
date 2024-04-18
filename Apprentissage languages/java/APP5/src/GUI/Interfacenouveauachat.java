package GUI;
import data.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfacenouveauachat {
	Pharmacie pharma;
	 JOptionPane op1;
	 Client client;
	 
	public Interfacenouveauachat(Pharmacie pharma, Client client) {
		super();
		this.pharma = pharma;
		this.client=client;
	}

	public void start() {
	
		JFrame frameNVA = new JFrame("Nouveau achat");
		frameNVA.setSize(400,400);
		frameNVA.setResizable(false);
		
		JPanel JPNAchat = new JPanel(new GridLayout(3,1));
		
			JPanel Pmed = new JPanel();
				
				JLabel LrefMed = new JLabel("reference du medicament : ");
				LrefMed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				Pmed.add(LrefMed);
				JTextField  refdField = new JTextField ("");
				refdField.setColumns(10);
				Pmed.add(refdField);
			
				JPNAchat.add(Pmed);
			
			JPanel PQuantite = new JPanel();
				
				JLabel Lquantite = new JLabel("quantité : ");
				Lquantite.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				PQuantite.add(Lquantite);
				JTextField  quantiteField = new JTextField ("");
				quantiteField.setColumns(10);
				PQuantite.add(quantiteField);
			
				JPNAchat.add(PQuantite);
			
				
		
		JPanel retVP = new JPanel();	
		JButton validerCreationClient = new JButton("Valider");
		retVP.add(validerCreationClient);
		
		
		ActionListener actionBoutonValider = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent boutoncliqué) {
			
				String referenceM = refdField.getText();
				int quantiefloatM = Integer.valueOf(quantiteField.getText().toString());
			
				if (pharma.verifMed(referenceM)==1) {
					Medicament medic=pharma.trouveMed(referenceM);
					if(pharma.trouveStock(medic).getNombre()>=quantiefloatM) {
						pharma.ajoutStocks(referenceM, -quantiefloatM);
						client.achete(medic,quantiefloatM);
						op1.showMessageDialog(null, "le client a bien effectué un achat", "validation", JOptionPane.INFORMATION_MESSAGE);
						frameNVA.dispose();
					}else {
						op1.showMessageDialog(null, "le stock du medicment est trop bas", "erreur", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else {
					op1.showMessageDialog(null, "le medicament n'existe pas", "erreur", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		};
		validerCreationClient.addActionListener(actionBoutonValider);
    
		
		JPNAchat.add(retVP);
		
        
		frameNVA.getContentPane().add(JPNAchat);
		JPNAchat.setVisible(true);
		frameNVA.setVisible(true);
	}
	
}
