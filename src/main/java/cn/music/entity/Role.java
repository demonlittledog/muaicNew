package cn.music.entity;

public class Role {
	private int roleId;      //角色id自增主键
	private String roleName; //角色姓名
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
