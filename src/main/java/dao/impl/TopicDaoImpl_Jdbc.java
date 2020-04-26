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

import _00_init.util.GlobalService;
import model.*;
import dao.*;

// 本類別使用為標準的JDBC技術來存取資料庫。
public class TopicDaoImpl_Jdbc implements Serializable, TopicDao {
	private static final long serialVersionUID = 1L;
	private int topicid = 0;
	private int pageNo = 0;
	private int recordsPerPage = 9;
	private int totalPages = -1;
	private DataSource ds = null;
	String selected = "";
	private Connection conn = null;

	public TopicDaoImpl_Jdbc() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("TopicDaoImpl_Jdbc類別#建構子發生例外: " + ex.getMessage());
		}
	}

	// 儲存一筆資料
	@Override
	public int saveTopic(TopicBean tb) {
		String sql = "insert into addtopic" + " (categoryid, title, content, username,posttime) "
				+ " values (?, ?, ?, ?, ?)";
		int n = 0;
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, tb.getCategoryid());
			ps.setString(2, tb.getTitle());
			ps.setString(3, tb.getContent());
			ps.setString(4, tb.getUsername());
			ps.setTimestamp(5, tb.getPosttime());

			n = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("TopicDaoImpl_Jdbc類別#saveMember()發生例外: " + ex.getMessage());
		}
		return n;
	}

	// 修改一筆資料

	@Override
	public int updateTopic(TopicBean topic) {
		int n = 0;
		String sql = "UPDATE addtopic SET " + " title=?, categoyid=?, content=?  WHERE topicid = ?";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, topic.getTitle());
			ps.setInt(2, topic.getCategoryid());
			ps.setString(3, topic.getContent());
