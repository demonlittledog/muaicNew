package cn.music.entity;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

public class CommonClassInfo {
	private int commonClassId;   //普通班级id
	private String commonClassName; //普通班级名称
	private int classTypeId;      //普通班级类型id
	private int teaManId;         //老师id
	private BigDecimal commonCost; //普通课时费
	private int commonSum;         //普通总课程数
	private Date commonClassDate;  //普通开班日期
	private int commonWeek;        //普通周
	private Time commonTime;       //普通上课时间
	private int commonSpan;        //普通上课时长
	private int studentNum;        //学生人数
	private int commonStatus;      //普通班级状态1为正常，0为冻结

	public int getCommonClassId() {
		return commonClassId;
	}

	public void setCommonClassId(int commonClassId) {
		this.commonClassId = commonClassId;
	}

	public String getCommonClassName() {
		return commonClassName;
	}

	public void setCommonClassName(String commonClassName) {
		this.commonClassName = commonClassName;
	}

	public int getClassTypeId() {
		return classTypeId;
	}

	public void setClassTypeId(int classTypeId) {
		this.classTypeId = classTypeId;
	}

	public int getTeaManId() {
		return teaManId;
	}

	public void setTeaManId(int teaManId) {
		this.teaManId = teaManId;
	}

	public BigDecimal getCommonCost() {
		return commonCost;
	}

	public void setCommonCost(BigDecimal commonCost) {
		this.commonCost = commonCost;
	}

	public int getCommonSum() {
		return commonSum;
	}

	public void setCommonSum(int commonSum) {
		this.commonSum = commonSum;
	}

	public Date getCommonClassDate() {
		return commonClassDate;
	}

	public void setCommonClassDate(Date commonClassDate) {
		this.commonClassDate = commonClassDate;
	}

	public int getCommonWeek() {
		return commonWeek;
	}

	public void setCommonWeek(int commonWeek) {
		this.commonWeek = commonWeek;
	}

	public Time getCommonTime() {
		return commonTime;
	}

	public void setCommonTime(Time commonTime) {
		this.commonTime = commonTime;
	}

	public int getCommonSpan() {
		return commonSpan;
	}

	public void setCommonSpan(int commonSpan) {
		this.commonSpan = commonSpan;
	}

	public int getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}

	public int getCommonStatus() {
		return commonStatus;
	}

	public void setCommonStatus(int commonStatus) {
		this.commonStatus = commonStatus;
	}
}
