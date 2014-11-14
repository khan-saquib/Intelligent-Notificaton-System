package service;



import java.util.ArrayList;
import java.util.List;

import bo.EventType;
import bo.Notification;
import bo.NotificationType;
import bo.User;
import bo.UserPreference;
import bo.UserPreferenceGroup;
import data.EventTypesDB;
import data.NotificationDB;
import data.UserPreferenceDB;
import data.UserPreferenceGroupDB;

public class UserPreferenceService {
	
	
	public void saveUserPreferencesForNewUser(UserPreference userPreference, 
			List<Notification> notifications, List<Integer> eventTypes, User user) throws Exception
	{
		UserPreferenceGroupDB userPreferenceDB = new UserPreferenceGroupDB();
		userPreference.setNotifications(notifications);
		List<EventType> eventTypeBO = new ArrayList<>();
		for(Integer eventTypeID: eventTypes)
			eventTypeBO.add(new EventType(eventTypeID, null, null, null));
		userPreference.setEventTypes(eventTypeBO);
		userPreferenceDB.insertUserPreference(userPreference, user);
	}
	
	public UserPreferenceGroup getAllUserPreferencesByUserID(Integer userID) throws Exception
	{
		UserPreferenceGroupDB userPreferenceGroupDB = new UserPreferenceGroupDB();
		UserPreferenceGroup userPreferenceGroup = userPreferenceGroupDB.getUserPreferenceGroup(userID);
		return userPreferenceGroup;
	}
	
	public UserPreference getUserPreferenceByUserPreferenceID(Integer userPreferenceID) throws Exception
	{
		UserPreferenceDB userPreferenceDB  = new UserPreferenceDB();
		UserPreference userPreference = userPreferenceDB.getUserPreferenceFromUserPreferenceID(userPreferenceID);
		return userPreference;
	}
	
	public void saveEditedUserPreference(UserPreference userPreference) throws Exception
	{
		UserPreferenceDB userPreferenceDB = new UserPreferenceDB();
		userPreferenceDB.saveEditedUserPreference(userPreference);
	}
	
	public List<EventType> getAllEventTypes() throws Exception
	{
		EventTypesDB eventTypesDB = new EventTypesDB();
		return eventTypesDB.getListOfEventTypes();
	}
	
	public List<NotificationType> getAllNotificationTypes() throws Exception
	{
		NotificationDB notificationDB = new NotificationDB();
		return notificationDB.getListOfAllNotificationTypes();
	}

}