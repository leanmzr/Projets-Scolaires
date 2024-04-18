package GUI;
import data.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InterfaceGestionDesachats {
	Pharmacie pharma;
	JOptionPane op1;
	
	
	public InterfaceGestionDesachats(Pharmacie pharma) {
		super();
		this.pharma = pharma;
	}

	public void start(){
	
		JFrame frameAchat = new JFrame("Gestion Des achats");
		frameAchat.setSize(600,200);
		frameAchat.setResizable(false);
		
		JPanel GSTachat = new JPanel(new GridLayout(3,1));
		
		JPanel PA1 = new JPanel();
		JLabel SSclient = new JLabel("numero de securité sociale : ");
		SSclient.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		PA1.add(SSclient);
		
		
		JPanel PA = new JPanel();     
			
			JButton Bnewachat = new JButton("effectuer un achat pour ce client");
			PA.add(Bnewachat);
			JButton Bvoirachat = new JButton("voir les achats du client");
			PA.add(Bvoirachat);
     
	        GSTachat.add(PA1);
	        GSTachat.add(PA);
	        

	        String[] clientlist = new String[pharma.getClients().size()] ;
	        int i =0;
	        for (Client client : pharma.getClients()) {
	        	
	        	clientlist[i] = client.getNumeroSS();
	        	System.out.println(clientlist[i]);
	        	i++;
	        }

	        JComboBox<String> bookList = new JComboBox<>(clientlist);

	        //add to the parent container (e.g. a JFrame):
	        PA1.add(bookList);
		
	        //get the selected item:
        
	        ActionListener actionBoutonopVoira = new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent boutoncliqué) {
	            	
	            	String numSSC = (String) bookList.getSelectedItem();
	            	if (pharma.verifClient(numSSC)==1) {
	            		
	            		InterfaceVoirachat interfacevoirachat = new InterfaceVoirachat(pharma,pharma.trouveClient(numSSC));
	                	interfacevoirachat.start();
	            		
	            	}else {
	            		System.out.println("la"+boutoncliqué.getID());
	            		op1.showMessageDialog(null, "Le client n'existe pas ", "erreur", JOptionPane.INFORMATION_MESSAGE);
	            	}
	            }
	        };
	        
        ActionListener actionBoutonopNVachat = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent boutoncliqué) {
            	String numSSC = (String) bookList.getSelectedItem();
            	if (pharma.verifClient(numSSC)==1) {
            		
            		Interfacenouveauachat interfacenouveauachat = new Interfacenouveauachat(pharma,pharma.trouveClient(numSSC));
            		interfacenouveauachat.start();
            		
            	}else {
            		System.out.println("la"+boutoncliqué.getID());
            		op1.showMessageDialog(null, "Le client n'existe pas ", "erreur", JOptionPane.INFORMATION_MESSAGE);
            	}
            	
            	
            }
        };
        Bvoirachat.addActionListener(actionBoutonopVoira);
        
        
        
        
        Bnewachat.addActionListener(actionBoutonopNVachat);
        
        
       
       
       
	       
		
        
        frameAchat.getContentPane().add(GSTachat);
        GSTachat.setVisible(true);
        frameAchat.setVisible(true);
	}}
	
	