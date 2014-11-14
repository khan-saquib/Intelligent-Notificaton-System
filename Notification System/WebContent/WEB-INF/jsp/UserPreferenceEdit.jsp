<%@page import="bo.Notification"%>
<%@page import="bo.Contact"%>
<%@page import="bo.NotificationType"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="bo.EventType"%>
<%@page import="bo.UserPreference"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%
  	List<EventType> allEventTypes = (List<EventType>)request.getAttribute("allEventTypes");
  	List<NotificationType> allNotificationTypes = (List<NotificationType>)request.getAttribute("allNotificationTypes");
  	List<Contact> allContacts = (List<Contact>)request.getAttribute("userContacts");
  	UserPreference userPreference = (UserPreference)request.getAttribute("userPreference");
  %>
<script type="text/javascript">
var count = <%=userPreference.getNotifications().size()-1%>;
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

<form action="SaveEditedPreference" method="post">
	<div id="selectPreferences">
		<h3>Select the events you want to get notifications for: </h3>
	
			<p id ="eventNotification">
			
			<span>How would you like to be notified?</span><br/><br/>
			
			<%
			int counter = 0;
			for(Notification notification : userPreference.getNotifications()) 
			{ 
			
				if(counter==0) {
				%>
				<span id="NotificationSample">
				<%} 
				else {%>
				<span>
				<%} %>
			<span>By </span>
			<select name="notificationContact<%=counter%>">
			 <%for(Contact contact: allContacts) 
			 {%> 
	               		<option 
	               		<% if(notification.getContact().getContactID() == 
	               				contact.getContactID())
			               			{ %>
			               			selected="selected"
			               		
			               		<%	} %>
	               		
	               		value="<%=contact.getContactID()%>">
	               				<%=contact.getContactValue()%>
	               		</option>
			<%} %>
			</select>
			
			<input id="notificationCount" name="notificationUnitCount<%=counter%>"
			type="text" value="<%=notification.getUnitCount()%>">
			
			<select name="notificationType<%=counter%>" id="dropdown">
			               <%for(NotificationType notificationType: allNotificationTypes) 
			               		{%> 
			               		<option 
			               		<% if(notification.getNotificationType().getNotificationTypeID() == 
			               				notificationType.getNotificationTypeID())
			               			{ %>
			               			selected="selected"
			               		
			               		<%	} %>
			               		value="<%=notificationType.getNotificationTypeID()%>">
			               				<%=notificationType.getNotificationTypeName()%>
			               		</option>
			               		<%} %>
			</select>
			before the event<br>
				<% if(counter==0) {
				%>
					</span>
				<%} 
				else {%>
				</span>
				<%} %>
			
			<% counter++;
			} %>
			
				<span id="Notification"></span>
				<label onclick="addNewNotification()">Add Another Notification</label>
			</p>
		<div id="checkboxItems">
		<%for(EventType eventType: allEventTypes) 
		{ %>
		<input type="checkbox" name="EventTypeCheckBox" 
		<%for(EventType eventTypeSelected: userPreference.getEventTypes()) 
			{
				if(eventType.getEventTypeID()==eventTypeSelected.getEventTypeID())
				{
				%>
					checked="checked"
		<%		}
			}%>
		value="<%=eventType.getEventTypeID()%>">
		<%=eventType.getEventTypeName().toLowerCase()%><br>
		<%} %>
		</div>
		Preference Name: <input type="text" name="UserPreferenceName" maxlength="30" value="<%=userPreference.getPreferenceName()%>">
		<p><input style="padding-left:12px;padding-right:12px" type="submit" onclick="" value="Save"><button style="margin-left:20px;padding-top:2px;padding-bottom:2px;" onclick="Dashboard">Cancel</button></p>
	</div>
</form>