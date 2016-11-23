package com.packt.MSA.service;

import com.packt.MSA.domain.*;

public class UserService {
	public void registerUser(User u){
		u.setRole("SUPER_USER");
	}
}