//			ps.setString(4, topic.getUsername());
//			ps.setTimestamp(4, topic.getPosttime());
			ps.setInt(4, topic.getTopicid());

			n = ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("TopicDaoImpl_Jdbc()#updateTopic(topic, long)發生例外: " + ex.getMessage());
		}
		return n;
	}

	// 刪除一筆資料

	@Override
	public int deleteTopic(int topicid) {
		int n = 0;
		String sql = "DELETE FROM addtopic WHERE topicid = ?";

		try (Connection connection = ds.getConnection(); PreparedStatement pStmt = connection.prepareStatement(sql);) {
			pStmt.setInt(1,  topicid);
			n = pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("TopicDaoImpl_Jdbc()#deleteTopic()發生例外: " + ex.getMessage());
		}
		return n;
	}

	// 取得查詢一筆資料
	@Override
	public TopicBean getTopic(int topicid) {
		TopicBean bean = null;
		String sql = "SELECT * FROM addtopic WHERE topicid = ?";

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, topicid);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					bean = new TopicBean();
					bean.setCategoryid(rs.getInt(2));
					bean.setTitle(rs.getString(3));
					bean.setContent(rs.getString(4));
					bean.setUsername(rs.getString(5));
					bean.setPosttime(rs.getTimestamp(6));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("TopicDaoImpl_Jdbc()#queryBook()發生例外: " + ex.getMessage());
		}
		return bean;
	}
	
	// 取得查詢整筆個人資料
		public List<TopicBean> getMytopic(int memberid) {
			List<TopicBean> list1 = new ArrayList<TopicBean>();
			
			String sql = "SELECT f.memberid, a.topicid, a.categoryid, m.category, a.title, a.username, a.posttime ,c.reply_num "+
			"FROM addtopic a INNER JOIN menu m ON a.categoryid = m.categoryid "+
			"Left JOIN (SELECT topicid,count(*) reply_num from reply group by topicid) c "+ 	
			"ON c.topicid = a.topicid "+
			"Left JOIN (SELECT memberid,username from forummember) f "+
			"ON a.username = f.username "+
			"WHERE f.memberid=? ORDER BY a.posttime DESC";
			
			try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
				ps.setInt(1, memberid);
				try (ResultSet rs = ps.executeQuery();) {
					while (rs.next()) {
						TopicBean bean = new TopicBean();
						
						bean.setMemberid(rs.getInt("memberid"));
						bean.setTopicid(rs.getInt("topicid"));
						bean.setCategoryid(rs.getInt("categoryid"));
						bean.setCategory(rs.getString("category"));
						bean.setUsername(rs.getString("username"));
						bean.setTitle(rs.getString("title"));
						bean.setPosttime(rs.getTimestamp("posttime"));
						bean.setReply_num(rs.getInt("reply_num"));
						
						list1.add(bean);
					}
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException("TopicDaoImpl_Jdbc()#query()發生例外: " + ex.getMessage());
			}
			return list1;
		}
		
	
	// 取得查詢整筆資料
		@Override
		public  List<TopicBean> getAlltopic() {
			List<TopicBean> list2 = new ArrayList<TopicBean>();
//			String sql = "SELECT a.topicid, a.categoryid, m.category, a.title, a.username, a.posttime ,c.reply_num FROM addtopic a INNER JOIN menu m ON a.categoryid = m.categoryid INNER JOIN (SELECT topicid,count(*) reply_num from reply group by topicid) c 	ON c.topicid = a.topicid ORDER BY a.posttime DESC";
			String sql = "SELECT a.topicid, a.categoryid, m.category, a.title, a.username, a.posttime ,"
					+"(CASE WHEN c.reply_num IS NULL THEN 0 ELSE c.reply_num END) "
					+"reply_num FROM addtopic a INNER JOIN menu m ON a.categoryid = m.categoryid "
					+"LEFT JOIN (SELECT topicid,count(*) reply_num from reply group by topicid) c 	ON c.topicid = a.topicid "
					+"ORDER BY a.posttime DESC";

			try (Connection connection = ds.getConnection(); 
					PreparedStatement ps = connection.prepareStatement(sql);) {
				try (ResultSet rs = ps.executeQuery();) {
					while (rs.next()) {
						TopicBean bean = new TopicBean();
						
						bean.setTopicid(rs.getInt("topicid"));
						bean.setCategoryid(rs.getInt("categoryid"));						
						bean.setCategory(rs.getString("category"));
						bean.setUsername(rs.getString("username"));
						bean.setTitle(rs.getString("title"));
						bean.setPosttime(rs.getTimestamp("posttime"));
						bean.setReply_num(rs.getInt("reply_num"));

						
						list2.add(bean);
					}
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException("TopicDaoImpl_Jdbc()#query發生例外: " + ex.getMessage());
			}
			return list2;
		}
		
		// 取得查詢分類整筆資料
				@Override
				public  List<TopicBean> getCategorytopic(int categoryid) {
					List<TopicBean> list3 = new ArrayList<TopicBean>();
					
//					String sql = "SELECT a.topicid, a.categoryid, m.category, a.title, a.username, a.posttime ,c.reply_num FROM addtopic a INNER JOIN menu m ON a.categoryid = m.categoryid INNER JOIN (SELECT topicid,count(*) reply_num from reply group by topicid) c 	ON c.topicid = a.topicid WHERE a.categoryid=? ORDER BY a.posttime DESC";
					String sql = "SELECT a.topicid, a.categoryid, m.category, a.title, a.username, a.posttime ,"
							+"(CASE WHEN c.reply_num IS NULL THEN 0 ELSE c.reply_num END) "
							+"reply_num FROM addtopic a INNER JOIN menu m ON a.categoryid = m.categoryid "
							+"LEFT JOIN (SELECT topicid,count(*) reply_num from reply group by topicid) c 	ON c.topicid = a.topicid "
							+" WHERE a.categoryid=? ORDER BY a.posttime DESC";

					try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
						ps.setInt(1, categoryid);
						try (ResultSet rs = ps.executeQuery();) {
							while (rs.next()) {
								TopicBean bean = new TopicBean();
								
								bean.setTopicid(rs.getInt("topicid"));
								bean.setCategoryid(rs.getInt("categoryid"));
								bean.setCategory(rs.getString("category"));
								bean.setUsername(rs.getString("username"));
								bean.setTitle(rs.getString("title"));
								bean.setPosttime(rs.getTimestamp("posttime"));
								bean.setReply_num(rs.getInt("reply_num"));
								
								list3.add(bean);
							}
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
						throw new RuntimeException("TopicDaoImpl_Jdbc()#query()發生例外: " + ex.getMessage());
					}
					return list3;
				}

	
	
	// 計算頁數
	@Override
	public int getTotalPages() {
		// 注意下一列敘述的每一個型態轉換
		totalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));

		return totalPages;
	}
	// 查詢某一頁的資料

	@Override
	public List<TopicBean> getPageTopics() {
		List<TopicBean> list = new ArrayList<TopicBean>();

		String sql0 = "SELECT  * FROM (SELECT  ROW_NUMBER() OVER (ORDER BY TOPICID)"
				+ " AS RowNum, a.topicid, m.category, a.title, a.username, "
				+ " a.posttime FROM addtopic a JOIN menu m ON  a.categoryid = m.categoryid )"
				+ " AS NewTable WHERE RowNum >= ? AND RowNum <= ?";

		String sql = sql0;
		// 由頁碼推算出該頁是由哪一筆紀錄開始(1 based)
		int startRecordNo = (pageNo - 1) * recordsPerPage + 1;
		int endRecordNo = (pageNo) * recordsPerPage;

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, startRecordNo);
			ps.setInt(2, endRecordNo);
			try (ResultSet rs = ps.executeQuery();) {
				// 只要還有紀錄未取出，rs.next()會傳回true
				// 迴圈內將逐筆取出ResultSet內的紀錄
				while (rs.next()) {
					// 準備一個新的BookBean，將ResultSet內的一筆紀錄移植到BookBean內
					TopicBean topic = new TopicBean();
					topic.setCategoryid(rs.getInt("categoryid"));
					topic.setTitle(rs.getString("title"));
					topic.setContent(rs.getString("content"));
					topic.setPosttime(rs.getTimestamp("posttime"));
					topic.setUsername(rs.getString("username"));

					// 最後將BookBean物件放入大的容器內
					list.add(topic);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("TopicDaoImpl_Jdbc()#getPageTopics()發生例外: " + ex.getMessage());
		}
		return list;
	}

	@Override
	public int getPageNo() {
		return pageNo;
	}

	@Override
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	 //計算我的總文章數
		@Override
		public long getMyRecordCounts(int memberid) {
			long count = 0; // 必須使用 long 型態
			String sql = "SELECT count(*) " + 
					"FROM addtopic a INNER JOIN menu m ON a.categoryid = m.categoryid " + 
					"Left JOIN (SELECT topicid,count(*) reply_num from reply group by topicid) c " + 
					"ON c.topicid = a.topicid "+ 
					"Left JOIN (SELECT memberid,username from forummember) f " + 
					"ON a.username = f.username " + 
					"WHERE f.memberid=?";
			
			try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
				ps.setInt(1, memberid);
				try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					count = rs.getLong(1);
				}
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException("TopicDaoImpl_Jdbc()#getRecordCounts()發生例外: " + ex.getMessage());
			}
			return count;
		}
	
	
   //計算總文章數
	@Override
	public long getRecordCounts() {
		long count = 0; // 必須使用 long 型態
		String sql = "SELECT count(*) FROM addtopic";
		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {
			if (rs.next()) {
				count = rs.getLong(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("TopicDaoImpl_Jdbc()#getRecordCounts()發生例外: " + ex.getMessage());
		}
		return count;
	}
	
	   //計算分類文章數
		@Override
		public long getCategoryRecordCounts(int categoryid) {
			long count = 0; // 必須使用 long 型態
			String sql = "SELECT count(*) FROM addtopic a INNER JOIN menu m ON a.categoryid = m.categoryid WHERE a.categoryid=?";
			try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
				ps.setInt(1, categoryid);
				try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					count = rs.getLong(1);
				}
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException("TopicDaoImpl_Jdbc()#getRecordCounts()發生例外: " + ex.getMessage());
			}
			return count;
		}
	
	@Override
	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	@Override
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
   
	//文章分類
	@Override
	public List<String> getCategory() {
		String sql = "SELECT DISTINCT category " + "From addtopic a JOIN menu m ON a.categoryid = m.categoryid";
		List<String> list = new ArrayList<>();
		try (Connection connection = ds.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				String cate = rs.getString(1);
				if (cate != null) {
					list.add(cate);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("DaoImpl_Jdbc()#getCategory()發生例外: " + ex.getMessage());
		}
		return list;
	}

	@Override
	public String getCategoryTag() {
		String ans = "";
		List<String> list = getCategory();
		ans += "<SELECT name='category'>";
		for (String cate : list) {
			if (cate.equals(selected)) {
				ans += "<option value='" + cate + "' selected>" + cate + "</option>";
			} else {
				ans += "<option value='" + cate + "'>" + cate + "</option>";
			}
		}
		ans += "</SELECT>";
		return ans;
	}

	@Override
	public void setSelected(String selected) {
		this.selected = selected;
	}

	

	@Override
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

}
