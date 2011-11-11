<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="de.vogella.gae.java.todo.model.TaxiRequest" %>
<%@ page import="de.vogella.gae.java.todo.dao.Dao" %>

<!DOCTYPE html>


<%@page import="java.util.ArrayList"%>

<html>
	<head>
		<title>Todos</title>
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
      <th>Assigned To</th>
      <th>totalPeople</th>
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
<%=req.getAssignedDriverLogin()%>
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

<div class="headline">Taxi Update Pickup Location</div>



<form action="/change" method="post" accept-charset="utf-8">
	<table>
		<tr>
			<td><label for="refId">Reference ID</label></td>
			<td><input type="text" name="refId" id="refId" size="65"/></td>
		</tr>
	
	<tr>
		<td valign="top"><label for="assigned">Assigned To</label></td>
		<td><input type="text" name="assigned" id="assigned" size="65" /></td>
	</tr>

	<tr>
			<td colspan="2" align="right"><input type="submit" value="Update Pickup Location"/></td>
		</tr>
	</table>
</form>




</div>
</body>
</html>

