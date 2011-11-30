package com.taxiride;

public class DriverInfo {
	
	private String deviceID;
	private String fullName;
	private String pin;
	private String cabName;
	private String phoneNum;
	private String maxPickUp;
	private String maxDropOff;
	private String paymentType; 
	private String currentLatitude;
	private String currentLongitude;
	private String isAvailable;
	private boolean isAuth;
	
	

	public DriverInfo() {
		this.deviceID = null;
		this.fullName = null;
		this.pin = null;
		this.cabName = null;
		this.phoneNum = null;
		this.maxPickUp = null;
		this.maxDropOff = null;
		this.paymentType = null;
		this.currentLatitude = currentLatitude;
		this.currentLongitude = currentLongitude;
		this.isAvailable = isAvailable;
		this.isAuth = isAuth;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getCabName() {
		return cabName;
	}
	public void setCabName(String cabName) {
		this.cabName = cabName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getMaxPickUp() {
		return maxPickUp;
	}
	public void setMaxPickUp(String maxPickUp) {
		this.maxPickUp = maxPickUp;
	}
	public String getMaxDropOff() {
		return maxDropOff;
	}
	public void setMaxDropOff(String maxDropOff) {
		this.maxDropOff = maxDropOff;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
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
