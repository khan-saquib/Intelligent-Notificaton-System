package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import bo.Event;
import data.EventDB;

public class EventService {

	
	
	
	public void readEvents()
	{
		File file = new File("C:\\Users\\Saquib\\git\\Intelligent Notification Systems\\"
				+ "Notification System\\src\\service\\categorized.txt");
		Scanner scanner;
		Event event;
		List<Event> events = new ArrayList<>();
		int temp;
		Date date = new Date(System.currentTimeMillis()); 
		List<String> eventsList = new ArrayList<>();
		String[] eventString;
		
		try {
			scanner = new Scanner(file);
			String eventString1;
            while (scanner.hasNextLine()) 
	        {
	        	eventString1 = scanner.nextLine();
	        	while(scanner.hasNextLine())
	        	{
	        		if(!eventString1.contains("\t"))
	        			eventString1 += scanner.nextLine();
	        		else break;
	        	}
	        	
	        	eventsList.add(eventString1);
	        	
	        	if(eventString1.split("\t").length == 6 || eventString1.split("\t").length == 5)
	        	{
	        		
	        		event = new Event();
	        		eventString = eventString1.split("\t");
	            	event.setEventTitle(eventString[0]);
	            	event.setEventURL(eventString[1]);
	            	temp = eventString[1].indexOf('=');
	            	event.setEventID(Integer.parseInt(eventString[1].substring(temp+1)));
	            	event.setCategory(eventString[2]);
	                event.setTimeDuration(eventString[3]);
	                event.setStartTime(eventString[4]);
	            	event.setEventDate(date);
	        		if(eventString1.split("\t").length == 6)
	        		{
	        			event.setEventDescription(eventString[5]);
	        		}
	        		events.add(event);
	        	}
	        	
		    }
            
            
            EventDB eventDatabase = new EventDB();
            
            for(Event event1: events)
            {
            	
            	if(!(event1.getEventTitle().length()>50))
            	{
            		eventDatabase.addEvent(event1);
            	}
            }
            
           
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args)
	{
		EventService file = new EventService();
		file.readEvents();
	}
	
	
	
	
	
}
