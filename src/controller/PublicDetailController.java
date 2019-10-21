package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Song;
import model.dao.SongDAO;
import util.CodeMessageUtil;

public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SongDAO songDAO = new SongDAO();
    public PublicDetailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int id = 0;
		try {
			id = Integer.valueOf(request.getParameter("id"));
		} catch (NullPointerException e) {
			response.sendRedirect(request.getContextPath() + "/admin/song/index?msg=" + CodeMessageUtil.ERROR);
			return;
		}
		Song objSong = (Song) songDAO.getItemByID(id);
		String hasvisited = (String) session.getAttribute("hasvisited" + id);
		if (hasvisited == null) {
			session.setAttribute("hasvisited" + id, "yes");
			session.setMaxInactiveInterval(3600);
			songDAO.increaseview(id);
		}
		songDAO.increaseview(id);
		request.setAttribute("objSong", objSong);
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/detail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
