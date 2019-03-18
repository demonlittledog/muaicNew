package cn.music.service.commonManagerService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.music.dao.commonManagerDao.StudentInfoDao;
import cn.music.entity.CommonClassInfo;
import cn.music.entity.StudentInfo;

public class StudentInfoService {
	private StudentInfoDao studentInfoDao = new StudentInfoDao();
	//得到所有学生信息
	public List<StudentInfo> getStudentInfo(int currentPageNo,int pageSize,String studentName){
		return studentInfoDao.getStudentInfo(currentPageNo,pageSize,studentName);
	}

	//得到所有学生信息
	public int getAllStudentInfoCount(String studentName){
		return studentInfoDao.getAllStudentInfoCount(studentName);
	}

	//查询学生信息
	public List<Map<String, Object>> getAllStudentInfo(int currentPageNo,int pageSize,String commonClassName,String studentName){
		return studentInfoDao.getAllStudentInfo(currentPageNo, pageSize, commonClassName, studentName);
	}
	
	//获得学生总数
	public int getStudentIntoCount(String commonClassName,String studentName){
		return studentInfoDao.getStudentIntoCount(commonClassName, studentName);
	}

	//修改学生信息
	public int updateStudentInfo(StudentInfo studentInfo){
		return studentInfoDao.updateStudentInfo(studentInfo);
	}

	//增加学生信息
	public int addStudentInfo(StudentInfo studentInfo){
		return studentInfoDao.addStudentInfo(studentInfo);
	}

	//查询学生信息通过学费表
	public List<Map<String,Object>> getStudentByTuition(int currentPageNo,int pageSize,int commonClassId,String studentName){
		return studentInfoDao.getStudentByTuition(currentPageNo,pageSize,commonClassId,studentName);
	}

	//查询学生信息数量通过学费表
	public int getStudentByTuitionCount(int commonClassId,String studentName){
		return studentInfoDao.getStudentByTuitionCount(commonClassId,studentName);
	}

	//查询家长手机号是否存在
	public String queryParentsPhone(String parentsPhone){
		return studentInfoDao.queryParentsPhone(parentsPhone);
	}

	//通过学生id得到手机号
	public String getParentsPhoneById(int studentId){
		return studentInfoDao.getParentsPhoneById(studentId);
	}

	//修改学生状态
	public int updateStudentStatus(int status,int studentId){
		return studentInfoDao.updateStudentStatus(status,studentId);
	}

	//通过班级id得到班级学生
	public List<Map<String,Object>> getStudentListByCommonClass(int currentPageNo,int pageSize,int commonClassId,String studentName){
		return studentInfoDao.getStudentListByCommonClass(currentPageNo,pageSize,commonClassId,studentName);
	}

	//通过班级id得到班级学生数量
	public int getStudentListByCommonClassCount(int commonClassId,String studentName){
		return studentInfoDao.getStudentListByCommonClassCount(commonClassId,studentName);
	}

	//select通过学生和密码判断用户名是否存在
	public int getStudentIdByPassword(String parentsPhone, String password){
		return studentInfoDao.getStudentIdByPassword(parentsPhone,password);
	}

	//查询今天打卡谁打卡了
	public List<Map<String,Object>> getStudentByToday(int commonClassId){
		return studentInfoDao.getStudentByToday(commonClassId);
	}

	//查询今天打卡谁没打卡了
	public List<Map<String,Object>> getStudentByNoToday(int commonClassId){
		return studentInfoDao.getStudentByNoToday(commonClassId);
	}










	//验证学生账号密码和登陆状态
	public int getStudentId(String parentsPhone,String password){
		return studentInfoDao.getStudentId(parentsPhone,password);
	}
	//查询学生的状态是否冻结
	public int status(int studentId,String password){
		return studentInfoDao.status(studentId,password);
	}
	//通过学生id得到手机号
	public String getStudentNameById(int studentId){
		return studentInfoDao.getStudentNameById(studentId);
	}

	//得到班级信息
	public List<CommonClassInfo> commonclassinfos(){

		return studentInfoDao.commonclassinfos();
	}
	//签到
	public int qiandao(int studentId, int commonClassId, Date commonCardTime, int commonStatus){
		return studentInfoDao.qiandao(studentId,commonClassId,commonCardTime,commonStatus);
	}
	//得到学生签到的时间
	public Date classId(int studentId) {
		return studentInfoDao.classId(studentId);
	}
	//根据班级id得到该班级上课时长
    /*public int longtime(int classId){
        return sd.longtime(classId);
    }*/
	//得到该该班级的上课时间
    /*public  String classtime(int classId){
        return sd.classtime(classId);
    }*/
	//得到下课时间
    /*public String xia(String commonTime,int commonSpan){
        return sd.xia(commonTime,commonSpan);
    }*/

	//判断时间内是否已经签到过
	public boolean hourMinuteBetween(String nowDate, String startDate, String endDate) throws Exception{
		return studentInfoDao.hourMinuteBetween(nowDate,startDate,endDate);
	}

    /*public  String uptime(int student){
        return sd.uptime(student);
    }*/

	//得到签到状态
	public int commonStatus(int studentId,int commonCardId,String like){
		return studentInfoDao.commonStatus(studentId,commonCardId,like);
	}
	//得到签到班级id
	public int commonCardId(int studentId,String like){
		return  studentInfoDao.commonCardId(studentId,like);
	}

	//根据学生id和周几得到今天这个学生都上那个课,如果一个学生今天又很多课判断一下现在时间距离哪节课的时间最近
	public CommonClassInfo studentTodayClass(int id,int commonWeek){
		return studentInfoDao.studentTodayClass(id,commonWeek);
	}
	//得到今天周几
	public int commonWeek(){
		return studentInfoDao.commonWeek();
	}

	//得到这个班级的一节课多少钱
	public BigDecimal commonCost(int classId){
		return studentInfoDao.commonCost(classId);
	}

	//给这个学生在这个班级的余额减掉一节课的课时费
	public int commontuition(BigDecimal commonCost,int studentId,int classId,BigDecimal commonCost1){
		return studentInfoDao.commontuition(commonCost,studentId,classId,commonCost1);
	}

	//事务处理签到和结算余额
	public int a (int studentId, int commonClassId, Date commonCardTime, int commonStatus,BigDecimal commonCost,int studentId1,int classId) throws SQLException {
		return studentInfoDao.a(studentId,commonClassId,commonCardTime,commonStatus,commonCost,studentId1,classId);
	}
}
