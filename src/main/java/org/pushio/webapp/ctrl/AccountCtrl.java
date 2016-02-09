package org.pushio.webapp.ctrl;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.pushio.webapp.entity.base.Account;
import org.pushio.webapp.helper.servlet.Servlets;
import org.pushio.webapp.service.AccountService;
import org.pushio.webapp.support.PageRequest;
import org.pushio.webapp.support.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountCtrl extends BaseController{
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping(value = "/find")
	@ResponseBody
	public Response findPage(ServletRequest request,PageRequest pageRequest, Response response) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "");
		Page<Account> page = accountService.findPage(searchParams, pageRequest);
		response.setFieldPaths(new String[]{
			"addUser"
		});
		response.setData(page  );
		return response;
	}
}
