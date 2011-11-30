package com.taxiride;

public class PassengerInfo {
	
	private String fromAddress;
	private String toAddress;
	private double fromLat;
	private double fromlog;
	private double toLat;
	private double toLog;
	private String totalPeople;
	private boolean isEnableGPS;
	private double distance; 
	private String phoneNum; 
	private String fullName; 
	private String paymentType;
	private String confirmID;
	
	
	
	
	
	public PassengerInfo() {
		this.fromAddress = null;
		this.toAddress = null;
		this.fromLat = 0;
		this.fromlog = 0;
		this.toLat = 0;
		this.toLog = 0;
		this.totalPeople = "";
		this.isEnableGPS = false;
		this.distance = 0; 
		this.phoneNum="";
		this.fullName="";
		this.paymentType = ""; 
		this.confirmID =""; 
	}
	
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public double getFromLat() {
		return fromLat;
	}
	public void setFromLat(double fromLat) {
		this.fromLat = fromLat;
	}
	public double getFromlog() {
		return fromlog;
	}
	public void setFromlog(double fromlog) {
		this.fromlog = fromlog;
	}
	public double getToLat() {
		return toLat;
	}
	public void setToLat(double toLat) {
		this.toLat = toLat;
	}
	public double getToLog() {
		return toLog;
	}
	public void setToLog(double toLog) {
		this.toLog = toLog;
	}
	public String getTotalPeople() {
		return totalPeople;
	}
	public void setTotalPeople(String totalPeople) {
		this.totalPeople = totalPeople;
	}
	public boolean isEnableGPS() {
		return isEnableGPS;
	}
	public void setEnableGPS(boolean isEnableGPS) {
		this.isEnableGPS = isEnableGPS;
	}
	public void setDistance(double distance){
		this.distance = distance; 
		
	}
	
	public double getDistance(){
		return distance; 
	}
	
	public void setPhoneNum(String phoneNum){
		this.phoneNum = phoneNum;
		
	}
	public String getPhoneNum(){
		return phoneNum;
	}

	public void setfullName(String fullName){
		this.fullName = fullName;
	}
	
	public String getfullName(){
		return fullName; 
	}
	public void setPaymentType(String paymentType){
		this.paymentType = paymentType; 
	}
	public String getPaymentType(){
		return paymentType;
	}
	
	public String getconfirmID(){
		return confirmID;
	}
	
	public void setconfirmID(String confirmID){
		this.confirmID = confirmID;
		
	}
}
