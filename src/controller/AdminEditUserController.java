package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDAO;
import util.CodeMessageUtil;
import util.StringUtil;

@MultipartConfig
public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO = new UserDAO();
	public AdminEditUserController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.valueOf(request.getParameter("id"));
		} catch (NullPointerException e) {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=" + CodeMessageUtil.ERROR);
			return;
		}
		User objUsersOld = (User) userDAO.getItemByID(id);
		request.setAttribute("objUsersOld", objUsersOld);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User objUser = (User)session.getAttribute("userinfo");
		int id = 0;
		String username = "";
		String password = "";
		String fullname = "";
		try {
			id = Integer.valueOf(request.getParameter("id"));
		} catch (NullPointerException e) {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=" + CodeMessageUtil.ERROR);
			return;
		}
		if(!"".equals(request.getParameter("username"))) {
			username = request.getParameter("username");
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=" + CodeMessageUtil.ERROR);
			return;
		}
		if(!"".equals(request.getParameter("password"))) {
			password = StringUtil.md5(request.getParameter("password"));
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=" + CodeMessageUtil.ERROR);
			return;
		}
		if(!"".equals(request.getParameter("fullname"))) {
			fullname = request.getParameter("fullname");
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=" + CodeMessageUtil.ERROR);
			return;
		}
		User objUsers = new User(id, username, password, fullname, 0);
		if("admin".equals(objUser.getUsername())) {
		if(userDAO.editItem(objUsers) > 0) {
			response.sendRedirect(request.getContextPath()+"/admin/user/index?msg="+CodeMessageUtil.EDIT_SUCCESS);
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/user/index?msg="+CodeMessageUtil.ERROR);
			return;
		}
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/user/index?msg="+CodeMessageUtil.ERROR);
			return;
		}
	}

}
