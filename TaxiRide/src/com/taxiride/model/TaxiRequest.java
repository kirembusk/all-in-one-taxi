package com.taxiride.model;
/*
 * Providing a passenger info strucutre when returning an object
 * from the server.
 */
public class TaxiRequest {
	
	private Long id;
	private String requestName;
	private String requestPhoneNumber;
	private String requestPickupLocation;
	private String requestDestination;
	private String assignedDriverLogin; 
	private String assignedDriverName;
	private String assignedDriverPhoneNumber;
	private String assignedDriverLatitude;
	private String assignedDriverLongitude;
	private String estimatedArrivalTime;
	private String preferredPayment;
	private String currentLatitude;
	private String currentLongitude;
	private String toLatitude;
	private String toLongitude;
	private String totalDistance;
	private int totalPeople;
	private String isRequestTaken;
	private String isRequestCompleted;
	public TaxiRequest(Long id, String requestName, String requestPhoneNumber,
			String requestPickupLocation, String requestDestination,
			String assignedDriverLogin, String assignedDriverName,
			String assignedDriverPhoneNumber, String assignedDriverLatitude,
			String assignedDriverLongitude, String estimatedArrivalTime,
			String preferredPayment, String currentLatitude,
			String currentLongitude, String toLatitude, String toLongitude,
			String totalDistance, int totalPeople, String isRequestTaken,
			String isRequestCompleted) {
		this.id = id;
		this.requestName = requestName;
		this.requestPhoneNumber = requestPhoneNumber;
		this.requestPickupLocation = requestPickupLocation;
		this.requestDestination = requestDestination;
		this.assignedDriverLogin = assignedDriverLogin;
		this.assignedDriverName = assignedDriverName;
		this.assignedDriverPhoneNumber = assignedDriverPhoneNumber;
		this.assignedDriverLatitude = assignedDriverLatitude;
		this.assignedDriverLongitude = assignedDriverLongitude;
		this.estimatedArrivalTime = estimatedArrivalTime;
		this.preferredPayment = preferredPayment;
		this.currentLatitude = currentLatitude;
		this.currentLongitude = currentLongitude;
		this.toLatitude = toLatitude;
		this.toLongitude = toLongitude;
		this.totalDistance = totalDistance;
		this.totalPeople = totalPeople;
		this.isRequestTaken = isRequestTaken;
		this.isRequestCompleted = isRequestCompleted;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRequestName() {
		return requestName;
	}
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}
	public String getRequestPhoneNumber() {
		return requestPhoneNumber;
	}
	public void setRequestPhoneNumber(String requestPhoneNumber) {
		this.requestPhoneNumber = requestPhoneNumber;
	}
	public String getRequestPickupLocation() {
		return requestPickupLocation;
	}
	public void setRequestPickupLocation(String requestPickupLocation) {
		this.requestPickupLocation = requestPickupLocation;
	}
	public String getRequestDestination() {
		return requestDestination;
	}
	public void setRequestDestination(String requestDestination) {
		this.requestDestination = requestDestination;
	}
	public String getAssignedDriverLogin() {
		return assignedDriverLogin;
	}
	public void setAssignedDriverLogin(String assignedDriverLogin) {
		this.assignedDriverLogin = assignedDriverLogin;
	}
	public String getAssignedDriverName() {
		return assignedDriverName;
	}
	public void setAssignedDriverName(String assignedDriverName) {
		this.assignedDriverName = assignedDriverName;
	}
	public String getAssignedDriverPhoneNumber() {
		return assignedDriverPhoneNumber;
	}
	public void setAssignedDriverPhoneNumber(String assignedDriverPhoneNumber) {
		this.assignedDriverPhoneNumber = assignedDriverPhoneNumber;
	}
	public String getAssignedDriverLatitude() {
		return assignedDriverLatitude;
	}
	public void setAssignedDriverLatitude(String assignedDriverLatitude) {
		this.assignedDriverLatitude = assignedDriverLatitude;
	}
	public String getAssignedDriverLongitude() {
		return assignedDriverLongitude;
	}
	public void setAssignedDriverLongitude(String assignedDriverLongitude) {
		this.assignedDriverLongitude = assignedDriverLongitude;
	}
	public String getEstimatedArrivalTime() {
		return estimatedArrivalTime;
	}
	public void setEstimatedArrivalTime(String estimatedArrivalTime) {
		this.estimatedArrivalTime = estimatedArrivalTime;
	}
	public String getPreferredPayment() {
		return preferredPayment;
	}
	public void setPreferredPayment(String preferredPayment) {
		this.preferredPayment = preferredPayment;
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
	public String getToLatitude() {
		return toLatitude;
	}
	public void setToLatitude(String toLatitude) {
		this.toLatitude = toLatitude;
	}
	public String getToLongitude() {
		return toLongitude;
	}
	public void setToLongitude(String toLongitude) {
		this.toLongitude = toLongitude;
	}
	public String getTotalDistance() {
		return totalDistance;
	}
	public void setTotalDistance(String totalDistance) {
		this.totalDistance = totalDistance;
	}
	public int getTotalPeople() {
		return totalPeople;
	}
	public void setTotalPeople(int totalPeople) {
		this.totalPeople = totalPeople;
	}
	public String getIsRequestTaken() {
		return isRequestTaken;
	}
	public void setIsRequestTaken(String isRequestTaken) {
		this.isRequestTaken = isRequestTaken;
	}
	public String getIsRequestCompleted() {
		return isRequestCompleted;
	}
	public void setIsRequestCompleted(String isRequestCompleted) {
		this.isRequestCompleted = isRequestCompleted;
	}
	
	
}