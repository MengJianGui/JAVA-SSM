package com.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.domain.Tutor;
import com.ssm.service.HrmService;
import com.ssm.util.PageModel;

@Controller
public class TutorController {

	/**
	 * 自动注入UserService
	 */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/**
	 * 查询所有导师
	 */
	@RequestMapping(value="/tutor/selectTutor")
	public String selectTutor(Model model, Integer pageIndex, @ModelAttribute Tutor tutor) {
		PageModel pageModel = new PageModel();
		if(pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		//查询所有用户信息
		List<Tutor> tutors = hrmService.findTutor(tutor, pageModel);
		for(Tutor element : tutors) {
			System.out.println(element);
		}
		model.addAttribute("tutors", tutors);
		model.addAttribute("pageModel", pageModel);
		
		return "tutor/tutor";
	}
	
	/**
	 * 处理删除导师请求
	 */
	@RequestMapping(value="/tutor/removeTutor")
	public  ModelAndView removeTutor(String ids, ModelAndView mv) {
		String[] idArray = ids.split(",");
		for(String id : idArray) {
			hrmService.removeTutorById(Integer.parseInt(id));
		}
		
		mv.setViewName("redirect:/tutor/selectTutor");
		return mv;
	}
	
	/**
	 * 处理添加请求
	 */
	@RequestMapping(value="/tutor/addTutor")
	public ModelAndView addTutor(String flag, @ModelAttribute Tutor tutor, ModelAndView mv) {
		if(flag.equals("1")) {
			mv.setViewName("/tutor/showAddTutor");
		}else {
			hrmService.addTutor(tutor);
			mv.setViewName("redirect:/tutor/selectTutor");
		}
		
		return mv;
	}
	
	/**
	 * 处理修改导师请求
	 */
	@RequestMapping(value="/tutor/updateTutor")
	public ModelAndView updateTutor(String flag, @ModelAttribute Tutor tutor, ModelAndView mv) {
		if(flag.equals("1")) {
			Tutor target = hrmService.findTutorById(tutor.getId());
			mv.addObject("tutor", target);
			mv.setViewName("/tutor/showUpdateTutor");
		}else {
			hrmService.modifyTutor(tutor);
			mv.setViewName("redirect:/tutor/selectTutor");
		}
		
		return mv;
	}
}
