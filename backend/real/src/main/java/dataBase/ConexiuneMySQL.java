package dataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import entities.Masa;
import entities.Restaurant;
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

	public ArrayList<Restaurant> getRestaurants() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Masa> getMese(Restaurant r) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addRestaurant() {
		// TODO Auto-generated method stub
		return null;
	}

	public String addMasa(Restaurant r) {
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

	public String getReservations(Restaurant r) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addZona(Restaurant r) {
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

	public String changeDescriere(Restaurant r) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDescriere(Restaurant r) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getOrar() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCurrentSeats(Restaurant r) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	

	

	

	

	

}
