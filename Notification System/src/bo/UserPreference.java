package bo;

import java.util.List;

public class UserPreference {

	Integer userPreferenceID;
	List<EventType> eventTypes;
	String preferenceName;	
	List<Notification> notifications; 
	
	
	public UserPreference(Integer userPreferenceID, List<EventType> eventTypes,
			String preferenceName, List<Notification> notifications) {
		super();
		this.userPreferenceID = userPreferenceID;
		this.eventTypes = eventTypes;
		this.preferenceName = preferenceName;
		this.notifications = notifications;
	}
	public UserPreference() {
		super();
	}
	public Integer getUserPreferenceID() {
		return userPreferenceID;
	}
	public void setUserPreferenceID(Integer userPreferenceID) {
		this.userPreferenceID = userPreferenceID;
	}
	public List<EventType> getEventTypes() {
		return eventTypes;
	}
	public void setEventTypes(List<EventType> eventTypes) {
		this.eventTypes = eventTypes;
	}
	public String getPreferenceName() {
		return preferenceName;
	}
	public void setPreferenceName(String preferenceName) {
		this.preferenceName = preferenceName;
	}
	public List<Notification> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
	
	
	
}
