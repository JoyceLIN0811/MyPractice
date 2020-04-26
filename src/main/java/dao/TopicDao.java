package dao;

import java.util.List;

import model.*;

import java.sql.Connection;

import model.*;

public interface TopicDao {
	// 儲存一筆資料
	public int saveTopic(TopicBean tb);

	// 修改一筆資料
	public int updateTopic(TopicBean topic);

	// 刪除一筆資料

	public int deleteTopic(int topicid);

	// 取得查詢一筆資料

	public TopicBean getTopic(int topicid);
	
	// 取得查詢整筆個人資料
	public List<TopicBean> getMytopic(int memberid) ;
	
	
	// 取得查詢整筆資料
	public List<TopicBean> getAlltopic() ;
	
	// 取得查詢分類整筆資料
	public List<TopicBean> getCategorytopic(int categoryid) ;

	// 計算頁數
	public int getTotalPages();
	// 查詢某一頁的資料
	public List<TopicBean> getPageTopics();
	
	public int getPageNo();

	public  void setPageNo(int pageNo);
	
	// 計算紀錄總筆數
	public long getMyRecordCounts(int memberid);
	
	public long getRecordCounts();
	
	public long getCategoryRecordCounts(int categoryid);

	
	public int getRecordsPerPage();
	
	public void setRecordsPerPage(int recordsPerPage);
	
	//文章分類
	public List<String> getCategory();
	
	public String getCategoryTag();
	
	public void setSelected(String selected);

	public void setConnection(Connection con);


}