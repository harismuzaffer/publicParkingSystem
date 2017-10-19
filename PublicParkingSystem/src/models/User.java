package models;

public class User 
{
	private String uid;
	private String uname;
	private String pwd;
	private String userRole;
	
	
	public String getUid()
	{
		return uid;
	}
	public String getUname() 
	{
		return uname;
	}
	public String getPwd() 
	{
		return pwd;
	}
	public String getUserRole() 
	{
		return userRole;
	}
	public void setUid(String userId) 
	{
		this.uid = userId;
	}
	public void setUname(String userName) 
	{
		this.uname = userName;
	}
	public void setPwd(String password) 
	{
		this.pwd = password;
	}
	public void setUserRole(String userRole) 
	{
		this.userRole = userRole;
	}	
}
