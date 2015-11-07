package dataBase;
import java.util.ArrayList;
import java.util.Collection;
import entities.*;

public interface Conexiune {

	public ArrayList<Restaurant> getRestaurants();
	
	public ArrayList<Masa> getMese(Restaurant r);
	
	public String addRestaurant();
	
	public String addMasa(Restaurant r);
	
	public String makeReservation(Masa m);

	public String getReservations(Masa m);
	
	public String getReservations(Restaurant r);
	
	public String addZona(Restaurant r);
	
	public String deleteZona(Zona z);
	
	public String freeTable(Masa m);
	
	public String occupyTable(Masa m);
	
	public String addUser();
	
	public String deleteUser();
	
	public String changeDescriere(Restaurant r);
	
	public String getDescriere(Restaurant r);
	
	public String getOrar();
	
	public int getCurrentSeats(Restaurant r);

}
