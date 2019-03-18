package cn.music.servlet.commonManagerServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.music.entity.TeaManInfo;
import cn.music.service.commonManagerService.TeaManInfoService;

public class CheckLoginServlet extends HttpServlet{
	private TeaManInfoService ts = new TeaManInfoService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String teaManPhone = req.getParameter("teaManPhone");
		String password = req.getParameter("password");
		String teaManName = ts.getTeaManNameByTeaManPhone(teaManPhone);
		
		if(teaManName==null){
			req.setAttribute("error", "登录失败，用户名不存在！");
			req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
		}else{
			TeaManInfo teaManInfo = ts.getTeaManByPassword(teaManPhone, password);
			if(teaManInfo==null){
				req.setAttribute("error", "登录失败，用户名或密码错误！");
				req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
			}else{
				int status = teaManInfo.getStatus();
				status =1;
				if(status==0){
					req.setAttribute("error", "登录失败，该用户账号已冻结！");
					req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
				}else{
					HttpSession session = req.getSession();
					session.setAttribute("teaManInfo", teaManInfo);
					resp.sendRedirect("top.do");
				}
			}
		}
	}
}
