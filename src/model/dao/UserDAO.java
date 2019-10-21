package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import model.bean.User;
import util.ConnectDBUtil;
import util.DefineUtil;

public class UserDAO {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement ps;

	public User getItemByID(int id) {
		User objUsers = null;
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT * FROM users WHERE id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				objUsers = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getInt("role_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objUsers;
	}

	public int delItem(int id) {
		conn = ConnectDBUtil.getConnection();
		int check = 0;
		String sql = "DELETE FROM users WHERE id=?";
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
		String sql = "SELECT COUNT(*) AS count FROM users";
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
	
	public int numberOfItemsFind(String fullname) {
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT COUNT(*) AS count FROM users WHERE fullname LIKE ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + fullname + "%");
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

	public ArrayList<User> getItemsPagination(int offset) {
		ArrayList<User> list = new ArrayList<User>();
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT * FROM users LIMIT ?, ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, offset);
			ps.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = ps.executeQuery();
			while (rs.next()) {
				User objUsers = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getInt("role_id"));
				list.add(objUsers);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}
		return list;
	}
	
	
	public ArrayList<User> findItemsPagination(int offset, String fullname) {
		ArrayList<User> list = new ArrayList<User>();
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT * FROM users WHERE fullname LIKE ? LIMIT ?, ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + fullname + "%");
			ps.setInt(2, offset);
			ps.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = ps.executeQuery();
			while (rs.next()) {
				User objUsers = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getInt("role_id"));
				list.add(objUsers);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}
		return list;
	}

	public int editItem(User objUsers) {
		conn = ConnectDBUtil.getConnection();
		int check = 0;
		String sql = "UPDATE users SET username=?, password=?, fullname=?, role_id=? WHERE id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, objUsers.getUsername());
			ps.setString(2, objUsers.getPassword());
			ps.setString(3, objUsers.getFullname());
			ps.setInt(4, objUsers.getRole_id());
			ps.setInt(5, objUsers.getId());
			check = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}
		return check;
	}

	public int addItem(User objUsers) {
		conn = ConnectDBUtil.getConnection();
		int check = 0;
		String sql = "INSERT INTO users(username, password, fullname, role_id) VALUES (?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, objUsers.getUsername());
			ps.setString(2, objUsers.getPassword());
			ps.setString(3, objUsers.getFullname());
			ps.setInt(4, objUsers.getRole_id());
			check = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}
		return check;
	}
	
	public ArrayList<Category> getItems() {
		ArrayList<Category> list = new ArrayList<Category>();
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT * FROM categories ORDER BY id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Category objCategory = new Category(rs.getInt("id"), rs.getString("name"));
				list.add(objCategory);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, st, conn);
		}
		return list;
	}

	public int addItem(Category objCategory) {
		conn = ConnectDBUtil.getConnection();
		int check = 0;
		String sql = "INSERT INTO categories(name) VALUES (?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, objCategory.getName());
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
		UserDAO userDAO = new UserDAO();
			System.out.println(userDAO.checkUsername("admin"));

	}

	public User getItemByUsernameAndPassword(User objUser) {
		User user = null;
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT * FROM users WHERE username=? AND password=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, objUser.getUsername());
			ps.setString(2, objUser.getPassword());
			rs = ps.executeQuery();
			if(rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getInt("role_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}
		return user;
	}

	public boolean checkUsername(String username) {
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT * FROM users WHERE username LIKE ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}
		return false;
	}
	
}
