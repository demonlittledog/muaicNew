package cn.music.service.commonManagerService;

import cn.music.dao.commonManagerDao.RoleDao;

public class RoleService {
	private RoleDao roleDao = new RoleDao();
	
	//通过id获得用户类型
	public String getRoleNameByRoleId(int roleId){
		return roleDao.getRoleNameByRoleId(roleId);
	}
}
