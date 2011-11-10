package de.vogella.gae.java.todo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.vogella.gae.java.todo.model.Todo;
import de.vogella.gae.java.todo.model.TaxiRequest;

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
	
	public void addTaxiRequest(String requestConfirmationNumber, String requestName, String requestPhoneNumber, String requestPickupLocation, String requestDestination, String requestSpecialNeeds, String requestDriverName, int totalPeople) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			TaxiRequest request = new TaxiRequest( requestConfirmationNumber,  requestName, requestPhoneNumber, requestPickupLocation,  requestDestination,  requestSpecialNeeds,  requestDriverName, totalPeople);
			em.persist(request);
			em.close();
		}
	}
	
	public void updateTaxiPickupLocation(long refId, String requestPickupLocation) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			TaxiRequest request = em.find(TaxiRequest.class, refId);
			request.setRequestPickupLocation(requestPickupLocation);
			em.persist(request);
			
		//	em.remove(request);
		} finally {
			em.close();
		}
	}
	
}
