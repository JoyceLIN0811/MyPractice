package service;

import model.*;

public interface MemberService {
	boolean emailExists(String email);
	int saveMember(MemberBean mb);
//	void updateUnpaidOrderAmount(OrderBean ob);
	MemberBean queryMember(String email);
	public MemberBean checkIDPassword(String email, String password) ;
	public MemberBean queryUsername(String email);
	
}
