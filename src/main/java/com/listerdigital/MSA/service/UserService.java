package com.listerdigital.MSA.service;

import com.listerdigital.MSA.domain.*;

public class UserService {
	public void registerUser(User u,User v) throws CloneNotSupportedException{
		//System.out.println(v.getRole()+","+u.getRole());
		u.setRole(v.getRole());
	}
}
