import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.* ;

public class Interfacegraphique{
	JOptionPane op1;
	
    public void Menu(JFrame frame, JPanel ancienPanel, Database database) {
        ancienPanel.setVisible(false);
        frame.setSize(400,500);
        
    JPanel menu = new JPanel(new GridLayout(4,1));
    
    
    	
            JLabel titreMenu = new JLabel("Gestion des etudiants : Menu");
            titreMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            titreMenu.setFont(new Font("Serif", Font.BOLD, 20));
            menu.add(titreMenu);
            
            JPanel PM = new JPanel( );
            
            JButton nouvMatiere = new JButton("Ajouter une nouvelle matière");
            menu.add(nouvMatiere);
            
            
            
            ActionListener actionBoutonNvM = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent boutoncliqué) {
                    ajoutMatiere(frame,menu,database);
                }
            };
            nouvMatiere.addActionListener(actionBoutonNvM);
            
            JButton nouvEtudiant = new JButton("Creer un nouvel Etudiant");
            menu.add(nouvEtudiant);
            ActionListener actionBoutonNvE = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent boutoncliqué) {
                    creationEtudiant(frame,menu,database);
                }
            };
            nouvEtudiant.addActionListener(actionBoutonNvE);
            
            JButton RechEtudiant = new JButton("Rechercher un Etudiant");
            menu.add(RechEtudiant);
            ActionListener actionBoutonRechE = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent boutoncliqué) {
                    rechercheEtudiant(frame,menu,database);
                }
            };
            RechEtudiant.addActionListener(actionBoutonRechE);
            
            
    frame.getContentPane().add(menu);
            }

    public static Object[][] creer2D(ArrayList<String> matieres) {
    	Object[][] data = new Object[matieres.size()][3];
    	int i=-1;
    	for( String mat : matieres) {
    		i++;
    		Object[] test = new Object[3];
    		test[0]=mat;
            test[1]="";
            test[2]="";
            data[i]=test;
        
    	}
    	return data;
    }
    public static Object[][] creer2D2(ArrayList<String> matieres) {
    	Object[][] data = new Object[matieres.size()][1];
    	int i=-1;
    	for( String mat : matieres) {
    		i++;
    		Object[] test = new Object[1];
    		test[0]=mat;
            
            data[i]=test;
        
    	}
    	return data;
    }
	
	public void creationEtudiant(JFrame frame, JPanel ancienPanel,Database database) {
		op1 = new JOptionPane();
		ancienPanel.setVisible(false);
		
		JPanel pannelCréationEtudiant = new JPanel(new GridLayout(4,1));

		JPanel infoE = new JPanel(new GridLayout(4,1));
		
		infoE.setLayout(new BoxLayout(infoE, BoxLayout.PAGE_AXIS));
		
			JLabel titre = new JLabel("Création d'un etudiant");
			titre.setFont(new Font("Serif", Font.BOLD, 20));
			titre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			pannelCréationEtudiant.add(titre);
			
			JPanel Pnom = new JPanel();
		
				JLabel Nom = new JLabel("Nom : ");
				Nom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				Pnom.add(Nom);
				JTextField  nomField = new JTextField ("");
				nomField.setColumns(10);
			    Pnom.add(nomField);
			
			    infoE.add(Pnom);
			
			JPanel Pprenom = new JPanel();
				
				JLabel Prenom = new JLabel("Prenom : ");
				Prenom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				Pprenom.add(Prenom);
				JTextField  prenomField = new JTextField ("");
				prenomField.setColumns(10);
			    Pprenom.add(prenomField);
			
			    infoE.add(Pprenom);
				
			JPanel Pid = new JPanel();
				
				JLabel ID = new JLabel("Identifiant : ");
				ID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				Pid.add(ID);
				JTextField  IDField = new JTextField ("");
				IDField.setColumns(10);
			    Pid.add(IDField);
			
			    infoE.add(Pid);
		
			    pannelCréationEtudiant.add(infoE);	    
			    
		 JPanel frameTab = new JPanel(new BorderLayout());
		 
				 
				String[] columns = new String[] {
			            "Matière", "Note orale", "Note écrite"
			        };
			         
			        Object[][] data = creer2D( database.getListe_matières()) ;
			        JTable table = new JTable(data, columns);
			    
			        JScrollPane scroll = new JScrollPane(table);
			        table.setFillsViewportHeight(true);
			 
			        
			        frameTab.add(scroll,BorderLayout.CENTER);

			pannelCréationEtudiant.add(frameTab);
				
				
			JPanel retVP = new JPanel();	
			JButton validerCreationEtudiant = new JButton("Valider");
			retVP.add(validerCreationEtudiant);
			
		    ActionListener actionBoutonValider = new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent boutoncliqué) {
					String NomE = nomField.getText();
					String PrenomE = prenomField.getText();
					String IDE = IDField.getText();
					if (IDE.length()>=5) {
						op1.showMessageDialog(null, "l'identifiant depasse les 5 caracteres", "erreur", JOptionPane.INFORMATION_MESSAGE);
					}else {
					ArrayList<Matière> liste_matières = new ArrayList<Matière>();
					int i=-1;
					for (String mat :database.getListe_matières()) {
						System.out.println(mat);
						i++;
						Object ob1 = (table.getModel().getValueAt(i, 1));
						Object ob2 = (table.getModel().getValueAt(i, 2));
						System.out.println(ob1=="");
						System.out.println(ob2=="");
						if(ob1==""||ob2=="") {
							
						}else {
						
						Double note1 = Double.valueOf(ob1.toString());
						Double note2 = Double.valueOf(ob2.toString());
						System.out.println(note1 + note2);
						Matière matiero = new Matière(mat,note1,note2);
						liste_matières.add(matiero);
						}
					}
					System.out.println(PrenomE + NomE + IDE);
					for(Matière mat : liste_matières) {
						System.out.println(mat.getNom_matière() + mat.getNote_orale() + mat.getNote_écrite() + mat.CalculMoyenne());
					}
					database.ajouter_élève(PrenomE, NomE, IDE, liste_matières);
					op1.showMessageDialog(null, "L'éleve a bien été ajouté", "validation", JOptionPane.INFORMATION_MESSAGE);
					
					System.out.println(database.getListe_élèves());}
					creationEtudiant( frame,  pannelCréationEtudiant, database);
					
				}
	
			};
			validerCreationEtudiant.addActionListener(actionBoutonValider);
	    
			
			JButton retour = new JButton("retour");
			retVP.add(retour);
            
            ActionListener Aretour = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent boutoncliqué) {
                    
                    Menu(frame,pannelCréationEtudiant,database);
                }
            };
            retour.addActionListener(Aretour);
            pannelCréationEtudiant.add(retVP);
			
			frame.getContentPane().add(pannelCréationEtudiant);
			pannelCréationEtudiant.setVisible(true);
	    
	}
	public void ajoutMatiere(JFrame frame, JPanel ancienPanel,Database database) {
		
        ancienPanel.setVisible(false);
        JPanel pannelAjoutMatiere = new JPanel(new GridLayout(4,1));
        
        JLabel titre1 = new JLabel("Ajout d'une nouvelle matière");
        titre1.setFont(new Font("Serif", Font.BOLD, 20));
        titre1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pannelAjoutMatiere.add(titre1);
        
        JPanel texte = new JPanel();
        
            JLabel titre = new JLabel("Nom de la matière : ");
            titre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            texte.add(titre);
            
            JTextField  Field = new JTextField ("");
            Field.setColumns(10);
            texte.add(Field);
            
            pannelAjoutMatiere.add(texte);
            
            
            JPanel frameTab = new JPanel(new BorderLayout());
			
			/// 
			String[] columns = new String[] {
		            "Matière"
		        };
		         
			
		        Object[][] data = creer2D2( database.getListe_matières()) ;
		        JTable table = new JTable(data, columns);
		    
		        JScrollPane scroll = new JScrollPane(table);
		        table.setFillsViewportHeight(true);
		 
		        
		        frameTab.add(scroll,BorderLayout.CENTER);

		    pannelAjoutMatiere.add(frameTab);
            ///
            
            
		    JPanel retVP = new JPanel();	
            JButton validerCreationEtudiant = new JButton("Valider");
            retVP.add(validerCreationEtudiant);
            
            ActionListener actionBoutonValider = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent boutoncliqué) {
                    String matiere = Field.getText();
                    database.ajouter_matière(matiere);
                    op1.showMessageDialog(null, "La matiere a bien été ajouté", "validation", JOptionPane.INFORMATION_MESSAGE);
                    ajoutMatiere(frame,pannelAjoutMatiere,database);
                    //Menu(frame,pannelAjoutMatiere,database);
                }
            };
            validerCreationEtudiant.addActionListener(actionBoutonValider);
            
            JButton retour = new JButton("retour");
            retVP.add(retour);
            
            ActionListener Aretour = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent boutoncliqué) {
                    
                    Menu(frame,pannelAjoutMatiere,database);
                }
            };
            retour.addActionListener(Aretour);
            pannelAjoutMatiere.add(retVP);
            
            
            
            frame.getContentPane().add(pannelAjoutMatiere);
            pannelAjoutMatiere.setVisible(true);
    }
	public static void popup(JFrame frame, String message) {
		
        JDialog popup = new JDialog(frame,"Information");
        JTextArea l = new JTextArea(message);
        
        popup.add(l);
        popup.setSize(250, 150); 
        popup.setLocationRelativeTo(null);
        popup.setVisible(true);  
    }
    public void rechercheEtudiant(JFrame frame, JPanel ancienPanel, Database database) {
    	
        ancienPanel.setVisible(false);
        frame.setSize(300,200);
        JPanel pannelRechercheEtudiant = new JPanel(new GridLayout(3,1));
        
            JLabel titre = new JLabel("Rechercher un etudiant");
            titre.setFont(new Font("Serif", Font.BOLD, 20));
            titre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            pannelRechercheEtudiant.add(titre);
            
            JPanel PID = new JPanel();
                JLabel IDL = new JLabel("Identifiant : ");
                IDL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                PID.add(IDL);
                JTextField  IDField = new JTextField ("");
                IDField.setColumns(10);
                PID.add(IDField);
                pannelRechercheEtudiant.add(PID);
                
            JPanel RVP = new JPanel();    
            JButton validerCreationEtudiant = new JButton("Valider");
            RVP.add(validerCreationEtudiant);
            
            ActionListener actionBoutonValider = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent boutoncliqué) {
                    String IDE = IDField.getText();
                    if (database.chercher_élève(IDE) != -1) {
                    	int index = database.chercher_élève(IDE);
                    	Elève élève = database.getListe_élèves().get(index);
                    actionEtudiant(frame,pannelRechercheEtudiant, élève, database); 
                    }
                    else {popup(frame,"L'identifiant saisi est incorrect");
                    	
                    }
                }
            };
            validerCreationEtudiant.addActionListener(actionBoutonValider);
            
            JButton retour = new JButton("retour");
           retour.setSize(new Dimension(20,20));
           
           RVP.add(retour);
            
            ActionListener Aretour = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent boutoncliqué) {
                    
                    Menu(frame,pannelRechercheEtudiant,database);
                }
            };
            retour.addActionListener(Aretour);
            pannelRechercheEtudiant.add(RVP);
            
            frame.getContentPane().add(pannelRechercheEtudiant);
            pannelRechercheEtudiant.setVisible(true);
    }
    public void actionEtudiant(JFrame frame, JPanel ancienPanel, Elève élève, Database database) {
    	frame.setSize(500,600);
        ancienPanel.setVisible(false);
        JTextArea Lafficher = new JTextArea("");
        JPanel pannelactionEtudiant = new JPanel(new GridLayout(6,1));
        
            JLabel titre = new JLabel("fiche sur "+ élève.getPrénom_élève() +" "+ élève.getNom_élève());
            titre.setFont(new Font("Serif", Font.BOLD, 20));
            titre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            pannelactionEtudiant.add(titre);
            
         JButton BtnMoyenne = new JButton("Calculer la moyenne");
         
         	pannelactionEtudiant.add(BtnMoyenne);
                ActionListener actionCalculMoyenne = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent boutoncliqué) {
                        double moy = élève.CalculMoyenne();
                        Lafficher.setText("\nLa moyenne de l'élève est  : " + moy );
                    }
                };
         BtnMoyenne.addActionListener(actionCalculMoyenne);
         
                JButton BtnAdmission = new JButton("Verifier l'admission");
                 pannelactionEtudiant.add(BtnAdmission);
                        ActionListener actionEstAdmis = new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent boutoncliqué) {
                                String ad = élève.Admission();
                                Lafficher.setText(ad);
                            }
                        };
                        BtnAdmission.addActionListener(actionEstAdmis);
                        
        JButton BtnInformation = new JButton("Afficher ses informations");
                 pannelactionEtudiant.add(BtnInformation);
                 
                        ActionListener actionInfo = new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent boutoncliqué) {
                                 String info = élève.Affiche();
                                 
                                 popup(frame,info);
                                 System.out.println(info);
                                 Lafficher.setText(info);
                            }
                        };
                        BtnInformation.addActionListener(actionInfo);
                        
            
            Lafficher.setAlignmentY(50);
            Lafficher.setAlignmentX(50);
            pannelactionEtudiant.add(Lafficher);
            
            JPanel R = new JPanel();
            JButton retour = new JButton("retour");
            R.add(retour);
            
            ActionListener Aretour = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent boutoncliqué) {
                    
                    Menu(frame,pannelactionEtudiant,database);
                }
            };
            retour.addActionListener(Aretour);
            pannelactionEtudiant.add(R);
            
            frame.getContentPane().add(pannelactionEtudiant);
            pannelactionEtudiant.setVisible(true);
    }
}