package org.pushio.webapp.ctrl;

import java.util.Date;

import org.pushio.webapp.helper.Constants;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
/**
 * 
* @ClassName: BaseController
* @Description: Controller父类
* @author 赵宝东
* @date 2015年3月2日 下午1:19:01
*
 */
public class BaseController {
	
    @InitBinder    
    public void initBinder(WebDataBinder binder) throws Exception {
        CustomDateEditor dateEditor = new CustomDateEditor(Constants.DF, true);    
        binder.registerCustomEditor(Date.class, dateEditor);        
    }   

}
