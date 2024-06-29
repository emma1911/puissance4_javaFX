package PuissanceModel;

import java.util.Scanner;

public class Joueur implements Comparable<Joueur> {
	private  int id ;
	private String nom ;
	private String prenom;
	private int score;
    private int nombrePartie;
	
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void incrementerScore() {
		this.score++;
	}
	public void decrementerScore() {
		this.score--;
	}
	
	public Joueur(int id,String nom,String prenom,int score , int nombrePartie) {
		this.nom = nom;
		this.prenom = prenom;
		this.id = id;
		this.score = score;
		this.nombrePartie = nombrePartie;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Joueur(int id,String nom,int score) {
		this.nom = nom;
		this.id = id;
		this.score = score;
	}
	public Joueur(int id,String nom,String prenom,int score) {
		this.nom = nom;
		this.prenom = prenom;
		this.id = id;
		this.score = score;
	}
	
	public Joueur(Joueur joueur ) {
		this.nom = joueur.getNom();
		this.id = joueur.getId();
		this.score = joueur.getScore();
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "( Nom: "+nom +", Prenom: "+ prenom+","+ " joueur id: "+id+", Score: "+score+")\n";
	}
	public int ChoisierCoup() {
		System.out.print("Choisir une colonne: ");
		Scanner clavier = new Scanner(System.in);
		int numColonne=clavier.nextInt();
		return numColonne-1;		
	}
	public int getNombrePartie() {
		return nombrePartie;
	}
	public void setNombrePartie(int nombrePartie) {
		this.nombrePartie = nombrePartie;
	}
	@Override
	public int compareTo(Joueur o) {
		if(this.score>o.score)
			return 1;
		else
			if(this.score<o.score)
				return -1;	
		return 0;
	}

}
