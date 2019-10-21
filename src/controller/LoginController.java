package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDAO;
import util.StringUtil;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/auth/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = StringUtil.md5(request.getParameter("password"));
		UserDAO userDAO = new UserDAO();
		User objUser = new User(username, password);
		User userinfo = userDAO.getItemByUsernameAndPassword(objUser);
		if(userinfo != null) {
			session.setAttribute("userinfo", userinfo);
			response.sendRedirect(request.getContextPath()+"/admin");
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/auth/login?msg=Error");
			return;
		}
		
	}
}
