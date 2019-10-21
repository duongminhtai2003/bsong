package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CategoryDAO;
import util.CodeMessageUtil;

public class AdminDeleteCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDeleteCatController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryDAO categoryDAO = new CategoryDAO();
		int id = 0; 
		try {
			id = Integer.valueOf(request.getParameter("id"));
		} catch (NullPointerException e) {
			response.sendRedirect(request.getContextPath()+"/admin/cat/index?msg="+CodeMessageUtil.ERROR);
			return;
		}
		
		if(categoryDAO.DeleteItem(id) > 0) {
			response.sendRedirect(request.getContextPath()+"/admin/cat/index?msg="+CodeMessageUtil.DELETE_SUCCESS);
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/cat/index?msg="+CodeMessageUtil.ERROR);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
