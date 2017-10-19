package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.HistoryParking;
import models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO 
{
	private JdbcTemplate template;

	public JdbcTemplate getTemplate()
	{
		return template;
	}
	
	@Autowired
	public void setTemplate(JdbcTemplate template)
	{
		this.template = template;
	}
	
	public User SelectUser(String userId,String password)
	{
		User u= null;
		String sql="select * from PARKING_USERS where USER_ID=? and PASSWORD=?";
		try{
			u= template.queryForObject(sql, new UserMapper(), userId, password);
		}
		catch(EmptyResultDataAccessException e){
			u= null;
		}
		return u;
		
	}
	
	public boolean AddUser(User user)
	{
		String sql="insert into PARKING_USERS values(?,?,?,?)";
		String userId=user.getUid();
		String password=user.getPwd();
		String userRole=user.getUserRole();
		String userName=user.getUname();
		int count=template.update(sql,userId,password,userRole,userName);
		if(count>0)
			return true;
		return false;
	}
	
	public class UserMapper implements RowMapper<User>
	{
	
		@Override
		public User mapRow(ResultSet result, int rownum)throws SQLException
		{
			User user = new User();
			user.setUid(result.getString("USER_ID"));
			user.setPwd(result.getString("PASSWORD"));
			user.setUserRole(result.getString("USER_ROLE"));
			user.setUname(result.getString("USER_NAME"));
			return user;
		}
	}
	}
	