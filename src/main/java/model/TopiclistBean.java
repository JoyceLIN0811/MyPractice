package model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;

public class TopiclistBean implements Serializable {
	private static final long serialVersionUID = 1L;
	Integer topicid;
	String category;
	String title;
	String username;
	Timestamp posttime;


	public TopiclistBean(String  category, String title, String content, String username,  Timestamp posttime) {
		this.category = category;
		this.title = title;
		this.username = username;
		this.posttime = posttime;
		}


	public Timestamp getPosttime() {
		return posttime;
	}
	
	public String getUsername() {
		return username;
	}


	public void setPosttime(Timestamp posttime) {
		this.posttime = posttime;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public TopiclistBean() {
	}


	public Integer getTopicid() {
		return topicid;
	}


	public void setTopicid(Integer topicid) {
		this.topicid = topicid;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


}