import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.util.ArrayList;

public class main {
	    public static void main(String[] args) {
	    	ArrayList<Elève> liste_élèves = new ArrayList<Elève>();
	        ArrayList<String> liste_matières = new ArrayList<String>();
	        Database database = new Database(liste_élèves, liste_matières);
	        Interfacegraphique interfacegraphique = new Interfacegraphique();
	        JPanel initialitation = new JPanel();
	        JFrame frame = new JFrame("Ma fenetre");
	        frame.setSize(600,600);
			interfacegraphique.Menu(frame, initialitation, database);
	        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        frame.setVisible(true);
	        //database.ajouter_matière("Sport");
	       // database.ajouter_matière("maths");
	        //database.ajouter_matière("géo");
	    }
	    
	    
	}
