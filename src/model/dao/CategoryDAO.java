package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import util.ConnectDBUtil;

public class CategoryDAO {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement ps;
	
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
	
	public ArrayList<Category> findByIdAndOrderByNewsId(){
		ArrayList<Category> list = new ArrayList<Category>();
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT * FROM categories ORDER BY id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
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
	
	public Category getItemByID(int id){
		Category objCategory = null;
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT * FROM categories WHERE id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				objCategory = new Category(rs.getInt("id"), rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}
		return objCategory;
	}
	
	public ArrayList<Category> getItemsByDifferentID(int id) {
		ArrayList<Category> list = new ArrayList<Category>();
		conn = ConnectDBUtil.getConnection();
		String sql = "SELECT * FROM categories WHERE id != ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category objCategory = new Category(rs.getInt("id"), rs.getString("name"));
				list.add(objCategory);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}
		return list;
	}
	
	public int EditItem(Category objCategory){
		conn = ConnectDBUtil.getConnection();
		int check = 0;
		String sql = "UPDATE categories SET name=? WHERE id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, objCategory.getName());
			ps.setInt(2, objCategory.getId());
			check = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectDBUtil.close(rs, ps, conn);
		}
		return check;
	}
	
	public int addItem(Category objCategory){
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
	
	public int DeleteItem(int id){
		conn = ConnectDBUtil.getConnection();
		int check = 0;
		String sql = "DELETE FROM categories WHERE id=?";
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
	public static void main(String[] args) {
		CategoryDAO categoryDAO = new CategoryDAO();
		for (Category c : categoryDAO.getItemsByDifferentID(5)) {
			System.out.println(c.getName());
		}
				
	}
}
