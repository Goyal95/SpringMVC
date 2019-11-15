package com.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.springmvc.entity.FacebookEmployee;
import com.springmvc.service.FacebookServiceInterface;

@Controller
public class UserController {
	
	@Autowired
	private FacebookServiceInterface fsi;
	
	@RequestMapping("register.htm")
	public ModelAndView registerUser(
			@RequestParam("name") String name, 
			@RequestParam("address") String address, 
			@RequestParam("pass") String password, 
			@RequestParam("email") String email
			) {
		
		FacebookEmployee fe = new FacebookEmployee();
		fe.setAddress(address);
		fe.setEmail(email);
		fe.setName(name);
		fe.setPassword(password);
		
		int i = fsi.registerUser(fe);
		String result = "Registration Failed";
		
		if(i>0) {
			result = "Registration Success";
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("a1", name);

		mv.setViewName("result.jsp");
		return mv;
	}
	
	@RequestMapping("login.htm")
	public ModelAndView loginUser() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	
	@RequestMapping("timeline.htm")
	public ModelAndView timelineUser() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}

}
