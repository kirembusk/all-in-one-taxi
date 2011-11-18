package de.vogella.gae.java.todo;

import java.io.IOException;
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

import de.vogella.gae.java.todo.dao.Dao;
import de.vogella.gae.java.todo.dao.EMFService;
import de.vogella.gae.java.todo.model.TaxiDriver;

@SuppressWarnings("serial")
public class ServletDeleteTaxiDriver extends HttpServlet {


	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println("Deleting Taxi Driver... ");





		String regID = checkNull(req.getParameter("regID"));
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


		Dao.INSTANCE.removeTaxiDriver(id);

		resp.sendRedirect("/TaxiDriverDelete.jsp");
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
