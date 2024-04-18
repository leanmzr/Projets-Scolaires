package GUI;
import data.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfacenouveaumed {
	Pharmacie pharma;
	 JOptionPane op1;
	 
	public Interfacenouveaumed(Pharmacie pharma) {
		super();
		this.pharma = pharma;
	}

	public void start() {
	
		JFrame frameNVMed = new JFrame("Nouveau med");
		frameNVMed.setSize(400,400);
		frameNVMed.setResizable(false);
		
		JPanel JPNmed = new JPanel(new GridLayout(5,1));
		
			JPanel Plibelle = new JPanel();
				
				JLabel LLib = new JLabel("Libellé : ");
				LLib.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				Plibelle.add(LLib);
				JTextField  fieldLib = new JTextField ("");
				fieldLib.setColumns(10);
				Plibelle.add(fieldLib);
			
			    JPNmed.add(Plibelle);
			
			JPanel PRef = new JPanel();
				
				JLabel reference = new JLabel("Reference : ");
				reference.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				PRef.add(reference);
				JTextField  referenceField = new JTextField ("");
				referenceField.setColumns(10);
				PRef.add(referenceField);
			
			    JPNmed.add(PRef);
				
			JPanel Pdescription = new JPanel();
				
				JLabel Ldesc = new JLabel("Description : ");
				Ldesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				Pdescription.add(Ldesc);
				JTextField  descField = new JTextField ("");
				descField.setColumns(10);
				Pdescription.add(descField);
	
				JPNmed.add(Pdescription);

			JPanel Pprix = new JPanel();
				
				JLabel Lprix = new JLabel("Prix : ");
				Lprix.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				Pprix.add(Lprix);
				JTextField  prixField = new JTextField ("");
				prixField.setColumns(10);
				Pprix.add(prixField);
			
				JPNmed.add(Pprix);
				
		
		JPanel retVP = new JPanel();	
		JButton validerCreationClient = new JButton("Valider");
		retVP.add(validerCreationClient);
		
		
		ActionListener actionBoutonValider = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent boutoncliqué) {
			
				String libM = fieldLib.getText();
				String referenceM = referenceField.getText();
				String descM = descField.getText();
				
				Double prixfloatM = Double.valueOf(prixField.getText().toString());
			
				
				if (pharma.verifMed(referenceM)==1) {
					op1.showMessageDialog(null, "Le medicament existe deja dans la pharmacie","erreur", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					pharma.ajoutMed(new Medicament(libM,referenceM,descM, prixfloatM));
					op1.showMessageDialog(null, "Le medicament a bien été ajouté", "validation", JOptionPane.INFORMATION_MESSAGE);
					frameNVMed.dispose();
				
			}}
		};
		validerCreationClient.addActionListener(actionBoutonValider);
    
		
		JPNmed.add(retVP);
		
        
		frameNVMed.getContentPane().add(JPNmed);
		JPNmed.setVisible(true);
		frameNVMed.setVisible(true);
	}
	
}
	