<%@page import="bo.UserPreference"%>
<%@page import="bo.UserPreferenceGroup"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% UserPreferenceGroup userPreferenceGroup = (UserPreferenceGroup)request.getAttribute("userPreferenceGroup"); %>
<div id="MainDisplayAreaHeader">
<h1>Edit Preferences Page</h1><h4><a id ='logOut' href='Logout'>LOG OUT</a></h4><br/>
<span class='clr'></span>
<select id="UserPreferenceID">
 			<%for(UserPreference userPreference: userPreferenceGroup.getUserPreference()) 
			 {%> 
	               		<option 
	               		           		value="<%=userPreference.getUserPreferenceID()%>">
	               				<%=userPreference.getPreferenceName()%>
	               		</option>
			<%} %>
			
</select>
<button onclick='viewSelectedPreference()'>Edit</button>
</div>