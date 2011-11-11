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
		<title>Login</title>
		<link rel="stylesheet" type="text/css" href="css/main.css"/>
		  <meta charset="utf-8"> 
	</head>
	<body>



<hr />

<div class="main">

<div class="headline">Taxi Driver Login</div>



<form action="/login" method="post" accept-charset="utf-8">
	<table>
		<tr>
			<td><label for="loginName">Login Name</label></td>
			<td><input type="text" name="loginName" id="loginName" size="65"/></td>
		</tr>
		<tr>
			<td valign="top"><label for="loginPin">Password</label></td>
			<td><input type="text" name="loginPin" id="loginPin" size="65"/></td>
		</tr>
	
	<tr>
			<td colspan="2" align="right"><input type="submit" value="Login"/></td>
		</tr>
	</table>
</form>




</div>
</body>
</html>