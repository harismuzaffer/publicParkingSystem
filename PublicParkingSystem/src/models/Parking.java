package models;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

public class Parking {

	private int slotId;
	private Date entryTime;
	@Autowired
	private Vehicle veh;
	
	public int getSlotId() {
		return slotId;
	}
	
	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}
	
	public Date getEntryTime() {
		return entryTime;
	}
	
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	
	public Vehicle getVeh() {
		return veh;
	}
	
	public void setVeh(Vehicle veh) {
		this.veh = veh;
	}
	
	
}
