package cn.music.dao.commonManagerDao;

import cn.music.dao.baseDao.BaseDao;

import java.sql.SQLException;

public class RoleDao extends BaseDao {
	//通过id获得用户类型
	public String getRoleNameByRoleId(int roleId){
		String sql = "select roleName from role where roleId=?";
		super.rs = super.executeSql(sql,roleId);
		String roleName = null;
		try {
			if(rs.next()){
				roleName = rs.getString("roleName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return roleName;
	}
}
