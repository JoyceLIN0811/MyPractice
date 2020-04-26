package service.impl;

import dao.*;
import dao.impl.*;
import model.*;
import service.*;

public class MemberServiceImpl implements MemberService {

	MemberDao  dao ;
	public MemberServiceImpl() {
		this.dao = new MemberDaoImpl_Jdbc();
	}

	@Override
	public int saveMember(MemberBean mb) {
		return dao.saveMember(mb);
	}

	@Override
	public boolean emailExists(String email) {
		return dao.emailExists(email);
	}

	@Override
	public MemberBean queryMember(String email) {
		return dao.queryMember(email);
	}

	public MemberBean checkIDPassword(String email, String password) {
		MemberBean mb = dao.checkIDPassword(email, password);
		return mb;
	}
	
	public MemberBean queryUsername(String email) {
		return dao.queryUsername(email);
	}

}
