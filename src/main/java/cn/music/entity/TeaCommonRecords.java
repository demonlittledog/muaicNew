package cn.music.entity;

import java.util.Date;

public class TeaCommonRecords {
	private int teaCommonCardId;  //老师普通打卡id
	private int teaManId;     //老师管理员id
	private int commonClassId;  //普通班级id
	private Date teaCommonCardTime; //老师普通签到时间
	private int teaCommonStatus;    //老师普通签到状态，0为未签到，1为已签到
	public int getTeaCommonCardId() {
		return teaCommonCardId;
	}
	public void setTeaCommonCardId(int teaCommonCardId) {
		this.teaCommonCardId = teaCommonCardId;
	}
	public int getTeaManId() {
		return teaManId;
	}
	public void setTeaManId(int teaManId) {
		this.teaManId = teaManId;
	}
	public int getCommonClassId() {
		return commonClassId;
	}
	public void setCommonClassId(int commonClassId) {
		this.commonClassId = commonClassId;
	}
	public Date getTeaCommonCardTime() {
		return teaCommonCardTime;
	}
	public void setTeaCommonCardTime(Date teaCommonCardTime) {
		this.teaCommonCardTime = teaCommonCardTime;
	}
	public int getTeaCommonStatus() {
		return teaCommonStatus;
	}
	public void setTeaCommonStatus(int teaCommonStatus) {
		this.teaCommonStatus = teaCommonStatus;
	}
	
}
