package dao;

import java.util.List;

import model.*;

import java.sql.Connection;

import model.*;

public interface ReplyDao {
	
	// 儲存一筆資料
	public int saveReply(ReplyBean rb);

	// 修改一筆資料
	public int updateReply(ReplyBean reply);

	// 刪除一筆資料

	public int deleteReply(int replyid);

	// 取得查詢一筆資料

	public ReplyBean getReply(int replyid);
	
	// 取得查詢整筆資料
	public List<ReplyBean> getAllreply(int topicid) ;
	
	// 計算紀錄總筆數
	public long getRecordCounts(int topicid);
	
	public void setSelected(String selected);

	public void setConnection(Connection con);


}