package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDAO;
import util.CodeMessageUtil;
import util.DefineUtil;

public class AdminFindUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDAO userDAO = new UserDAO();
    public AdminFindUserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String fullname = "";
		if(!"".equals(session.getAttribute("fullname"))) {
			fullname = (String) session.getAttribute("fullname");
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=" + CodeMessageUtil.ERROR);
			return;
		}
		int numberOfItems = userDAO.numberOfItemsFind(fullname);
		int numberOfPage = (int)Math.ceil((float)numberOfItems / DefineUtil.NUMBER_PER_PAGE);
		int currentPage = 1;
		try {
			currentPage = Integer.valueOf(request.getParameter("pageid"));
			System.out.println(currentPage);
		} catch (NumberFormatException e) {
			
		}
		if(currentPage > numberOfPage || currentPage < 1) {
			currentPage = 1;
		}
		int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;
		
		ArrayList<User> listUsers = userDAO.findItemsPagination(offset, fullname);
		
		request.setAttribute("listUsers", listUsers);
		request.setAttribute("numberOfPage", numberOfPage);
		request.setAttribute("currentPage", currentPage);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/find.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String fullname = "";
		if(!"".equals(request.getParameter("fullname"))) {
			fullname = request.getParameter("fullname");
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=" + CodeMessageUtil.ERROR);
			return;
		}
		session.setAttribute("fullname", fullname);
		response.sendRedirect(request.getContextPath() + "/admin/user/find");
	}
}
