package com.ltts.project.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ltts.project.Dao.BookingDao;
import com.ltts.project.Dao.MovieDao;
import com.ltts.project.model.Booking;
import com.ltts.project.model.Member;
import com.ltts.project.model.Movie;
import com.ltts.project.model.Screen;

public class BookingController {
	@Autowired
	BookingDao bod;
	
	@RequestMapping(value="addbooking", method=RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest req, Model model) {
		ModelAndView mv=null;
		int bid=Integer.parseInt(req.getParameter("Bid"));
		int bseats=Integer.parseInt(req.getParameter("bseats"));
		String date = req.getParameter("date");
		String time = req.getParameter("time");
	
		int tprice=Integer.parseInt(req.getParameter("price"));
		
//		Movie mid=(Movie)req.getParameter("mid");
//		Screen sid=Integer.parseInt(req.getParameter("sid"));
//		Member uid=req.getParameter("uid");
		
	//	ApplicationContext ac=new ClassPathXmlApplicationContext();
		Booking book=new Booking(bid,bseats,date,time,tprice);
		System.out.println("***** INSIDE CONTROLLER ****"+book);
		boolean b=bod.InsertBooking(book);
		
		if(b==true) {
			mv=new ModelAndView("login");
			model.addAttribute("");
		}
		else {
			String message = "New user created successfully";
			mv=new ModelAndView("login");
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

	private Date Date(String parameter) {
		// TODO Auto-generated method stub
		return null;
	}

}
