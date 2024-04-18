package GUI;

import data.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface {
	private Pharmacie pharma ;
	 
	
	public Interface(Pharmacie pharma) {
		super();
		this.pharma = pharma;
	}


	public void Menu() {
		
		
		JFrame frame = new JFrame("Menu pharmacie");
        frame.setSize(900,400);
        frame.setResizable(false);
        
        JPanel panel = new JPanel(new GridBagLayout()) ;
        GridBagConstraints gc = new GridBagConstraints();
		gc.insets = new Insets(10,10,10,10);
		gc.ipady = gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 3;
		gc.weighty = 1;
		
        JButton newClient = new JButton("Gestion des clients");
        newClient.setPreferredSize(new Dimension(250, 100));
        JButton newMed = new JButton("gestion des médicaments");
        newMed.setPreferredSize(new Dimension(250, 100));
        JButton bachat = new JButton("gestion des achats");
        bachat.setPreferredSize(new Dimension(250, 100));
        
        gc.gridx = 0;
		gc.gridy = 0;
        panel.add(newClient, gc);
        gc.gridx = 2;
		gc.gridy = 0;
        panel.add(newMed,gc);
        gc.gridx = 4;
		gc.gridy = 0;
        panel.add(bachat,gc);
        
 
        ActionListener actionBoutonNewClient = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent boutoncliqué) {
            	InterfaceGestionDesClients interfaceGestionDesClients = new InterfaceGestionDesClients(pharma);
            	interfaceGestionDesClients.start();
            }
        };
        newClient.addActionListener(actionBoutonNewClient);
        
        ActionListener actionBoutonNewMed = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent boutoncliqué) {
            	InterfaceGestionDesMedicament interfaceGestionDesmed = new InterfaceGestionDesMedicament(pharma);
            	interfaceGestionDesmed.start();
            }
        };
        newMed.addActionListener(actionBoutonNewMed);
        
        ActionListener actionBoutonachat = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent boutoncliqué) {
            	InterfaceGestionDesachats interfaceGestionDesacha = new InterfaceGestionDesachats(pharma);
            	interfaceGestionDesacha.start();
            }
        };
        bachat.addActionListener(actionBoutonachat);
        
        frame.getContentPane().add(panel);
        
        
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
	
	
	
	
	
}



