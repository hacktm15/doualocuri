package dataBase;
import java.util.ArrayList;
import java.util.Collection;
import entities.*;

public interface Conexiune {

	public ArrayList<Pub> getPubs();
	
	public ArrayList<Masa> getMese(Pub r);
	
	public String addPub();
	
	public String addMasa(Pub r);
	
	public String makeReservation(Masa m);

	public String getReservations(Masa m);
	
	public String getReservations(Pub r);
	
	public String addZona(Pub r);
	
	public String deleteZona(Zona z);
	
	public String freeTable(Masa m);
	
	public String occupyTable(Masa m);
	
	public String addUser();
	
	public String deleteUser();
	
	public String changeDescriere(Pub r);
	
	public String getDescriere(Pub r);
	
	public String getOrar();
	
	public int getCurrentSeats(Pub r);

}
