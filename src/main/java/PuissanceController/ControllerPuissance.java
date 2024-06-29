package PuissanceController;

import PuissanceInterface.InterfacePuissance;
import PuissanceModel.*;
import PuissanceModel.DAO.Dao;
import PuissanceModel.DAO.DaoJoueur;
import PuissanceModel.DAO.DaoPartie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;


public class ControllerPuissance {
	InterfacePuissance interfacePuissance = new InterfacePuissance();
	BorderPane border;
	private TableView<Joueur> playerTable;
	private TableView<Partie> partieTable;

    private TextField firstNameTextField = new TextField();
    private TextField lastNameTextField = new TextField();
	GestionJoueur gestion = new GestionJoueur();
	private TextField filePathTextField;
	
	public ControllerPuissance(BorderPane border) {
		this.border = border;
		this.playerTable = new TableView<>();
		this.partieTable = new TableView<>();
        this.filePathTextField = new TextField(); // Create a TextField

	}
	 
	public void getStart () {
		
		this.border.setTop(interfacePuissance.menuBar(this));
		this.border.setCenter(interfacePuissance.formPlayer1(this));
	}
	
	public void lancerPuissance() {
		this.border.setTop(interfacePuissance.menuBar(this));
		this.border.setLeft(interfacePuissance.CreateJoueur1());
		this.border.setCenter(interfacePuissance.createButton());
		this.border.setRight(interfacePuissance.CreateJoueur2());
		this.border.setBottom(interfacePuissance.CreateAnnulerButton());
		gestionAction();
	}
	
	public void form0() {
        // Create a VBox for the form
        VBox formBox = new VBox();
        formBox.setAlignment(Pos.CENTER);
        formBox.setSpacing(10);
        
        // Create labels and text fields for first name and last name
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");

        // Add labels and text fields to the VBox
        formBox.getChildren().addAll(firstNameLabel, firstNameTextField, lastNameLabel, lastNameTextField);

        // Add the formBox to the top of the border pane
        border.setTop(formBox);
		this.border.setCenter(interfacePuissance.getStartButton(this));

    }
	
