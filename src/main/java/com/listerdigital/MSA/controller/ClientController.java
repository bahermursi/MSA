package com.listerdigital.MSA.controller;

import java.io.IOException;
import java.util.Map;
import com.listerdigital.MSA.service.*;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;

//import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.listerdigital.MSA.domain.*;

@Controller
@SessionAttributes("ses")
public class ClientController {
	@RequestMapping(value = "/ClientDetails")
    public String clientDetails(@ModelAttribute(value ="ses") User user, Map<String, Object> model) {
        
    	
    	//System.out.println("Login Controller:"+user.getRole());
    	//model.put("user", user);
        //model.put("ses", user);
        Client client=new Client();
        model.put("client", client);
        return "ClientDetails";
    }
	
	@RequestMapping(value = "/CreateClient")
	public String createClient(@Valid @ModelAttribute(value="client") Client client,BindingResult result,Map<String, Object> model) throws IOException{
		if (result.hasErrors()){  	
            return "ClientDetails";
        }
		ClientService cs=new ClientService(client);
		model.put("client", client);
		return "ClientCreated";
	}

}
