package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import models.User;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.UserDAO;

@Controller
public class LoginController {
	
	@Autowired
	UserDAO udao;
	
	@RequestMapping(value="/validate", method=RequestMethod.POST )
	public String validate(HttpServletRequest request, @RequestParam("uid")String uid, @RequestParam("pwd")String pwd){
		
		User u= udao.SelectUser(uid, pwd);
		HttpSession s= request.getSession();
		if(u!=null){
			s.setAttribute("user", uid);
			if(u.getUserRole().equals("admin"))
				return "/admin/admin";
			else if(u.getUserRole().equals("user"))
				return "/user/user";
		}
		String msg= "username or password is not correct";
		s.setAttribute(msg, msg);
		return "/login/login";
	}
	
}
