package services;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dao.HistoryDAO;
import dao.ParkingDAO;
import models.HistoryParking;
import models.Parking;

@Controller
public class Deallocate {
	
	@Autowired
	HistoryDAO hdao;
	@Autowired
	ParkingDAO pdao;
	
	public void updateHistoryParking(int slotId){
		
		Parking p= pdao.getParkInfoBySlot(slotId);
		Date entryTime= p.getEntryTime();
		HistoryParking hp= new HistoryParking();
		hp.setPark(p);		
		Date current = new Date();
		hp.setExitTime(new Timestamp(current.getTime()));
		long amount= ((current.getTime()-entryTime.getTime())/216000000)*10;
		hp.setBillAmount(amount);
		hdao.insert(hp);
		
	}

}
