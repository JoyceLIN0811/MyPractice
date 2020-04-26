package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _00_init.util.GlobalService;
import model.*;
import dao.*;

// 本類別使用為標準的JDBC技術來存取資料庫。
public class MemberDaoImpl_Jdbc implements MemberDao {

	private DataSource ds = null;
	private Connection conn = null;
	public MemberDaoImpl_Jdbc() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#建構子發生例外: " + ex.getMessage());
		}
	}
	// 儲存MemberBean物件，將參數mb新增到Member表格內。
	public int saveMember(MemberBean mb) {
		String sql = "insert into forummember " 
				+ " (email, password, username, gender,age) "
				+ " values (?, ?, ?, ?, ?)";
		int n = 0;
		try (
			Connection con = ds.getConnection(); 
			PreparedStatement ps = con.prepareStatement(sql);
		) {
			ps.setString(1, mb.getEmail());
			ps.setString(2, mb.getPassword());
			ps.setString(3, mb.getUsername());
			ps.setString(4, mb.getGender());
			ps.setInt(5, mb.getAge());

			n = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#saveMember()發生例外: " 
										+ ex.getMessage());
		}
		return n;
	}
	// 判斷參數id(會員帳號)是否已經被現有客戶使用，如果是，傳回true，表示此id不能使用，
	// 否則傳回false，表示此id可使用。
	@Override
	public boolean emailExists(String email) {
		boolean exist = false;
		String sql = "SELECT * FROM forummember WHERE email = ?";
		try (
			Connection connection = ds.getConnection(); 
			PreparedStatement ps = connection.prepareStatement(sql);
		) {
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					exist = true;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#idExists()發生例外: " 
					+ ex.getMessage());
		}
		return exist;
	}
	
	// 由參數 id (會員帳號) 到Member表格中 取得某個會員的所有資料，傳回值為一個MemberBean物件，
	// 如果找不到對應的會員資料，傳回值為null。
	@Override
	public MemberBean queryMember(String email) {
		MemberBean mb = null;
		String sql = "SELECT * FROM forummember WHERE email = ?";
		try (
			Connection connection = ds.getConnection(); 
			PreparedStatement ps = connection.prepareStatement(sql);
		) {
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					mb = new MemberBean();
					mb.setEmail(rs.getString("email"));
					mb.setPassword(rs.getString("password"));
					mb.setUsername(rs.getString("username"));
					mb.setGender(rs.getString("gender"));
					mb.setAge(rs.getInt("age"));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#queryMember()發生例外: " 
					+ ex.getMessage());
		}
		return mb;
	}
	
	public MemberBean queryUsername(String email) {
		MemberBean mb = null;
		String sql = "SELECT username FROM forummember WHERE email = ?";
		try (
			Connection connection = ds.getConnection(); 
			PreparedStatement ps = connection.prepareStatement(sql);
		) {
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					mb = new MemberBean();
					mb.setUsername(rs.getString("username"));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#queryMember()發生例外: " 
					+ ex.getMessage());
		}
		return mb;
	}

	// 檢查使用者在登入時輸入的帳號與密碼是否正確。如果正確，傳回該帳號所對應的MemberBean物件，
	// 否則傳回 null。
	@Override
	public MemberBean checkIDPassword(String email, String password) {
		MemberBean mb = null;
		String sql = "SELECT * FROM forummember m WHERE m.email = ? and m.password = ?";
		try (
			Connection con = ds.getConnection(); 
			PreparedStatement ps = con.prepareStatement(sql);
		) {
			ps.setString(1, email);
			ps.setString(2, password);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					mb = new MemberBean();
					mb.setMemberid(rs.getInt("memberid"));
					mb.setEmail(rs.getString("email"));
					mb.setPassword(rs.getString("password"));
					mb.setUsername(rs.getString("username"));
					mb.setGender(rs.getString("gender"));
					mb.setAge(rs.getInt("age"));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#checkIDPassword()發生SQL例外: " 
					+ ex.getMessage());
		}
		return mb;
	}
	

	
	@Override
	public void setConnection(Connection conn) {
        this.conn = conn;
	}
	
	
}
