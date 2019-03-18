package cn.music.servlet.commonManagerServlet;

import cn.music.entity.Page;
import cn.music.service.commonManagerService.StudentInfoService;
import cn.music.service.commonManagerService.TeaManInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class QueryTeacherInfoServlet extends HttpServlet{
	private TeaManInfoService tmis = new TeaManInfoService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		//获得当前页面
		int currentPageNo = 1;
		String str = req.getParameter("currentPageNo");
		if(null!=str&&str.length()>0){
			currentPageNo = Integer.parseInt(str);
		}
		//当前显示页的最大记录数
		int pageSize=5;
		str=req.getParameter("pageSize");
		if(null!=str&&str.length()>0){
			pageSize = Integer.parseInt(str);
		}

		//接收班级名称
		String commonClassName = req.getParameter("commonClassName");
		if(null==commonClassName){
			commonClassName="";
		}else{
			commonClassName = commonClassName.trim();
		}

		//接收老师姓名
		String teaManName = req.getParameter("teaManName");
		if(null==teaManName){
			teaManName="";
		}else{
			teaManName = teaManName.trim();
		}

		List<Map<String,Object>> list = tmis.getAllTeacherInfo(currentPageNo,pageSize,commonClassName,teaManName);

		//获得总页数
		int totalCount = tmis.getTeacherIntoCount(commonClassName,teaManName);

		Page pages = new Page();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		if(totalPageCount==0){
			totalPageCount=1;
		}

		req.setAttribute("list",list);
		req.setAttribute("currentPageNo",currentPageNo);
		req.setAttribute("totalPageCount",totalPageCount);
		req.getRequestDispatcher("jsp/teacherInfo.jsp").forward(req,resp);
	}
}
