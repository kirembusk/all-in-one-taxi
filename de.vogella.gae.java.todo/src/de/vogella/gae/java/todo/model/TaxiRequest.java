package de.vogella.gae.java.todo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/** * Model class which will store the Todo Items * * @author Lars Vogel * */

@Entity
public class TaxiRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String requestName;
	private String requestPhoneNumber;
	private String requestPickupLocation;
	private String requestDestination;
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
	
	
	
	public TaxiRequest() {
		// TODO Auto-generated constructor stub
		this.requestName = "";
		this.requestPhoneNumber = "";
		this.requestPickupLocation = "";
		this.requestDestination = "";
		this.assignedDriverName = "";
		this.assignedDriverPhoneNumber = "";
		this.assignedDriverLatitude = "";
		this.assignedDriverLongitude = "";
		this.estimatedArrivalTime = "";
		this.preferredPayment = "";
		this.currentLatitude = "";
		this.currentLongitude = "";
		this.toLatitude = "";
		this.toLongitude = "";
		this.totalDistance = "";
		this.totalPeople = 0;
		this.isRequestTaken = "N";
		this.isRequestCompleted = "N";
	}

	public TaxiRequest(String requestName, String requestPhoneNumber,
			String requestPickupLocation, String requestDestination,
			String assignedDriverName, String assignedDriverPhoneNumber,
			String assignedDriverLatitude, String assignedDriverLongitude,
			String estimatedArrivalTime, String preferredPayment,
			String currentLatitude, String currentLongitude, String toLatitude,
			String toLongitude, String totalDistance, int totalPeople) {
		this.requestName = requestName;
		this.requestPhoneNumber = requestPhoneNumber;
		this.requestPickupLocation = requestPickupLocation;
		this.requestDestination = requestDestination;
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
		this.isRequestTaken = "N";
		this.isRequestCompleted = "N";
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

