package PuissanceModel.DAO;

import PuissanceModel.Coup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCoup extends Dao <Coup>{

	@Override
	public Boolean create(Coup a) {
		try (Connection connection = Dao.getConnection()) {
            String rq = "insert into coup( `numero_column_j1`, `numero_column_j2`) values (?,?)";
            PreparedStatement ps=connection.prepareStatement(rq);
            ps.setLong(1,a.getCoupJ1());
            ps.setLong(2,a.getCoupJ2());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }	
	}

	@Override
	public Coup find(Coup a) {
		Coup c = null;
	    try (Connection connection = getConnection()) {
	        String rq = "SELECT * FROM coup WHERE id_coup = ?";
	        PreparedStatement ps = connection.prepareStatement(rq);
	        ps.setLong(1, a.getId());
	        ResultSet resultSet = ps.executeQuery();
	        while (resultSet.next()) {
	            int numero_column_j1 = resultSet.getInt("numero_column_j1");
	            int numero_column_j2 = resultSet.getInt("numero_column_j2");	
	            c = new Coup(numero_column_j1,numero_column_j2);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return c;
	}

	@Override
	public List<Coup> findAll() {
		List <Coup> c = new ArrayList <>() ;
		try (Connection connection = Dao.getConnection()) {
            String rq = "SELECT * FROM coup";
            PreparedStatement ps=connection.prepareStatement(rq);
            ResultSet resultSet =ps.executeQuery();
            while (resultSet.next()) {
            	int numero_column_j1 = resultSet.getInt("numero_column_j1");
	            int numero_column_j2 = resultSet.getInt("numero_column_j2");	
	            c.add(new Coup (numero_column_j1,numero_column_j2));
            	
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return c;
	}

	@Override
	public Boolean update(Coup a) {
		try (Connection connection = Dao.getConnection()) {
	        String rq = "UPDATE coup " + 
	                    "SET numero_column_j1 = ?, numero_column_j2 = ? " + 
	                    "WHERE id_coup = ?";
	        PreparedStatement ps = connection.prepareStatement(rq);
	        ps.setLong(1, a.getCoupJ1());
	        ps.setLong(2, a.getCoupJ2());
	        ps.setLong(3, a.getId());
	        ps.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean delete(Coup a) {
		try (Connection connection = Dao.getConnection()) {
            String rq = "DELETE FROM coup WHERE id_coup = ?";
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
