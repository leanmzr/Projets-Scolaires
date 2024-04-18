package GUI;
import data.*;
import Model.*;


import javax.swing.*;

import Model.ModelMedicament;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InterfaceGestionDesClients {
	Pharmacie pharma;
	
	public InterfaceGestionDesClients(Pharmacie pharma) {
		super();
		this.pharma = pharma;
	}

	public void start(){
	
		JFrame frameClient = new JFrame("Gestion Des Clients");
		frameClient.setSize(700,300);
		frameClient.setResizable(false);
		
		JPanel GSTclient = new JPanel(new GridLayout(2,1));
		
		JPanel PC = new JPanel();
		JButton newClient = new JButton("Ajouter un nouveau client");
		
        PC.add(newClient);
        GSTclient.add(PC);
        
        ActionListener actionBoutonopNVclient = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent boutoncliqué) {
            	IntefraceNouveauClient interfacenouveauclient = new IntefraceNouveauClient(pharma);
                interfacenouveauclient.start();
                frameClient.getContentPane().revalidate();
            }
        };
        newClient.addActionListener(actionBoutonopNVclient);
        
        
        JPanel TAB = new JPanel(new BorderLayout());
        ModelClient modmed = new ModelClient(pharma);
        JTable tableau = new JTable(modmed);
        tableau.setFillsViewportHeight(true);
        TAB.add(new JScrollPane(tableau), BorderLayout.CENTER);
        
        GSTclient.add(TAB);
		
        
        frameClient.getContentPane().add(GSTclient);
        GSTclient.setVisible(true);
        frameClient.setVisible(true);
	}}
	
	