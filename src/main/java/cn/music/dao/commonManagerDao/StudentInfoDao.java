package cn.music.dao.commonManagerDao;

import cn.music.dao.baseDao.BaseDao;
import cn.music.entity.CommonClassInfo;
import cn.music.entity.CommonRecords;
import cn.music.entity.StudentInfo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentInfoDao extends BaseDao {
	//得到所有学生信息
	public List<StudentInfo> getStudentInfo(int currentPageNo,int pageSize,String studentName){
		String sql = "select studentName,parentsName,parentsPhone,status from studentinfo where studentName like '%"+studentName+"%' limit ?,?";
		rs=super.executeSql(sql,(currentPageNo-1)*pageSize,pageSize);
		List<StudentInfo> list = new ArrayList<StudentInfo>();
		try {
			while(rs.next()){
				StudentInfo se = new StudentInfo();
				se.setStudentName(rs.getString(1));
				se.setParentsName(rs.getString(2));
				se.setParentsPhone(rs.getString(3));
				se.setStatus(rs.getInt(4));
				list.add(se);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return list;
	}

	//得到所有学生信息
	public int getAllStudentInfoCount(String studentName){
		int count = 0;
		String sql = "select count(1) from studentinfo where studentName like '%"+studentName+"%'";
		super.rs = super.executeSql(sql);
		try {
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.closeAll();
		}
		return count;
	}



	//查询学生信息通过班级
	public List<Map<String, Object>> getAllStudentInfo(int currentPageNo,int pageSize,String commonClassName,String studentName){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		String sql = "SELECT * FROM(SELECT sti.`studentId`,sti.`parentsPhone`,sti.`password`,sti.`studentName`,sti.`parentsName`,sti.`studentSex`,sti.`studentBirthdate`,cci.`commonClassName`,sti.`status` FROM studentInfo sti LEFT JOIN commonTuition ct ON sti.`studentId`=ct.`studentId` LEFT JOIN commonClassInfo cci ON ct.`commonClassId`=cci.`commonClassId` where cci.commonStatus=1 UNION SELECT sti.`studentId`,sti.`parentsPhone`,sti.`password`,sti.`studentName`,sti.`parentsName`,sti.`studentSex`,sti.`studentBirthdate`,'',sti.`status` FROM studentInfo sti WHERE  sti.`studentId` NOT IN (SELECT ct.`studentId` FROM commonTuition ct INNER JOIN commonClassInfo cci on cci.commonClassId=ct.commonClassId where cci.commonStatus=1)) AS A WHERE `commonClassName` LIKE '%"+commonClassName+"%' AND `studentName` LIKE '%"+studentName+"%' limit ?,?";
		super.rs = super.executeSql(sql, (currentPageNo-1)*pageSize,pageSize);
		try {
			while(rs.next()){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("studentId", rs.getInt(1));
				map.put("parentsPhone", rs.getString(2));
				map.put("password", rs.getString(3));
				map.put("studentName", rs.getString(4));
				map.put("parentsName", rs.getString(5));
				map.put("studentSex", rs.getInt(6));
				map.put("studentBirthdate", rs.getDate(7));
				map.put("commonClassName", rs.getString(8));
				map.put("status", rs.getInt(9));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return list;
	}

	
	//获得学生总数
	public int getStudentIntoCount(String commonClassName,String studentName){
		int count = 0;
		String sql = "SELECT count(1) FROM(SELECT sti.`studentId`,sti.`parentsPhone`,sti.`password`,sti.`studentName`,sti.`parentsName`,sti.`studentSex`,sti.`studentBirthdate`,cci.`commonClassName`,sti.`status` FROM studentInfo sti LEFT JOIN commonTuition ct ON sti.`studentId`=ct.`studentId` LEFT JOIN commonClassInfo cci ON ct.`commonClassId`=cci.`commonClassId` where cci.commonStatus=1 UNION SELECT sti.`studentId`,sti.`parentsPhone`,sti.`password`,sti.`studentName`,sti.`parentsName`,sti.`studentSex`,sti.`studentBirthdate`,'',sti.`status` FROM studentInfo sti WHERE  sti.`studentId` NOT IN (SELECT ct.`studentId` FROM commonTuition ct INNER JOIN commonClassInfo cci on cci.commonClassId=ct.commonClassId where cci.commonStatus=1)) AS A WHERE `commonClassName` LIKE '%"+commonClassName+"%' AND `studentName` LIKE '%"+studentName+"%'";
		super.rs = super.executeSql(sql);
		try {
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return count;
	}

	//修改学生信息
	public int updateStudentInfo(StudentInfo studentInfo){
		String sql = "update studentInfo set parentsPhone=?,password=?,studentName=?,parentsName=?,studentSex=?,studentBirthdate=?,status=? where studentId=?";
		return super.executeUpdate(sql,studentInfo.getParentsPhone(),studentInfo.getPassword(),studentInfo.getStudentName(),studentInfo.getParentsName(),studentInfo.getStudentSex(),studentInfo.getStudentBirthdate(),studentInfo.getStatus(),studentInfo.getStudentId());
	}

	//增加学生信息
	public int addStudentInfo(StudentInfo studentInfo){
		String sql = "insert into studentInfo values(null,?,?,?,?,?,?,?)";
		return  super.executeUpdate(sql,studentInfo.getParentsPhone(),studentInfo.getPassword(),studentInfo.getStudentName(),studentInfo.getParentsName(),studentInfo.getStudentSex(),studentInfo.getStudentBirthdate(),studentInfo.getStatus());
	}

	//查询学生信息通过学费表
	public List<Map<String,Object>> getStudentByTuition(int currentPageNo,int pageSize,int commonClassId,String studentName){
		String sql = "select sti.studentId,sti.parentsPhone,sti.studentName,sti.parentsName,sti.studentSex,sti.studentBirthdate,sti.status from studentInfo sti LEFT JOIN commonTuition ct ON sti.`studentId`=ct.`studentId` where sti.`studentId` NOT IN (SELECT ct.`studentId` FROM commonTuition ct where ct.commonClassId=?) and sti.`studentName` LIKE '%"+studentName+"%' GROUP BY sti.studentId limit ?,?";
		super.rs = super.executeSql(sql,commonClassId,(currentPageNo-1)*pageSize,pageSize);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			while (rs.next()){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("studentId",rs.getInt(1));
				map.put("parentsPhone",rs.getString(2));
				map.put("studentName",rs.getString(3));
				map.put("parentsName",rs.getString(4));
				map.put("studentSex",rs.getInt(5));
				map.put("studentBirthdate",rs.getDate(6));
				map.put("status",rs.getInt(7));
            	list.add(map);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.closeAll();
		}
		return list;
	}

	//查询学生信息数量通过学费表
	public int getStudentByTuitionCount(int commonClassId,String studentName){
		int count = 0;
		String sql = "select count(1) from(select sti.studentId from studentInfo sti LEFT JOIN commonTuition ct ON sti.`studentId`=ct.`studentId` where sti.`studentId` NOT IN (SELECT ct.`studentId` FROM commonTuition ct where ct.commonClassId=?) and sti.`studentName` LIKE '%"+studentName+"%' GROUP BY sti.studentId) as A";
		super.rs = super.executeSql(sql,commonClassId);
		try {
			if(rs.next()){
                count = rs.getInt(1);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.closeAll();
		}
		return count;
	}


	//查询家长手机号是否存在
	public String queryParentsPhone(String parentsPhone){
		String sql = "select parentsPhone from studentInfo where parentsPhone=?";
		super.rs = super.executeSql(sql,parentsPhone);
		String str = null;
		try {
			if(rs.next()){
				str = rs.getString(1);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.closeAll();
		}
		return str;
	}

	//通过学生id得到手机号
	public String getParentsPhoneById(int studentId){
		String sql = "select parentsPhone from studentInfo where studentId=?";
		super.rs = super.executeSql(sql,studentId);
		String str = null;
		try {
			if(rs.next()){
				str = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.closeAll();
		}
		return str;
	}

	//修改学生状态
	public int updateStudentStatus(int status,int studentId){
		String sql = "update studentInfo set status=? where studentId=?";
		return super.executeUpdate(sql,status,studentId);
	}


	//通过班级id得到班级学生
	public List<Map<String,Object>> getStudentListByCommonClass(int currentPageNo,int pageSize,int commonClassId,String studentName){
		String sql = "select sti.studentId,sti.parentsPhone,sti.studentName,sti.parentsName,sti.studentSex,sti.studentBirthdate,ct.commonSurplus,ct.commonAllCost,sti.status from studentInfo sti left join commonTuition ct on sti.`studentId`=ct.`studentId` where ct.commonClassId=? and sti.`studentName` LIKE '%"+studentName+"%' GROUP BY sti.studentId limit ?,?";
		super.rs = super.executeSql(sql,commonClassId,(currentPageNo-1)*pageSize,pageSize);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			while (rs.next()){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("studentId",rs.getInt(1));
				map.put("parentsPhone",rs.getString(2));
				map.put("studentName",rs.getString(3));
				map.put("parentsName",rs.getString(4));
				map.put("studentSex",rs.getInt(5));
				map.put("studentBirthdate",rs.getDate(6));
				map.put("commonSurplus",rs.getBigDecimal(7));
				map.put("commonAllCost",rs.getBigDecimal(8));
				map.put("status",rs.getInt(9));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.closeAll();
		}
		return list;
	}

	//通过班级id得到班级学生数量
	public int getStudentListByCommonClassCount(int commonClassId,String studentName){
		int count = 0;
		String sql = "select count(1) from(select sti.studentId from studentInfo sti left join commonTuition ct on sti.`studentId`=ct.`studentId` where ct.commonClassId=? and sti.`studentName` LIKE '%"+studentName+"%' GROUP BY sti.studentId) as A";
		super.rs = super.executeSql(sql,commonClassId);
		try {
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.closeAll();
		}
		return count;
	}


	//select通过学生和密码判断用户名是否存在
	public int getStudentIdByPassword(String parentsPhone, String password){
		String sql = "select studentId from studentInfo where parentsPhone=? and password=?";
		super.rs = super.executeSql(sql, parentsPhone,password);
		int count = -1;
		try {
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return count;
	}

	//查询今天打卡谁打卡了
	public List<Map<String,Object>> getStudentByToday(int commonClassId){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String sql = "SELECT s.studentId,s.parentsPhone,s.studentName,s.parentsName,s.studentSex,cr.commonCardTime FROM studentInfo s INNER JOIN commonRecords cr ON s.studentId=cr.studentId INNER JOIN commonClassInfo cc ON cc.commonClassId=cr.commonClassId WHERE TO_DAYS(cr.commonCardTime) = TO_DAYS(NOW()) AND cc.commonClassId=? AND cr.commonCardTime>=CONCAT(DATE(NOW()),' ',cc.commonTime) AND cr.commonCardTime<=DATE_ADD(CONCAT(DATE(NOW()),' ',cc.commonTime), INTERVAL cc.commonSpan MINUTE)";
		super.rs = super.executeSql(sql,commonClassId);

		try {
			while (rs.next()){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("studentId",rs.getInt(1));
				map.put("parentsPhone",rs.getString(2));
				map.put("studentName",rs.getString(3));
				map.put("parentsName",rs.getString(4));
				map.put("studentSex",rs.getInt(5));
				map.put("commonCardTime",rs.getTimestamp(6));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.closeAll();
		}
		return list;
	}

	//查询今天打卡谁没打卡了
	public List<Map<String,Object>> getStudentByNoToday(int commonClassId){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String sql = "SELECT s.studentId,s.parentsPhone,s.studentName,s.parentsName,s.studentSex FROM studentInfo s INNER JOIN commonTuition ct ON s.studentId=ct.studentId where s.studentId not in(SELECT s.studentId FROM studentInfo s INNER JOIN commonRecords cr ON s.studentId=cr.studentId INNER JOIN commonClassInfo cc ON cc.commonClassId=cr.commonClassId WHERE TO_DAYS(cr.commonCardTime) = TO_DAYS(NOW()) AND cc.commonClassId=? AND cr.commonCardTime>=CONCAT(DATE(NOW()),' ',cc.commonTime) AND cr.commonCardTime<=DATE_ADD(CONCAT(DATE(NOW()),' ',cc.commonTime), INTERVAL cc.commonSpan MINUTE)) and ct.commonClassId=?";
		super.rs = super.executeSql(sql,commonClassId,commonClassId);

		try {
			while (rs.next()){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("studentId",rs.getInt(1));
				map.put("parentsPhone",rs.getString(2));
				map.put("studentName",rs.getString(3));
				map.put("parentsName",rs.getString(4));
				map.put("studentSex",rs.getInt(5));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.closeAll();
		}
		return list;
	}














	//验证学生账号密码和登陆状态
	public int getStudentId(String parentsPhone,String password){
		int count = -1;
		String sql="SELECT studentId FROM studentInfo WHERE parentsPhone=? AND `password`=?";
		rs=super.executeSql(sql,parentsPhone,password);
		try {
			while (rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	//查询学生的状态是否冻结
	public int status(int studentId,String password){
		int status=0;
		String sql="SELECT `status` FROM studentinfo WHERE studentId=? AND `password`=?";
		rs=super.executeSql(sql,studentId,password);
		try {
			while (rs.next()){
				status=rs.getInt("status");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	//通过学生id得到手机号
	public String getStudentNameById(int studentId){
		String sql = "select studentName from studentInfo where studentId=?";
		super.rs = super.executeSql(sql,studentId);
		String str = null;
		try {
			if(rs.next()){
				str = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.closeAll();
		}
		return str;
	}


	//得到班级表
	public List<CommonClassInfo> commonclassinfos(){
		List<CommonClassInfo> list = new ArrayList<CommonClassInfo>();

		String sql="SELECT commonClassId,commonClassName FROM commonclassinfo";
		rs=super.executeSql(sql);
		try {
			while (rs.next()){
				CommonClassInfo cc = new CommonClassInfo();
				cc.setCommonClassId(rs.getInt(1));
				cc.setCommonClassName(rs.getString(2));
				list.add(cc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//签到成功
	public int qiandao(int studentId, int commonClassId, Date commonCardTime, int commonStatus){
		int count=0;
		String sql="INSERT INTO commonRecords(studentId,commonClassId,commonCardTime,commonStatus)VALUES(?,?,?,?)";
		return super.executeUpdate(sql,studentId,commonClassId,commonCardTime,commonStatus);
	}

	//得到学生所在的班级
	public Date classId(int studentId){
		List<CommonRecords> list = new ArrayList<CommonRecords>();
		String sql="SELECT commonCardTime FROM commonrecords WHERE studentId=?";
		Date date = null;
		rs=super.executeSql(sql,studentId);
		try {
			while (rs.next()){
				//commonRecords cs = new commonRecords();
				date = rs.getTimestamp("commonCardTime");
				//list.add(cs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return date;
	}


	//判断时间内是否已经签到过
	public boolean hourMinuteBetween(String nowDate, String startDate, String endDate) throws Exception{

		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

		Date now = format.parse(nowDate);
		Date start = format.parse(startDate);
		Date end = format.parse(endDate);

		long nowTime = now.getTime();
		long startTime = start.getTime();
		long endTime = end.getTime();

		return nowTime >= startTime && nowTime <= endTime;
	}

	//得到签到状态
	public int commonStatus(int studentId,int commonCardId,String like){
		int commonStatus = 0;

		System.out.println(like);
		String sql = "SELECT COUNT(1) FROM commonrecords WHERE studentId=? and commonClassId =? AND commoncardtime LIKE ?";
		rs=super.executeSql(sql,studentId,commonCardId,"%"+like+"%");
		try {
			while (rs.next()){
				commonStatus=rs.getInt(1);
				System.out.println(rs.getInt(1));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commonStatus;
	}

	//得到签到班级id
	public int commonCardId(int studentId,String like){
		String sql = "SELECT COUNT(commonCardId) FROM commonrecords WHERE studentId=? AND commoncardtime LIKE ?";
		int commonCardId = 0;
		rs=super.executeSql(sql,studentId,"%"+like+"%");
		try {
			while (rs.next()){
				commonCardId=rs.getInt("commonCardId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commonCardId;
	}

	//根据学生id和周几得到今天这个学生都上那个课,如果一个学生今天又很多课判断一下现在时间距离哪节课的时间最近
	public CommonClassInfo studentTodayClass(int id,int commonWeek){
		String sql="SELECT commonClassId,commonClassName FROM commonclassinfo cc WHERE commonStatus=1 AND commonClassId IN( SELECT commonClassId FROM commontuition WHERE studentId=? AND commonClassId IN(SELECT commonClassId FROM commonclassinfo WHERE commonWeek=? AND NOW()>=CONCAT(DATE(NOW()),' ',commonTime) AND NOW()<=DATE_ADD(CONCAT(DATE(NOW()),' ',commonTime), INTERVAL commonSpan MINUTE)))";
		rs=super.executeSql(sql,id,commonWeek);
		CommonClassInfo cc = null;
		try {
			while (rs.next()){
				cc = new CommonClassInfo();
				cc.setCommonClassId(rs.getInt("commonClassId"));
				cc.setCommonClassName(rs.getString("commonClassName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return  cc;
	}
	//得到今天周几
	public int commonWeek(){
		Calendar cleadar =Calendar.getInstance();
		int day = cleadar.get(Calendar.DAY_OF_WEEK);
		if(day!=1){
			day=day-1;
		}else if(day==1){
			day=7;
		}
		return day;
	}

	//得到这个班级的一节课多少钱
	public BigDecimal commonCost(int classId){
		String sql="SELECT commonCost FROM commonclassinfo cc WHERE  cc.commonClassId=?";
		BigDecimal commonCost = null;
		rs=super.executeSql(sql,classId);
		try {
			while (rs.next()){
				commonCost = rs.getBigDecimal("commonCost");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commonCost;
	}

	//给这个学生在这个班级的余额减掉一节课的课时费
	public int commontuition(BigDecimal commonCost,int studentId,int classId,BigDecimal commonCost1){
		String sql = "UPDATE commontuition ct SET commonSurplus=commonSurplus-? WHERE ct.studentId=? AND ct.commonClassId=? and commonSurplus>=?";
		return executeUpdate(sql,commonCost,studentId,classId,commonCost1);
	}


	//事务处理签到和结算余额
	public Boolean ceshi(int studentId, int commonClassId, Date commonCardTime, int commonStatus){
		Boolean ceshi=false;
		return ceshi;
	}
	//事务处理签到和结算余额
	public int a (int studentId, int commonClassId, Date commonCardTime, int commonStatus,BigDecimal commonCost,int studentId1,int classId) throws SQLException {

		Connection connection = super.getConn();
		try {
			int a=0;int b=0;
			//connection.setTransactionIsolation();
			connection.setAutoCommit(false);//关闭自动提交
			String sql="INSERT INTO commonRecords(studentId,commonClassId,commonCardTime,commonStatus)VALUES(?,?,?,?)";
			String sql1 = "UPDATE commontuition ct SET commonSurplus=commonSurplus-? WHERE ct.studentId=? AND ct.commonClassId=?";
			PreparedStatement pstmt = connection.prepareStatement(sql);//sql语句添加到事务
			pstmt.setInt(1, studentId);
			pstmt.setInt(2, commonClassId);
			pstmt.setTimestamp(3,new Timestamp(commonCardTime.getTime()));
			pstmt.setInt(4,1);
			a=pstmt.executeUpdate();
			pstmt=connection.prepareStatement(sql1);//sql语句添加到事务
			pstmt.setBigDecimal(1, commonCost);
			pstmt.setInt(2, studentId);
			pstmt.setInt(3,classId);
			b=pstmt.executeUpdate();
			if(a>0&&b>0) {
				connection.commit();
				System.out.println("提交成功!");
				connection.setAutoCommit(true);//3,提交完成后回复现场将Auto commit,还原为true,
				return 1;
			}else{
				connection.rollback();//4,当异常发生执行catch中SQLException时，记得要rollback(回滚)；
				System.out.println("插入失败，回滚！");
				connection.setAutoCommit(true);
				return  -1;
			}

		} catch (SQLException e) {
			if(!connection.isClosed()){
				connection.rollback();//4,当异常发生执行catch中SQLException时，记得要rollback(回滚)；
				System.out.println("插入失败，回滚！");
				connection.setAutoCommit(true);
			}

			return  -1;
		}
		finally {
			super.closeAll();
		}

	}
}
