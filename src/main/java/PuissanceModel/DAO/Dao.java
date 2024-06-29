package PuissanceModel.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public abstract class Dao <T> {
	
	public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/puissance4";
        String username = "root";
        String password = "";
        return DriverManager.getConnection(url, username, password);
    }
	
	
	public abstract Boolean create (T a);
	
	public abstract T find (T a);
	
	public abstract List <T> findAll ();
		
	public abstract Boolean update (T a);
	
	public abstract boolean delete (T a);
	
	
}
