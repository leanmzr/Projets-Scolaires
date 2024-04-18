package GUI;
import data.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;

public class InterfaceGestionDesMedicament {
	Pharmacie pharma;
	
	public InterfaceGestionDesMedicament(Pharmacie pharma) {
		super();
		this.pharma = pharma;
	}

	public void start(){
	
		JFrame frameMed = new JFrame("Gestion Des Medicament");
		frameMed.setSize(700,500);
		frameMed.setResizable(false);
		
		JPanel GSTmed = new JPanel(new GridLayout(3,1));
		
		JPanel PC = new JPanel();
		
			JButton BnewMED = new JButton("Ajouter un nouveau medicament");
	        PC.add(BnewMED);
	        
			JButton BnewStock = new JButton("Alimenter le stock d'un medicament");
	        PC.add(BnewStock);
     
	        GSTmed.add(PC);
        
        ActionListener actionBoutonopNVmed = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent boutoncliqué) {
            	Interfacenouveaumed interfacenouveaumed = new Interfacenouveaumed(pharma);
            	interfacenouveaumed.start();
            	frameMed.getContentPane().revalidate();
            }
        };
        BnewMED.addActionListener(actionBoutonopNVmed);
        
        ActionListener actionBoutonopNVStock = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent boutoncliqué) {
            	InterfacenouveauStock interfacenouveauStock = new InterfacenouveauStock(pharma);
            	interfacenouveauStock.start();
            	frameMed.getContentPane().revalidate();
            }
        };
        BnewStock.addActionListener(actionBoutonopNVStock);
        
        
        JPanel TAB = new JPanel(new BorderLayout());
        ModelMedicament modmed = new ModelMedicament(pharma);
        JTable tableau = new JTable(modmed);
        tableau.setFillsViewportHeight(true);
        TAB.add(new JScrollPane(tableau), BorderLayout.CENTER);
        
        GSTmed.add(TAB);
		
        
        frameMed.getContentPane().add(GSTmed);
        GSTmed.setVisible(true);
        frameMed.setVisible(true);
	}}
	
	