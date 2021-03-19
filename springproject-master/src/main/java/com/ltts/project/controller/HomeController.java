package com.ltts.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ltts.project.Dao.MemberDao;
import com.ltts.project.Dao.MovieDao;
import com.ltts.project.model.Member;
import com.ltts.project.model.Movie;

@RestController
public class HomeController {
	
	@Autowired
	MemberDao md;
	
	@Autowired
	MovieDao mod;
	
	@RequestMapping("")
	public ModelAndView secondMethod() {
		return new ModelAndView("index");
	}
	
	@RequestMapping("/registration")
	public ModelAndView registerUser() {
		return new ModelAndView("registration");
	}
	
	@RequestMapping("/login")
	public ModelAndView loginuser() {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="adduser", method=RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest req, Model model) {
		ModelAndView mv=null;
		String name=req.getParameter("uname");
		String pass=req.getParameter("pass");
		String email=req.getParameter("email");
		String mobile=req.getParameter("mobile");
		
	//	ApplicationContext ac=new ClassPathXmlApplicationContext();
		Member m=new Member(name,pass,email,mobile);
		System.out.println("***** INSIDE CONTROLLER ****"+m);
		boolean b=md.InsertMember(m);
		
		if(b==false) {
			mv=new ModelAndView("login");
			model.addAttribute("");
		}
		else {
			String message = "New user created successfully";
			mv=new ModelAndView("error");
			model.addAttribute("msg",message );
			
		}
		/*
		 * try { b=md.InsertMember(m); mv=new ModelAndView("success"); } catch(Exception
		 * e) {
		 * 
		 * mv=new ModelAndView("error"); }
		 */
		
		
		return mv;
	}

	
	@RequestMapping(value="checkuser", method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest req, Model model) {
		ModelAndView mv=null;
		
		
		
		String email=req.getParameter("email");
		String pass=req.getParameter("pass");
		
		Member m =md.checkMember(email);
	//	Movie mov= mod.getAllMovies()
        List<Movie> li =mod.getAllMovies();
		
		model.addAttribute("list", li);
		
		//return mv;
	
		
		
		if(m.getPassword().equals(pass)){
			model.addAttribute("name",m.getUserName());
			mv=new ModelAndView("addMovie");
			 //List<Movie> li =mod.getAllMovies();
				
			
				model.addAttribute("list", li);
				
				//return mv;
			
			
		}
			else {
				mv=new ModelAndView("error");
				model.addAttribute("msg", "Incorrect credentials");
				
			}
		
		return mv;
			
		}
	
}
