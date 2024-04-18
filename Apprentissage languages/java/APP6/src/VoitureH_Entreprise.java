import java.time.LocalDate;

public class VoitureH_Entreprise extends VoitureH {
	
	LocalDate dateauj = java.time.LocalDate.now();
	
	public VoitureH_Entreprise(String imatricule) {
		super(imatricule);
		// TODO Auto-generated constructor stub
	}

	@Override
	public  double getnbHFB() {
		nbHFB=-miseService.compareTo(dateauj)*110;
		return nbHFB ;
		// TODO Auto-generated method stub
		
	}
	
}