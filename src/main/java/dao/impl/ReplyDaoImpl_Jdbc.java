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
public class ReplyDaoImpl_Jdbc implements Serializable, ReplyDao {
	private static final long serialVersionUID = 1L;
	private int replyid = 0;
	private DataSource ds = null;
	String selected = "";
	private Connection conn = null;

	public ReplyDaoImpl_Jdbc() {
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
	public int saveReply(ReplyBean rb) {
		String sql = "insert into reply" + " (username, topicid, replycontent,replytime) " + " values (?, ?, ?, ?)";
		int n = 0;
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, rb.getUsername());
			ps.setInt(2, rb.getTopicid());
			ps.setString(3, rb.getReplycontent());
			ps.setTimestamp(4, rb.getReplytime());

			n = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("ReplyDaoImpl_Jdbc類別#saveReply()發生例外: " + ex.getMessage());
		}
		return n;
	}

	// 修改一筆資料

	@Override
	public int updateReply(ReplyBean reply) {
		int n = 0;
		String sql = "UPDATE reply SET " + " replycontent=?  WHERE replyid = ?";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, reply.getReplycontent());
			ps.setInt(2, reply.getReplyid());
			n = ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("TopicDaoImpl_Jdbc()#updateTopic(topic, long)發生例外: " + ex.getMessage());
		}
		return n;
	}

	// 刪除一筆資料

	@Override
	public int deleteReply(int replyid) {
		int n = 0;
		String sql = "DELETE FROM reply WHERE replyid = ?";

		try (Connection connection = ds.getConnection(); PreparedStatement pStmt = connection.prepareStatement(sql);) {
			pStmt.setInt(1, replyid);
			n = pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("TopicDaoImpl_Jdbc()#deleteTopic()發生例外: " + ex.getMessage());
		}
		return n;
	}

	// 取得查詢一筆資料
	@Override
	public ReplyBean getReply(int replyid) {
		ReplyBean bean = null;
		String sql = "SELECT * FROM reply WHERE replyid = ?";

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, replyid);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					bean = new ReplyBean();
					bean.setUsername(rs.getString(2));
					bean.setTopicid(rs.getInt(3));
					bean.setReplycontent(rs.getString(4));
					bean.setReplytime(rs.getTimestamp(5));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("TopicDaoImpl_Jdbc()#queryBook()發生例外: " + ex.getMessage());
		}
		return bean;
	}

	// 取得查詢整筆資料
	@Override
	public List<ReplyBean> getAllreply(int topicid) {
		List<ReplyBean> list = new ArrayList<ReplyBean>();

		String sql = "SELECT replyid, title, reply.username, replycontent, replytime FROM reply JOIN addtopic ON reply.topicid = addtopic.topicid WHERE reply.topicid=? ORDER BY replytime DESC";

		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, topicid);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					ReplyBean bean = new ReplyBean();

					bean.setReplyid(rs.getInt("replyid"));
					bean.setTitle(rs.getString("title"));
					bean.setUsername(rs.getString("username"));
					bean.setReplycontent(rs.getString("replycontent"));
					bean.setReplytime(rs.getTimestamp("replytime"));

					list.add(bean);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("ReplyDaoImpl_Jdbc()#queryReply()發生例外: " + ex.getMessage());
		}
		return list;
	}

	// 計算總回覆數
	@Override
	public long getRecordCounts(int topicid) {
		long count = 0; // 必須使用 long 型態
		String sql = "SELECT count(*) FROM reply WHERE topicid=?";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, topicid);
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
	public void setSelected(String selected) {
		this.selected = selected;
	}

	@Override
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

}
