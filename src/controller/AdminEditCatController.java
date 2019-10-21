package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CategoryDAO;
import util.CodeMessageUtil;

public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditCatController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryDAO categoryDAO = new CategoryDAO();
		int id = 0;
		try {
			id = Integer.valueOf(request.getParameter("id"));
		} catch (NullPointerException e) {
			response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=" + CodeMessageUtil.ERROR);
			return;
		}

		Category objCategoryOld = (Category) categoryDAO.getItemByID(id);
		request.setAttribute("objCategoryOld", objCategoryOld);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/edit.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryDAO categoryDAO = new CategoryDAO();
		int id = 0;
		String name = "";
		try {
			id = Integer.valueOf(request.getParameter("id"));
		} catch (NullPointerException e) {
			response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=" + CodeMessageUtil.ERROR);
			return;
		}
		
		if (!"".equals(request.getParameter("name"))) {
			name = request.getParameter("name");
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=" + CodeMessageUtil.ERROR);
			return;
		}
		Category objCategory = new Category(id, name);
		if (categoryDAO.EditItem(objCategory) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg="+CodeMessageUtil.EDIT_SUCCESS);
			return;
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/cat/edit?msg="+CodeMessageUtil.ERROR);
			return;
		}
	}
}
