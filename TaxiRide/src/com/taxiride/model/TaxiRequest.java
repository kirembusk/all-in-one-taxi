package com.taxiride.model;

public class TaxiRequest {
	
	private Long id;
	private String requestConfirmationNumber;
	private String requestName;
	private String requestPhoneNumber;
	private String requestPickupLocation;
	private String requestDestination;
	private String requestSpecialNeeds;
	private String assignedDriverName;
	private int totalPeople;
	boolean isRequestFilled;
	
	
	public TaxiRequest(Long id, String requestConfirmationNumber,
			String requestName, String requestPhoneNumber,
			String requestPickupLocation, String requestDestination,
			String requestSpecialNeeds, String assignedDriverName,
			int totalPeople, boolean isRequestFilled) {
		this.id = id;
		this.requestConfirmationNumber = requestConfirmationNumber;
		this.requestName = requestName;
		this.requestPhoneNumber = requestPhoneNumber;
		this.requestPickupLocation = requestPickupLocation;
		this.requestDestination = requestDestination;
		this.requestSpecialNeeds = requestSpecialNeeds;
		this.assignedDriverName = assignedDriverName;
		this.totalPeople = totalPeople;
		this.isRequestFilled = isRequestFilled;
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
	public int getTotalPeople() {
		return totalPeople;
	}
	public void setTotalPeople(int totalPeople) {
		this.totalPeople = totalPeople;
	}
	public boolean isRequestFilled() {
		return isRequestFilled;
	}
	public void setRequestFilled(boolean isRequestFilled) {
		this.isRequestFilled = isRequestFilled;
	}
	
	

}
