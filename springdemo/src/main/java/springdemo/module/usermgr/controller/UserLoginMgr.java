package com.springdemo.module.usermgr.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
/**
 * �û���¼����
 * @author joe
 * @date  2011-10-24 ����03:34:39
 */
@Controller
public class UserLoginMgr extends BaseContoller{
	@Autowired
	private UserMgrService userMgrService;
	
	@RequestMapping(value="saveLogin.htm",method=RequestMethod.POST)
    public ModelAndView saveLogin(HttpSession session,SUser user,ModelMap modelMap){
		if(user!=null){
			String userName = user.getUserName();
			try {
				SUser dbuser = userMgrService.getUserByUserName(userName);
				if(dbuser==null){
					//�ɹ����ض���Ļ�ӭ���棬��ֹ�ظ��ύ
					return new ModelAndView("login",COMMON_FAIL_ALERT_KEY,"�û�������!");
				}
				if(!dbuser.getPwd().equals(user.getPwd())){
					
					return new ModelAndView("login",COMMON_FAIL_ALERT_KEY,"�������!");
				}
				
				session.setAttribute(USER_SESSION_KEY, dbuser);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		//�ɹ����ض���Ļ�ӭ���棬��ֹ�ظ��ύ
		return new ModelAndView("redirect:login_succ.htm");
    }
	//��ȡ
	@RequestMapping(value="get/{id}.htm",method=RequestMethod.GET)
    public ModelAndView get(@PathVariable Integer id) throws Exception{
		SUser sUser = userMgrService.getUserById(id);
		 if(sUser==null){
			throw  new ActionException("�û�["+id+"]������!");
		 }
		return new ModelAndView("user_detail","user",sUser);
    }
	//�б�
	@RequestMapping(value="list.htm",method=RequestMethod.GET)
    public ModelAndView list() throws ServiceException{
		List<SUser> users = userMgrService.getUsers();
		return new ModelAndView("user_list","users",users);
    }
	//ע��ɹ�
	@RequestMapping(value="login_succ.htm")
	public  String signuoSucc(HttpSession session,ModelMap map){
		SUser user = (SUser) session.getAttribute(USER_SESSION_KEY);
		if(user==null){
			return "redirect:login.htm";
		}
		map.put("user", user);
		return "index";
	}
	@RequestMapping("logout.htm")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:login.htm";
	}
	//��ҳ
	@RequestMapping(value="index.htm")
	public  String index(){
		return "forward:login_succ.htm";
	}
}
