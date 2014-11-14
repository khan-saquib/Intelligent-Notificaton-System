package bo;

public class EventType {
	
	Integer eventTypeID;
	String eventTypeName;
	EventType eventTypeParent;
	String tag;
	
	public Integer getEventTypeID() {
		return eventTypeID;
	}
	public void setEventTypeID(Integer eventTypeID) {
		this.eventTypeID = eventTypeID;
	}
	public String getEventTypeName() {
		return eventTypeName;
	}
	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}
	public EventType getEventTypeParent() {
		return eventTypeParent;
	}
	public void setEventTypeParent(EventType eventTypeParent) {
		this.eventTypeParent = eventTypeParent;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public EventType(int eventTypeID, String eventTypeName,
			EventType eventTypeParent, String tag) {
		super();
		this.eventTypeID = eventTypeID;
		this.eventTypeName = eventTypeName;
		this.eventTypeParent = eventTypeParent;
		this.tag = tag;
	}
	public EventType() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}
