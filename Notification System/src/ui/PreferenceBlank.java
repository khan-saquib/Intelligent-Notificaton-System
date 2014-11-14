package ui;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserPreferenceService;
import service.UserService;
import bo.EventType;
import bo.NotificationType;
import bo.Contact;
import bo.User;

/**
 * Servlet implementation class EditPreferences
 */
@WebServlet("/BlankPreference")
public class PreferenceBlank extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreferenceBlank() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User)request.getSession().getAttribute("user");
		if(user == null)
		{
			response.sendRedirect("Login.html");
		}
		
		try {
			UserPreferenceService userPreferencesService = new UserPreferenceService();
			UserService userService = new UserService();
			List<EventType> allEventTypes = userPreferencesService.getAllEventTypes();
			List<NotificationType> allNotificationTypes = userPreferencesService.getAllNotificationTypes();
			List<Contact> userContacts = userService.getContactsOfUser(user);
			request.setAttribute("userContacts", userContacts);
			request.setAttribute("allEventTypes", allEventTypes);
			request.setAttribute("allNotificationTypes", allNotificationTypes);
			
			String nextJSP = "/WEB-INF/jsp/UserPreferenceBlank.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("DB Error Occured. Please contact administrator");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
