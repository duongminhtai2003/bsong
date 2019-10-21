package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import model.bean.Contact;
import model.bean.Song;
import util.ConnectDBUtil;
import util.DefineUtil;

public class ContactDAO {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement ps;

	public Song getItemByID(int id) {
		Song objSongs = null;
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT s.id, s.name AS songName, s.preview_text, s.detail_text, s.date_create, s.picture, s.counter, s.cat_id, c.name AS catName"
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
						rs.getString("picture"), objCategory);
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
		String sql = "DELETE FROM contacts WHERE id=?";
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
		String sql = "SELECT COUNT(*) AS count FROM contacts";
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
	
	public int numberOfItemsFind(String name) {
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM contacts WHERE name LIKE ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + name + "%");
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

	public ArrayList<Contact> getItemsPagination(int offset) {
		ArrayList<Contact> list = new ArrayList<Contact>();
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT * FROM contacts LIMIT ?, ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, offset);
			ps.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = ps.executeQuery();
			while (rs.next()) {
				Contact objContact = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getString("message"));
				list.add(objContact);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}
		return list;
	}
	
	/*
	 * public ArrayList<Song> findItemsPagination(int offset, String songName) {
	 * ArrayList<Song> list = new ArrayList<Song>(); conn =
	 * ConnectDBUtil.getConnection(); String sql =
	 * "SELECT s.id, s.name AS songName, s.preview_text, s.detail_text, s.date_create, s.picture, s.counter, s.cat_id, c.name AS catName"
	 * + " FROM songs AS s" + " INNER JOIN categories AS c ON s.name LIKE %?%" +
	 * " ORDER BY s.id DESC LIMIT ?, ?"; try { ps = conn.prepareStatement(sql);
	 * ps.setString(1, songName); ps.setInt(2, offset); ps.setInt(3,
	 * DefineUtil.NUMBER_PER_PAGE); rs = ps.executeQuery(); while (rs.next()) {
	 * Category objCategory = new Category(rs.getInt("cat_id"),
	 * rs.getString("catName")); Song objSongs = new Song(rs.getInt("id"),
	 * rs.getInt("counter"), rs.getString("songName"), rs.getString("preview_text"),
	 * rs.getString("detail_text"), rs.getTimestamp("date_create"),
	 * rs.getString("picture"), objCategory); list.add(objSongs); } } catch
	 * (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * finally { ConnectDBUtil.close(rs, ps, conn); } return list; }
	 */
	
	public ArrayList<Contact> findItemsPagination(int offset, String name) {
		ArrayList<Contact> list = new ArrayList<Contact>();
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT * FROM contacts WHERE name LIKE ? LIMIT ?, ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + name + "%");
			ps.setInt(2, offset);
			ps.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = ps.executeQuery();
			while (rs.next()) {
				Contact objContact = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getString("message"));
				list.add(objContact);
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
		String sql = "UPDATE songs SET name=?, preview_text=?, detail_text=?, picture=?, cat_id=? WHERE id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, objSongs.getName());
			ps.setString(2, objSongs.getPreview_text());
			ps.setString(3, objSongs.getDetail_text());
			ps.setString(4, objSongs.getPicture());
			ps.setInt(5, objSongs.getCategory().getId());
			ps.setInt(6, objSongs.getId());
			check = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}
		return check;
	}

	public int addItem(Contact objContact) {
		conn = ConnectDBUtil.getConnection();
		int check = 0;
		String sql = "INSERT INTO contacts(name, email, website, message) VALUES (?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, objContact.getName());
			ps.setString(2, objContact.getEmail());
			ps.setString(3, objContact.getWebsite());
			ps.setString(4, objContact.getMessage());
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
		ContactDAO contactDAO = new ContactDAO();
		for (Contact c : contactDAO.findItemsPagination(0, "ngọc")) {
			System.out.println(c.getName());
		}
		
	}
}
