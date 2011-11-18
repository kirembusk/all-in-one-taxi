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

public class ServletGetUpdateTaxiRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String requestID = checkNull(req.getParameter("requestID"));

		long id = 0;
		
		try
		{
			id = Long.parseLong(requestID);
		}
		catch (NumberFormatException nf)
		{
			id = 0;
		}
		
		TaxiRequest request = new TaxiRequest();
		request = Dao.INSTANCE.getUpdateTaxiRequest(id);


		PrintWriter out = resp.getWriter();

		Gson gson = new Gson();
		String jsonDrivers = gson.toJson(request);
		out.println(jsonDrivers);
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

