package ui;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserPreferenceService;
import bo.User;
import bo.UserPreferenceGroup;

/**
 * Servlet implementation class PreferenceList
 */
@WebServlet( urlPatterns = {"/ViewPreferences"})
public class PreferenceList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreferenceList() {
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
		
		System.out.println("Inside PreferenceList");
		User user = (User)request.getSession().getAttribute("user");
		UserPreferenceService userPreferencesService = new UserPreferenceService();
		UserPreferenceGroup userPreferenceGroup;
		try {
			userPreferenceGroup = userPreferencesService.getAllUserPreferencesByUserID(user.getUserID());
		} catch (Exception e) {
			throw new IOException(e);
		}
		request.setAttribute("userPreferenceGroup", userPreferenceGroup);
		String nextJSP = "/WEB-INF/jsp/UserPreferenceList.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(nextJSP);
		rd.forward(request, response);
		
	}

}
