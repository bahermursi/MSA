package com.packt.MSA.file;
import com.packt.MSA.domain.*;
import com.packt.MSA.repository.UserRepository;
import com.packt.MSA.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.*;

public class UserMetadata {
	public boolean getUser(User u) throws JsonParseException, JsonMappingException, IOException{
		FileReader fr=new FileReader();
		ObjectMapper mapper = new ObjectMapper();
		
		//List<User> userList=mapper.readValue(, valueType)
		//List<User> list = mapper.readValue(fr.getJSONString(), TypeFactory.defaultInstance().constructCollectionType(List.class, User.class));
		/*
		UserService userv=new UserService();
        userv.registerUser(u);
        */
		List<User> list;
		
		UserRepository usr=mapper.readValue(fr.getJSONString(), UserRepository.class);
		list=usr.getUsers();
		for(int i=0;i<usr.getTokencount();i++){
			System.out.println(list.get(i).getUsername());
		}
		
		//System.out.println(usr.getTokencount());
        return true;
	}
}
