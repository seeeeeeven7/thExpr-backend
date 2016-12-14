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
}
