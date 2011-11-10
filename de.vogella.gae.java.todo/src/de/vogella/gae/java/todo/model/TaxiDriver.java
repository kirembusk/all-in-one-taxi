package de.vogella.gae.java.todo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/** * Model class which will store the Todo Items * * @author Lars Vogel * */

@Entity
public class TaxiDriver {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String loginName;
	private String loginPin;
	private String fullName;
	private String cabName;
	private String phoneNumber;
	private String currentLatitude;
	private String currentLongitude;
	boolean isAvailable;
	
	
	public TaxiDriver(String loginName, String loginPin,
			String fullName, String cabName, String phoneNumber,
			String currentLatitude, String currentLongitude) {
		this.loginName = loginName;
		this.loginPin = loginPin;
		this.fullName = fullName;
		this.cabName = cabName;
		this.phoneNumber = phoneNumber;
		this.currentLatitude = currentLatitude;
		this.currentLongitude = currentLongitude;
		this.isAvailable = true;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPin() {
		return loginPin;
	}
	public void setLoginPin(String loginPin) {
		this.loginPin = loginPin;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getCabName() {
		return cabName;
	}
	public void setCabName(String cabName) {
		this.cabName = cabName;
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
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	

}

