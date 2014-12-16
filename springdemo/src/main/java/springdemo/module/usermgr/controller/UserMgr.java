package com.springdemo.module.usermgr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springdemo.core.ActionException;
import com.springdemo.core.BaseContoller;
import com.springdemo.core.ServiceException;
import com.springdemo.module.usermgr.service.UserMgrService;
import com.springdemo.module.usermgr.vo.SUser;

@Controller
@RequestMapping(value="usermgr")
public class UserMgr extends BaseContoller{
	@Autowired
	private UserMgrService userMgrService;
	//ע��
	@RequestMapping(value="signup.htm",method=RequestMethod.GET)
	public String signup(){
		return "/signup";
	}
	
	@RequestMapping(value="saveSignup.htm",method=RequestMethod.POST)
    public ModelAndView saveSignup(SUser user,SUser user2,String userName,String pwd) throws ServiceException{
		userMgrService.signUp(user);
		//�ɹ����ض���Ļ�ӭ���棬��ֹ�ظ��ύ
		return new ModelAndView("redirect:signup_succ/{userName}.htm","userName",userName);
    }
	//��ȡ
	@RequestMapping(value="get/{id}.htm",method=RequestMethod.GET)
    public ModelAndView get(@PathVariable Integer id) throws Exception{
		SUser sUser  = userMgrService.getUserById(id);
		 if(sUser==null){
			throw  new ActionException("�û�["+id+"]������!");
		 }
		return new ModelAndView("user_detail","user",sUser);
    }
	//�б�
	@RequestMapping(value="list.htm",method=RequestMethod.GET)
    public ModelAndView list() throws ServiceException{
		List<SUser> users  = userMgrService.getUsers();
		return new ModelAndView("user_list","users",users);
    }
	//ע��ɹ�
	@RequestMapping(value="signup_succ/{userName}.htm")
	public  String signuoSucc(@PathVariable("userName") String userName,ModelMap map){
		map.addAttribute("userName",userName);
		return "signup_succ";
	}
}
