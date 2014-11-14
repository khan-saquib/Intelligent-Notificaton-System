package bo;

import java.util.List;

public class UserPreferenceGroup {

	Integer userPreferenceGroupID;
	User user;
	List<UserPreference> userPreference;
	
	public Integer getUserPreferenceGroupID() {
		return userPreferenceGroupID;
	}
	public void setUserPreferenceGroupID(Integer userPreferenceGroupID) {
		this.userPreferenceGroupID = userPreferenceGroupID;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<UserPreference> getUserPreference() {
		return userPreference;
	}
	public void setUserPreference(List<UserPreference> userPreference) {
		this.userPreference = userPreference;
	}
	public UserPreferenceGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserPreferenceGroup(Integer userPreferenceGroupID, User user,
			List<UserPreference> userPreference) {
		super();
		this.userPreferenceGroupID = userPreferenceGroupID;
		this.user = user;
		this.userPreference = userPreference;
	}
	
	
	
}
