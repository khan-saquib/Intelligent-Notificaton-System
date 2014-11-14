package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bo.Event;


public class EventDB {
	
	
	public void addEvent(Event event)
	{
		String[] UserIDColumnName = new String[1]; 
		UserIDColumnName[0] = "UserID";
		
		
		try (Connection connection  = DBConnectionFactory.getConnectionObject())
		{
			PreparedStatement ps;
			ps = connection.prepareStatement("INSERT INTO EventUTD VALUES (?,?,?,?,?,?,?)");
			ps.setInt(1, event.getEventID());
		    ps.setString(2, event.getEventTitle());
		    ps.setString(3, event.getEventURL());
		    ps.setString(4, event.getCategory());
		    ps.setString(5, event.getTimeDuration());
		    ps.setString(6, event.getStartTime());
		    ps.setString(7, event.getEventDescription());
		    
		    ps.executeUpdate();
		   
		}
		catch (SQLException e) {
		    e.printStackTrace();
		} 
		catch (Exception e1) {
			
			e1.printStackTrace();
		}
		

	}
}
