package dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import models.HistoryParking;
import models.Parking;

import org.omg.CORBA.DATA_CONVERSION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryDAO {
	private  JdbcTemplate template;

	public JdbcTemplate getTemplate() {
		return template;
	}
    @Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
    
	public boolean insert(HistoryParking hPark)
	{
		String sql="insert into PARKING_HISTORY values(?,?,?,?,?,?,?)";
		String vehicleRegNo=hPark.getPark().getVeh().getVehicleNo();
		System.out.println(vehicleRegNo);
		int slotId=hPark.getPark().getSlotId();
		Date entryTime=hPark.getPark().getEntryTime();
		System.out.println(entryTime);
		Date exitTime=hPark.getExitTime();
		System.out.println(exitTime);
		String ownerNo=hPark.getPark().getVeh().getOwnerNo();
		long  billAmoount=hPark.getBillAmount();
		String vehicleType=hPark.getPark().getVeh().getVehicleType();       
        int count=template.update(sql,vehicleRegNo,entryTime,exitTime,ownerNo,billAmoount,slotId,vehicleType);
        if(count>0)
    	   return true;
        return false;
	}
	
	public int dayAmount(Date date ,String vehicleType)
	{
		    String sql="select sum(BILL_AMOUNT)from PARKING_HISTORY where to_char(ENTRY_TIME, 'DD-MM-YY')=? and ENTRY_TIME=?";
		    SimpleDateFormat formatter = new SimpleDateFormat("DD-MM-YY");  
		    String sDate= formatter.format(date); 
	        int total= template.queryForObject(sql,Integer.class, sDate,vehicleType);	
	        return total;
	}
	
	public int monthlyAmount(Date date,String vehicleType)
	{
			String sql="select sum(BILL_AMOUNT)from PARKING_HISTORY where to_char(ENTRY_TIME, 'MM')=? and ENTRY_TIME=?";
			SimpleDateFormat formatt =new SimpleDateFormat("MM");
			String mDate=formatt.format(date);
			int total1 = template.queryForObject(sql,Integer.class,mDate,vehicleType);
			return total1;
	}
	
	public List<HistoryParking> ParkedList(Date date)
	{		 
		 SimpleDateFormat formatter = new SimpleDateFormat("DD-MM-YY");  
     	 String sDate= formatter.format(date); 
		 String sql="select * from PARKING_HISTORY where to_char(ENTRY_TIME, 'DD-MM-YY')=? ";
		 
		  return template.query(sql, new HistoryMapper(),sDate);
		 
	}
	
	  public List<HistoryParking> VehicleHistory(String vehicleNo)
		 {
		  String sql="select * from PARKING_HISTORY where VEHILCE_REG_NO=?";
		  return template.query(sql, new HistoryMapper(),vehicleNo);
		 }
	
	  private class HistoryMapper implements RowMapper<HistoryParking>
	     {
	    	 @Override
	    	 public HistoryParking mapRow(ResultSet result,int rownum) throws SQLException
	    	 {
	    		 HistoryParking hPark= new HistoryParking();
	    		 Parking p= new Parking();
	    		 hPark.setPark(p);
	    		 hPark.getPark().getVeh().setVehicleNo(result.getString("VEHILCE_REG_NO"));
	    		 hPark.getPark().setEntryTime(result.getDate("ENTRY_TIME"));
	    		 hPark.setExitTime(result.getDate("EXIT_TIME"));
	    		 hPark.getPark().getVeh().setOwnerNo(result.getString("OWNER_MOBILE"));
	    		 hPark.setBillAmount(result.getInt("BILL_AMOUNT"));
	    		 hPark.getPark().setSlotId(result.getInt("SLOT_ID"));
	    		 hPark.getPark().getVeh().setVehicleType(result.getString("SLOT_TYPE"));
	    		 return hPark;
	    	 }
	     }
}
