package de.vogella.gae.java.todo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;

import de.vogella.gae.java.todo.dao.Dao;
import de.vogella.gae.java.todo.dao.EMFService;
import de.vogella.gae.java.todo.model.TaxiDriver;

@SuppressWarnings("serial")
public class ServletRequestTaxiDriver extends HttpServlet {
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

		String regID = checkNull(req.getParameter("regID"));
		String loginName = checkNull(req.getParameter("loginName"));
		String loginPin = checkNull(req.getParameter("loginPin"));
		String fullName = checkNull(req.getParameter("fullName"));
		String cabName = checkNull(req.getParameter("cabName"));
		String phoneNumber = checkNull(req.getParameter("phoneNumber"));
		String maxPickupDistance = checkNull(req.getParameter("maxPickupDistance"));
		String maxDropOffDistance = checkNull(req.getParameter("maxDropOffDistance"));
		String preferredPayment = checkNull(req.getParameter("preferredPayment"));

		long id = 0;
		
		if (!regID.equals(""))
		{
			try
			{
				id = Long.parseLong(regID);
			}
			catch (NumberFormatException nf)
			{
				id = 0;
			}
		}


		String currentLatitude = "0.00";
		String currentLongitude = "0.00";
		String result = "fail";

		if (isTaxiDriverExists(loginName))
		{
			result = updateTaxiDriver(id, loginName, loginPin, fullName, cabName, phoneNumber, currentLatitude, currentLongitude, maxPickupDistance, maxDropOffDistance, preferredPayment);
		}
		else
		{
			addTaxiDriver(loginName, loginPin, fullName, cabName, phoneNumber, currentLatitude, currentLongitude, maxPickupDistance, maxDropOffDistance, preferredPayment);
			id = getTaxiDriverId(loginName);
			
			if (id > 0)
			{
				result = String.valueOf(id);
			}

		}

		
		

		PrintWriter out = resp.getWriter();

		//Gson gson = new Gson();
		//String jsonDrivers = gson.toJson(result);
		//out.println(jsonDrivers);
		out.println(result);
		out.flush();

	}

	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		s = s.trim();
		return s;
	}

	private String getCurrentDate(String f)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(f);

		Date today = new Date();

		String currentDate = sdf.format(today);

		return currentDate;
	}

	private void addTaxiDriver(String loginName, String loginPin, String fullName, String cabName, String phoneNumber, String currentLatitude, String currentLongitude, String maxPickupDistance, String maxDropOffDistance, String preferredPayment) 
	{
		Dao.INSTANCE.addTaxiDriver(loginName, loginPin, fullName, cabName, phoneNumber, currentLatitude, currentLongitude, maxPickupDistance, maxDropOffDistance, preferredPayment);
	}

	private String updateTaxiDriver(Long id, String loginName, String loginPin, String fullName, String cabName, String phoneNumber, String currentLatitude, String currentLongitude, String maxPickupDistance, String maxDropOffDistance, String preferredPayment) 
	{
		return Dao.INSTANCE.updateTaxiDriver(id, loginName, loginPin, fullName, cabName, phoneNumber, currentLatitude, currentLongitude, maxPickupDistance, maxDropOffDistance, preferredPayment);
	}

	private boolean isTaxiDriverExists(String loginName)
	{
		return Dao.INSTANCE.isTaxiDriverExists(loginName);
	}

	private long getTaxiDriverId(String loginName)
	{
		return Dao.INSTANCE.getTaxiDriverId(loginName);
	}
}
