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

public class ServletApproveTaxiRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String loginID = req.getParameter("loginID");
		String action = req.getParameter("action");
		
		
		List<TaxiRequest> requests = new ArrayList<TaxiRequest>();
		requests = Dao.INSTANCE.getMyTaxiRequest(loginID);

		if (action.equals("json"))
		{
			PrintWriter out = resp.getWriter();

			Gson gson = new Gson();
			String jsonDrivers = gson.toJson(requests);
			out.println(jsonDrivers);
			out.flush();

		}
		else if (action.equals("html"))
		{
			long id = 0;
			
			try
			{
				id = Long.parseLong(loginID);
			}
			catch (NumberFormatException nf)
			{
				id = 0;
			}
			
			String name = "";
			String phone = "";
			String pickup = "";
			String destination = "";
			String assigned = "";
			int totalPeople = 0;
			String taken = "";
			String completed = "";
			
			resp.setContentType("text/html");

			PrintWriter out = resp.getWriter();

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Display My Taxi Request Orders</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<table border='1' cellpadding='5' cellspacing='5'>");
			out.println("<tr>");


			out.println("<th>ID</th>");
			out.println("<th>Name</th>");
			out.println("<th>Phone Number</th>");
			out.println("<th>Pickup Location</th>");
			out.println("<th>Destination</th>");
			out.println("<th>Assigned To</th>");
			out.println("<th>totalPeople</th>");
			out.println("<th>Request Taken</th>");
			out.println("<th>Request Completed</th>");

			out.println("</tr>");

			
			for (TaxiRequest myRequest : requests) {
				out.println("<tr>");
			
				id = myRequest.getId();
				name = myRequest.getRequestName();
				phone = myRequest.getRequestPhoneNumber();
				pickup = myRequest.getRequestPickupLocation();
				destination = myRequest.getRequestDestination();
				assigned = myRequest.getAssignedDriverLogin();
				totalPeople = myRequest.getTotalPeople();
				taken = myRequest.getIsRequestTaken();
				completed = myRequest.getIsRequestCompleted();
				
				
				out.println("<td align='center'>" + id + "</td>");
				out.println("<td align='center'>" + name + "</td>");
				out.println("<td align='center'>" + phone + "</td>");
				out.println("<td align='right'>" + pickup + "</td>");
				out.println("<td align='right'>" + destination + "</td>");
				out.println("<td align='right'>" + assigned + "</td>");
				out.println("<td align='right'>" + totalPeople + "</td>");
				out.println("<td align='right'>" + taken + "</td>");
				out.println("<td align='right'>" + completed + "</td>");
				
				out.println("</tr>");
			}

			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
			out.close();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		doGet(req, resp);

	}
}

