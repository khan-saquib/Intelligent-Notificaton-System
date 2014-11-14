package ui;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserPreferenceService;
import bo.User;
import bo.UserPreferenceGroup;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter
(description = "Filters all the users by making them login if they have logged out", 
urlPatterns = {
		"/Dashboard"
})
public class UserSessionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public UserSessionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("Inside UserVerification Filter");
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res= (HttpServletResponse)response;
		//check if "role" attribute is null
        User user = (User)req.getSession().getAttribute("user");
        UserPreferenceService preferenceService = new UserPreferenceService();
        UserPreferenceGroup userPreferences = null;
        if(user !=null  && user.isVerified() ==  true)
			try {
				userPreferences = preferenceService.getAllUserPreferencesByUserID(user.getUserID());
			} catch (Exception e) {
				// TODO Show ERROR PAGE
				e.printStackTrace();
			}
        
        if(user == null)
        {
        	res.sendRedirect("Login.html");
        }
        else if(user.isVerified() ==  false)
        {
        	res.sendRedirect("EmailVerification");
        }
        else if(userPreferences == null)
        {
        	res.sendRedirect("BlankPreference");
        }
        else
        {
        	chain.doFilter(request, response);
        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
