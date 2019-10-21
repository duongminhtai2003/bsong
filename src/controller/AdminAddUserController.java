package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.dao.UserDAO;
import util.CodeMessageUtil;
import util.StringUtil;

public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO = new UserDAO();
	public AdminAddUserController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = "";
		String password = "";
		String fullname = "";
		if (!"".equals(request.getParameter("username")) && !"".equals(request.getParameter("password")) && !"".equals(request.getParameter("fullname"))) {
			username = request.getParameter("username");
			password = StringUtil.md5(request.getParameter("password"));
			fullname = request.getParameter("fullname");
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=" + CodeMessageUtil.ERROR);
			return;
		}

		User objUsers = new User(1, username, password, fullname, 0);
		if (userDAO.addItem(objUsers) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=" + CodeMessageUtil.ADD_SUCCESS);
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/user/add?msg=" + CodeMessageUtil.ERROR);
			return;
		}

	}

}
