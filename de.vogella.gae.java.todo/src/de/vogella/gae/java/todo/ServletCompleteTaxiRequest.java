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

public class ServletCompleteTaxiRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String driverLoginName = checkNull(req.getParameter("driverLoginName"));
		String driverLoginPin = checkNull(req.getParameter("driverLoginPin"));

		TaxiDriver driver = Dao.INSTANCE.checkLogin(driverLoginName, driverLoginPin);

		String driverLoginID = "";
		
		String requestID = "";
		long refID = 0;

		String result = "fail";
		
		if (driver != null)
		{
			requestID = checkNull(req.getParameter("requestID"));
			driverLoginID = driver.getLoginName();
			
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
				result = Dao.INSTANCE.completeTaxiRequest(refID, driverLoginID);
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

