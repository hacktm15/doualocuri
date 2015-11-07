package entities;

public class Pub {
	private final int id;
	private String name;
	private String about;
	private double lat;
	
	public Pub(double lat, String name, String about, double lng, int id, String adresa, boolean isOpen) {
		this.lat = lat;
		this.name = name;
		this.about = about;
		this.lng = lng;
		this.id = id;
		this.adresa = adresa;
		this.isOpen = isOpen;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	private double lng;
	private String adresa;
	private boolean isOpen;
	
}
