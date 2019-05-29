package com.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.domain.Service;
import com.ssm.domain.User;
import com.ssm.service.HrmService;
import com.ssm.util.PageModel;

@Controller
public class ServiceController {

	/**
	 * 自动注入HrmService
	 * 自动装配，其作用是为了消除代码Java代码里面的getter/setter与bean属性中的property。
	 */
	@Autowired
	@Qualifier("hrmService") //指定注入Bean的名称
	private HrmService hrmService;
	
	/**
	 * 由于服务规则库和用户是对象关联映射，
	 * 所以不能直接接收参数，需要创建Service和User对象。
	 * */
	public void genericAssociation(Integer user_id, Service service) {
		if(user_id != null) {
			User user = new User();
			user.setId(user_id);
			service.setUser(user);
		}	
	}
	
	/**
	 * 在网页上点击查询服务规则，会调用此过程
	 * @param model
	 * @param pageIndex
	 * @param user_id
	 * @param service
	 * @return
	 */
	@RequestMapping(value="/service/selectService")
	public String SelectService(Model model, Integer pageIndex, Integer user_id,
			@ModelAttribute Service service) {
		//判断是否有关联对象传递
		this.genericAssociation(user_id, service);
		//创建分页查询对象
		PageModel pageModel = new PageModel();
		if(pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		List<User> users = hrmService.findAllUser();
		
		//查询服务规则，每四个规则一页
		List<Service> services = hrmService.findService(service, pageModel);
		model.addAttribute("users", users);
		model.addAttribute("services", services);
		model.addAttribute("pageModel", pageModel);
		
		return "service/service";
	}
	
	/**
	 * 处理添加服务规则的请求
	 */
	@RequestMapping(value="/service/addService")
	 public ModelAndView addService(String flag, Integer user_id,
			 @ModelAttribute Service service, ModelAndView mv){
		if(flag.equals("1")){
			// 查询职位信息
			List<User> users = hrmService.findAllUser();
			// 设置Model数据
			mv.addObject("users", users);
			// 返回添加员工页面
			mv.setViewName("service/showAddService");
		}else{
			// 判断是否有关联对象传递，如果有，创建关联对象
			this.genericAssociation(user_id, service);
			// 添加操作
			hrmService.addService(service);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/service/selectService");
		}
		// 返回
		return mv;
	}
	
	/**
	 * 处理删除服务 规则的请求
	 */
	@RequestMapping(value="service/removeService")
	public ModelAndView removeService(String ids, ModelAndView mv) {
		String[] idArray = ids.split(",");
		for(String id:idArray) {
			hrmService.removeServiceById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/service/selectService");
		return mv;
	}
	
	/**
	 * 对服务规则不满意，进行更改
	 */
	@RequestMapping("/service/updateService")
	public ModelAndView updateService(String flag, Integer user_id, ModelAndView mv,
			@ModelAttribute Service service, HttpSession session) {
		if(flag.equals("1")) {
			Service target = hrmService.findServiceById(service.getId());
			List<User> users = hrmService.findAllUser();
			mv.addObject("users", users);
			mv.addObject("service", target);
			mv.setViewName("/service/showUpdateService");
		}else {
			this.genericAssociation(user_id, service);
			hrmService.modifyService(service);
			mv.setViewName("redirect:/service/selectService");
		}
		return mv;
	}
	
}
