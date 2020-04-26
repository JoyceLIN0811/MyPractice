package service.impl;

import java.util.List;

import dao.*;
import dao.impl.*;
import model.*;
import service.*;

public class ReplyServiceImpl implements ReplyService {

	ReplyDao dao;

	public ReplyServiceImpl() {
		this.dao = new ReplyDaoImpl_Jdbc();
	}

	@Override
	public int saveReply(ReplyBean rb) {
		return dao.saveReply(rb);
	}

	@Override
	public int updateReply(ReplyBean reply) {
		return dao.updateReply(reply);
	}

	@Override
	public int deleteReply(int replyid) {
		return dao.deleteReply(replyid);
	}

	@Override
	public ReplyBean getReply(int replyid) {
		return dao.getReply(replyid);
				}


	@Override
	public long getRecordCounts(int topicid) {
		return dao.getRecordCounts(topicid);
	}

	@Override
	public void setSelected(String category) {
		dao.setSelected(category);

	}

	@Override
	public List<ReplyBean> getAllreply(int topicid) {
		return dao. getAllreply(topicid);
	}

}
