package dataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import entities.Masa;
import entities.Pub;
import entities.Zona;

public class ConexiuneMySQL implements Conexiune {

	Connection connection;
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost/horoscop";
	public static final String username = "root";
	public static final String password = "13289399";

	public ConexiuneMySQL(String where, String user, String pass) {
		connection = null;
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(URL, username, password);
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}

	public ArrayList<Pub> getPubs() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Masa> getMese(Pub r) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addPub() {
		// TODO Auto-generated method stub
		return null;
	}

	public String addMasa(Pub r) {
		// TODO Auto-generated method stub
		return null;
	}

	public String makeReservation(Masa m) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getReservations(Masa m) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getReservations(Pub r) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addZona(Pub r) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteZona(Zona z) {
		// TODO Auto-generated method stub
		return null;
	}

	public String freeTable(Masa m) {
		// TODO Auto-generated method stub
		return null;
	}

	public String occupyTable(Masa m) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public String changeDescriere(Pub r) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDescriere(Pub r) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getOrar() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCurrentSeats(Pub r) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	

	

	

	

	

}
