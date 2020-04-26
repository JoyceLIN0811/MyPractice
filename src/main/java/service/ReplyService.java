package service;

import java.util.List;

import model.*;

 public  interface ReplyService {
		// 儲存一筆資料
	  int saveReply(ReplyBean rb);

	// 修改一筆資料
	  int updateReply(ReplyBean reply);

	// 刪除一筆資料

	  int deleteReply(int replyid);


	// 取得查詢一筆資料

	  ReplyBean getReply(int replyid);
	  
	  //取得整筆資料
	  public List<ReplyBean> getAllreply(int topicid) ;

	// 計算紀錄總筆數
		public long getRecordCounts(int topicid);
	
	void setSelected(String category);



}