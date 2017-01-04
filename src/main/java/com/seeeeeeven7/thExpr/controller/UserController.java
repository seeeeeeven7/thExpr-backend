package com.seeeeeeven7.thExpr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seeeeeeven7.thExpr.models.User;
import com.seeeeeeven7.thExpr.models.UserRepository;
import com.seeeeeeven7.thExpr.service.CurrentUserHolder;

@Controller
public class UserController {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private CurrentUserHolder currentUserHolder;
	
	@RequestMapping(value = "/api/users", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	@RequestMapping(value = "/api/user", method = RequestMethod.GET)
	@ResponseBody
	public User getUser() {
		return currentUserHolder.get();
	}
	
	
}
