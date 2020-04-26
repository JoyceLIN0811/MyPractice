package model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;

public class MenuBean implements Serializable {
	private static final long serialVersionUID = 1L;
	Integer categoryid;
	String category;

	public MenuBean(Integer categoryid, String  category) {
		this.categoryid=categoryid;
		this.category = category;
		}
	
	public MenuBean() {
		
	}
	
	public Integer getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}




	
	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


}