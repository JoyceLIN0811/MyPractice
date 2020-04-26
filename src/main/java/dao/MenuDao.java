package dao;

import java.util.List;

import model.*;

import java.sql.Connection;

import model.*;

public interface MenuDao {
	
//	List<MenuBean> getCategory() ;
	MenuBean getCategory() ;

	MenuBean getCategoryById() ;

	int getId();
	
	int getSelected();

//	String getSelectTag();

//	String getTagName();
	
	void setId(int id);
	
	void setSelected(int selected);

//	void setTagName(String tagName);
	
	
	


}