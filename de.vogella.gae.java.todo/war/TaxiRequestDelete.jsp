<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="de.vogella.gae.java.todo.model.TaxiRequest" %>
<%@ page import="de.vogella.gae.java.todo.model.TaxiDriver" %>
<%@ page import="de.vogella.gae.java.todo.dao.Dao" %>

<!DOCTYPE html>


<%@page import="java.util.ArrayList"%>

<html>
	<head>
		<title>Taxi Driver</title>
		<link rel="stylesheet" type="text/css" href="css/main.css"/>
		  <meta charset="utf-8"> 
	</head>
	<body>
<%
Dao dao = Dao.INSTANCE;




List<TaxiRequest> requests = new ArrayList<TaxiRequest>();
           
requests = dao.listAllTaxiRequest();
    
%>
	<div style="width: 100%;">
		<div class="line"></div>
		<div class="topLine">
			<div style="float: left;"><img src="images/todo.png" /></div>
			<div style="float: left;" class="headline">Taxi Requests</div>
			<div style="float: right;">&nbsp;</div>
		</div>
	</div>

<div style="clear: both;"/>	
You have a total number of <%= requests.size() %>  Taxi Requests.


			
<table>
  <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Phone Number</th>
      <th>Pickup Location</th>
      <th>Destination</th>
      <th>Assigned Driver Name</th>
      <th>Assigned Driver Phone Number</th>
      <th>Assigned Driver Latitude</th>
      <th>Assigned Driver Longitude</th>
      <th>Estimated Arrival Time</th>
      <th>Preferred Payment</th>
      <th>Current Latitude</th>
      <th>Current Longitude</th>
      <th>To Latitude</th>
      <th>To Longitude</th>
      <th>Total Distance</th>
      <th>Total People</th>
      <th>Request Taken</th>
      <th>Request Completed</th>
            
    </tr>

<% for (TaxiRequest req : requests) {%>
<tr> 
<td>
<%=req.getId()%>
</td>
<td>
<%=req.getRequestName()%>
</td>
<td>
<%=req.getRequestPhoneNumber()%>
</td>>
<td>
<%=req.getRequestPickupLocation()%>
</td>
<td>
<%=req.getRequestDestination()%>
</td>
<td>
<%=req.getAssignedDriverName()%>
</td>
<td>
<%=req.getAssignedDriverPhoneNumber()%>
</td>
<td>
<%=req.getAssignedDriverLatitude()%>
</td>
<td>
<%=req.getAssignedDriverLongitude()%>
</td>
<td>
<%=req.getEstimatedArrivalTime()%>
</td>
<td>
<%=req.getPreferredPayment()%>
</td>
<td>
<%=req.getCurrentLatitude()%>
</td>
<td>
<%=req.getCurrentLongitude()%>
</td>
<td>
<%=req.getToLatitude()%>
</td>
<td>
<%=req.getToLongitude()%>
</td>
<td>
<%=req.getTotalDistance()%>
</td>
<td>
<%=req.getTotalPeople()%>
</td>
<td>
<%=req.getIsRequestTaken()%>
</td>
<td>
<%=req.getIsRequestCompleted()%>
</td>
<%}
%>
</tr> 

</table>


<hr />

<div class="main">

<div class="headline">Taxi Driver Adding/Updating</div>



<form action="/delete" method="post" accept-charset="utf-8">
	<table>
		
	<tr>
		<td valign="top"><label for="ccID">Confirmation ID</label></td>
		<td><input type="text" name="ccID" id="ccID" size="65" /></td>
	</tr>
	
	<tr>
			<td colspan="2" align="right"><input type="submit" value="Delete Taxi Request"/></td>
		</tr>
	</table>
</form>




</div>
</body>
</html>

