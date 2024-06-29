package PuissanceModel.DAO;

import PuissanceModel.Joueur;
import PuissanceModel.Partie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoPartie extends Dao <Partie>{

	@Override
	public Boolean create(Partie a) {
		try (Connection connection = Dao.getConnection()) {
			
            String rq = "insert into partie( `joueur_1`, `joueur_2`,`nombre_jeton_j1`,`nombre_jeton_j2`,`score_j1`,`score_j2`) values (?,?,?,?,?,?)";
            PreparedStatement ps=connection.prepareStatement(rq);
            ps.setLong(1,a.getJ1().getId());
            ps.setLong(2,a.getJ2().getId());
            ps.setLong(3,a.getNbJetonJ1());
            ps.setLong(4,a.getNbJetonJ2());
            ps.setLong(5,a.getScoreJ1());
            ps.setLong(6,a.getScoreJ2());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }	
	}

	


	public Partie find(Partie a) {
		Partie p = null;
	    try (Connection connection = getConnection()) {
	        String rq = "SELECT * FROM partie WHERE id_partie = ?";
	        PreparedStatement ps = connection.prepareStatement(rq);
	        ps.setLong(1, a.getId());
	        ResultSet resultSet = ps.executeQuery();
	        while (resultSet.next()) {
	            int j1Id = resultSet.getInt("joueur_1");
	            int j2Id = resultSet.getInt("joueur_2");
	            int nbJetonJ1 = resultSet.getInt("nombre_jeton_j1");
	            int nbJetonJ2 = resultSet.getInt("nombre_jeton_j2");
	            int scoreJ1 = resultSet.getInt("score_j1");
	            int scoreJ2 = resultSet.getInt("score_j2");
	            Joueur j1 = new Joueur(j1Id, null ,null, 0, 0); 
	            Joueur j2 = new Joueur(j2Id, null ,null, 0, 0); 
	            DaoJoueur dj = new DaoJoueur();
	            j1 = dj.find(j1);
	            j2 = dj.find(j2);
	            p = new Partie(j1, j2);
	            p.setNbJetonJ1(nbJetonJ1);
	            p.setNbJetonJ2(nbJetonJ2);
	            p.setScoreJ1(scoreJ1);
	            p.setScoreJ2(scoreJ2);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return p;
	}
	
	@Override
	public List<Partie> findAll() {
		List <Partie> p = new ArrayList <>() ;
		try (Connection connection = Dao.getConnection()) {
            String rq = "SELECT * FROM partie";
            PreparedStatement ps=connection.prepareStatement(rq);
            ResultSet resultSet =ps.executeQuery();
            while (resultSet.next()) {
            	int j1Id = resultSet.getInt("joueur_1");
	            int j2Id = resultSet.getInt("joueur_2");
	            Joueur j1 = new Joueur(j1Id, null ,null, 0, 0); 
	            Joueur j2 = new Joueur(j2Id, null ,null, 0, 0);
	            DaoJoueur dj = new DaoJoueur();
	            j1 = dj.find(j1);
	            j2 = dj.find(j2);
            	p.add(new Partie (j1,j2));
            	
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return p;	
	}

	@Override
	public Boolean update(Partie a) {
	    try (Connection connection = Dao.getConnection()) {
	        String rq = "UPDATE partie " + 
	                    "SET nombre_jeton_j1 = ?, nombre_jeton_j2 = ?, score_j1 = ?, score_j2 = ? " + 
	                    "WHERE id_partie = ?";
	        PreparedStatement ps = connection.prepareStatement(rq);
	        ps.setLong(1, a.getNbJetonJ1());
	        ps.setLong(2, a.getNbJetonJ2());
	        ps.setLong(3, a.getScoreJ1());
	        ps.setLong(4, a.getScoreJ2());
	        ps.setLong(5, a.getId());
	        ps.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean delete(Partie a) {
		try (Connection connection = Dao.getConnection()) {
            String rq = "DELETE FROM partie WHERE id_partie = ?";
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
