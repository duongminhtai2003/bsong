package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDAO;
import util.CodeMessageUtil;


public class AdminDeleteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDAO userDAO = new UserDAO();
    
    public AdminDeleteUserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User objUser = (User)session.getAttribute("userinfo");
		int id = 0; 
		try {
			id = Integer.valueOf(request.getParameter("id"));
		} catch (NullPointerException e) {
			response.sendRedirect(request.getContextPath()+"/admin/user/index?msg="+CodeMessageUtil.ERROR);
			return;
		}
		if("admin".equals(objUser.getUsername())) {
			if(userDAO.delItem(id) > 0) {
				response.sendRedirect(request.getContextPath()+"/admin/user/index?msg="+CodeMessageUtil.DELETE_SUCCESS);
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/user/index?msg="+CodeMessageUtil.ERROR);
				return;
			}
		}
		else {
			response.sendRedirect(request.getContextPath()+"/admin/user/index?msg="+CodeMessageUtil.ERROR);
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
