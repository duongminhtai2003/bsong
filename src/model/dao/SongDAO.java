package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import model.bean.Song;
import util.ConnectDBUtil;
import util.DefineUtil;

public class SongDAO {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement ps;
	
	public void increaseview(int id) {
		conn = ConnectDBUtil.getConnection();
		String sql = "UPDATE songs SET counter = counter +1 WHERE id= ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}
		
	}

	public Song getItemByID(int id) {
		Song objSongs = null;
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT s.id, s.name AS songName, s.preview_text, s.detail_text, s.date_create, s.singer, s.filemusic, s.picture, s.counter, s.cat_id, c.name AS catName"
				+ " FROM songs AS s" + " INNER JOIN categories AS c ON s.id = ? and s.cat_id = c.id"
				+ " ORDER BY s.id DESC";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Category objCategory = new Category(rs.getInt("cat_id"), rs.getString("catName"));
				objSongs = new Song(rs.getInt("id"), rs.getInt("counter"), rs.getString("songName"),
						rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"),
						rs.getString("picture"), objCategory, rs.getString("singer"), rs.getString("filemusic"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objSongs;
	}

	public int delItem(int id) {
		conn = ConnectDBUtil.getConnection();
		int check = 0;
		String sql = "DELETE FROM songs WHERE id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			check = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}
		return check;
	}

	public int getMaxfid() {
		int Maxfid = 0;
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT MAX(id) FROM songs";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			rs.next();
			Maxfid = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, st, conn);
		}

		return Maxfid;
	}

