package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bo.EventType;

public class EventTypesDB {

	public List<EventType> getListOfEventTypes() throws Exception {
		EventType eventType;
		List<EventType> eventTypeList = new ArrayList<>();
		Connection conn = DBConnectionFactory.getConnectionObject();

		PreparedStatement ps = conn
				.prepareStatement("SELECT EventTypeID,EventTypeName,EventTypeParentID,Tag "
						+ "FROM EventType;");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			eventType = new EventType();
			eventType.setEventTypeID(rs.getInt("EventTypeID"));
			eventType.setEventTypeName(rs.getString("EventTypeName"));
			eventType.setEventTypeParent(null);
			eventType.setTag(rs.getString("Tag"));
			eventTypeList.add(eventType);
		}
		rs.close();
		ps.close();
		DBConnectionFactory.destroyConnection(conn);
		if (eventTypeList.size() == 0)
			return null;
		else
			return eventTypeList;
	}

}
