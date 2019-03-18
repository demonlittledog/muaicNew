package cn.music.entity;

import java.util.Date;

public class TeaMatchRecords {
	private int teaMatchCardId;   //老师集训打卡id
	private int teaManId;         //老师管理员id
	private int matchClassId;     //集训班级id
	private Date teaMatchCardTime; //老师集训签到时间
	private int teaMatchStatus;    //老师集训签到状态，0为未签到，1为已签到
	public int getTeaMatchCardId() {
		return teaMatchCardId;
	}
	public void setTeaMatchCardId(int teaMatchCardId) {
		this.teaMatchCardId = teaMatchCardId;
	}
	public int getTeaManId() {
		return teaManId;
	}
	public void setTeaManId(int teaManId) {
		this.teaManId = teaManId;
	}
	public int getMatchClassId() {
		return matchClassId;
	}
	public void setMatchClassId(int matchClassId) {
		this.matchClassId = matchClassId;
	}
	public Date getTeaMatchCardTime() {
		return teaMatchCardTime;
	}
	public void setTeaMatchCardTime(Date teaMatchCardTime) {
		this.teaMatchCardTime = teaMatchCardTime;
	}
	public int getTeaMatchStatus() {
		return teaMatchStatus;
	}
	public void setTeaMatchStatus(int teaMatchStatus) {
		this.teaMatchStatus = teaMatchStatus;
	}
	
}