	public int numberOfItems() {
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM songs";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				int count = rs.getInt("count");
				return count;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, st, conn);
		}
		return 0;
	}

	public int numberOfItemsFind(Song objSong) {
		conn = ConnectDBUtil.getConnection();
		String sql = "";
		if (!"".equals(objSong.getName()) && objSong.getCategory().getId() <= 0) {
			sql = "SELECT COUNT(*) AS count FROM songs WHERE name LIKE ?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, "%" + objSong.getName() + "%");
				rs = ps.executeQuery();
				if (rs.next()) {
					int count = rs.getInt("count");
					return count;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				ConnectDBUtil.close(rs, ps, conn);
			}
		} else if (objSong.getCategory().getId() > 0 && "".equals(objSong.getName())) {
			sql = "SELECT COUNT(*) AS count FROM songs WHERE cat_id = ?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, objSong.getCategory().getId());
				rs = ps.executeQuery();
				if (rs.next()) {
					int count = rs.getInt("count");
					return count;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				ConnectDBUtil.close(rs, ps, conn);
			}
		} else if (!"".equals(objSong.getName()) && objSong.getCategory().getId() > 0) {
			sql = "SELECT COUNT(*) AS count FROM songs WHERE cat_id = ? AND name LIKE ?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, objSong.getCategory().getId());
				ps.setString(2, "%" + objSong.getName() + "%");
				rs = ps.executeQuery();
				if (rs.next()) {
					int count = rs.getInt("count");
					return count;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				ConnectDBUtil.close(rs, ps, conn);
			}
		}
		return 0;
	}

	public int numberOfItemsByCatID(int id) {
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM songs WHERE cat_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				int count = rs.getInt("count");
				return count;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}
		return 0;
	}

	public int numberOfItemsFind(String songName) {
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM songs WHERE name LIKE ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + songName + "%");
			rs = ps.executeQuery();
			if (rs.next()) {
				int count = rs.getInt("count");
				return count;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}
		return 0;
	}

	public ArrayList<Song> getItemsPagination(int offset) {
		ArrayList<Song> list = new ArrayList<Song>();
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT s.id, s.name AS songName, s.preview_text, s.detail_text, s.date_create, s.picture, s.counter, s.cat_id, s.singer, s.filemusic, c.name AS catName"
				+ " FROM songs AS s" + " INNER JOIN categories AS c ON s.cat_id = c.id"
				+ " ORDER BY s.id DESC LIMIT ?, ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, offset);
			ps.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category objCategory = new Category(rs.getInt("cat_id"), rs.getString("catName"));
				Song objSongs = new Song(rs.getInt("id"), rs.getInt("counter"), rs.getString("songName"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"), rs.getString("picture"), objCategory, rs.getString("singer"), rs.getString("filemusic"));
				list.add(objSongs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}

		return list;
	}

	public ArrayList<Song> getNewItems() {
		ArrayList<Song> list = new ArrayList<Song>();
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT s.id, s.name AS songName, s.preview_text, s.detail_text, s.date_create, s.picture, s.counter, s.cat_id, s.singer, s.filemusic, c.name AS catName"
				+ " FROM songs AS s" + " INNER JOIN categories AS c ON s.cat_id = c.id" + " ORDER BY s.id DESC LIMIT ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, DefineUtil.NUMBER_NEW_SONG);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category objCategory = new Category(rs.getInt("cat_id"), rs.getString("catName"));
				Song objSongs = new Song(rs.getInt("id"), rs.getInt("counter"), rs.getString("songName"),
						rs.getString("preview_text"),
						rs.getString("detail_text"),
						rs.getTimestamp("date_create"),
						rs.getString("picture"),
						objCategory, rs.getString("singer"), rs.getString("filemusic"));
				list.add(objSongs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}

		return list;
	}

	public ArrayList<Song> getItemsBySinger(String singer) {
		ArrayList<Song> list = new ArrayList<Song>();
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT s.id, s.name AS songName, s.preview_text, s.detail_text, s.date_create, s.picture, s.counter, s.cat_id, s.singer, s.filemusic, c.name AS catName "
				+ "FROM songs AS s " + "INNER JOIN categories AS c ON s.cat_id = c.id AND s.singer LIKE ?"
				+ "ORDER BY s.id DESC LIMIT ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + singer + "%");
			ps.setInt(2, 3);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category objCategory = new Category(rs.getInt("cat_id"), rs.getString("catName"));
				Song objSongs = new Song(rs.getInt("id"), rs.getInt("counter"), rs.getString("songName"),
						rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"),
						rs.getString("picture"), objCategory, rs.getString("singer"), rs.getString("filemusic"));
				list.add(objSongs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}

		return list;
	}

	public ArrayList<Song> findItemsPagination(int offset, Song objSong) {
		ArrayList<Song> list = new ArrayList<Song>();
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT s.id, s.name AS songName, s.preview_text, s.detail_text, s.date_create, s.picture, s.counter, s.cat_id, s.singer, s.filemusic, c.name AS catName"
				+ " FROM songs AS s" + " INNER JOIN categories AS c ON s.cat_id = c.id WHERE 1 LIMIT ?, ? ";
		if (!"".equals(objSong.getName())) {
			sql = "SELECT s.id, s.name AS songName, s.preview_text, s.detail_text, s.date_create, s.picture, s.counter, s.cat_id, s.singer, s.filemusic, c.name AS catName"
					+ " FROM songs AS s" + " INNER JOIN categories AS c ON s.cat_id = c.id"
					+ " WHERE 1 AND s.name LIKE ? LIMIT ?, ? ";
		} else if (objSong.getCategory().getId() > 0) {
			sql = "SELECT s.id, s.name AS songName, s.preview_text, s.detail_text, s.date_create, s.picture, s.counter, s.cat_id, s.singer, s.filemusic, c.name AS catName"
					+ " FROM songs AS s" + " INNER JOIN categories AS c ON s.cat_id = c.id"
					+ " WHERE s.cat_id = ? LIMIT ?, ? ";
		} else if (!"".equals(objSong.getName()) && objSong.getCategory().getId() > 0) {
			sql = "SELECT s.id, s.name AS songName, s.preview_text, s.detail_text, s.date_create, s.picture, s.counter, s.cat_id, s.singer, s.filemusic, c.name AS catName"
					+ " FROM songs AS s" + " INNER JOIN categories AS c ON s.cat_id = c.id"
					+ " WHERE s.cat_id = ? AND s.name LIKE ? LIMIT ?, ? ";
		}

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, offset);
			ps.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			if (!"".equals(objSong.getName())) {
				ps.setString(1, "%" + objSong.getName() + "%");
				ps.setInt(2, offset);
				ps.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			} else if (objSong.getCategory().getId() > 0) {
				ps.setInt(1, objSong.getCategory().getId());
				ps.setInt(2, offset);
				ps.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			} else if (!"".equals(objSong.getName()) && objSong.getCategory().getId() > 0) {
				ps.setInt(1, objSong.getCategory().getId());
				ps.setString(2, "%" + objSong.getName() + "%");
				ps.setInt(3, offset);
				ps.setInt(4, DefineUtil.NUMBER_PER_PAGE);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Category objCategory = new Category(rs.getInt("cat_id"), rs.getString("catName"));
				Song objSongs = new Song(rs.getInt("id"), rs.getInt("counter"), rs.getString("songName"),
						rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"),
						rs.getString("picture"), objCategory, rs.getString("singer"), rs.getString("filemusic"));
				list.add(objSongs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}
		return list;
	}

	public int editItem(Song objSongs) {
		conn = ConnectDBUtil.getConnection();
		int check = 0;
		String sql = "UPDATE songs SET name=?, preview_text=?, detail_text=?, picture=?, cat_id=?, singer=?, filemusic=? WHERE id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, objSongs.getName());
			ps.setString(2, objSongs.getPreview_text());
			ps.setString(3, objSongs.getDetail_text());
			ps.setString(4, objSongs.getPicture());
			ps.setInt(5, objSongs.getCategory().getId());
			ps.setString(6, objSongs.getSinger());
			ps.setString(7, objSongs.getFilemusic());
			ps.setInt(8, objSongs.getId());
			check = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}
		return check;
	}

	public int addItem(Song objSongs) {
		conn = ConnectDBUtil.getConnection();
		int check = 0;
		String sql = "INSERT INTO songs(name, preview_text, detail_text, picture, cat_id, singer, filemusic) VALUES (?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, objSongs.getName());
			ps.setString(2, objSongs.getPreview_text());
			ps.setString(3, objSongs.getDetail_text());
			ps.setString(4, objSongs.getPicture());
			ps.setInt(5, objSongs.getCategory().getId());
			ps.setString(6, objSongs.getSinger());
			ps.setString(7, objSongs.getFilemusic());
			check = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}
		return check;
	}

	public static void main(String[] args) {
		SongDAO songDAO = new SongDAO();
		Song objSong = new Song("", new Category(2));
		System.out.println(songDAO.numberOfItemsFind(objSong));

	}

	public ArrayList<Song> getItemsPaginationByCatID(int offset, int id) {
		ArrayList<Song> list = new ArrayList<Song>();
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT s.id, s.name AS songName, s.preview_text, s.detail_text, s.date_create, s.picture, s.counter, s.cat_id, c.name AS catName"
				+ " FROM songs AS s" + " INNER JOIN categories AS c ON s.cat_id = c.id" 
				+ " WHERE s.cat_id = ?"
				+ " ORDER BY s.id DESC LIMIT ?, ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, offset);
			ps.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category objCategory = new Category(rs.getInt("cat_id"), rs.getString("catName"));
				Song objSongs = new Song(rs.getInt("id"), rs.getInt("counter"), rs.getString("songName"),
						rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"),
						rs.getString("picture"), objCategory);
				list.add(objSongs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}
		return list;
	}

	public int numberOfItemsBySname(String sname) {
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM songs WHERE name LIKE ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+sname+"%");
			rs = ps.executeQuery();
			if (rs.next()) {
				int count = rs.getInt("count");
				return count;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}
		return 0;
	}

	public ArrayList<Song> getItemsPaginationBySname(int offset, String sname) {
		ArrayList<Song> list = new ArrayList<Song>();
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT s.id, s.name AS songName, s.preview_text, s.detail_text, s.date_create, s.picture, s.counter, s.cat_id, c.name AS catName"
				+ " FROM songs AS s" 
				+ " INNER JOIN categories AS c ON s.cat_id = c.id" 
				+ " WHERE s.name LIKE ?"
				+ " ORDER BY s.id DESC LIMIT ?, ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+sname+"%");
			ps.setInt(2, offset);
			ps.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category objCategory = new Category(rs.getInt("cat_id"), rs.getString("catName"));
				Song objSongs = new Song(rs.getInt("id"), rs.getInt("counter"), rs.getString("songName"),
						rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"),
						rs.getString("picture"), objCategory);
				list.add(objSongs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}
		return list;
	}

	public Song getItemBySname(String sname) {
		Song objSongs = null;
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT s.id, s.name AS songName, s.preview_text, s.detail_text, s.date_create, s.singer, s.filemusic, s.picture, s.counter, s.cat_id, c.name AS catName"
				+ " FROM songs AS s" 
				+ " INNER JOIN categories AS c ON s.cat_id = c.id"
				+ " WHERE s.name LIKE ?"
				+ " ORDER BY s.id DESC";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+sname+"%");
			rs = ps.executeQuery();
			if (rs.next()) {
				Category objCategory = new Category(rs.getInt("cat_id"), rs.getString("catName"));
				objSongs = new Song(rs.getInt("id"), rs.getInt("counter"), rs.getString("songName"),
						rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("date_create"),
						rs.getString("picture"), objCategory, rs.getString("singer"), rs.getString("filemusic"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objSongs;
	}
}
