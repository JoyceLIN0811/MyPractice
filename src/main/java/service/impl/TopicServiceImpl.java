package service.impl;

import java.util.List;

import dao.*;
import dao.impl.*;
import model.*;
import service.*;

public class TopicServiceImpl implements TopicService {

	TopicDao dao;

	public TopicServiceImpl() {
		this.dao = new TopicDaoImpl_Jdbc();
	}

	@Override
	public int saveTopic(TopicBean tb) {
		return dao.saveTopic(tb);
	}

	@Override
	public int updateTopic(TopicBean topic) {
		return dao.updateTopic(topic);
	}

	@Override
	public int deleteTopic(int topicid) {
		return dao.deleteTopic(topicid);
	}

	@Override
	public TopicBean getTopic(int topicid) {
		return dao.getTopic(topicid);
				}

	@Override
	public int getTotalPages() {
		return dao.getTotalPages();
	}

	@Override
	public List<TopicBean> getPageTopics() {
		return dao.getPageTopics();
	}

	@Override
	public int getPageNo() {
		return dao.getPageNo();
	}

	@Override
	public void setPageNo(int pageNo) {
		dao.setPageNo(pageNo);

	}

	@Override
	public long getRecordCounts() {
		return dao.getRecordCounts();
	}

	@Override
	public int getRecordsPerPage() {
		return dao.getRecordsPerPage();
	}

	@Override
	public void setRecordsPerPage(int recordsPerPage) {
		dao.setRecordsPerPage(recordsPerPage);
	}

	@Override
	public List<String> getCategory() {
		return dao.getCategory();
	}

	@Override
	public String getCategoryTag() {
		return dao.getCategoryTag();
	}

	@Override
	public void setSelected(String category) {
		dao.setSelected(category);

	}

	@Override
	public List<TopicBean> getAlltopic() {
		return dao.getAlltopic();

	}

	@Override
	public List<TopicBean> getCategorytopic(int categoryid) {
		return dao.getCategorytopic(categoryid);

	}

}
