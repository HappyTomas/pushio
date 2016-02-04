package org.pushio.webapp.ctrl.example;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.pushio.webapp.ctrl.BaseController;
import org.pushio.webapp.entity.example.Twitter;
import org.pushio.webapp.helper.servlet.Servlets;
import org.pushio.webapp.service.example.TwitterService;
import org.pushio.webapp.support.PageRequest;
import org.pushio.webapp.support.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/twitter")
public class TwitterCtrl extends BaseController{
	
	@Autowired
	TwitterService twitterService;
	
	@RequestMapping(value = "/find")
	@ResponseBody
	public Response findPage(ServletRequest request,PageRequest pageRequest, Response response) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "");
		Page<Twitter> page = twitterService.findPage(searchParams, pageRequest);
		response.setFieldPaths(new String[]{
			"addUser"
		});
		response.setData(page  );
		return response;
	}
}
