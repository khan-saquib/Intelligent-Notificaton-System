package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.Notification;
import bo.User;
import bo.UserPreference;
import bo.UserPreferenceGroup;

public class UserPreferenceGroupDB {
		
	
	/**
	 * Inserts the new userPreference into the database
	 * Accepts SQLConnection as a Connection object.
	 * IMPORTANT: TO BE CALLED FROM ANOTHER DB LAYER METHOD ONLY
	 * 
	 * @param userPreference
	 * @param user
	 * @param conn
	 * @throws Exception
	 */
	public void insertUserPreferenceGroup(UserPreference userPreference, User user, Connection conn) throws Exception
	{
		String sql;
		PreparedStatement ps;
		int affectedRows;
		sql = "INSERT INTO [UserPreferenceGroups] ([UserID],[UserPreferenceID]) values (?,?);";
	    ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	    ps.setInt(2, userPreference.getUserPreferenceID());
	    ps.setInt(1, user.getUserID());
	    
	    affectedRows = ps.executeUpdate();
	    if (affectedRows == 0) {
            throw new Exception("Creating userPreferenceGroups failed, no rows affected.");
        }

        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
	    	    ps.close();
            }
            else {
            	conn.rollback();
                throw new Exception("Creating userPreferenceGroups failed, no ID obtained.");
            }
        }
	}
	
	
	/**
	 * Saves the UserPreferenceGroup into the database
	 * 
	 * @param userPreference
	 * @param eventTypes 
	 * @param notifications 
	 * @throws Exception 
	 */
	public void insertUserPreference(UserPreference userPreference, User user) throws Exception
	{
    	Connection conn  = DBConnectionFactory.getConnectionObject();
		NotificationDB notificationDB = new NotificationDB();
		UserPreferenceDB userPreferenceDB = new UserPreferenceDB();
		conn.setAutoCommit(false);
		
		//Save the User Preference
		userPreference = userPreferenceDB.insertUserPreference(userPreference, conn);
		
		//save the list of Event Types
		userPreferenceDB.insertEventTypeList(userPreference.getEventTypes(),userPreference.getUserPreferenceID(), conn);
		
		//Save the list of notifications
		for(Notification notification: userPreference.getNotifications())
		{
			notification.setUserPreference(userPreference);
			notificationDB.insertNotification(notification, conn);
		}
			
		//Save the UserPreferenceGroup
		insertUserPreferenceGroup(userPreference, user, conn);
	    
		conn.commit();
	    DBConnectionFactory.destroyConnection(conn);
    }
	
	public UserPreferenceGroup getUserPreferenceGroup(Integer userID) throws Exception
	{
		List<UserPreference> userPreferences = new ArrayList<>();
		UserPreferenceGroup userPreferenceGroup = new UserPreferenceGroup(0, null, userPreferences);
		Connection conn  = DBConnectionFactory.getConnectionObject();
    	    PreparedStatement ps = conn.prepareStatement("Select upg.UserPreferenceGroupID, upg.UserPreferenceID, up.UserPreferenceName from UserPreferenceGroups upg "
    	    				 + "INNER JOIN UserPreference up ON up.UserPreferenceID = upg.UserPreferenceID "
    	    				 + "where userID =?;");
    	    ps.setInt(1, userID);
    	    
	    	ResultSet rs = ps.executeQuery();
	    	if(rs.next())
	    	{
	    		userPreferenceGroup.setUserPreferenceGroupID(rs.getInt(1));
	    		userPreferences.add(new UserPreference(rs.getInt(2),null, rs.getString(3), null));
	    	}
	    	else
	    	{
	    		return null;
	    	}
	    	while(rs.next())
	    	{
	    		userPreferences.add(new UserPreference(rs.getInt(2),null,null, null));
	    	}
    	    
    	    rs.close();
    	    ps.close();
    	    DBConnectionFactory.destroyConnection(conn);
        	return userPreferenceGroup;
		
	}
	
}
