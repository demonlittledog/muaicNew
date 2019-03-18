package cn.music.service.commonManagerService;

import cn.music.dao.commonManagerDao.TeaManInfoDao;
import cn.music.entity.TeaManInfo;

import java.util.List;
import java.util.Map;

public class TeaManInfoService {
	private TeaManInfoDao teaManInfoDao = new TeaManInfoDao();
	
	//select通过用户名判断用户名是否存在
	public String getTeaManNameByTeaManPhone(String teaManPhone){
		return teaManInfoDao.getTeaManNameByTeaManPhone(teaManPhone);
	}

	//select通过用户名判断用户名是否存在得到id
	public int getTeaManIdByTeaManPhone(String teaManPhone){
		return teaManInfoDao.getTeaManIdByTeaManPhone(teaManPhone);
	}

	//select通过用户名和密码判断用户名是否存在
	public TeaManInfo getTeaManByPassword(String teaManPhone,String password){
		return teaManInfoDao.getTeaManByPassword(teaManPhone, password);
	}

	//查询老师信息通过班级
	public List<Map<String,Object>> getAllTeacherInfo(int currentPageNo, int pageSize, String commonClassName, String teaManName){
		return teaManInfoDao.getAllTeacherInfo(currentPageNo,pageSize,commonClassName,teaManName);
	}

	//获得老师总数
	public int getTeacherIntoCount(String commonClassName,String teaManName){
		return teaManInfoDao.getTeacherIntoCount(commonClassName,teaManName);
	}

	//修改老师信息
	public int updateTeahcerInfo(TeaManInfo teaManInfo){
		return teaManInfoDao.updateTeahcerInfo(teaManInfo);
	}

	//增加老师信息
	public int addTeacherInfo(TeaManInfo teaManInfo){
		return teaManInfoDao.addTeacherInfo(teaManInfo);
	}

	//修改老师管理员密码
	public int changePwd(TeaManInfo teaManInfo,String ypwd){
		return teaManInfoDao.changePwd(teaManInfo,ypwd);
	}

	//修改老师信息除密码状态
	public int updatePersonalInfoInfo(TeaManInfo teaManInfo){
		return teaManInfoDao.updatePersonalInfoInfo(teaManInfo);
	}

	//查询家长手机号是否存在
	public String queryTeaManPhone(String teaManPhone){
		return teaManInfoDao.queryTeaManPhone(teaManPhone);
	}

	//通过老师id得到手机号
	public String getTeaManPhoneById(int teaManId){
		return teaManInfoDao.getTeaManPhoneById(teaManId);
	}

	//获取全部老师的姓名
	public List<TeaManInfo> getTeaManName(){
		return teaManInfoDao.getTeaManName();
	}

	//根据老师姓名查询老师id
	public int getTeaManId(String teaManName){
		return teaManInfoDao.getTeaManId(teaManName);
	}
}
