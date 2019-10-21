package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CategoryDAO;
import util.CodeMessageUtil;

public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Category> listCat = new ArrayList<Category>();

	public AdminAddCatController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryDAO categoryDAO = new CategoryDAO();
		String name = "";
		if (!"".equals(request.getParameter("name"))) {
			name = request.getParameter("name");
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=" + CodeMessageUtil.ERROR);
			return;
		}
		Category objCategory = new Category(1, name);
		if (categoryDAO.addItem(objCategory) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=" + CodeMessageUtil.ADD_SUCCESS);
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/cat/add?msg=" + CodeMessageUtil.ERROR);
			return;
		}

	}

}
