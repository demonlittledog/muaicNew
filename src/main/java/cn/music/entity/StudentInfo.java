package cn.music.entity;


import java.util.Date;

public class StudentInfo {
	private int studentId;       //学生id自增主键
	private String parentsPhone; //家长手机号
	private String password;     //密码登录用
	private String studentName;  //学生姓名
	private String parentsName;  //家长姓名
	private int studentSex;      //学生性别,1男的、2女的
	private Date studentBirthdate; //学生出生日期
	private int status;           //用户状态1为正常，0为冻结
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getParentsPhone() {
		return parentsPhone;
	}
	public void setParentsPhone(String parentsPhone) {
		this.parentsPhone = parentsPhone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getParentsName() {
		return parentsName;
	}
	public void setParentsName(String parentsName) {
		this.parentsName = parentsName;
	}
	public int getStudentSex() {
		return studentSex;
	}
	public void setStudentSex(int studentSex) {
		this.studentSex = studentSex;
	}
	public Date getStudentBirthdate() {
		return studentBirthdate;
	}
	public void setStudentBirthdate(Date studentBirthdate) {
		this.studentBirthdate = studentBirthdate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
