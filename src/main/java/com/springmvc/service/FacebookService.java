package com.springmvc.service;

import org.springframework.stereotype.Service;
import com.springmvc.entity.FacebookEmployee;

@Service
public class FacebookService implements FacebookServiceInterface{
	
	public int registerUser(FacebookEmployee fe) {
		return 1;
	}

}
