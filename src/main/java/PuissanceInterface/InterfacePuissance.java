package PuissanceInterface;

import PuissanceController.ControllerPuissance;
import PuissanceModel.GestionJoueur;
import PuissanceModel.Joueur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.List;


public class InterfacePuissance {
	GridPane gridPane = new GridPane();
	Button annulerButton = new Button("Annuler");
	private Button[][] buttonList = new Button[10][10];
    private TextField firstNameTextField = new TextField();
    private TextField lastNameTextField = new TextField();
	Label joueur1 = new Label("");
	Label joueur2 = new Label("");
	GestionJoueur gestion = new GestionJoueur();
	List<Joueur> Joueurs = gestion.getList();
    
    public StackPane CreateAnnulerButton() {
        StackPane stackPane = new StackPane(annulerButton);
        stackPane.setPadding(new Insets(0, 0, 20, 0));
        return stackPane;
    }
    
    public Button getAnnulerButton() {
    	return this.annulerButton;
    }
    
    public MenuBar menuBar(ControllerPuissance cont) {
    	
    	MenuBar menuBar=new MenuBar();
    			//Menu Partie
    			Menu partie= new Menu("Partie");
    			MenuItem lancerPartie=new MenuItem("Lancer une partie");
    			lancerPartie.setOnAction(event -> cont.lancerPuissance());
    			
    			MenuItem ListePartie=new MenuItem("Liste partie");
    			ListePartie.setOnAction(event -> cont.listPartie());
	    			
    			MenuItem listImporter = new MenuItem("Importer une partie");
    			listImporter.setOnAction(event -> cont.importer());
    			
    			MenuItem listExporter = new MenuItem("Exporter une partie");
    			
    			
    			partie.getItems().addAll(lancerPartie,ListePartie,listImporter,listExporter);
    			menuBar.getMenus().add(partie);
    			
    			    			
    			
    			//Menu Classement
    			Menu classement = new Menu("Classement");
    			MenuItem listJoueur = new MenuItem("La liste des joueurs");
    			listJoueur.setOnAction(event -> cont.listjoueur());
    			
    			MenuItem listJoueurNbrPartie = new MenuItem("La liste des joueurs selon nombre de parties jouées");
    			listJoueurNbrPartie.setOnAction(event -> cont.listjoueurSelonNbPartie());
    			
    			classement.getItems().addAll(listJoueur,listJoueurNbrPartie);
    			
    			menuBar.getMenus().add(classement);
    			
    			
    			
    			//Menu Joueur
    			Menu joueur = new Menu("Joueur");
    			MenuItem ajouterJoueur = new MenuItem("Ajouter joueur");
    			ajouterJoueur.setOnAction(event -> cont.form0());
    			
    			MenuItem detailsJoueur = new MenuItem("Détaille joueur");
    			//méthode
    			
    			
    			joueur.getItems().addAll(ajouterJoueur,detailsJoueur);
    			
    			menuBar.getMenus().add(joueur);
    			
    			
    			//Menu Quitter
    			Menu quitter = new Menu("Quitter");
    			menuBar.getMenus().add(quitter);
    			
    			
    			
    			return menuBar;
    		
    }
    
    public Label CreateJoueur1() {
    	String ch = "Nom: " + Joueurs.get(1).getNom() + "\nScore: " +  Joueurs.get(1).getScore();
    	joueur1.setPadding(new Insets(210, 0, 0, 0));
    	joueur1.setText(ch);
    	return joueur1;
    }
    
    public Label CreateJoueur2() {
    	String ch = "Nom: " + Joueurs.get(2).getNom() + "\nScore: " +  Joueurs.get(2).getScore();
    	joueur2.setPadding(new Insets(210, 0, 0, 0));
    	joueur2.setText(ch);
    	return joueur2;
    }
    
    public void resetLabel() {
    	joueur1.setText("");
    	joueur2.setText("");
    }
    
    public Button formPlayer1 (ControllerPuissance cont) {
    	Button b = new Button("Start Game");
    	b.setOnAction(event -> cont.form());
    	return b;
    }
    
    public Button formPlayer (ControllerPuissance cont) {
    	Button b = new Button("Next");
    	b.setOnAction(event -> cont.chekEmpty(firstNameTextField,lastNameTextField));
    	return b;
    }
    
    public Button formPlayer2 (ControllerPuissance cont) {
    	Button b = new Button("Next");
    	b.setOnAction(event -> cont.chekEmpty2(firstNameTextField,lastNameTextField));
    	return b;
    }
    
    
    public Button getStartButton (ControllerPuissance cont) {
    	Button b = new Button("Go");
        b.setOnAction(event -> cont.chekEmpty(firstNameTextField,lastNameTextField));
        return b;
    }
    
    
    public GridPane createButton() {
    	gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                Button button = new Button();
                buttonList[row][col] = button;
                button.setStyle("-fx-background-radius: 0;" 
                        + "-fx-min-width: 50px;" 
                        + "-fx-min-height: 50px;" 
                        + "-fx-max-width: 50px;"
                        + "-fx-max-height: 50px;"
                );
                GridPane.setColumnIndex(button, 1);
                gridPane.add(button, col, 5 - row);
                
            }
        }
        return gridPane;
    	
    }
    public void resetGridPane(GridPane gridPane) {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                Button button = (Button) gridPane.getChildren().get(row * 7 + col);
                button.setStyle("-fx-background-radius: 0;" 
                        + "-fx-min-width: 50px;" 
                        + "-fx-min-height: 50px;" 
                        + "-fx-max-width: 50px;"
                        + "-fx-max-height: 50px;"
                );
            }
        }
    }
    public GridPane getGridPane() {
    	return this.gridPane;
    }
    public Button[][] getButton() {
    	return buttonList;
    }
    
    public void setCouleurButton(int numligne, int numColonne, String couleur) {
    	buttonList[numligne][numColonne].setStyle("-fx-background-radius: 0;" 
				+ "-fx-min-width: 50px;" 
				+ "-fx-min-height: 50px;" 
				+ "-fx-max-width: 50px;"
				+ "-fx-max-height: 50px;"
				+ "-fx-background-color:" + couleur + ";" );
	}
    
}
