package cn.music.entity;

import java.util.Date;

public class MatchRecords {
	private int matchCardId;   //集训打卡id
	private int studentId;     //学生id
	private int matchClassId;  //集训班级id
	private Date matchCardTime; //集训签到时间
	private int matchStatus;    //集训签到状态，0为未签到，1为已签到
	public int getMatchCardId() {
		return matchCardId;
	}
	public void setMatchCardId(int matchCardId) {
		this.matchCardId = matchCardId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getMatchClassId() {
		return matchClassId;
	}
	public void setMatchClassId(int matchClassId) {
		this.matchClassId = matchClassId;
	}
	public Date getMatchCardTime() {
		return matchCardTime;
	}
	public void setMatchCardTime(Date matchCardTime) {
		this.matchCardTime = matchCardTime;
	}
	public int getMatchStatus() {
		return matchStatus;
	}
	public void setMatchStatus(int matchStatus) {
		this.matchStatus = matchStatus;
	}
	
}
