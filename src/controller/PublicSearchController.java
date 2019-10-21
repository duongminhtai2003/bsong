package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Song;
import model.dao.SongDAO;
import util.DefineUtil;

public class PublicSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SongDAO songDAO = new SongDAO();

	public PublicSearchController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String snamefind = "";
		if (request.getParameter("editbox_search") != null) {
			snamefind = request.getParameter("editbox_search");
			session.setAttribute("snamefind", snamefind);
		}
		if (session.getAttribute("snamefind") != null) {
			snamefind = (String) session.getAttribute("snamefind");
		}
		int numberOfItems = songDAO.numberOfItemsBySname(snamefind);
		int numberOfPage = (int) Math.ceil((float) numberOfItems / DefineUtil.NUMBER_PER_PAGE);
		int currentPage = 1;
		try {
			currentPage = Integer.valueOf(request.getParameter("pageid"));
		} catch (NumberFormatException e) {

		}
		if (currentPage > numberOfPage || currentPage < 1) {
			currentPage = 1;
		}
		int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;
		ArrayList<Song> listSearch = songDAO.getItemsPaginationBySname(offset, snamefind);
		Song objSong = songDAO.getItemBySname(snamefind);
		request.setAttribute("listSearch", listSearch);
		request.setAttribute("objSong", objSong);
		request.setAttribute("numberOfPage", numberOfPage);
		request.setAttribute("currentPage", currentPage);
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/search.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
