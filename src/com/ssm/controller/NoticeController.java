package com.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ssm.util.HrmConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.domain.Notice;
import com.ssm.domain.User;
import com.ssm.service.HrmService;
import com.ssm.util.PageModel;

@Controller
public class NoticeController {

	/**
	 * 自动注入HrmService
	 */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/**
	 * 处理查询公告请求，在网站页面上点击公告查询，然后会调用此过程；
	 * @param model
	 * @param pageIndex
	 * @param user_id 学生id
	 * @param tutor_id 导师id
	 * @param notice
	 * @return
	 */
	@RequestMapping(value = "/notice/selectNotice")
	public String selectNotice(Model model, Integer pageIndex, Integer user_id,
			@ModelAttribute Notice notice) {
		//判断是否有关联对象传递
		this.genericAssociation(user_id, notice);
		//创建分页查询对象
		PageModel pageModel = new PageModel();
		if(pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		//查询公告发布者的信息，要么是学生
		List<User> users = hrmService.findAllUser();
		
		//查询公告信息,并且是分页展示的，每四个一页；
		List<Notice> notices = hrmService.findNotice(notice, pageModel);
		
		model.addAttribute("users", users);
		model.addAttribute("notices", notices);
		model.addAttribute("pageModel", pageModel);
		
		return "notice/notice";
	}
	
	/**
	 * 由于公告和发布者（导师或者学生）是对象关联映射，
	 * 所以不能直接接收参数，需要创建Tutor和User对象。
	 * */
	public void genericAssociation(Integer user_id, Notice notice) {
		if(user_id != null) {
			User user = new User();
			user.setId(user_id);
			notice.setUser(user);
		}	
	}
	
	/**
	 * 执行预览公告的功能
	 * @param id 要显示公告的id；
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/notice/previewNotice")
	public String previewNotice(Integer id, Model model) {
		Notice notice = hrmService.findNoticeById(id);
		
		model.addAttribute("notice", notice);
		
		return "notice/previewNotice";
	}
	
	/**
	 * 处理添加公告请求
	 * @param flag 标记，1表示跳转到添加页面，2表示执行添加操作；
	 * @param user_id
	 * @param tutor_id
	 * @return
	 */
	@RequestMapping(value="/notice/addNotice")
	public ModelAndView addNotice(String flag, Integer user_id, ModelAndView mv, 
				@ModelAttribute Notice notice,HttpSession session) {
		if(flag.equals("1")) {
			//查询发布者信息
			List<User> users = hrmService.findAllUser();
			mv.addObject("users", users);
			
			mv.setViewName("notice/showAddNotice");
		}else {
			/**
			 * 1、这一步很关键，因为jsp页面并没传回user_id的信息，不像添加员工的时候，手动添加了员工id，因此不能用之前添加员工的方法；
			 * 2、但是可以根据登录信息来确定公告发布者的信息，就用到了下面这一语句。
			 */
			User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
			notice.setUser(user);
			hrmService.addNotice(notice);
			mv.setViewName("redirect:/notice/selectNotice");
		}
		return mv;
	}
	
	/**
	 * 处理删除公告请求
	 */
	@RequestMapping(value="/notice/removeNotice")
	public ModelAndView removeNotice(String ids, ModelAndView mv) {
		String[] idArray = ids.split(",");
		for(String id : idArray) {
			//根据id删除公告
			hrmService.removeNoticeById(Integer.parseInt(id));
		}
		
		mv.setViewName("redirect:/notice/selectNotice");
		return mv;
	}
	
	/**
	 * 执行更新公告的功能
	 */
	@RequestMapping(value="/notice/updateNotice")
	public ModelAndView updateNotice(String flag, Integer user_id, 
			@ModelAttribute Notice notice, ModelAndView mv, HttpSession session) {
		
		if(flag.equals("1")) {
			//根据id查询公告
			Notice target = hrmService.findNoticeById(notice.getId());
			//需要查询的发布人的信息
			List<User> users = hrmService.findAllUser();
			mv.addObject("users", users);
			mv.addObject("notice", target);
			mv.setViewName("/notice/showUpdateNotice");
		}else {
			this.genericAssociation(user_id, notice);
			hrmService.modifyNotice(notice);
			mv.setViewName("redirect:/notice/selectNotice");
		}
		return mv;
	}
	
}
