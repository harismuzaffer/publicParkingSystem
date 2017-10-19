package daoInterfaces;

import models.Parking;

public interface ParkingDao {
	
	public boolean allocateParking(String mno, String regno, String vtype);
	
	public boolean addNewSlot(String vehicleType);
	
	public boolean Block(int slotID);
	
	public Parking getParkInfo(String vehicleRegNo);
	
	public boolean deallocateParking(int slotID);
	
	public int totalSlots(String vehicleType);
	
	public int availableSlots(String vehicleType);
}
