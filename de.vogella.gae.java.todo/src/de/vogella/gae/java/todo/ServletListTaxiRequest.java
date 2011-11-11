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

public class ServletListTaxiRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String id = req.getParameter("requestID");
		String action = req.getParameter("action");

		List<TaxiRequest> requests = new ArrayList<TaxiRequest>();
		requests = Dao.INSTANCE.listAllOpenTaxiRequest();

		if (action.equals("json"))
		{
			PrintWriter out = resp.getWriter();

			Gson gson = new Gson();
			String jsonDrivers = gson.toJson(requests);
			out.println(jsonDrivers);
			out.flush();

		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		doGet(req, resp);

	}
}

