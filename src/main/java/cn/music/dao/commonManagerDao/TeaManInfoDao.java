package cn.music.dao.commonManagerDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.music.dao.baseDao.BaseDao;
import cn.music.entity.TeaManInfo;

public class TeaManInfoDao extends BaseDao {
	//select通过用户名判断用户名是否存在
	public String getTeaManNameByTeaManPhone(String teaManPhone){
		String sql = "select teaManName from teaManInfo where teaManPhone=?";
		super.rs =  super.executeSql(sql, teaManPhone);
		String teaManName = null;
		try {
			if(rs.next()){
				teaManName = rs.getString("teaManName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return teaManName;
	}

	//select通过用户名判断用户名是否存在得到id
	public int getTeaManIdByTeaManPhone(String teaManPhone){
		String sql = "select teaManId from teaManInfo where teaManPhone=?";
		super.rs =  super.executeSql(sql, teaManPhone);
		int teaManId = 0;
		try {
			if(rs.next()){
				teaManId = rs.getInt("teaManId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return teaManId;
	}
	
	//select通过用户名和密码判断用户名是否存在
	public TeaManInfo getTeaManByPassword(String teaManPhone,String password){
		String sql = "select * from teaManInfo where teaManPhone=? and password=?";
		super.rs = super.executeSql(sql, teaManPhone,password);
		TeaManInfo teaManInfo = null;
		try {
			if(rs.next()){
				teaManInfo = new TeaManInfo();
				teaManInfo.setTeaManId(rs.getInt(1));
				teaManInfo.setTeaManPhone(rs.getString(2));
				teaManInfo.setPassword(rs.getString(3));
				teaManInfo.setTeaManName(rs.getString(4));
				teaManInfo.setTeaManSex(rs.getInt(5));
				teaManInfo.setTeaManBirthdate(rs.getDate(6));
				teaManInfo.setStatus(rs.getInt(7));
				teaManInfo.setRoleId(rs.getInt(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return teaManInfo;
	}

	//查询老师信息通过班级
	public List<Map<String,Object>> getAllTeacherInfo(int currentPageNo,int pageSize,String commonClassName,String teaManName){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		String sql = "select * from(select tmi.`teaManId`,tmi.`teaManPhone`,tmi.`password`,tmi.`teaManName`,tmi.`teaManSex`,tmi.`teaManBirthdate`,cci.`commonClassName`,tmi.`status` from teaManInfo tmi left join commonClassInfo cci on tmi.teaManId=cci.teaManId where cci.commonStatus=1 union select tmi.`teaManId`,tmi.`teaManPhone`,tmi.`password`,tmi.`teaManName`,tmi.`teaManSex`,tmi.`teaManBirthdate`,'',tmi.`status` from teaManInfo tmi where tmi.teaManId not in (select cci.teaManId from commonClassInfo cci where cci.commonStatus=1)) AS A where `commonClassName` LIKE '%"+commonClassName+"%' AND `teaManName` LIKE '%"+teaManName+"%' limit ?,?";
		super.rs = super.executeSql(sql, (currentPageNo-1)*pageSize,pageSize);
		try {
			while (rs.next()){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("teaManId", rs.getInt(1));
				map.put("teaManPhone", rs.getString(2));
				map.put("password", rs.getString(3));
				map.put("teaManName", rs.getString(4));
				map.put("teaManSex", rs.getInt(5));
				map.put("teaManBirthdate", rs.getDate(6));
				map.put("commonClassName", rs.getString(7));
				map.put("status", rs.getInt(8));
				list.add(map);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			super.closeAll();
		}
		return list;
	}

	//获得老师总数
	public int getTeacherIntoCount(String commonClassName,String teaManName){
		int count = 0;
		String sql = "select count(1) from(select tmi.`teaManId`,tmi.`teaManPhone`,tmi.`password`,tmi.`teaManName`,tmi.`teaManSex`,tmi.`teaManBirthdate`,cci.`commonClassName`,tmi.`status` from teaManInfo tmi left join commonClassInfo cci on tmi.teaManId=cci.teaManId where cci.commonStatus=1 union select tmi.`teaManId`,tmi.`teaManPhone`,tmi.`password`,tmi.`teaManName`,tmi.`teaManSex`,tmi.`teaManBirthdate`,'',tmi.`status` from teaManInfo tmi where tmi.teaManId not in (select cci.teaManId from commonClassInfo cci where cci.commonStatus=1)) AS A where `commonClassName` LIKE '%"+commonClassName+"%' AND `teaManName` LIKE '%"+teaManName+"%'";
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

	//修改老师信息
	public int updateTeahcerInfo(TeaManInfo teaManInfo){
		String sql = "update teaManInfo set teaManPhone=?,password=?,teaManName=?,teaManSex=?,teaManBirthdate=?,status=? where teaManId=?";
		return super.executeUpdate(sql,teaManInfo.getTeaManPhone(),teaManInfo.getPassword(),teaManInfo.getTeaManName(),teaManInfo.getTeaManSex(),teaManInfo.getTeaManBirthdate(),teaManInfo.getStatus(),teaManInfo.getTeaManId());
	}

	//增加老师信息
	public int addTeacherInfo(TeaManInfo teaManInfo){
		String sql = "insert into teaManInfo values(null,?,?,?,?,?,?,?)";
		return  super.executeUpdate(sql,teaManInfo.getTeaManPhone(),teaManInfo.getPassword(),teaManInfo.getTeaManName(),teaManInfo.getTeaManSex(),teaManInfo.getTeaManBirthdate(),teaManInfo.getStatus(),teaManInfo.getRoleId());
	}

	//修改老师管理员密码
	public int changePwd(TeaManInfo teaManInfo,String ypwd){
		String sql = "update `teaManInfo` set `password`=? where `teaManPhone`=? and `password`=?";
		return super.executeUpdate(sql,teaManInfo.getPassword(),teaManInfo.getTeaManPhone(),ypwd);
	}

	//修改老师信息除密码状态
	public int updatePersonalInfoInfo(TeaManInfo teaManInfo){
		String sql = "update teaManInfo set teaManPhone=?,teaManName=?,teaManSex=?,teaManBirthdate=? where teaManId=?";
		return super.executeUpdate(sql,teaManInfo.getTeaManPhone(),teaManInfo.getTeaManName(),teaManInfo.getTeaManSex(),teaManInfo.getTeaManBirthdate(),teaManInfo.getTeaManId());
	}

	//查询家长手机号是否存在
	public String queryTeaManPhone(String teaManPhone){
		String sql = "select teaManPhone from teaManInfo where teaManPhone=?";
		super.rs = super.executeSql(sql,teaManPhone);
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


	//通过老师id得到手机号
	public String getTeaManPhoneById(int teaManId){
		String sql = "select teaManPhone from teaManInfo where teaManId=?";
		super.rs = super.executeSql(sql,teaManId);
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


	//获取全部老师的姓名
	public List<TeaManInfo> getTeaManName(){
		String sql ="select teaManId,teaManName from teamaninfo where status=1";
		rs = super.executeSql(sql);
		List<TeaManInfo> list = new ArrayList<TeaManInfo>();
		try {
			while(rs.next()){
				TeaManInfo tmi =new TeaManInfo();
				tmi.setTeaManId(rs.getInt("teaManId"));
				tmi.setTeaManName(rs.getString("teaManName"));
				list.add(tmi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return list;
	}


	//根据老师姓名查询老师id
	public int getTeaManId(String teaManName){
		String sql ="select teaManId from teamaninfo where teamanname=?";
		rs = super.executeSql(sql,teaManName);
		try {
			while(rs.next()){
				return rs.getInt("teaManId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return 0;

	}
}
