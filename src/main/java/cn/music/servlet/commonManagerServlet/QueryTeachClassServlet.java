package cn.music.servlet.commonManagerServlet;

import cn.music.entity.Page;
import cn.music.entity.TeaManInfo;
import cn.music.service.commonManagerService.CommonClassInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2018/11/12.
 */
public class QueryTeachClassServlet extends HttpServlet{
    private CommonClassInfoService ccis = new CommonClassInfoService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        if(session.getAttribute("teaManInfo")==null){
            req.getRequestDispatcher("jsp/teaManAccess.jsp").forward(req,resp);
        }else {
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

            //得到老师姓名
            TeaManInfo teaManInfo = (TeaManInfo) session.getAttribute("teaManInfo");


            List<Map<String,Object>> list = ccis.getMyCommonClassInfo(teaManInfo.getTeaManName(),currentPageNo,pageSize,commonClassName);

            //获得总页数
            int totalCount = ccis.getMyCommonClassInfoCount(teaManInfo.getTeaManName(),commonClassName);

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
            req.getRequestDispatcher("jsp/queryTeachClass.jsp").forward(req,resp);
        }

    }
}
