package cn.music.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MatchClassInfo {
	private int matchClassId;  //集训班级id
	private String matchClassName; //集训班级名称
	private int teaManId;       //老师id
	private BigDecimal matchCost; //集训课时费
	private Date matchClassDate;  //集训开班日期
	private int matchSpan;       //集训上课时长
	private int matchStatus;      //集训班级状态1为正常，0为冻结
	public int getMatchClassId() {
		return matchClassId;
	}
	public void setMatchClassId(int matchClassId) {
		this.matchClassId = matchClassId;
	}
	public String getMatchClassName() {
		return matchClassName;
	}
	public void setMatchClassName(String matchClassName) {
		this.matchClassName = matchClassName;
	}
	public int getTeaManId() {
		return teaManId;
	}
	public void setTeaManId(int teaManId) {
		this.teaManId = teaManId;
	}
	public BigDecimal getMatchCost() {
		return matchCost;
	}
	public void setMatchCost(BigDecimal matchCost) {
		this.matchCost = matchCost;
	}
	public Date getMatchClassDate() {
		return matchClassDate;
	}
	public void setMatchClassDate(Date matchClassDate) {
		this.matchClassDate = matchClassDate;
	}
	public int getMatchSpan() {
		return matchSpan;
	}
	public void setMatchSpan(int matchSpan) {
		this.matchSpan = matchSpan;
	}

	public int getMatchStatus() {
		return matchStatus;
	}

	public void setMatchStatus(int matchStatus) {
		this.matchStatus = matchStatus;
	}
}
