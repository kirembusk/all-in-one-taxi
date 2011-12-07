package com.taxiride.model;

/*
 * Provide a taxi driver info structure when returning an object from the server
 * 
 */
public class TaxiDriver {
	

	private Long id;
	private String loginName;
	private String loginPin;
	private String fullName;
	private String cabName;
	private String phoneNumber;
	private String currentLatitude;
	private String currentLongitude;
	private String maxPickupDistance;
	private String maxDropOffDistance;
	private String preferredPayment;
	private String isAvailable;
	private boolean isAuth;
	
	
	public TaxiDriver(Long id, String loginName, String loginPin,
			String fullName, String cabName, String phoneNumber,
			String currentLatitude, String currentLongitude,
			String maxPickupDistance, String maxDropOffDistance,
			String preferredPayment, String isAvailable, boolean isAuth) {
		this.id = id;
		this.loginName = loginName;
		this.loginPin = loginPin;
		this.fullName = fullName;
		this.cabName = cabName;
		this.phoneNumber = phoneNumber;
		this.currentLatitude = currentLatitude;
		this.currentLongitude = currentLongitude;
		this.maxPickupDistance = maxPickupDistance;
		this.maxDropOffDistance = maxDropOffDistance;
		this.preferredPayment = preferredPayment;
		this.isAvailable = isAvailable;
		this.isAuth = isAuth;
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
	public String getMaxPickupDistance() {
		return maxPickupDistance;
	}
	public void setMaxPickupDistance(String maxPickupDistance) {
		this.maxPickupDistance = maxPickupDistance;
	}
	public String getMaxDropOffDistance() {
		return maxDropOffDistance;
	}
	public void setMaxDropOffDistance(String maxDropOffDistance) {
		this.maxDropOffDistance = maxDropOffDistance;
	}
	public String getPreferredPayment() {
		return preferredPayment;
	}
	public void setPreferredPayment(String preferredPayment) {
		this.preferredPayment = preferredPayment;
	}
	public String getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}
	public boolean isAuth() {
		return isAuth;
	}
	public void setAuth(boolean isAuth) {
		this.isAuth = isAuth;
	}
	
	

	
}
