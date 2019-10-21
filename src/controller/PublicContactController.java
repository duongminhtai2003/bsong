package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Contact;
import model.dao.ContactDAO;
import util.CodeMessageUtil;

public class PublicContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ContactDAO contactDAO = new ContactDAO();
    public PublicContactController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/contact.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String website = request.getParameter("website");
		String message = request.getParameter("message");
		
		Contact objContact = new Contact(0, name, email, website, message);
		if(contactDAO.addItem(objContact) > 0) {
			response.sendRedirect(request.getContextPath() + "/contact?msg=" + CodeMessageUtil.ADD_SUCCESS);
			return;
		}else {
			response.sendRedirect(request.getContextPath() + "/contact?msg=" + CodeMessageUtil.ERROR);
			return;
		}
	}

}
