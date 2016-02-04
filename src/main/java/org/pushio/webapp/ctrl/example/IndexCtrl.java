package org.pushio.webapp.ctrl.example;

import org.pushio.webapp.ctrl.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexCtrl extends BaseController{
	
	
	@RequestMapping("/")
	public String index(){
		return "hello";
	}
}
