package model;

import java.io.Serializable;


public class MemberBean implements Serializable {
	private static final long serialVersionUID = 1L;
	Integer memberid;
	String email;
	String password;
	String username;
	String gender;
	Integer age;


	public MemberBean(String  email, String password, String username, String gender, Integer age) {
		this.email = email;
		this.password = password;
		this.username = username;
		this.gender = gender;
		this.age = age;
		}


	public MemberBean() {
	}


	public Integer getMemberid() {
		return memberid;
	}


	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}
}


