package de.vogella.gae.java.todo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.vogella.gae.java.todo.model.Todo;
import de.vogella.gae.java.todo.model.TaxiRequest;
import de.vogella.gae.java.todo.model.TaxiDriver;

public enum Dao {
	INSTANCE;

	public List<Todo> listTodos() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from Todo m");
		List<Todo> todos = q.getResultList();
		return todos;
	}

	public void add(String userId, String summery, String description, String url) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Todo todo = new Todo(userId, summery, description, url);
			em.persist(todo);
			em.close();
		}
	}

	public List<Todo> getTodos(String userId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Todo t where t.author = :userId");
		q.setParameter("userId", userId);
		List<Todo> todos = q.getResultList();
		return todos;
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Todo todo = em.find(Todo.class, id);
			em.remove(todo);
		} finally {
			em.close();
		}
	}

	public boolean isTaxiDriverExists(String loginName) {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select d from TaxiDriver d where d.loginName = :loginName");
		q.setParameter("loginName", loginName);
		boolean result = false;
		TaxiDriver driver = new TaxiDriver();
		try
		{
			driver = (TaxiDriver) q.getSingleResult();
			result = true;
		}
		catch(Exception e)
		{
			result = false;
		}

		return result;
	}


	public long getTaxiDriverId(String loginName) {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select d from TaxiDriver d where d.loginName = :loginName");
		q.setParameter("loginName", loginName);

		TaxiDriver driver = null;

		long result = 0;

		try
		{
			driver = (TaxiDriver) q.getSingleResult();
			if (driver != null)
			{
				result = driver.getId();
			}
		}
		catch(Exception e)
		{
			result = 0;
		}

		return result;
	}

	public String cancelTaxiRequest(long refID, String loginID) {

		String result = "fail";
		
		synchronized (this) {

			EntityManager em = EMFService.get().createEntityManager();
			// Read the existing entries
			Query q = em.createQuery("select t from TaxiRequest t where t.id = :requestId and t.assignedDriverLogin = :loginId");
			q.setParameter("requestId", refID);
			q.setParameter("loginId", loginID);
			TaxiRequest request = null;
			try
			{
				request = (TaxiRequest) q.getSingleResult();
				request.setIsRequestTaken("N");
				em.persist(request);
			    result = "success";	
			}
			catch (Exception e)
			{
				result = "fail";
			}
			finally 
			{
				em.close();
			}

		}
		
		return result;
	}

	public TaxiDriver checkLogin(String loginName, String loginPin) {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select d from TaxiDriver d where d.loginName = :loginName and d.loginPin = :loginPin");
		q.setParameter("loginName", loginName);
		q.setParameter("loginPin", loginPin);
		TaxiDriver driver = null;
		try
		{
			driver = (TaxiDriver) q.getSingleResult();
			driver.setAuth(true);
		}
		catch(Exception e)
		{
			driver = null;
		}

		return driver;
	}

	public List<TaxiRequest> listAllOpenTaxiRequest() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from TaxiRequest m where m.isRequestTaken = :requestTaken");
		q.setParameter("requestTaken", "N");
		List<TaxiRequest> requests = q.getResultList();
		return requests;
	}

	public List<TaxiRequest> listAllTaxiRequest() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from TaxiRequest m");
		List<TaxiRequest> requests = q.getResultList();
		return requests;
	}

	public List<TaxiRequest> getTaxiRequest(String requestId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from TaxiRequest t where t.requestID = :requestId");
		q.setParameter("requestId", requestId);
		List<TaxiRequest> requests = q.getResultList();
		return requests;
	}

	public TaxiRequest getUpdateTaxiRequest(long requestID) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from TaxiRequest t where t.id = :requestId");
		q.setParameter("requestId", requestID);
		TaxiRequest request = (TaxiRequest) q.getSingleResult();
		return request;
	}

	public List<TaxiRequest> getMyTaxiRequest(String loginId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from TaxiRequest t where t.assignedDriverLogin = :loginId");
		q.setParameter("loginId", loginId);
		List<TaxiRequest> requests = q.getResultList();
		return requests;
	}




	public long addTaxiRequest(String requestName, String requestPhoneNumber, String requestPickupLocation, String requestDestination, String preferredPayment, String currentLatitude, String currentLongitude, String toLatitude, String toLongitude, int totalPeople, String totalDistance) {
		long result = 0;

		synchronized (this) {
			String assignedDriverLogin = "";
			String assignedDriverName = "";
			String assignedDriverPhoneNumber = "";
			String assignedDriverLatitude = "";
			String assignedDriverLongitude = "";
			String estimatedArrivalTime = "";

			EntityManager em = EMFService.get().createEntityManager();
			TaxiRequest request = new TaxiRequest(requestName, requestPhoneNumber, requestPickupLocation, requestDestination, assignedDriverLogin,
					assignedDriverName, assignedDriverPhoneNumber, assignedDriverLatitude, assignedDriverLongitude, estimatedArrivalTime, 
					preferredPayment, currentLatitude, currentLongitude, toLatitude, toLongitude, totalDistance, totalPeople);
			em.persist(request);
			em.close();
			result = request.getId();
		}

		return result;
	}

	public void removeTaxiRequest(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			TaxiRequest request = em.find(TaxiRequest.class, id);
			em.remove(request);
		} finally {
			em.close();
		}
	}

	public void addTaxiDriver(String loginName, String loginPin, String fullName, String cabName, String phoneNumber, String currentLatitude, String currentLongitude, String maxPickupDistance, String maxDropOffDistance, String preferredPayment) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			TaxiDriver driver = new TaxiDriver(loginName, loginPin, fullName, cabName, phoneNumber, currentLatitude, currentLongitude, maxPickupDistance, maxDropOffDistance, preferredPayment);
			em.persist(driver);
			em.close();
		}
	}

	public String updateTaxiDriver(long regID, String loginName, String loginPin, String fullName, String cabName, String phoneNumber, String currentLatitude, String currentLongitude, String maxPickupDistance, String maxDropOffDistance, String preferredPayment) {
		String result = "fail";

		synchronized (this) {

			EntityManager em = EMFService.get().createEntityManager();
			try {
				TaxiDriver driver = em.find(TaxiDriver.class, regID);

				if (driver != null)
				{
					driver.setLoginName(loginName);
					driver.setLoginPin(loginPin);
					driver.setFullName(fullName);
					driver.setCabName(cabName);
					driver.setPhoneNumber(phoneNumber);
					driver.setCurrentLatitude(currentLatitude);
					driver.setCurrentLongitude(currentLongitude);
					driver.setMaxPickupDistance(maxPickupDistance);
					driver.setMaxDropOffDistance(maxDropOffDistance);
					driver.setPreferredPayment(preferredPayment);
					result = String.valueOf(regID);
				}

				em.persist(driver);
			}
			catch (Exception e)
			{
				result = "fail";
			}
			finally {
				em.close();
			}
		}

		return result;
	}

	public void removeTaxiDriver(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			TaxiDriver driver = em.find(TaxiDriver.class, id);
			em.remove(driver);
		} finally {
			em.close();
		}
	}

	public List<TaxiDriver> listAllTaxiDriver() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from TaxiDriver m");
		List<TaxiDriver> drivers = q.getResultList();
		return drivers;
	}

	public String updateTaxiRequestAssignedTo(long refId, String assignedDriverLogin, String assignedDriverName, String assignedDriverPhoneNumber, String assignedDriverLatitude, String assignedDriverLongitude, String estimatedArrivalTime) {
		String result = "fail";
		EntityManager em = EMFService.get().createEntityManager();
		try {
			TaxiRequest request = em.find(TaxiRequest.class, refId);
			request.setAssignedDriverLogin(assignedDriverLogin);
			request.setAssignedDriverName(assignedDriverName);
			request.setAssignedDriverPhoneNumber(assignedDriverPhoneNumber);
			request.setAssignedDriverLatitude(assignedDriverLatitude);
			request.setAssignedDriverLongitude(assignedDriverLongitude);
			request.setEstimatedArrivalTime(estimatedArrivalTime);
			request.setIsRequestTaken("Y");
			request.setIsRequestCompleted("N");
			em.persist(request);
			result = "success";
		} 
		catch (Exception e)
		{
			result = "fail";
		}
		finally {
			em.close();
		}

		return result;
	}

	public void updateTaxiRequestTaken(long refId) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			TaxiRequest request = em.find(TaxiRequest.class, refId);
			request.setIsRequestTaken("Y");
			em.persist(request);

		} finally {
			em.close();
		}
	}

	public void updateTaxiRequestCompleted(long refId) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			TaxiRequest request = em.find(TaxiRequest.class, refId);
			request.setIsRequestCompleted("N");
			em.persist(request);

		} finally {
			em.close();
		}
	}
}
