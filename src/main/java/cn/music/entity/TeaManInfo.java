package cn.music.entity;

import java.util.Date;

public class TeaManInfo {
	private int teaManId;       //老师管理员id自增主键
	private String teaManPhone; //老师管理员id自增主键手机号
	private String password;    //密码登录用
	private String teaManName;  //老师姓名
	private int teaManSex;      //老师性别,1男的、2女的
	private Date teaManBirthdate; //老师出生日期
	private int status;          //用户状态1为正常，0为冻结
	private int roleId;          //角色id对应角色表主键
	public int getTeaManId() {
		return teaManId;
	}
	public void setTeaManId(int teaManId) {
		this.teaManId = teaManId;
	}
	public String getTeaManPhone() {
		return teaManPhone;
	}
	public void setTeaManPhone(String teaManPhone) {
		this.teaManPhone = teaManPhone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTeaManName() {
		return teaManName;
	}
	public void setTeaManName(String teaManName) {
		this.teaManName = teaManName;
	}
	public int getTeaManSex() {
		return teaManSex;
	}
	public void setTeaManSex(int teaManSex) {
		this.teaManSex = teaManSex;
	}
	public Date getTeaManBirthdate() {
		return teaManBirthdate;
	}
	public void setTeaManBirthdate(Date teaManBirthdate) {
		this.teaManBirthdate = teaManBirthdate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
}
