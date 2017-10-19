package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import models.Parking;
import models.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import daoInterfaces.ParkingDao;

@Repository
public class ParkingDAO implements ParkingDao {

	@Autowired
	JdbcTemplate template;

	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public boolean allocateParking(String mno, String regno, String vtype){
		
		String s= "update parking_current set vehicle_reg_no=?, owner_mobile=?,"
				+ "entry_time=current_timestamp where slot_id=(select min(slot_id) "
				+ "from parking_current where owner_mobile is null and slot_type=?" 
				+ "and (owner_mobile!='block' or owner_mobile is null))";
		int c= template.update(s, mno, regno, vtype);
		if(c==1)
			return true;
		else
			return false;
	}
	
	public boolean addNewSlot(String vehicleType){
		String s= "insert into current_parking values((select max(slot_Id)"
				+ "from parking_current where slot_type=?),?,null,null,null)";
		int c= template.update(s, vehicleType, vehicleType);
		if(c==1)
			return true;
		else
			return false;
		
	}
	
	public boolean Block(int slotID){
		String s= "update current_parking set owner_mobile='null' "
				+ "where slot_type=?)";
		int c= template.update(s, slotID);
		if(c==1)
			return true;
		else
			return false;
		
	}
	
	public Parking getParkInfo(String vehicleRegNo){
		String s= "select *from parking_current where vehicle_reg_no= ?";
		Parking p= template.queryForObject(s, new ParkingMapper(), vehicleRegNo);
		return p;
		
	}
	
	public Parking getParkInfoBySlot(int slotId){
		String s= "select *from parking_current where slot_id= ?";
		Parking p= template.queryForObject(s, new ParkingMapper(), slotId);
		return p;
		
	}
	
	public boolean deallocateParking(int slotID) {
		String s= "update parking_current set vehicle_reg_no=null,"
				+ "owner_mobile=null, entry_time=null where slot_id=?";
		int c= template.update(s, slotID);
		if(c==1)
			return true;
		else
			return false;
		
	}
	
	public int totalSlots(String vehicleType){
		
		String s= "select count(*) from parking_current where slot_type= ?";
		int c= template.update(s, vehicleType);
		return c;
	}
	
	public int availableSlots(String vehicleType){
		String s= "select count(*) from parking_current"
				+ "where slot_type=? and owner_mobile!=null";
		int c= template.update(s, vehicleType);
		return c;
		
	}
	
	private static class ParkingMapper implements RowMapper<Parking>{

		@Override
		public Parking mapRow(ResultSet r, int row) throws SQLException {
			Parking p= new Parking();
			Vehicle v= new Vehicle();
			p.setVeh(v);
			p.setSlotId(r.getInt("slot_id"));
			p.setEntryTime(r.getTimestamp("entry_time"));
			p.getVeh().setOwnerNo(r.getString("owner_mobile"));
			p.getVeh().setVehicleNo(r.getString("vehicle_reg_no"));
			p.getVeh().setVehicleType(r.getString("slot_type"));
			p.setSlotId(r.getInt("slot_id"));
			return p;	
		}	
	}	
}
