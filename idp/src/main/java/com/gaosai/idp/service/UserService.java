package com.gaosai.idp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaosai.idp.mapping.ISpMapping;
import com.gaosai.idp.mapping.IUserMapping;

@Service
public class UserService {
	
	@Autowired
	private IUserMapping userDao;
	@Autowired
	private ISpMapping spDao;
	
	

}
