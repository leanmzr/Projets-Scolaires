
import java.time.LocalDate;

public class VoitureH_loue extends VoitureH {
	LocalDate dateFinDeContrat ;
	LocalDate dateauj = java.time.LocalDate.now();
	
	public VoitureH_loue(String imatricule, LocalDate dateFinDeContrat) {
		super(imatricule);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getnbHFB() {
		nbHFB=-miseService.compareTo(dateauj)*250;
		return nbHFB ;
		
	}
	
	
}