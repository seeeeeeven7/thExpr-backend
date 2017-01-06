package com.seeeeeeven7.thExpr.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Expr {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private String fileMD5;

	public String getFileMD5() {
		return fileMD5;
	}

	public void setFileMD5(String fileMD5) {
		this.fileMD5 = fileMD5;
	}
}
