package de.vogella.gae.java.todo;

import java.io.IOException;
import java.io.PrintWriter;
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
public class ServletSubmitRequest extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println("Making Taxi Reservation... ");


		String requestName = checkNull(req.getParameter("requestName"));
		String requestPhoneNumber = checkNull(req.getParameter("requestPhoneNumber"));
		String requestPickupLocation = checkNull(req.getParameter("requestPickupLocation"));
		String requestDestination = checkNull(req.getParameter("requestDestination"));
		String preferredPayment = checkNull(req.getParameter("preferredPayment"));
		String currentLatitude = checkNull(req.getParameter("currentLatitude"));
		String currentLongitude = checkNull(req.getParameter("currentLongitude"));
		String toLatitude = checkNull(req.getParameter("toLatitude"));
		String toLongitude = checkNull(req.getParameter("toLongitude"));
		String totalPeople = checkNull(req.getParameter("totalPeople"));
		String totalDistance = checkNull(req.getParameter("totalDistance"));
	
		int maxPeople = 0;
		int maxDistance = 0;
		
		try
		{
			maxPeople = Integer.parseInt(totalPeople);
		}
		catch (NumberFormatException nf)
		{
			maxPeople = 0;
		}
		
		try
		{	
			maxDistance = Integer.parseInt(totalDistance);
		}
		catch (NumberFormatException nf)
		{
			maxDistance = 0;
		}
		
		long id = 0;
		String result = "fail";
		
		id = Dao.INSTANCE.addTaxiRequest(requestName, requestPhoneNumber, requestPickupLocation, requestDestination, preferredPayment, currentLatitude, currentLongitude, toLatitude, toLongitude, maxPeople, totalDistance);

		if (id > 0)
		{
			result = String.valueOf(id);
		}
		
		PrintWriter out = resp.getWriter();
		out.println(result);
		out.flush();

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
