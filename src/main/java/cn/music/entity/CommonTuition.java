package cn.music.entity;

import java.math.BigDecimal;

public class CommonTuition {
	private int commonTuiId;   //普通学费id
	private int studentId;     //学生id
	private int commonClassId; //普通班级id
	private BigDecimal commonSurplus; //普通剩余学费
	private BigDecimal commonAllCost; //普通总学费
	public int getCommonTuiId() {
		return commonTuiId;
	}
	public void setCommonTuiId(int commonTuiId) {
		this.commonTuiId = commonTuiId;
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
	public BigDecimal getCommonSurplus() {
		return commonSurplus;
	}
	public void setCommonSurplus(BigDecimal commonSurplus) {
		this.commonSurplus = commonSurplus;
	}
	public BigDecimal getCommonAllCost() {
		return commonAllCost;
	}
	public void setCommonAllCost(BigDecimal commonAllCost) {
		this.commonAllCost = commonAllCost;
	}
	
}
