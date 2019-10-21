package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;

@WebServlet("/CheckUsernameController")
public class CheckUsernameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     UserDAO userDAO = new UserDAO();
    public CheckUsernameController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (userDAO.checkUsername(request.getParameter("username"))) {
			response.getWriter().write("<img src=\"img/not-available.png\" />");
	     } else {
	    	 response.getWriter().write("<img src=\"img/available.png\" />");
	     }
	}

}
