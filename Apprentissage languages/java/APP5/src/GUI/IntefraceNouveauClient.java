package GUI;
import data.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntefraceNouveauClient {
	Pharmacie pharma;
	 JOptionPane op1;
	 
	public IntefraceNouveauClient(Pharmacie pharma) {
		super();
		this.pharma = pharma;
	}

	public void start() {
	
		JFrame frameNVClient = new JFrame("Nouveau Client");
		frameNVClient.setSize(400,400);
		frameNVClient.setResizable(false);
		
		JPanel JPNclient = new JPanel(new GridLayout(6,1));
		
			JPanel Pnom = new JPanel();
				
				JLabel Nom = new JLabel("Nom : ");
				Nom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				Pnom.add(Nom);
				JTextField  nomField = new JTextField ("");
				nomField.setColumns(10);
			    Pnom.add(nomField);
			
			    JPNclient.add(Pnom);
			
			JPanel Pprenom = new JPanel();
				
				JLabel Prenom = new JLabel("Prenom : ");
				Prenom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				Pprenom.add(Prenom);
				JTextField  prenomField = new JTextField ("");
				prenomField.setColumns(10);
			    Pprenom.add(prenomField);
			
			    JPNclient.add(Pprenom);
				
			JPanel PNSS = new JPanel();
				
				JLabel NSS = new JLabel("Numero de securité sociale : ");
				NSS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				PNSS.add(NSS);
				JTextField  NSSField = new JTextField ("");
				NSSField.setColumns(10);
				PNSS.add(NSSField);
	
				JPNclient.add(PNSS);

			JPanel Padress = new JPanel();
				
				JLabel adresse = new JLabel("Adresse : ");
				adresse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				Padress.add(adresse);
				JTextField  adresseField = new JTextField ("");
				adresseField.setColumns(10);
				Padress.add(adresseField);
			
			    JPNclient.add(Padress);
				
			JPanel Ptel = new JPanel();
				
				JLabel Tel = new JLabel("Telephone : ");
				Tel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				Ptel.add(Tel);
				JTextField TelField = new JTextField ("");
				TelField.setColumns(10);
				Ptel.add(TelField);
	
				JPNclient.add(Ptel);
		
		
		JPanel retVP = new JPanel();	
		JButton validerCreationClient = new JButton("Valider");
		retVP.add(validerCreationClient);
		
		
		ActionListener actionBoutonValider = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent boutoncliqué) {
			
				String NomC = nomField.getText();
				String PrenomC = prenomField.getText();
				String NSSC = NSSField.getText();
				String adressC = adresseField.getText();
				String TelC = TelField.getText();
				
				if (NSSC.length()<13) {
					op1.showMessageDialog(null, "le numero de securité sociale doit depasser 13 caracteres", "erreur", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					pharma.ajoutClient(new Client( NSSC, NomC, PrenomC, adressC, TelC));
					op1.showMessageDialog(null, "Le Client a bien été ajouté", "validation", JOptionPane.INFORMATION_MESSAGE);
					frameNVClient.setVisible(false);
				}
			}
		};
		validerCreationClient.addActionListener(actionBoutonValider);
    
		
		JPNclient.add(retVP);
		
        
		frameNVClient.getContentPane().add(JPNclient);
		JPNclient.setVisible(true);
        frameNVClient.setVisible(true);
	}
	}