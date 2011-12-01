package de.vogella.gae.java.todo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import de.vogella.gae.java.todo.dao.Dao;
import de.vogella.gae.java.todo.model.TaxiRequest;

public class ServletRevokeTaxiRequest {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String requestID = checkNull(req.getParameter("requestID"));
		String deviceID =  checkNull(req.getParameter("deviceID"));
		
		long id = 0;
		
		try
		{
			id = Long.parseLong(requestID);
		}
		catch (NumberFormatException nf)
		{
			id = 0;
		}
		
		String result = "fail";
				
		result = Dao.INSTANCE.revokeTaxiRequest(id, deviceID);

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