	public void listjoueurSelonNbPartie() {
		border.setLeft(null);
	 	border.setRight(null);
        // Connect to the database and retrieve player data
        ObservableList<Joueur> joueurs = retrieveJoueurDataFromDatabase2();

        // Populate the TableView with player data
        TableColumn<Joueur, String> nom = new TableColumn<>("Nom");
        nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        TableColumn<Joueur, String> prenom = new TableColumn<>("Prenom");
	    prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));	    
        TableColumn<Joueur, String> id = new TableColumn<>("id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Joueur, String> score = new TableColumn<>("score");
        score.setCellValueFactory(new PropertyValueFactory<>("score"));
        TableColumn<Joueur, Integer> nombrePartieColumn = new TableColumn<>("Nombre Partie");
        nombrePartieColumn.setCellValueFactory(new PropertyValueFactory<>("nombrePartie"));

        
        playerTable.getColumns().clear();
        playerTable.setItems(joueurs);
        playerTable.getColumns().add(id);
        playerTable.getColumns().add(nom);
        playerTable.getColumns().add(prenom);

        playerTable.getColumns().add(score);
        playerTable.getColumns().add(nombrePartieColumn);

        // Add the TableView to the border pane
        border.setCenter(playerTable);
	}
	
	public void form() {
        // Create a VBox for the form
        VBox formBox = new VBox();
        formBox.setAlignment(Pos.CENTER);
        formBox.setSpacing(10);
        
        Label player1 = new Label("Player 1");
        // Create labels and text fields for first name and last name
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");

        // Add labels and text fields to the VBox
        formBox.getChildren().addAll(player1,firstNameLabel, firstNameTextField, lastNameLabel, lastNameTextField);

        // Add the formBox to the top of the border pane
        border.setTop(formBox);
		this.border.setCenter(interfacePuissance.formPlayer2(this));

    }
	
	public void form2() {
        // Create a VBox for the form
        VBox formBox = new VBox();
        formBox.setAlignment(Pos.CENTER);
        formBox.setSpacing(10);
        
        Label player2 = new Label("Player 2");
        // Create labels and text fields for first name and last name
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");

        // Add labels and text fields to the VBox
        formBox.getChildren().addAll(player2,firstNameLabel, firstNameTextField, lastNameLabel, lastNameTextField);

        // Add the formBox to the top of the border pane
        border.setTop(formBox);
		this.border.setCenter(interfacePuissance.getStartButton(this));

    }
	
	public void chekEmpty(TextField firstNameTextField , TextField lastNameTextField ) {
    	if (this.firstNameTextField.getText().isEmpty() && this.lastNameTextField.getText().isEmpty()) {
            System.out.println("First name and last name must not be empty");

        } else {
        		lancerPuissance();
        		int id = Integer.parseInt("10");
                String nom = this.firstNameTextField.getText();
                String prenom = this.lastNameTextField.getText();
                int score = Integer.parseInt("100");
                
                // Create a new Joueur object
                Joueur joueur = new Joueur(id, nom,prenom, score, 0);
                
                // Insert the joueur object into the database
                DaoJoueur daoJoueur = new DaoJoueur();
            	daoJoueur.create(joueur);
                System.out.println("Done!!");
        }
    }
	
	public void chekEmpty2(TextField firstNameTextField , TextField lastNameTextField ) {
    	if (this.firstNameTextField.getText().isEmpty() && this.lastNameTextField.getText().isEmpty()) {
            System.out.println("First name and last name must not be empty");

        } else {
        	form2();
        	int id = Integer.parseInt("100");
            String nom = this.firstNameTextField.getText();
            String prenom = this.lastNameTextField.getText();
            int score = Integer.parseInt("20");
            // Create a new Joueur object
            Joueur joueur = new Joueur(id, nom,prenom, score);
            
            // Insert the joueur object into the database
            DaoJoueur daoJoueur = new DaoJoueur();
            daoJoueur.create(joueur);
            System.out.println("Done!!");
        }
    }
	
	

	public void listjoueur() {
	    border.setLeft(null);
	    border.setRight(null);
	    // Connect to the database and retrieve player data
	    ObservableList<Joueur> joueurs = retrieveJoueurDataFromDatabase();

	    // Populate the TableView with player data
	    TableColumn<Joueur, String> nom = new TableColumn<>("Nom");
	    nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
	    TableColumn<Joueur, String> prenom = new TableColumn<>("Prenom");
	    prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
	    TableColumn<Joueur, String> id = new TableColumn<>("Id");
	    id.setCellValueFactory(new PropertyValueFactory<>("id"));
	    TableColumn<Joueur, String> score = new TableColumn<>("Score");
	    score.setCellValueFactory(new PropertyValueFactory<>("score"));

	    playerTable.getColumns().clear();
	    playerTable.setItems(joueurs);
	    playerTable.getColumns().add(id);
	    playerTable.getColumns().add(nom);
	    playerTable.getColumns().add(prenom);
	    playerTable.getColumns().add(score);

	    // Add the TableView to the border pane
	    border.setCenter(playerTable);
	}
	 
	 public void listPartie() {
		 border.setLeft(null);
	        border.setRight(null);
	        // Connect to the database and retrieve game data
	        ObservableList<Partie> parties = retrieveJoueurDataFromDatabase3();

	        // Populate the TableView with game data
	        TableColumn<Partie, Integer> idPartieCol = new TableColumn<>("ID Partie");
	        idPartieCol.setCellValueFactory(new PropertyValueFactory<>("id"));
	        TableColumn<Partie, String> nomj1 = new TableColumn<>("Joueur 1");
	        nomj1.setCellValueFactory(new PropertyValueFactory<>("j1"));
	        TableColumn<Partie, String> nomj2 = new TableColumn<>("Joueur 2");
	        nomj2.setCellValueFactory(new PropertyValueFactory<>("j2"));
	        TableColumn<Partie, Integer> scoreJ1Col = new TableColumn<>("Score Joueur 1");
	        scoreJ1Col.setCellValueFactory(new PropertyValueFactory<>("scorej1"));

	        TableColumn<Partie, Integer> scoreJ2Col = new TableColumn<>("Score Joueur 2");
	        scoreJ2Col.setCellValueFactory(new PropertyValueFactory<>("scorej2"));
	        TableColumn<Partie, Integer> nbJetonJ1 = new TableColumn<>("Nombre Jeton 1");
	        nbJetonJ1.setCellValueFactory(new PropertyValueFactory<>("nbJetonJ1"));
	        TableColumn<Partie, Integer> nbJetonJ2 = new TableColumn<>("Nombre Jeton 2");
	        nbJetonJ2.setCellValueFactory(new PropertyValueFactory<>("nbJetonJ1"));

	        partieTable.getColumns().clear();
	        partieTable.setItems(parties);
	        partieTable.getColumns().add(idPartieCol);
	        partieTable.getColumns().add(nomj1);
	        partieTable.getColumns().add(nomj2);
	        partieTable.getColumns().add(scoreJ1Col);
	        partieTable.getColumns().add(scoreJ2Col);
	        partieTable.getColumns().add(nbJetonJ1);
	        partieTable.getColumns().add(nbJetonJ2);



	        // Add the TableView to the border pane
	        border.setCenter(partieTable);
	    }
	 
		public void importer() {
	        // Create a VBox to hold the TextField and import button
	        VBox vbox = new VBox();
	        vbox.setAlignment(Pos.CENTER);
	        vbox.setSpacing(10);
	        
	        // Add the TextField to the VBox
	        vbox.getChildren().add(filePathTextField);
	        
	        // Create a Button for importing
	        Button importButton = new Button("Import");
	        // Here we have the action that the button will get it
	        importButton.setOnAction(event -> {
	            // Get the text from the TextField
	            String filePath = filePathTextField.getText();
	            
	            File file = new File(filePath);
	            if (file.exists()) {
	                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
	                    String line;
                        DaoPartie daoPartie = new DaoPartie();
	                    while ((line = bufferedReader.readLine()) != null) {
	                    	String[] fields = line.split(",");
	                    	System.out.print(fields);
	                        if (fields.length == 9) { 
	                            int idPartie = Integer.parseInt(fields[0].trim());
	                            String nomJ1 = fields[1].trim();
	                            String prenomJ1 = fields[2];
	                            int scoreJ1 = Integer.parseInt(fields[3].trim());
	                            String nomJ2 = fields[4].trim();
	                            String prenomJ2 = fields[5].trim();
	                            int scoreJ2 = Integer.parseInt(fields[6].trim());
	                            int nbJetonJ1 = Integer.parseInt(fields[7].trim());
	                            int nbJetonJ2 = Integer.parseInt(fields[8].trim());
	                            
	                            int idj1 = retrieveOrGeneratePlayerId(nomJ1,prenomJ1, scoreJ1); 
	                            int idj2 = retrieveOrGeneratePlayerId(nomJ2,prenomJ2, scoreJ2); 

	                            Joueur joueur1 = new Joueur(idj1, nomJ1, prenomJ1, scoreJ1);
	                            Joueur joueur2 = new Joueur(idj2, nomJ2,prenomJ2, scoreJ2);

	                            Partie partie = new Partie();
	                            partie.setId(idPartie);
	                            partie.setJ1(joueur1);
	                            partie.setJ2(joueur2);
	                            partie.setNbJetonJ1(nbJetonJ1);
	                            partie.setNbJetonJ2(nbJetonJ2);
	                            partie.setScoreJ1(scoreJ1);
	                            partie.setScoreJ2(scoreJ2);
	                            
	                            // Insert the partie into the database
	                            daoPartie.create(partie);
	                            
	                            System.out.println("Done!!");
	                        }                    }
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            } else {
	                System.out.println("File " + file + " does not exist");
	            }
	        });
	        
	        // Add the import button to the VBox
	        vbox.getChildren().add(importButton);
	        
	        // Add the VBox to the border pane
	        border.setCenter(vbox);
	    }
		

		//to generate the id player
		private int retrieveOrGeneratePlayerId(String playerName, String playerPrenom, int score) {
	        // Example logic: Query the database to check if the player exists, if not, insert and get the ID
	        DaoJoueur daoJoueur = new DaoJoueur();
	        Integer playerId = daoJoueur.getPlayerIdByName(playerName);
	        if (playerId == null) {
	            Joueur joueur = new Joueur(0,playerName,playerPrenom, score,0); // Assuming a constructor that takes the name and score
	            daoJoueur.create(joueur);
	            playerId = daoJoueur.getPlayerIdByName(playerName);
	        }
	        return playerId;
	    }

		private ObservableList<Joueur> retrieveJoueurDataFromDatabase2() {
	        ObservableList<Joueur> joueurs = FXCollections.observableArrayList();
	        
	        String query = "SELECT joueur, COUNT(*) AS nombrePartie FROM (" +
	                       "    SELECT joueur_1 AS joueur FROM partie " +
	                       "    UNION ALL " +
	                       "    SELECT joueur_2 AS joueur FROM partie" +
	                       ") AS all_players GROUP BY joueur ORDER BY nombrePartie DESC";
	        
	        try (Connection connection = Dao.getConnection();
				 PreparedStatement ps = connection.prepareStatement(query);
				 ResultSet rs = ps.executeQuery()) {
	            
	            while (rs.next()) {
	                int id = rs.getInt("joueur");
	                String nom = getPlayerNameById(id);
	                String prenom = getPlayerPrenomById(id);  
	                int score = getPlayerScoreById(id);  
	                int nombrePartie = rs.getInt("nombrePartie");
	                Joueur joueur = new Joueur(id, nom, prenom, score, nombrePartie);
	                joueurs.add(joueur);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return joueurs;
	        }
		
		private ObservableList<Partie> retrieveJoueurDataFromDatabase3() {
		    ObservableList<Partie> parties = FXCollections.observableArrayList();
		    try (Connection connection = Dao.getConnection()) {
		        Statement statement = connection.createStatement();
		        ResultSet resultSet = statement.executeQuery("SELECT * FROM partie");

		        // Populate the list of players
		        while (resultSet.next()) {
		            int id = resultSet.getInt("id_partie");
		            int idj1 = resultSet.getInt("joueur_1");
		            int idj2 = resultSet.getInt("joueur_2");
		            int scorej1 = getPlayerScoreById(idj1);
		            int scorej2 = getPlayerScoreById(idj2);
		            int nbJetonj1 = resultSet.getInt("nombre_jeton_j1");
		            int nbJetonj2 = resultSet.getInt("nombre_jeton_j2");

		            String nomj1 = getPlayerNameById(idj1);
		            String nomj2 = getPlayerNameById(idj2);
		            String prenomj1 = getPlayerPrenomById(idj1);
		            String prenomj2 = getPlayerPrenomById(idj2);
		            
		            Joueur joueur1 = new Joueur(idj1, nomj1, prenomj1, scorej1);
		            Joueur joueur2 = new Joueur(idj2, nomj2, prenomj2, scorej2);

		            Partie partie = new Partie(id);
		            partie.setJ1(joueur1);
		            partie.setJ2(joueur2);
		            partie.setNbJetonJ1(nbJetonj1);
		            partie.setNbJetonJ2(nbJetonj2);
		            partie.setScoreJ1(scorej1);
		            partie.setScoreJ2(scorej2);

		            parties.add(partie);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return parties;
		}
		
		
		private String getPlayerNameById(int id) {
		    String name = null;
		    String query = "SELECT Nom FROM player WHERE id = ?";

		    try (Connection connection = Dao.getConnection();
		         PreparedStatement ps = connection.prepareStatement(query)) {
		        ps.setInt(1, id);
		        try (ResultSet rs = ps.executeQuery()) {
		            if (rs.next()) {
		                name = rs.getString("nom");
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return name;
		}
		
		private String getPlayerPrenomById(int id) {
		    String prenom = null;
		    String query = "SELECT Prenom FROM player WHERE id = ?";

		    try (Connection connection = Dao.getConnection();
		         PreparedStatement ps = connection.prepareStatement(query)) {
		        ps.setInt(1, id);
		        try (ResultSet rs = ps.executeQuery()) {
		            if (rs.next()) {
		            	prenom = rs.getString("prenom");
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return prenom;
		}

		// Helper method to retrieve player score by ID
		private int getPlayerScoreById(int id) {
		    int score = 0;
		    String query = "SELECT score FROM player WHERE id = ?";

		    try (Connection connection = Dao.getConnection();
		         PreparedStatement ps = connection.prepareStatement(query)) {
		        ps.setInt(1, id);
		        try (ResultSet rs = ps.executeQuery()) {
		            if (rs.next()) {
		                score = rs.getInt("score");
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return score;
		}
		
	    // to get player data from the database
		private ObservableList<Joueur> retrieveJoueurDataFromDatabase() {
		    ObservableList<Joueur> joueurs = FXCollections.observableArrayList();
		    try (Connection connection = Dao.getConnection()) {
		        Statement statement = connection.createStatement();
		        ResultSet resultSet = statement.executeQuery("SELECT * FROM player");

		        // Populate the list of players
		        while (resultSet.next()) {
		            joueurs.add(new Joueur(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getInt("score"), resultSet.getInt("nombrePartie")));
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return joueurs;
		}
	    
	    
	    
	    
	public void gestionAction() {
		Partie partie = new Partie();
		Game game = new Game(partie.getJ1().getId(),partie.getJ2().getId());
		for(int i = 0 ; i < 6 ; i++) {
			 for (int j = 0; j < 7; j++) {
				Button b = interfacePuissance.getButton()[i][j];
				final int jj = j;
				b.setOnAction(event -> {
					try {
						if (partie.getRolejoueur() == partie.getJ1().getId()) {
							if(jeux(partie,game,jj,"#ED83AA",partie.getJ1(),partie.getJ2())) {
								this.afficherDialogue("winer", "Le joueur " + partie.getJ1().getNom() + " he is the winer !");
								game.initialiseGrille();
								interfacePuissance.resetGridPane(interfacePuissance.getGridPane());
							}
						}
						else {
							if(jeux(partie,game,jj,"#87CEEB",partie.getJ2(),partie.getJ1())) {
								this.afficherDialogue("winer", " the player " + partie.getJ2().getNom() + " he is the winer !");
								game.initialiseGrille();
								interfacePuissance.resetGridPane(interfacePuissance.getGridPane());
							}
						}
					}
					catch (CoupException e) {
						System.out.print(e.getMessage());
					}
					
				});
				Button annulerButton = interfacePuissance.getAnnulerButton();
			    annulerButton.setOnAction(event -> {
			    	game.initialiseGrille();
			    	interfacePuissance.resetGridPane(interfacePuissance.getGridPane());
			    });
			 }
		}
	}
	private void afficherDialogue(String titre, String message) {
	    Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle(titre);
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();
	}
	public Boolean jeux(Partie partie , Game game, int numColonne , String color, PuissanceModel.Joueur joueur, PuissanceModel.Joueur adversaire) throws CoupException {
		final int numLigne = game.getLigneVideByColonne(numColonne);
		if (numLigne != -1) {
			game.setCoup(numLigne, numColonne, joueur.getId());
			System.out.println(game);
			interfacePuissance.setCouleurButton(numLigne,numColonne,color);
			partie.modifieRole();
            if (game.estGagnant(numLigne, numColonne, joueur.getId())) {
            	System.out.println("winer");
            	joueur.incrementerScore();
            	adversaire.decrementerScore();
                return true;
            } else {
                if (game.estRemplie()) {
                	this.afficherDialogue("Null", "La partie est null entre le joueur " + joueur.getNom() + " et le joueur " + adversaire.getNom());
                	game.initialiseGrille();
                	interfacePuissance.resetGridPane(interfacePuissance.getGridPane());
                	return true;
                }
            }
        } else {
            return false;
        }
		return false;
		
	}
}