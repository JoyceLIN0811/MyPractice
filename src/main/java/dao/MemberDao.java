package dao;

import java.sql.Connection;

import model.*;
//import _04_ShoppingCart.model.OrderBean;

public interface MemberDao {
	
	public boolean emailExists(String email);

	public int saveMember(MemberBean mb) ;
	
	public MemberBean queryMember(String email);
	
	public MemberBean queryUsername(String email);
	
	public MemberBean checkIDPassword(String email, String password);	
	
//	void updateUnpaidOrderAmount(OrderBean ob);

	public void setConnection(Connection con);
}