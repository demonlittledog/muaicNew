package cn.music.entity;

import java.math.BigDecimal;

public class MatchTuition {
	private int matchTuiId;   //集训学费id
	private int studentId;    //学生id
	private int matchClassId; //集训班级id
	private BigDecimal matchSurplus; //集训剩余学费
	private BigDecimal matchAllCost; //集训总学费
	public int getMatchTuiId() {
		return matchTuiId;
	}
	public void setMatchTuiId(int matchTuiId) {
		this.matchTuiId = matchTuiId;
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
	public BigDecimal getMatchSurplus() {
		return matchSurplus;
	}
	public void setMatchSurplus(BigDecimal matchSurplus) {
		this.matchSurplus = matchSurplus;
	}
	public BigDecimal getMatchAllCost() {
		return matchAllCost;
	}
	public void setMatchAllCost(BigDecimal matchAllCost) {
		this.matchAllCost = matchAllCost;
	}
	
}
