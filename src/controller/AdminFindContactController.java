package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Contact;
import model.dao.ContactDAO;
import util.CodeMessageUtil;
import util.DefineUtil;

public class AdminFindContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ContactDAO contactDAO = new ContactDAO();
    public AdminFindContactController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = "";
		if(!"".equals(session.getAttribute("name"))) {
			name = (String) session.getAttribute("name");
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/contact/index?msg=" + CodeMessageUtil.ERROR);
			return;
		}
		int numberOfItems = contactDAO.numberOfItemsFind(name);
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
		
		ArrayList<Contact> listContacts = contactDAO.findItemsPagination(offset, name);
		
		request.setAttribute("listContacts", listContacts);
		request.setAttribute("numberOfPage", numberOfPage);
		request.setAttribute("currentPage", currentPage);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/contact/find.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = "";
		if(!"".equals(request.getParameter("name"))) {
			name = request.getParameter("name");
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/contact/index?msg=" + CodeMessageUtil.ERROR);
			return;
		}
		session.setAttribute("name", name);
		response.sendRedirect(request.getContextPath() + "/admin/contact/find");
	}
}
