package com.listerdigital.MSA.file;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.listerdigital.MSA.domain.*;
import com.listerdigital.MSA.repository.*;
import com.listerdigital.MSA.service.UserService;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.*;

public class UserMetadata {
	public boolean getUser(User u) throws JsonParseException, JsonMappingException, IOException, CloneNotSupportedException{
		FileReader fr=new FileReader();
		ObjectMapper mapper = new ObjectMapper();
		
		List<User> list;
		
		UserRepository usr=mapper.readValue(fr.getJSONString("user.json"), UserRepository.class);
		
		
		list=usr.getUsers();
		
		
		for(int i=0;i<usr.getTokencount();i++){
			if(u.getUsername().equals(list.get(i).getUsername()) && u.getPassword().equals(list.get(i).getPassword())){
				UserService userv=new UserService();
		        userv.registerUser(u,list.get(i));
		        return true;
			}
		}
		
		
		
		
		//usr.getUsers().add(list.get(2));
		
		//System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(usr));
		
		//Overwrite the json String
		//fr.putJSONString("user.json",mapper.writerWithDefaultPrettyPrinter().writeValueAsString(usr));
		
		
		
		
		                               
		
		//System.out.println(usr.getTokencount());
        return false;
	}
}
