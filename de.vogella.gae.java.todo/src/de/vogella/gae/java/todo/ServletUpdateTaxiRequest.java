package de.vogella.gae.java.todo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.vogella.gae.java.todo.dao.Dao;

public class ServletUpdateTaxiRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		System.out.println("Update Taxi Reservation... ");

		
/*	
		String name = checkNull(req.getParameter("requestor"));
		String phoneNumber = checkNull(req.getParameter("phoneNumber"));
		String from = checkNull(req.getParameter("from"));
		String to = checkNull(req.getParameter("to"));
		String people = checkNull(req.getParameter("people"));
		int numberPeople = 0;
		try
		{
			numberPeople = Integer.parseInt(people);
		}
		catch (NumberFormatException nf)
		{
			numberPeople = 0;
		}

		Random r = new Random();
		int randInt = r.nextInt(10);
		String today = getCurrentDate("EEEMMddyyyyHHmmssSSS");

		String requestId = today + String.valueOf(randInt);
*/
		String refId = checkNull(req.getParameter("refId"));
		String assignedTo = checkNull(req.getParameter("assigned"));
		long id = Long.parseLong(refId);
		
		//Dao.INSTANCE.updateTaxiAssignedTo(id, assignedTo);
	//	Dao.INSTANCE.updateTaxiRequestTaken(id);
	//	Dao.INSTANCE.updateTaxiRequestCompleted(id);

		resp.sendRedirect("/TaxiUpdateApplication.jsp");
	}
	

	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
	
	private String getCurrentDate(String f)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(f);
		
		Date today = new Date();
		
		String currentDate = sdf.format(today);
		
		return currentDate;
	}
}

