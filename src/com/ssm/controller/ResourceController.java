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

import com.ssm.domain.Resource;
import com.ssm.domain.User;
import com.ssm.service.HrmService;
import com.ssm.util.PageModel;

@Controller
public class ResourceController {

	
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/**
	 * 判断是否有关联对象映射
	 * @param user_id
	 * @param notice
	 */
	public void genericAssociation(Integer user_id, Resource resource) {
		if(user_id != null) {
			User user = new User();
			user.setId(user_id);
			resource.setUser(user);
		}	
	}
	
	/**
	 * 处理查询设备预约信息请求
	 */
	@RequestMapping(value = "/resource/selectResource")
	public String selectResource(Model model, Integer pageIndex, Integer user_id,
			@ModelAttribute Resource resource) {
		this.genericAssociation(user_id, resource);
		PageModel pageModel = new PageModel();
		if(pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		List<User> users = hrmService.findAllUser();
		List<Resource> resources = hrmService.findResource(resource, pageModel);
		model.addAttribute("users", users);
		model.addAttribute("resources", resources);
		model.addAttribute("pageModel", pageModel);
		return "resource/resource";
	}
	
	/**
	 * 处理添加公告的请求
	 */
	@RequestMapping(value = "/resource/addResource")
	public ModelAndView addResource(String flag, Integer user_id, ModelAndView mv,
			@ModelAttribute Resource resource, HttpSession session) {
		if(flag.equals("1")) {
			List<User> users = hrmService.findAllUser();
			mv.addObject("users", users);
			mv.setViewName("resource/showAddResource");
		}else {
			this.genericAssociation(user_id, resource);
			hrmService.addResource(resource);
			mv.setViewName("redirect:/resource/selectResource");
		}
		return mv;
	}
	
	/**
	 * 处理删除设备请求信息
	 */
	@RequestMapping(value = "/resource/removeResource")
	public ModelAndView removeResource(String ids, ModelAndView mv) {
		String[] idArray = ids.split(",");
		for(String id:idArray) {
			hrmService.removeResourceById(Integer.parseInt(id));
		}
		
		mv.setViewName("redirect:/resource/selectResource");
		return mv;
	}
	
	/**
	 * 处理修改设备预约信息
	 */
	@RequestMapping(value="/resource/updateResource")
	public ModelAndView updateResource(String flag, Integer user_id, 
			@ModelAttribute Resource resource, ModelAndView mv) {
		if(flag.equals("1")) {
			Resource target = hrmService.findResourceById(resource.getId());
			List<User> users = hrmService.findAllUser();
			//设置Model对象
			mv.addObject("resource", target);
			mv.addObject("users", users);
			mv.setViewName("/resource/showUpdateResource");
		}else {
			//创建并封装关联对象
			this.genericAssociation(user_id, resource);
			//执行修改操作
			hrmService.modifyResource(resource);
			//设置客户端跳转到查询请求
			mv.setViewName("redirect:/resource/selectResource");
		}
		
		return mv;
	}
}
