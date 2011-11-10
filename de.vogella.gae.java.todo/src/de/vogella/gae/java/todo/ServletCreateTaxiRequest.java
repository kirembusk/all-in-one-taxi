package de.vogella.gae.java.todo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import de.vogella.gae.java.todo.dao.Dao;

@SuppressWarnings("serial")
public class ServletCreateTaxiRequest extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub


/*
		Dao.INSTANCE.addTaxiRequest("111111", "John Doe", "4153381211", "1600 Holloway Avenue SF CA 94132", "1200 19th Avenue SF CA 94132", "", 2);
		Dao.INSTANCE.addTaxiRequest("111112", "Mary Beth", "4153381212", "200 Jackson Street SF CA 94109", "200 20th Avenue SF CA 94133", "", 3);
		Dao.INSTANCE.addTaxiRequest("111113", "Kate Richardson", "4153381213", "2100 Geary Blvd SF CA 94112", "300 21st Avenue SF CA 94134", "", 4);
		Dao.INSTANCE.addTaxiRequest("111114", "Jane Silverman", "4153381214", "2700 32nd Avenue SF CA 94116", "400 22nd Avenue SF CA 94135", "", 1);
		Dao.INSTANCE.addTaxiRequest("111115", "Jack Jackson", "4153381215", "1350 Kearny Street SF CA 94110", "500 23rd Avenue SF CA 94136", "", 2);
		Dao.INSTANCE.addTaxiRequest("111116", "Larry Deng", "4153381216", "201 Clement Street SF CA 94122", "600 24th Avenue SF CA 94137", "", 3);
*/

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println("Making Taxi Reservation... ");


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

		String requestConfirmationNumber = today + String.valueOf(randInt);

		String driverName = "";
		
		Dao.INSTANCE.addTaxiRequest(requestConfirmationNumber, name, phoneNumber, from, to, "", driverName, numberPeople);

		resp.sendRedirect("/TaxiRequestApplication.jsp");
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
