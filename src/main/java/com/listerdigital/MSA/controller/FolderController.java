package com.listerdigital.MSA.controller;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.listerdigital.MSA.service.*;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
//import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.listerdigital.MSA.domain.*;
import com.listerdigital.MSA.file.FolderReader;

@Controller
public class FolderController {
	@RequestMapping(value="/managefolders")
	public String manage(@RequestParam String fname,Map<String,Object> model){
		FolderReader fr=new FolderReader(); 
		List<String> folders=fr.getFolders("MSA/"+fname);
		model.put("folders", folders);
		model.put("currentdir", "MSA/"+fname);
		return "ManageFolders";
	}
	
}
