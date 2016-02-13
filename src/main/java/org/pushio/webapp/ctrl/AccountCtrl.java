package org.pushio.webapp.ctrl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.pushio.webapp.entity.base.Account;
import org.pushio.webapp.entity.base.Employee;
import org.pushio.webapp.helper.hash.MD5YHWL;
import org.pushio.webapp.helper.servlet.Servlets;
import org.pushio.webapp.service.AccountService;
import org.pushio.webapp.support.PageRequest;
import org.pushio.webapp.support.Response;
import org.pushio.webapp.support.ResponseFactory;
import org.pushio.webapp.support.StatusCode;
import org.pushio.webapp.vo.CurrentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountCtrl extends BaseController{
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	EmployeeSer accountService;
	
	@RequestMapping(value = "/find")
	@ResponseBody
	public Response findPage(ServletRequest request,PageRequest pageRequest, Response response,HttpSession session ) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "");
		Page<Account> page = accountService.findPage(searchParams, pageRequest);
		response.setFieldPaths(new String[]{
			"addUser"
		});
		response.setData(page);
		return response;
	}
	
	
	@RequestMapping(value = "/getOne")
	@ResponseBody
	public Response getOne(long id,Response response) {
		response.setData(accountService.getOne(id));
		return response;
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Response delete(Long[] ids,Response response ) {
		try {
			response.setMessage("删除成功");
			if (ids.length > 1) {
				accountService.deletes(ids);
			} else if (ids.length == 1) {
				accountService.delete(ids[0]);
			} else if (ids.length == 0) {
				throw new Exception("ids数组为空好吗");
			}
		} catch (EmptyResultDataAccessException ee) {
			response.setErrorcode(StatusCode.FAIL);
			response.setMessage("这行数据已经不存在了");
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorcode(StatusCode.SYS);
			response.setMessage("删除失败");
		}
		;

		return response;
	}

	/**
	 * 新增或修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "/save.do")
	@ResponseBody
	public Response save(Account account,Response response) {
		Subject currentUser = SecurityUtils.getSubject();
		CurrentInfo currentInfo = (CurrentInfo) currentUser.getSession()
				.getAttribute("currentInfo");
		response.setData(accountService.save(account, currentInfo));
		return response;

	}

	/**
	 * 激活一个账号 随机生成密码发送到邮箱 使用中文名转拼音作为账号名
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/createAccount")
	@ResponseBody
	public Response createAccount(long employeeId) {

		Account account = null;
		Subject currentUser = SecurityUtils.getSubject();
		CurrentInfo currentInfo = (CurrentInfo) currentUser.getSession()
				.getAttribute("currentInfo");
		Response response = ResponseFactory.getResponse();
		response.setMessage("激活成功");
		Employee employee = employeeService.getOne(employeeId);

		// 作为saveEmployeeAccout回调MAP,用于获取用户名和明文密码
		Map<String, Object> params = new HashMap<String, Object>(2);
		try {
			if (employee.getEmail() != null) {// 如果拥有邮箱，那么可以搞下去
				account = accountService.saveEmployeeAccout(employeeId,
						currentInfo, params);

				if (account != null && account.getId() != null) {
					String sender = "华正大数据";
					String subject = "华正大数据提醒您,您的账号已经创建成功.";
					String mailKey = MD5YHWL.MD5(account.getId()+account.getLoginName()+System.currentTimeMillis());
					keyValueRedisService.set(mailKey, params,259200l);
					String url = PlatformConfiguration.config.getString("visitUri")+"/dodo.do?s="+mailKey;
					String text = "您的账号是:"+ account.getLoginName() + 
					"</br>您的密码是:"+ params.get("password") +
					"</br>有效期为72个小时,请尽快登录平台修改密码并且绑定您的个人手机号码"+ 
					"登录地址为："+url;
					mailSendService.sendTextMail(employee.getName(),
							employee.getEmail(), sender, subject,  text);

				}else{
					response.setMessage("帐号已激活");
				}

			}

			else {// 没有邮箱的员工88
				response.setMessage("没有邮箱的员工无法激活帐号！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorcode(ErrorCode.SYS);
			response.setMessage("激活失败");
		}

		return response;

	}
}
