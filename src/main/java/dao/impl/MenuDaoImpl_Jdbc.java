package dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.cj.exceptions.RSAException;

import _00_init.util.GlobalService;
import model.*;
import dao.*;

// 本類別使用為標準的JDBC技術來存取資料庫。
public class MenuDaoImpl_Jdbc implements Serializable, MenuDao {
	private static final long serialVersionUID = 1L;
	private String tagName = "";
	private int selected = -1;
	private int id = 0;
	DataSource ds = null;

	public MenuDaoImpl_Jdbc() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("MenuDaoImpl_Jdbc類別#建構子發生例外: " + ex.getMessage());
		}
	}

	@Override
	public MenuBean getCategoryById() {
		MenuBean mb = null;
		
		String sql = "SELECT*FROM menu WHERE categoryid=?";
				try(
						Connection connection=ds.getConnection();
						PreparedStatement ps = connection.prepareStatement(sql);
						){
					ps.setInt(1, id);
					try(ResultSet rs = ps.executeQuery();){
						if(rs.next()) {
							mb = new MenuBean(rs.getInt(1), rs.getString(2));
						}
					}
				}catch (Exception ex) {
					ex.printStackTrace();
					throw new RuntimeException("MenuDaoImpl_Jdbc()#getCompanyById()發生例外: " 
							+ ex.getMessage());
				}
				return mb;
			}
	@Override
	public MenuBean getCategory() {
		
		MenuBean  mb1 = null;
		String sql = "SELECT category FROM menu";
		
		try(
				Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				){
			while(rs.next()) {
				mb1 = new MenuBean();
				}
			}catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException("MenuDaoImpl_Jdbc()#getCompany()發生例外: " 
						+ ex.getMessage());
			}
			return mb1;
	}
	
	@Override
	public int getSelected() {
		return selected;
	}

	@Override
	public void setSelected(int selected) {
		this.selected = selected;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}
	
}
	


