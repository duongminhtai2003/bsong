package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.SongDAO;
import util.CodeMessageUtil;


public class AdminDeleteSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SongDAO songDAO = new SongDAO();
    public AdminDeleteSongController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0; 
		try {
			id = Integer.valueOf(request.getParameter("id"));
		} catch (NullPointerException e) {
			response.sendRedirect(request.getContextPath()+"/admin/song/index?msg="+CodeMessageUtil.ERROR);
			return;
		}
		
		if(songDAO.delItem(id) > 0) {
			response.sendRedirect(request.getContextPath()+"/admin/song/index?msg="+CodeMessageUtil.DELETE_SUCCESS);
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/song/index?msg="+CodeMessageUtil.ERROR);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
