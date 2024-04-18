import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public abstract class Employé {
	private String nom;
	private String prénom;
	private String numéro;
	private Date date_de_naissance;
	private Date date_embauche;

	
	public abstract void affiche_info();
	public abstract double CalculSalaire();

	public Employé(String nom, String prénom, String numéro, Date date_de_naissance, Date date_embauche) {
		super();
		this.nom = nom;
		this.prénom = prénom;
		this.numéro = numéro;
		this.date_de_naissance = date_de_naissance;
		this.date_embauche = date_embauche;
	}
	
	public int Prime_Ancienneté() {
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Date dateActuelle= date;
        Date Date_embauche = s.parse(date_embauche);
        long diff = Math.abs(dateActuelle.getTime() - Date_embauche.getTime());
        long diffparjour= TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
        if(diffparjour/365<5) {
            return 0;
        }
        else {
            return 1;
        }
      }
	
//SETTERS
	public void setNom(String nom) {
		this.nom = nom;}
	public void setPrénom(String prénom) {
		this.prénom = prénom;}
	public void setNuméro(String numéro) {
		this.numéro = numéro;}
	public void setDateNaissance(Date date_de_naissance) {
		this.date_de_naissance = date_de_naissance ;}
	public void setDateEmbauche(Date date_embauche) {
		this.date_embauche = date_embauche ;}
	
	
//GETTERS
	public String GetNom () {
		return nom;}
	public String GetPrenom () {
		return prénom;}
	public String GetNum () {
		return numéro;}
	public Date GetDateNaissance () {
		return date_de_naissance;}
	public Date GetDateEmbauche () {
		return date_embauche;}
	
}