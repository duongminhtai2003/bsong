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

public class AdminSearchSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SongDAO songDAO = new SongDAO();

	public AdminSearchSongController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sname = "";
		if (request.getParameter("sname") != null) {
			sname = request.getParameter("sname");
			session.setAttribute("sname", sname);
		}
		if (session.getAttribute("sname") != null) {
			sname = (String) session.getAttribute("sname");
		}
		int numberOfItems = songDAO.numberOfItemsBySname(sname);
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
		ArrayList<Song> listSongs = songDAO.getItemsPaginationBySname(offset, sname);
		request.setAttribute("listSongs", listSongs);
		request.setAttribute("numberOfPage", numberOfPage);
		request.setAttribute("currentPage", currentPage);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/find.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
