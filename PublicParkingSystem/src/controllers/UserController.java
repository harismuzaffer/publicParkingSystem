package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import models.HistoryParking;
import models.Parking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;

import services.Deallocate;
import dao.HistoryDAO;
import dao.ParkingDAO;
import dao.UserDAO;

@Controller
public class UserController {

	@Autowired
	ParkingDAO pdao;
	@Autowired
	HistoryDAO hdao;
	@Autowired
	Deallocate d;
	
	@RequestMapping("/allocateForm")
	public String allocateForm(){
		
		return "/user/allocateForm";
	}
	
	@RequestMapping(value="/allocate", method=RequestMethod.POST)
	public String allocate(@RequestParam("mno") String mno, @RequestParam("regno") String regno, @RequestParam("vtype") String vtype , HttpServletRequest request){
		
		String s= "Slot Allocated Successfully";
		String ss= "Allocation Failed";
		HttpSession session= request.getSession();
		boolean b= pdao.allocateParking(mno, regno, vtype);
		if(b)
			session.setAttribute("allocated", s);
		else
			session.setAttribute("allocated", ss);
		return "/user/allocated";
	}
	
	@RequestMapping("/deallocateForm")
	public String deallocateForm(){
		
		return "/user/deallocateForm";
	}
	
	@RequestMapping("/deallocate")
	public String deallocate(@RequestParam("slotid") int slotid, HttpServletRequest request){
		
		String s= "Slot Deallocated Successfully";
		String ss= "Deallocation Failed";
		HttpSession session= request.getSession();
		d.updateHistoryParking(slotid);
		boolean b= pdao.deallocateParking(slotid);
		if(b)
			session.setAttribute("deallocated", s);
		else
			session.setAttribute("deallocated", ss);
		return "/user/deallocated";
	}
	
	@RequestMapping(value="/checkSlot", method=RequestMethod.POST)
	public String checkSlot(@RequestParam(value="regno") String regno, HttpServletRequest request){
		
		Parking p= pdao.getParkInfo(regno);
		HttpSession session= request.getSession();
		session.setAttribute("parked", p);
		return "/user/checkSlot";
	}
	
	
}
