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

public class ServletDriverLoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String loginName = req.getParameter("loginName");
		String loginPin = req.getParameter("loginPin");

		TaxiDriver driver = Dao.INSTANCE.checkLogin(loginName, loginPin);

		PrintWriter out = resp.getWriter();

		Gson gson = new Gson();
		String jsonDrivers = gson.toJson(driver);
		out.println(jsonDrivers);
		out.flush();



	}
}
