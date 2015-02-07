package com.ibao.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CodeController {
	
	@RequestMapping(value={"/admin/code.html", "/admin/code.do"})
	public String index(){
		return "admin/code/index";
	}
	

}
