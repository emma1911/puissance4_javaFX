package PuissanceModel.DAO;

import PuissanceModel.Joueur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoJoueur extends Dao <Joueur>{

	
	@Override
	public Boolean create(Joueur a) {

		try (Connection connection = Dao.getConnection()) {
            String rq = "insert into player( `Nom`, `Prenom` , `score`, `nombrePartie` ) values (?,?,?,?)";
            PreparedStatement ps=connection.prepareStatement(rq);
            ps.setString(1, a.getNom());
            ps.setString(2, a.getPrenom());
            ps.setInt(3, a.getScore());
            ps.setInt(4, a.getNombrePartie());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
		 
	}
	
	public Joueur findByNameAndPrenom(String nom, String prenom) {
        Joueur joueur = null;
        String sql = "SELECT * FROM player WHERE Nom = ? AND Prenom = ?";
        try (Connection connection = Dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nom);
            statement.setString(2, prenom);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int score = resultSet.getInt("score");
                    int nombrePartie = resultSet.getInt("nombrePartie");
                    joueur = new Joueur(id, nom, prenom, score, nombrePartie);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return joueur;
    }
	 

	public Integer getPlayerIdByName(String playerName) {
        String sql = "SELECT id FROM player WHERE nom = ?";
        try (Connection connection = Dao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, playerName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if player does not exist
    }
	
	@Override
	public Joueur find(Joueur a) {
		Joueur j = null;
		try (Connection connection = Dao.getConnection()) {
            String rq = "SELECT * FROM player where id = ?";
            PreparedStatement ps=connection.prepareStatement(rq);
            ps.setLong(1,a.getId());
            ResultSet resultSet =ps.executeQuery();
            while (resultSet.next()) {
            	int id = resultSet.getInt("id");
            	String nom = resultSet.getString("Nom");
            	String prenom = resultSet.getString("Prenom");
            	int score = resultSet.getInt("score");
            	int nbpartie = resultSet.getInt("nbpartie");
            	j = new Joueur (id,nom,prenom,score,nbpartie);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return j;	
	}

	
	@Override
	public List<Joueur> findAll() {
		List <Joueur> j = new ArrayList <>() ;
		try (Connection connection = Dao.getConnection()) {
            String rq = "SELECT * FROM player";
            PreparedStatement ps=connection.prepareStatement(rq);
            ResultSet resultSet =ps.executeQuery();
            while (resultSet.next()) {
            	int id = resultSet.getInt("id");
            	String nom = resultSet.getString("Nom");
            	String prenom = resultSet.getString("Prenom");
            	int score = resultSet.getInt("score");
            	int nbpartie = resultSet.getInt("nbpartie");
            	j.add(new Joueur (id,nom,prenom,score,nbpartie));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return j;	
	}

	@Override
	public Boolean update(Joueur a) {
		try (Connection connection = Dao.getConnection()) {
            String rq = "UPDATE player" + 
            		"SET Nom = ?, score = ?" + 
            		"WHERE id = ?";
            PreparedStatement ps=connection.prepareStatement(rq);
            ps.setString(1, a.getNom());
            ps.setInt(2, a.getScore());
            ps.setInt(3, a.getId());
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
		
	}
	
	@Override
	public boolean delete(Joueur a) {
		try (Connection connection = Dao.getConnection()) {
            String rq = "DELETE FROM player WHERE id = ?";
            PreparedStatement ps=connection.prepareStatement(rq);
            ps.setLong(1,a.getId());
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}

	
}







