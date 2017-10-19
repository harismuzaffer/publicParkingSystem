package models;

import java.util.Date;

import org.omg.CORBA.DATA_CONVERSION;

public class HistoryParking {
	private Date exitTime;
	private long billAmount;
	private Parking park;
	
	public Date getExitTime() {
		return exitTime;
	}
	public long getBillAmount() {
		return billAmount;
	}
	public Parking getPark() {
		return park;
	}
	public void setExitTime(Date exitTime) {
		this.exitTime = exitTime;
	}
	public void setBillAmount(long billAmount) {
		this.billAmount = billAmount;
	}
	public void setPark(Parking park) {
		this.park = park;
	}

}
