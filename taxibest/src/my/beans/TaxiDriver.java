package my.beans;

public class TaxiDriver {
	private String id;
	private String name;
	private String pin;
	private String phoneNumber;
	private String currentLatitude;
	private String currentLongitude;
	
	public TaxiDriver() {
		this.id = "";
		this.name = "";
		this.pin = "";
		this.phoneNumber = "";
		this.currentLatitude = "";
		this.currentLongitude = "";
	}
	
	public TaxiDriver(String id, String name, String pin, String phoneNumber,
			String currentLatitude, String currentLongitude) {
		this.id = id;
		this.name = name;
		this.pin = pin;
		this.phoneNumber = phoneNumber;
		this.currentLatitude = currentLatitude;
		this.currentLongitude = currentLongitude;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCurrentLatitude() {
		return currentLatitude;
	}
	public void setCurrentLatitude(String currentLatitude) {
		this.currentLatitude = currentLatitude;
	}
	public String getCurrentLongitude() {
		return currentLongitude;
	}
	public void setCurrentLongitude(String currentLongitude) {
		this.currentLongitude = currentLongitude;
	}
	
	

}
