<%@page import="bo.Contact"%>
<%@page import="bo.NotificationType"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="bo.EventType"%>
<%@page import="bo.UserPreference"%>
<%@page import="bo.UserPreferenceGroup"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <%
  	List<EventType> allEventTypes = (List<EventType>)request.getAttribute("allEventTypes");
  	List<NotificationType> allNotificationTypes = (List<NotificationType>)request.getAttribute("allNotificationTypes");
  	List<Contact> allContacts = (List<Contact>)request.getAttribute("userContacts");
  %>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>INTELLIGENT NOTIFICATIONS SYSTEM</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/DahBoardMenu.css" media="screen" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/EditPreferences.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script type="text/javascript">
var count=0;
function addNewNotification()
{
	$sampleData = $("#NotificationSample").html();
	count = count+1;
	$sampleData = $sampleData.split('notificationContact0').join('notificationContact'+count);
	$sampleData = $sampleData.split('notificationUnitCount0').join('notificationUnitCount'+count);
	$sampleData = $sampleData.split('notificationType0').join('notificationType'+count);
	$("#Notification").append($sampleData);
}
</script>
</head>
  

<body>

<header style="display:block; margin:0 20px 10px; padding:20px 30px 10px; position:relative;text-align:center;text-shadow:rgba(0, 0, 0, 0.2) 1px 1px 1px;">
	<h1 style="color:rgba(26, 89, 120, 0.901961);font-family:BebasNeueRegular, 'Arial Narrow', Arial, sans-serif !important;
	font-size:35px;font-weight:400;line-height:35px;padding:0 0 5px;position:relative;
	text-shadow:rgba(0, 0, 0, 0.0980392) 1px 1px 1px;">
	<span style="color:#7CBCD6;text-shadow:rgba(255, 255, 255, 0.8) 0 1px 1px;">INTELLIGENT</span> 
	NOTIFICATION SYSTEM
	</h1>
</header>

<div style="width:100%;">
<!-- LEFT MENU -->
<div style="float:left;width:19%; padding-left: 20px" class="container">
  <ul>
    <li class="dropdown">
      <a href="#" data-toggle="dropdown">My Events <i class="icon-arrow"></i></a>
      <ul class="dropdown-menu">
        <li><a href="Dashboard">Registered Events</a></li>
        <li><a href="Dashboard">Recommended Events</a></li>
      </ul>
    </li>
    <li class="dropdown">
      <a href="Dashboard" data-toggle="dropdown">My Preferences <i class="icon-arrow"></i></a>
      <ul class="dropdown-menu">
        <li><a href="#">Edit Preferences</a></li>
      </ul>
    </li>
    <li class="dropdown">
      <a href="#" data-toggle="dropdown">My Profile <i class="icon-arrow"></i></a>
      <ul class="dropdown-menu">
        <li><a href="#">Edit Profile</a></li>
      </ul>
    </li>
  </ul>
</div>


<div id="MainDisplayArea" style="float:left; width:75%; padding-top:25px; padding-left:10px;">

<h1>Edit Preferences Page</h1>

<h4><a id ="logOut" href="Logout">LOG OUT</a></h4><br/>
<span class="clr"></span>

<form action="SaveInitialSettings" method="post">
	<div id="selectPreferences">
		<h3>Select the events you want to get notifications for: </h3>
	
			<p id ="eventNotification">
			
			<span>How would you like to be notified?</span><br/><br/>
			<span id="NotificationSample">
			<span>By </span>
			<select name="notificationContact0">
			 <%for(Contact contact: allContacts) 
			               		{%> 
			               		<option value="<%=contact.getContactID()%>">
			               				<%=contact.getContactValue()%>
			               		</option>
			               		<%} %>
			</select>
			
			<input id="notificationCount" name="notificationUnitCount0"
			type="text" value="15">
			
			<select name="notificationType0" id="dropdown">
			               <%for(NotificationType notificationType: allNotificationTypes) 
			               		{%> 
			               		<option value="<%=notificationType.getNotificationTypeID()%>">
			               				<%=notificationType.getNotificationTypeName()%>
			               		</option>
			               		<%} %>
			</select>
			before the event<br>
			</span>
			<span id="Notification">
			
			</span>
				
			<label onclick="addNewNotification()">Add Another Notification</label>
			
			</p>
		<div id="checkboxItems">
		<%for(EventType eventType: allEventTypes) 
		{ %>
		<input type="checkbox" name="EventTypeCheckBox" value="<%=eventType.getEventTypeID()%>">
		<%=eventType.getEventTypeName().toLowerCase()%><br>
		<%} %>
		</div>
		Name your Preference: <input type="text" name="UserPreferenceName" maxlength="30">
		<p><input type="submit" onclick="" value="Save"></p>
	</div>
</form>

</div>
</div>

<script type="text/javascript" src="js/index.js"></script>
</body>
</html>