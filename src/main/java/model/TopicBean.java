package model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;

public class TopicBean implements Serializable {
	private static final long serialVersionUID = 1L;
	Integer topicid;
	Integer categoryid;
	String title;
	String content;
	String username;
	Timestamp posttime;
	Integer reply_num;
	Integer memberid;
	
	

	public Integer getMemberid() {
		return memberid;
	}


	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}


	public Integer getReply_num() {
		return reply_num;
	}


	public void setReply_num(Integer reply_num) {
		this.reply_num = reply_num;
	}
	
	String  category;
	
	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}






	public TopicBean(Integer  categoryid, String title, String content, String username,  Timestamp posttime) {
		this.categoryid = categoryid;
		this.title = title;
		this.content = content;
		this.username = username;
		this.posttime = posttime;
		}
	
	public TopicBean() {
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





	public Integer getTopicid() {
		return topicid;
	}


	public void setTopicid(Integer topicid) {
		this.topicid = topicid;
	}


	public Integer getCategoryid() {
		return categoryid;
	}


	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TopicBean [categoryid=");
		builder.append(categoryid);
		builder.append(", title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", username=");
		builder.append(username);
		builder.append(", posttime=");
		builder.append(posttime);
		builder.append("]");
		return builder.toString();
	}




}