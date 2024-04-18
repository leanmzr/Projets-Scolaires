import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public abstract class VoitureH {
	
	
	String imatricule ;
	double nbHFB = 0 ;
	LocalDate miseService;
	
	public VoitureH(String imatricule) {
		super();
		
		LocalDate date = java.time.LocalDate.now();
		this.imatricule = imatricule;
		this.miseService = date;
	}
	
	public abstract double  getnbHFB() ;
 
}
