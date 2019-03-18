package cn.music.servlet.commonManagerServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.music.entity.TeaManInfo;
import cn.music.service.commonManagerService.RoleService;

public class TopDoServlet extends HttpServlet{
	private RoleService rs = new RoleService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("teaManInfo");
		TeaManInfo teaManInfo = null;
		if(obj!=null){
			teaManInfo = (TeaManInfo)obj;
			String roleName = rs.getRoleNameByRoleId(teaManInfo.getRoleId());
			session.setAttribute("roleName", roleName);
			resp.sendRedirect("jsp/main.jsp");
		}
	}
}
