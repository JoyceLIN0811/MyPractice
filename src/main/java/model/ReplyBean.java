package model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;

public class ReplyBean implements Serializable {
	private static final long serialVersionUID = 1L;
	Integer replyid;
	String username;
	Integer topicid;
	String replycontent;
	Timestamp replytime;
	String title;


	public ReplyBean(String  username, Integer topicid, String replycontent, Timestamp replytime) {
		
		this.username = username;
		this.topicid=topicid;
		this.replycontent = replycontent;
		this.replytime = replytime;
		}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public ReplyBean() {
	}


	public Integer getReplyid() {
		return replyid;
	}


	public void setReplyid(Integer replyid) {
		this.replyid = replyid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Integer getTopicid() {
		return topicid;
	}


	public void setTopicid(Integer topicid) {
		this.topicid = topicid;
	}


	public String getReplycontent() {
		return replycontent;
	}


	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}


	public Timestamp getReplytime() {
		return replytime;
	}


	public void setReplytime(Timestamp replytime) {
		this.replytime = replytime;
	}

}