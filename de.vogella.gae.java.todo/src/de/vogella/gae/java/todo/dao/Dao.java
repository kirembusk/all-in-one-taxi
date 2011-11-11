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
	
	public TaxiDriver checkLogin(String loginName, String loginPin) {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select d from TaxiDriver d where d.loginName = :loginName and d.loginPin = :loginPin");
		q.setParameter("loginName", loginName);
		q.setParameter("loginPin", loginPin);
		TaxiDriver driver = new TaxiDriver();
		try
		{
		    driver = (TaxiDriver) q.getSingleResult();
		    driver.setAuth(true);
		}
		catch(Exception e)
		{
			driver = new TaxiDriver();
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
	
	public List<TaxiRequest> getMyTaxiRequest(String loginId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from TaxiRequest t where t.assignedDriverLogin = :loginId");
		q.setParameter("loginId", loginId);
		List<TaxiRequest> requests = q.getResultList();
		return requests;
	}
	
	public void addTaxiRequest(String requestConfirmationNumber, String requestName, String requestPhoneNumber, String requestPickupLocation, String requestDestination, String requestSpecialNeeds, String requestDriverName, String requestDriverLogin, int totalPeople) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			TaxiRequest request = new TaxiRequest( requestConfirmationNumber,  requestName, requestPhoneNumber, requestPickupLocation,  requestDestination,  requestSpecialNeeds,  requestDriverName, requestDriverLogin, totalPeople);
			em.persist(request);
			em.close();
		}
	}
	
	public void addTaxiDriver(String loginName, String loginPin, String fullName, String cabName, String phoneNumber, String currentLatitude, String currentLongitude) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			TaxiDriver driver = new TaxiDriver(loginName, loginPin, fullName, cabName, phoneNumber, currentLatitude, currentLongitude);
			em.persist(driver);
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
	
	public void updateTaxiAssignedTo(long refId, String assignedTo) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			TaxiRequest request = em.find(TaxiRequest.class, refId);
			request.setAssignedDriverLogin(assignedTo);
			request.setIsRequestTaken("Y");
			request.setIsRequestCompleted("N");
			em.persist(request);
			
		} finally {
			em.close();
		}
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
