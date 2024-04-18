package GUI;
import data.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;

public class InterfaceVoirachat {
	Pharmacie pharma;
	 JOptionPane op1;
	 Client client;
	 
	public InterfaceVoirachat(Pharmacie pharma,Client client) {
		super();
		this.pharma = pharma;
		this.client = client;
	}

	public void start() {
	
		
	
		JFrame framevoirA = new JFrame("achat du client");
		framevoirA.setSize(700,500);
		framevoirA.setResizable(false);
		
		JPanel gen = new JPanel(new GridLayout(2,1));
		
		JPanel PL = new JPanel();
		JLabel labtitre = new JLabel("Achats de "+client.getPrenom()+" "+client.getNom()+" : ");
		labtitre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		PL.add(labtitre);
		gen.add(PL);
		
		
       
        
        JPanel TAB = new JPanel(new BorderLayout());
        ModelAchat modmed = new ModelAchat(client);
        JTable tableau = new JTable(modmed);
        tableau.setFillsViewportHeight(true);
        TAB.add(new JScrollPane(tableau), BorderLayout.CENTER);
        
        gen.add(TAB);
		
        
		framevoirA.getContentPane().add(gen);
		gen.setVisible(true);
		framevoirA.setVisible(true);
	}
	
}
