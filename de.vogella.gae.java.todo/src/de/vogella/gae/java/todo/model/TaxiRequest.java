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
	private String requestConfirmationNumber;
	private String requestName;
	private String requestPhoneNumber;
	private String requestPickupLocation;
	private String requestDestination;
	private String requestSpecialNeeds;
	private String assignedDriverName;
	private String assignedDriverLogin;
	private int totalPeople;
	private String isRequestTaken;
	private String isRequestCompleted;
	
	
	
	public TaxiRequest(String requestConfirmationNumber, String requestName,
			String requestPhoneNumber, String requestPickupLocation,
			String requestDestination, String requestSpecialNeeds,
			String assignedDriverName, String assignedDriverLogin,
			int totalPeople) {
		this.requestConfirmationNumber = requestConfirmationNumber;
		this.requestName = requestName;
		this.requestPhoneNumber = requestPhoneNumber;
		this.requestPickupLocation = requestPickupLocation;
		this.requestDestination = requestDestination;
		this.requestSpecialNeeds = requestSpecialNeeds;
		this.assignedDriverName = assignedDriverName;
		this.assignedDriverLogin = assignedDriverLogin;
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
	public String getRequestConfirmationNumber() {
		return requestConfirmationNumber;
	}
	public void setRequestConfirmationNumber(String requestConfirmationNumber) {
		this.requestConfirmationNumber = requestConfirmationNumber;
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
	public String getRequestSpecialNeeds() {
		return requestSpecialNeeds;
	}
	public void setRequestSpecialNeeds(String requestSpecialNeeds) {
		this.requestSpecialNeeds = requestSpecialNeeds;
	}
	public String getAssignedDriverName() {
		return assignedDriverName;
	}
	public void setAssignedDriverName(String assignedDriverName) {
		this.assignedDriverName = assignedDriverName;
	}
	public String getAssignedDriverLogin() {
		return assignedDriverLogin;
	}
	public void setAssignedDriverLogin(String assignedDriverLogin) {
		this.assignedDriverLogin = assignedDriverLogin;
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

