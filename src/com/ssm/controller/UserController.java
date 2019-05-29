package com.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ssm.domain.Tutor;
import com.ssm.domain.User;
import com.ssm.service.HrmService;
import com.ssm.util.HrmConstants;
import com.ssm.util.PageModel;

/**
 * 处理用户请求控制器
 * */
@Controller
public class UserController {
	
	/**
	 * 自动注入hrmService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;

	/**
	 * 处理/login请求
	 * */
	@RequestMapping(value="/login")
	 public ModelAndView login(@RequestParam("name") String name, @RequestParam("eduid")String eduid, 
			 ModelAndView mv, HttpSession session){
		// 根据登录名和密码查找用户，判断用户登录
		User user = hrmService.login(name, eduid);
		if(user != null){
			// 登录成功，将user对象设置到HttpSession作用范围域
			session.setAttribute(HrmConstants.USER_SESSION, user);
			// 转发到main请求
			mv.setView(new RedirectView("/ssm_hrm/main"));
		}else{
			// 登录失败，设置失败提示信息，并跳转到登录页面
			mv.addObject("message", "登录名或密码错误，请重新输入!");
			mv.setViewName("loginForm");
		}
		return mv;
	}
	
	/**
	 * 1、点击主页面注销退出按钮时，执行此操作
	 * @param mv 注销后跳转的页面
	 * @param session 保存用户信息的session
	 * @return 返回登录界面
	 */
	@RequestMapping(value="/logout")
	public ModelAndView logout(ModelAndView mv, HttpSession session) {
		//注销session
		session.invalidate();
		//跳转到登录界面
		mv.setViewName("redirect:/loginForm");
		return mv;
	}
	
	/**
	 * 1、当点击网站首页时执行此函数
	 * @param mv 当前页面，然后返回主页面的初始界面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView homePage(ModelAndView mv) {
		//跳转到首页界面
		mv.setViewName("main");
		return mv;
	}
	
	/**
	 * 处理查询请求
	 * @param pageIndex 请求的是第几页
	 * @param tutor_id 导师编号
	 * @param user 模糊查询参数
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/user/selectUser")
	public String selectUser(Integer pageIndex, Integer tutor_id, @ModelAttribute User user, Model model) {
		//模糊查询时判断是否有关联对象传递，如果有，创建并封装关联对象
		this.genericAssociation(tutor_id, user);
		//创建分页对象
		PageModel pageModel = new PageModel();
		if(pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		//查询导师信息，用于模糊查询
		List<Tutor> tutors = hrmService.findAllTutor();
		//查询成员信息
		List<User> users = hrmService.findUser(user, pageModel);
		//设置Model数据
		model.addAttribute("users", users);
		model.addAttribute("tutors", tutors);
		model.addAttribute("pageModel", pageModel);
		
		//返回成员页面
		return "user/user";
	}
	
	/**
	 * 处理添加成员的请求
	 * @param flag 标记，1表示跳转到页面，2表示执行添加工作；
	 * @param tutor_id 导师编号
	 * @param user 接受添加的成员
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/user/addUser")
	public ModelAndView addUser(String flag, Integer tutor_id, @ModelAttribute User user, ModelAndView mv) {
		if(flag.equals("1")) {
			//查询导师信息
			List<Tutor> tutors = hrmService.findAllTutor();
			//设置Model数据
			mv.addObject("tutors", tutors);
			//返回添加成员页面
			mv.setViewName("/user/showAddUser");
		}else {
			//判断是否有关联对象传递，如果有，则创建关联对象
			this.genericAssociation(tutor_id, user);
			//执行添加操作
			hrmService.addUser(user);
			//设置客户端跳转到查询请求
			mv.setViewName("redirect:/user/selectUser");
		}
		return mv;
	}
	
	/**
	 * 处理删除成员请求
	 * @param ids 需要删除的id字符串
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/user/removeUser")
	public ModelAndView removeUser(String ids, ModelAndView mv) {
		//分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray) {
			//根据id删除成员
			hrmService.removeUserById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
//		mv.setView(new RedirectView("/ssm_hrm/user/selectUser"));
//		mv.setViewName("forward:/user/selectUser");
		mv.setViewName("redirect:/user/selectUser");
		// 返回ModelAndView
		return mv;
	}
	
	/**
	 * 处理修改成员请求
	 * @param flag 标记，1表跳转到修改页面，2表示执行修改操作
	 * @param tutor_id 导师id，
	 * @param user 要修改的成员对象，
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/user/updateUser")
	public ModelAndView updateUser(String flag, Integer tutor_id, 
			@ModelAttribute User user, ModelAndView mv) {
		if(flag.equals("1")) {
			//根据id查询成员
			User target = hrmService.findUserById(user.getId());
			System.out.println(target);
			//需要查询的导师信息
			List<Tutor> tutors = hrmService.findAllTutor();
			//设置Model对象
			mv.addObject("user", target);
			mv.addObject("tutors", tutors);
			//返回修改成员页面
			mv.setViewName("/user/showUpdateUser");
		}else {
			//创建并封装关联对象
			this.genericAssociation(tutor_id, user);
			System.out.println("你是猴子吗？updateUser -->> " + user);
			//执行修改操作
			hrmService.modifyUser(user);
			//设置客户端跳转到查询请求
			mv.setViewName("redirect:/user/selectUser");
		}
		
		return mv;
	}
	
	/**
	 * 由于导师在User中是对象关联映射，
	 * 所以不能直接接收参数，需要创建Tutor对象
	 * */
	public void genericAssociation(Integer tutor_id, User user) {
		if(tutor_id != null) {
			Tutor tutor = new Tutor();
			tutor.setId(tutor_id);
			user.setTutor(tutor);
			System.out.println("haha" + user.getTutor());
		}
	}
}
