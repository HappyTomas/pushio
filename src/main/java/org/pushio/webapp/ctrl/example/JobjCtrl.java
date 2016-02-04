package org.pushio.webapp.ctrl.example;

import org.pushio.webapp.ctrl.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/obj")
public class JobjCtrl extends BaseController{
	
	
	@RequestMapping("/get/{id}")
	public String index(String id){
		return "hello";
	}
}
