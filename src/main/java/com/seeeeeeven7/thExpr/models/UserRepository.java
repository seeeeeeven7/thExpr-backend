package com.seeeeeeven7.thExpr.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByName(String name);

}
