package my.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import my.beans.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import share.utils.StringUtils;

/**
 * Servlet implementation class TaxiCenterController
 */
public class TaxiCenterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HashMap taxiDrivers;

    /**
     * Default constructor. 
     */
    public TaxiCenterController() {
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		taxiDrivers = new HashMap();
		
		TaxiDriver driver = new TaxiDriver("1", "John Doe", "1111", "4153381200", "0.00", "0.00");
		taxiDrivers.put("1", driver);
	
		driver = new TaxiDriver("2", "Jack Washington", "2222", "4153381201", "0.00", "0.00");
		taxiDrivers.put("2", driver);
		
		driver = new TaxiDriver("3", "Ken Peterson", "3333", "4153381202", "0.00", "0.00");
		taxiDrivers.put("3", driver);
		
		driver = new TaxiDriver("4", "John Doe", "4444", "4153381203", "0.00", "0.00");
		taxiDrivers.put("4", driver);
	
		driver = new TaxiDriver("5", "Kelly Clarkson", "5555", "4153381204", "0.00", "0.00");
		taxiDrivers.put("5", driver);
		
		driver = new TaxiDriver("6", "May Deng", "6666", "4153381205", "0.00", "0.00");
		taxiDrivers.put("6", driver);

    
    
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = StringUtils.getTrimWhiteSpaces(request.getParameter("action"));
		String id = "";
		TaxiDriver driver = new TaxiDriver();
		 
		String lat = StringUtils.getTrimWhiteSpaces(request.getParameter("lat"));
		String lon = StringUtils.getTrimWhiteSpaces(request.getParameter("lon"));
		
		if (action.equals("set"))
		{
			id = StringUtils.getTrimWhiteSpaces(request.getParameter("id"));
			
			driver = (TaxiDriver) taxiDrivers.get(id);
			
			if (driver != null)
			{	
				if (!lat.equals("") && !lon.equals(""))
				{
					driver.setCurrentLatitude(lat);
					driver.setCurrentLongitude(lon);
				
					taxiDrivers.put(id, driver);
				}
			}
		}
		else if (action.equals("json"))
		{
			String name = "";
			String phone = "";
			String locations = "";
			
			PrintWriter out = response.getWriter();
			out.println('[');
			Iterator iterator = taxiDrivers.keySet().iterator();
		    while (iterator.hasNext()) {
		      id = (String) iterator.next();
		      
		      driver = (TaxiDriver) taxiDrivers.get(id);
		      name = driver.getName();
		      phone = driver.getPhoneNumber();
		      locations = driver.getCurrentLatitude() + ";" + driver.getCurrentLongitude();
		      
		      out.println("  {");
		      out.print("    \"id\": \"");
		      out.print(id);
		      out.println("\",");
		      out.print("    \"name\": ");
		      out.print(name);
		      out.println(',');
		      out.print("    \"phone\": \"");
		      out.print(phone);
		      out.println("\",");
		      out.print("    \"locations\": ");
		      out.println(locations);
		      out.println("  },");
		      
		    }
		    
		    out.println(']');
		    out.flush();
			
		}
		else if (action.equals("html"))
		{
			String name = "";
			String phone = "";
			String locations = "";
			
			response.setContentType("text/html");
			
			PrintWriter out = response.getWriter();
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Display All Driver GPS Location</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<table border='1' cellpadding='5' cellspacing='5'>");
			out.println("<tr>");
			out.println("<th>Driver ID</th>");
			out.println("<th>Name</th>");
			out.println("<th>Phone</th>");
			out.println("<th>Locations (lat/long)</th>");
			out.println("</tr>");
			
			Iterator iterator = taxiDrivers.keySet().iterator();
		    while (iterator.hasNext()) {
		      out.println("<tr>");
		      id = (String) iterator.next();
		      
		      driver = (TaxiDriver) taxiDrivers.get(id);
		      name = driver.getName();
		      phone = driver.getPhoneNumber();
		      locations = driver.getCurrentLatitude() + ";" + driver.getCurrentLongitude();
		      
		      out.println("<td align='center'>" + id + "</td>");
		      out.println("<td align='center'>" + name + "</td>");
		      out.println("<td align='center'>" + phone + "</td>");
		      out.println("<td align='right'>" + locations + "</td>");
		      
		      out.println("</tr>");
		    }
		    
		    out.println("</table>");
		    out.println("</body>");
		    out.println("</html>");
		    out.close();
		    
			
		}
		
	}

}
