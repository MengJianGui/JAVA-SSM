package com.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.domain.Alarm;
import com.ssm.service.HrmService;
import com.ssm.util.PageModel;

@Controller
public class AlarmController {

	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/**
	 * 查询所有定时服务
	 */
	@RequestMapping(value="/alarm/selectAlarm")
	public String selectAlarm(Model model, Integer pageIndex, @ModelAttribute Alarm alarm) {
		PageModel pageModel = new PageModel();
		if(pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		//查询所有定时服务信息
		List<Alarm> alarms = hrmService.findAlarm(alarm, pageModel);
		model.addAttribute("alarms", alarms);
		model.addAttribute("pageModel", pageModel);
		
		return "alarm/alarm";
	}
	
	/**
	 * 处理删除定时服务请求
	 */
	@RequestMapping(value="/alarm/removeAlarm")
	public  ModelAndView removeAlarm(String ids, ModelAndView mv) {
		String[] idArray = ids.split(",");
		for(String id : idArray) {
			hrmService.removeAlarmById(Integer.parseInt(id));
		}
		
		mv.setViewName("redirect:/alarm/selectAlarm");
		return mv;
	}
	
	/**
	 * 处理添加请求
	 */
	@RequestMapping(value="/alarm/addAlarm")
	public ModelAndView addAlarm(String flag, @ModelAttribute Alarm alarm, ModelAndView mv) {
		if(flag.equals("1")) {
			mv.setViewName("/alarm/showAddAlarm");
		}else {
			hrmService.addAlarm(alarm);
			mv.setViewName("redirect:/alarm/selectAlarm");
		}
		
		return mv;
	}
	
	/**
	 * 处理修改定时服务请求
	 */
	@RequestMapping(value="/alarm/updateAlarm")
	public ModelAndView updateAlarm(String flag, @ModelAttribute Alarm alarm, ModelAndView mv) {
		if(flag.equals("1")) {
			Alarm target = hrmService.findAlarmById(alarm.getId());
			mv.addObject("alarm", target);
			mv.setViewName("/alarm/showUpdateAlarm");
		}else {
			hrmService.modifyAlarm(alarm);
			mv.setViewName("redirect:/alarm/selectAlarm");
		}
		
		return mv;
	}
}
