import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.*;

public class Main {
	
	
	
	public static void main(String[] args) {
		
	ArrayList<Entreprise> entreprises = new ArrayList<Entreprise>();
		
	LocalDate pastDate = LocalDate.parse("2024-01-12");
	 VoitureH_loue  nvv =  new VoitureH_loue("test",pastDate );
	
	 
	 
	JFrame frame = new JFrame("Nouveau Client");
	frame.setSize(400,400);
	frame.setResizable(false);
	
	
		JPanel retVP = new JPanel();	
		
		JPanel Pnom = new JPanel();
		
		JLabel Nom = new JLabel("Nom : ");
		Nom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		Pnom.add(Nom);
		JTextField  nomField = new JTextField ("");
		nomField.setColumns(10);
	    Pnom.add(nomField);
	    retVP.add(Pnom);
		
		
		JButton getdate = new JButton("creer");
		retVP.add(getdate);
		
		
		ActionListener actionBoutonValider = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent boutoncliqué) {
				
				entreprises.add(new Entreprise(nomField.getName()));
				
			}
		};
		
		getdate.addActionListener(actionBoutonValider);
		
		
		JPanel PA1 = new JPanel();
		JLabel entre = new JLabel("Entreprise a rechercher : ");
		entre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		PA1.add(entre);
		JTextField  entrepriseField = new JTextField ("");
		entrepriseField.setColumns(10);
		
		
		JButton rechercher = new JButton("rechercher");
		PA1.add(entrepriseField);
		JLabel ETrouve = new JLabel(" ");
		ETrouve.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		
		ActionListener actionBoutonrecherche = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent boutoncliqué) {
				int var =0;
				for (Entreprise entreprise: entreprises) {
					if (entreprise.getNom()==entrepriseField.getText()){
						ETrouve.setText("votre entreprise est : "+entreprise.getNom());
						var =1;
					}
				}
				if( var==0) {
					ETrouve.setText("aucune entreprise a ce nom");
				}
				
			}
		};
		
		rechercher.addActionListener(actionBoutonrecherche);
	    
	    PA1.add(rechercher);
		retVP.add(PA1);
		retVP.add(ETrouve);
		
		
		
		frame.add(retVP);
    
	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	frame.getContentPane().add(retVP);
	frame.setVisible(true);
	
	
	
	
}
}
