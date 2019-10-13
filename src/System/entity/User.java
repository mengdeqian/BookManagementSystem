package System.entity;

import java.io.Serializable;
import java.util.Date;

import System.auth.Role;

public class User implements Serializable{
	private static final long serialVersionUID = 1641287366256499144L;
	
	private String userName;
	private String password;
	private Role role;				//当前用户的角色
	private Date lastedLoginTime;	//上次登录时间
	
	public User(){
		role = new Role(); //默认user，系统就分配一个默认角色
	}
	public User(Role role){
		if(null == role){
			role = new Role();
		}
		setRole(role);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(null == obj) return false;
		if(!(obj instanceof User)) return false;
		User user = (User)obj;
		return userName.equals(user.getUserName()) && password.equals(user.getPassword());
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Date getLastedLoginTime() {
		return lastedLoginTime;
	}
	public void setLastedLoginTime(Date lastedLoginTime) {
		this.lastedLoginTime = lastedLoginTime;
	}
	
	
	
}
