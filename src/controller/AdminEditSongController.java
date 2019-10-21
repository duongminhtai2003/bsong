package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Category;
import model.bean.Song;
import model.dao.SongDAO;
import util.CodeMessageUtil;
import util.DefineUtil;
import util.FileUtil;

@MultipartConfig
public class AdminEditSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SongDAO songDAO = new SongDAO();
	public AdminEditSongController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.valueOf(request.getParameter("id"));
		} catch (NullPointerException e) {
			response.sendRedirect(request.getContextPath() + "/admin/song/index?msg=" + CodeMessageUtil.ERROR);
			return;
		}
		Song objSongsOld = (Song) songDAO.getItemByID(id);
		request.setAttribute("objSongsOld", objSongsOld);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 0;
		int catID = 0;
		String name = "";
		String singer = "";
		String preview_text = "";
		String detail_text = "";
		try {
			id = Integer.valueOf(request.getParameter("id"));
			catID = Integer.valueOf(request.getParameter("categories"));
		} catch (NullPointerException e) {
			response.sendRedirect(request.getContextPath() + "/admin/song/index?msg=" + CodeMessageUtil.ERROR);
			return;
		}
		if(!"".equals(request.getParameter("name"))) {
			name = request.getParameter("name");
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/song/index?msg=" + CodeMessageUtil.ERROR);
			return;
		}
		if(!"".equals(request.getParameter("singer"))) {
			singer = request.getParameter("singer");
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/song/index?msg=" + CodeMessageUtil.ERROR);
			return;
		}
		if(!"".equals(request.getParameter("preview_text"))) {
			preview_text = request.getParameter("preview_text");
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/song/index?msg=" + CodeMessageUtil.ERROR);
			return;
		}
		if(!"".equals(request.getParameter("detail_text"))) {
			detail_text = request.getParameter("detail_text");
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/song/index?msg=" + CodeMessageUtil.ERROR);
			return;
		}
		
		
		
		
		
		Part part = request.getPart("picture");
		String fileName = FileUtil.rename(part.getSubmittedFileName());
		if(!"".equals(fileName)) {
			//upload file
			String dirPath = request.getServletContext().getRealPath("")+ DefineUtil.DIR_UPLOAD;
			File dir = new File(dirPath);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			String filePath = dirPath + File.separator + fileName;
			part.write(filePath);
			System.out.println(filePath);
		}
		
		
		Part part1 = request.getPart("filemusic");
		String fileName1 = FileUtil.rename(part1.getSubmittedFileName());
		if(!"".equals(fileName1)) {
			//upload file
			String dirPath1 = request.getServletContext().getRealPath("")+ DefineUtil.DIR_UPLOAD;
			File dir1 = new File(dirPath1);
			if(!dir1.exists()) {
				dir1.mkdirs();
			}
			String filePath1 = dirPath1 + File.separator + fileName1;
			part1.write(filePath1);
			System.out.println(filePath1);
		}
		
		
		
		Song objSong = new Song(id, name, preview_text, detail_text, fileName, new Category(catID, null), singer, fileName1);
		if(songDAO.editItem(objSong) > 0) {
			response.sendRedirect(request.getContextPath()+"/admin/song/index?msg="+CodeMessageUtil.EDIT_SUCCESS);
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/song/add?msg="+CodeMessageUtil.ERROR);
			return;
		}
		
	}

}
