package de.vogella.gae.java.todo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.google.gson.Gson;

import de.vogella.gae.java.todo.dao.Dao;
import de.vogella.gae.java.todo.model.TaxiRequest;
import de.vogella.gae.java.todo.model.TaxiDriver;

public class ServletApproveTaxiRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String driverLoginName = checkNull(req.getParameter("driverLoginName"));
		String driverLoginPin = checkNull(req.getParameter("driverLoginPin"));

		TaxiDriver driver = Dao.INSTANCE.checkLogin(driverLoginName, driverLoginPin);

		String driverFullName = "";
		String driverLatitude = "";
		String driverLongitude = "";
		String estimatedArrivalTime = "";
		String requestID = "";
		long refID = 0;

		String result = "fail";
		
		if (driver != null)
		{
			driverFullName = driver.getFullName();
			driverLatitude = checkNull(req.getParameter("driverLatitude"));
			driverLongitude = checkNull(req.getParameter("driverLongitude"));
			estimatedArrivalTime = checkNull(req.getParameter("estimatedArrivalTime"));
			requestID = checkNull(req.getParameter("requestID"));

			try
			{
				refID = Long.parseLong(requestID);
			}
			catch (NumberFormatException nf)
			{
				refID = 0;
			}

			if (refID > 0)
			{
				result = Dao.INSTANCE.updateTaxiRequestAssignedTo(refID, driverFullName, driverLatitude, driverLongitude, estimatedArrivalTime);
			}

		}


		PrintWriter out = resp.getWriter();
		out.println(result);
		out.flush();

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		doGet(req, resp);

	}

	private String checkNull(String s) {
		if (s == null) {
			return "";
		}

		s = s.trim();

		return s;
	}
}

