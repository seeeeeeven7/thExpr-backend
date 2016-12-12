package com.seeeeeeven7.thExpr.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Size(min=2, max=30)
	private String name;
	
	@Size(min=32, max=32)
	private String passwordMD5;
	
	private Boolean isAdmin;

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
