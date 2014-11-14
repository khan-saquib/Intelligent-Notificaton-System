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
import bo.Contact;
import bo.EventType;
import bo.NotificationType;
import bo.User;
import bo.UserPreference;

/**
 * Servlet implementation class PreferenceEdit
 */
@WebServlet("/EditPreference")
public class PreferenceEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreferenceEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = (User)request.getSession().getAttribute("user");
		try {
			Integer userPreferenceID = Integer.parseInt(request.getParameter("userPreferenceID"));
			UserPreferenceService userPreferencesService = new UserPreferenceService();
			UserService userService = new UserService();
			List<EventType> allEventTypes = userPreferencesService.getAllEventTypes();
			List<NotificationType> allNotificationTypes = userPreferencesService.getAllNotificationTypes();
			List<Contact> userContacts = userService.getContactsOfUser(user);
			UserPreference userPreference = userPreferencesService.getUserPreferenceByUserPreferenceID(userPreferenceID);
			
			request.setAttribute("userContacts", userContacts);
			request.setAttribute("allEventTypes", allEventTypes);
			request.setAttribute("allNotificationTypes", allNotificationTypes);
			request.setAttribute("userPreference", userPreference);
			System.out.println("PREFERENCE NAME: \n" + userPreference.getPreferenceName());
			//Save the userPreference on the session for save after editing.
			request.getSession().setAttribute("userPreference", userPreference);
			
			String nextJSP = "/WEB-INF/jsp/UserPreferenceEdit.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("DB Error Occured. Please contact administrator");
		}
		
	}

}
