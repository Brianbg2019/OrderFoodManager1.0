package com.personal.ofm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class Users implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="user")
	private String user;
	
	/** The password. */
	@Column(name="password")
	private String password;
	
	/** The role. */
	@Column(name="role")	
	private String role;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
