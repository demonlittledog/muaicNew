package cn.music.entity;

import java.util.Date;

public class CommonRecords {
	private int commonCardId;  //普通打卡id
	private int studentId;     //学生id
	private int commonClassId;  //普通班级id
	private Date commonCardTime; //普通签到时间
	private int commonStatus;    //普通签到状态，0为未签到，1为已签到，2为补签
	public int getCommonCardId() {
		return commonCardId;
	}
	public void setCommonCardId(int commonCardId) {
		this.commonCardId = commonCardId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getCommonClassId() {
		return commonClassId;
	}
	public void setCommonClassId(int commonClassId) {
		this.commonClassId = commonClassId;
	}
	public Date getCommonCardTime() {
		return commonCardTime;
	}
	public void setCommonCardTime(Date commonCardTime) {
		this.commonCardTime = commonCardTime;
	}
	public int getCommonStatus() {
		return commonStatus;
	}
	public void setCommonStatus(int commonStatus) {
		this.commonStatus = commonStatus;
	}
	
}
