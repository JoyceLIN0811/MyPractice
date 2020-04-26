package service;

import java.util.List;

import model.*;

 public  interface TopicService {
	// 儲存一筆資料
	  int saveTopic(TopicBean tb);

	// 修改一筆資料
	  int updateTopic(TopicBean topic);

	// 刪除一筆資料

	  int deleteTopic(int  topicid);

	// 取得查詢一筆資料

	  TopicBean getTopic(int topicid);
	  
	  //取得整筆資料
	public List<TopicBean> getAlltopic() ;
	
	// 取得查詢分類整筆資料
		public List<TopicBean> getCategorytopic(int categoryid) ;

	
	

	// 計算頁數
	  int getTotalPages();
	// 查詢某一頁的資料
	  List<TopicBean> getPageTopics();
	
	  int getPageNo();

	   void setPageNo(int pageNo);
	
	// 計算紀錄總筆數
	  long getRecordCounts();
	
	  int getRecordsPerPage();
	
	  void setRecordsPerPage(int recordsPerPage);
	
	//文章分類
	  List<String> getCategory();
	
	  String getCategoryTag();
	
	void setSelected(String category);



}
