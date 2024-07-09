package com.demo.javaBean;


/**
 * 
 */
public class User {
	private String username;
	private String password;
	private int id;
	private String yanzm;
	private  String pictureUrl;
	private String quanxian;

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getQuanxian() {
		return quanxian;
	}

	public void setQuanxian(String quanxian) {
		this.quanxian = quanxian;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	private String sex;
	private String phone;
	private String email;
	private String signature;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getYanzm() {
		return yanzm;
	}
	public void setYanzm(String yanzm) {
		this.yanzm = yanzm;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", id=" + id + ", yanzm=" + yanzm
				+ ", getUsername()=" + getUsername() + ", getPassword()=" + getPassword() + ", getId()=" + getId()
				+ ", getYanzm()=" + getYanzm() + "]";
	}
	
	
}
