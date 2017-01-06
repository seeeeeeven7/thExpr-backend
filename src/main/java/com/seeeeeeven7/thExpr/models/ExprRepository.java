package com.seeeeeeven7.thExpr.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExprRepository extends JpaRepository<Expr, Long> {

	List<Expr> findAllByFileMD5(String md5);
	
}
